package touchepipi.network.Client;

import touchepipi.metier.Joueur;

/**
 * Classe contenant toute les méthodes de traitement des données, elles sont appellée dans le client.run()
 */
public class Reception
{
    public static void recevoirTir(String data, Joueur joueur)
    {
        String[] sData = data.split(",");

        int ligne = Integer.parseInt(sData[0]);
        int colonne = Integer.parseInt(sData[1]);
        int celulle = Integer.parseInt(sData[2]);
        String nom = sData[3];

        if(nom.equals(joueur.getNom()))
            joueur.placerJalon(joueur.getMap(), ligne, colonne, celulle);
        else
            joueur.placerJalon(joueur.getMapAdverse(), ligne, colonne, celulle);

    }

    public static void recevoirGagnant(String data)
    {

    }

    public static void recevoirTour(String data, Joueur joueur)
    {
        if(joueur.getNom().equals(data))
        {
            joueur.setCurrent(true);
            joueur.jouerTour();
        }
        else
            joueur.setCurrent(false);
    }

    public static void recevoirNom(String data, Joueur joueur)
    {
        String[] sData = data.split(",");
        String j1 = sData[0];
        String j2 = sData[1];

        if(j1.equals(joueur.getNom()))
            joueur.setJ2(j2);
        else
            joueur.setJ2(j1);
    }

    public static void recevoirDebut(String data, Joueur joueur)
    {
        for(int i = 0; i < 5; i++)
        {

        }
    }
}
