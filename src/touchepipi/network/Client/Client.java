package touchepipi.network.Client;

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
    public Client()
    {
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
