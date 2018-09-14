package touchepipi.network.Serveur;

import touchepipi.metier.Joueur;
import touchepipi.metier.JoueurServeur;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocket implements Runnable
{
    private Socket socket;

    private DataOutputStream out;
    private DataInputStream in;

    private boolean running;
    private JoueurServeur joueur;

    public ClientSocket(Socket socket, JoueurServeur joueur)
    {
        this.joueur = joueur;
        this.socket = socket;
        try
        {
            this.out = new DataOutputStream(socket.getOutputStream());
            this.in = new DataInputStream(socket.getInputStream());

            this.running = true;

            new Thread(this).start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean isRunning()
    {
        return this.running;
    }

    public void send(String message)
    {
        try
        {
            this.out.writeUTF(message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
    0 - Poser bateau
    1 - Tirer
     */
    @Override
    public void run()
    {
        while(this.isRunning())
        {
            try
            {
                String message = this.in.readUTF();
                String[] paquet = message.split("-");
                switch (paquet[0])
                {
                    case "0" : Reception.recevoirBateau(paquet[1]);
                    case "1" : Reception.recevoirTir(paquet[1]);
                    case "3" : Reception.recevoirFinTour(paquet[1]);
                    case "4" : Reception.recevoirConnection(paquet[1]);
                }
            }
            catch (Exception e)
            {
                this.close();
            }
        }
    }

    void close()
    {
        try
        {
            this.socket.close();
            this.running = false;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public JoueurServeur getJoueur()
    {
        return joueur;
    }
}
