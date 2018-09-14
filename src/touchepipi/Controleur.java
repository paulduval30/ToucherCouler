package touchepipi;

import touchepipi.IHM.IHM;
import touchepipi.metier.Joueur;
import touchepipi.metier.Partie;
import touchepipi.network.Serveur.Serveur;

import java.util.Scanner;

public class Controleur
{
    IHM ihm;
    Partie p;
    public Controleur()
    {
        this.p = new Partie(this);
        this.ihm = new IHM(this);
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            sc.nextLine();
            new Joueur();
        }

    }

    public void lancerPartie()
    {
        this.p.jouerTour();
    }

    public void lirePosistionBateau()
    {
        ihm.lirePositionBateau();
    }

    public void afficherCarte()
    {
        ihm.afficherMap(p.getCurrent());
    }
    public Partie getPartie()
    {
        return p;
    }

    public void poserBateau(Joueur current, int ligneDep, int ligneArr, int colonneDep, int colonneArr)
    {
        current.placerBateau(ligneDep, ligneArr, colonneDep, colonneArr);
    }

    public static void main(String[] argv)
    {
        Controleur c = new Controleur();
    }
}
