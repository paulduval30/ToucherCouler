package touchepipi.network.Client;

import touchepipi.metier.Joueur;

/**
 * Classe d'envoie des paquets client
 */
public class Paquet
{
    public static void envoyerTir(Joueur joueur, int ligne, int colonne)
    {
        joueur.getClient().send("1 -" + ligne + "," + colonne);
    }

    public static void envoyerBateau(Joueur joueur, int ligneDep, int ligneArr, int colonneDep, int colonneArr)
    {
        joueur.getClient().send("0-" + ligneDep + "," + colonneDep + "," + ligneArr + "," + colonneArr);
    }

    public static void finDetour(Joueur joueur)
    {
        joueur.getClient().send("3-" + joueur.getNom());
    }
}
