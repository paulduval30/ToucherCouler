package touchepipi.network.Serveur;

import touchepipi.metier.Joueur;
import touchepipi.metier.Partie;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Serveur implements Runnable
{
    private ServerSocket socket;

    private Partie partie;
    private boolean running;

    private ArrayList<ClientSocket> clients;

    public Serveur(Partie partie)
    {
        this.partie = partie;
        try
        {
            this.running = false;
            this.clients = new ArrayList<>();
            this.socket = new ServerSocket();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void listen()
    {
        try
        {
            this.socket.bind(new InetSocketAddress(25565), 1);
            System.out.println("En écoute sur le port 25565");
            this.running = true;
            new Thread(this).start();
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
                Socket clientSocket = this.socket.accept();
                this.clients.add(new ClientSocket(clientSocket));
                if(partie.getJ1() == null)
                    partie.setJ1(new Joueur("1"));
                else
                    partie.setJ2(new Joueur("2"));
                if(clients.size() == 2)
                {
                    Paquet.envoyerNom(clients.get(0), partie.getJ1());
                    Paquet.envoyerNom(clients.get(1), partie.getJ2());
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                break;
            }
        }

        this.close();
    }

    public void sendAll(String message)
    {
        for(ClientSocket c : clients)
        {
            c.send(message);
        }
    }
    public void close()
    {
        try
        {
            // On ferme toutes les connexions courantes
            for (ClientSocket client : this.clients)
                if (client.isRunning())
                    client.close();

            this.socket.close();
            this.running = false;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
