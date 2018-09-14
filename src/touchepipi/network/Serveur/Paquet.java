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

    public static void envoyerNom(ClientSocket c,JoueurServeur joueur)
    {
        c.send("1-" + joueur.getNom());
    }
}
