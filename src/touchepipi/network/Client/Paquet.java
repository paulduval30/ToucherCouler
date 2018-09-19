package touchepipi.network.Client;

import touchepipi.metier.Joueur;

/**
 * Classe d'envoie des paquets client
 */
public class Paquet
{
    public static void envoyerTir(Joueur joueur, int ligne, int colonne)
    {
        joueur.getClient().send("1 -" + ligne + "," + colonne + "," + joueur.getNom());
    }

    public static void envoyerBateau(Joueur joueur, int ligneDep, int ligneArr, int colonneDep, int colonneArr, int taille)
    {
        joueur.getClient().send("0-" + ligneDep + "," + colonneDep + "," + ligneArr + "," + colonneArr + "," + taille + "," + joueur.getNom());
    }

    public static void finDeTour(Joueur joueur)
    {
        joueur.getClient().send("3-" + joueur.getNom());
    }

    public static void connection(Joueur j)
    {
        j.getClient().send("4-" + j.getNom());
    }
}
