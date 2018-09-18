package touchepipi.network.Serveur;

import touchepipi.metier.JoueurServeur;
import touchepipi.metier.Partie;
import touchepipi.metier.PartieServeur;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Serveur implements Runnable
{
    private ServerSocket socket;

    private PartieServeur partie;
    private boolean running;

    private ArrayList<ClientSocket> clients;
    private int nbJoueur;

    public Serveur(PartieServeur partie)
    {
        nbJoueur = 0;
        Paquet.instance = this;
        Reception.instance = this;
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
                {
                    System.out.println("Hello J1");
                }
                else
                {
                    System.out.println("Hello J2");
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

    public PartieServeur getPartie()
    {
        return partie;
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

    public int getNbJoueur()
    {
        return nbJoueur;
    }

    public void ajouterJoueur()
    {
        this.nbJoueur ++;
    }

    public ArrayList<ClientSocket> getClients()
    {
        return clients;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }
}
