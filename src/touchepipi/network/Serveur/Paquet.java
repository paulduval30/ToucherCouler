package touchepipi.network.Serveur;

import touchepipi.metier.Joueur;
import touchepipi.metier.JoueurServeur;
import touchepipi.metier.Partie;

/**
 * Classe d'envoie des paquets Serveur
 */
public class Paquet
{
    public static void tir(Serveur s, int celulle, JoueurServeur j)
    {
        s.sendAll("4-" + celulle + "," + j.getNom());
    }


    public static void envoyerTour(Serveur s, Partie partie)
    {
        s.sendAll("3-" + partie.getCurrent().getNom());
    }

    public static void demarerPartie(Serveur s)
    {
        s.sendAll("0-Debut");
    }

    public static void envoyerNom(Serveur s)
    {
        s.sendAll("1-1:" + s.getPartie().getJ1().getNom()+",2:"+s.getPartie().getJ2().getNom());
    }
}
