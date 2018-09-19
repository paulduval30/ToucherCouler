package touchepipi.network.Client;

import touchepipi.metier.Joueur;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client implements Runnable
{
    private Socket socket;

    private boolean running;

    private DataOutputStream output;
    private DataInputStream input;
    private Joueur joueur;


    public Client(Joueur joueur)
    {
        this.joueur = joueur;
        try
        {
            this.socket = new Socket();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void connect(String host, int port)
    {
        try
        {
            this.socket.connect(new InetSocketAddress(host, port));
            this.running = true;
            this.output = new DataOutputStream(this.socket.getOutputStream());
            this.input = new DataInputStream(this.socket.getInputStream());

            System.out.println("Connecté au serveur " + host + ":" + port);
            new Thread(this).start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void send(String message)
    {
        try
        {
            this.output.writeUTF(message);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        while (this.running)
        {
            try
            {
                String message = input.readUTF();
                System.out.println(message);
                String[] sData = message.split("-");
                String paquet = sData[0];
                String data = sData[1];
                switch (paquet)
                {
                    case "0" : Reception.recevoirDebut(data, this.joueur);
                        break;
                    case "1" : Reception.recevoirNom(data, this.joueur);
                        break;
                    case "2" : Reception.recevoirTour(data, this.joueur);
                        break;
                    case "3" : Reception.recevoirTir(data, this.joueur);
                    default:
                        break;
                }
            }
            catch(Exception e )
            {
                e.printStackTrace();
                break;
            }
        }
        this.close();
    }


    public void close()
    {
        try
        {
            this.socket.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        this.running = false;
    }
}
