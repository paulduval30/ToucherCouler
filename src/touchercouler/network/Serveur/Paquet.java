package touchercouler.network.Serveur;

import touchercouler.metier.JoueurServeur;
import touchercouler.metier.PartieServeur;

/**
 * Classe d'envoie des paquets Serveur
 */
//TODO Gérer les erreurs d'envoie
public class Paquet
{
    public static Serveur instance;

    public static void tir(int ligne, int colonne, int celulle, JoueurServeur j)
    {
        instance.sendAll("3->" + ligne + "," + colonne + "," + celulle + "," + j.getJoueur().getNom());
    }


    public static void envoyerTour(PartieServeur partie)
    {
        instance.sendAll("2->" + partie.getCurrent().getNom());
    }

    public static void demarerPartie()
    {
        instance.sendAll("0->Debut");
    }

    public static void envoyerNom()
    {
        instance.sendAll("1->" + instance.getPartie().getJ1().getNom()+","+instance.getPartie().getJ2().getNom());
    }
}
