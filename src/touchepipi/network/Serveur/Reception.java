package touchepipi.network.Serveur;

/**
 * Classe servant a traiter les données reçus par le serveur.
 * Les méthodes sont appellées dans serveur.run()
 */
public class Reception
{
    public static void recevoirTir(String data)
    {
        System.out.println("Recevoir Tir : " + data);
    }

    public static void recevoirBateau(String data)
    {
        System.out.println("Recevoir Bateau : " + data);
    }

    public static void recevoirConnection(String data)
    {

    }

    public static void recevoirFinTour(String data)
    {

    }


}
