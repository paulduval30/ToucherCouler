package touchepipi.network.Serveur;

import touchepipi.metier.Joueur;
import touchepipi.metier.JoueurServeur;
import touchepipi.metier.Partie;

/**
 * Classe d'envoie des paquets Serveur
 */
public class Paquet
{
    public static Serveur instance;

    public static void tir(int ligne, int colonne, int celulle, JoueurServeur j)
    {
        instance.sendAll("3-" + ligne + "," + colonne + "," + celulle + "," + j.getNom());
    }


    public static void envoyerTour(Partie partie)
    {
        instance.sendAll("2-" + partie.getCurrent().getNom());
    }

    public static void demarerPartie()
    {
        instance.sendAll("0-Debut");
    }

    public static void envoyerNom()
    {
        instance.sendAll("1-" + instance.getPartie().getJ1().getNom()+","+instance.getPartie().getJ2().getNom());
    }
}
