package touchepipi;

import touchepipi.IHM.IHM;
import touchepipi.IHM.MainFrame;
import touchepipi.metier.Joueur;
import touchepipi.metier.Partie;
import touchepipi.metier.PartieServeur;
import touchepipi.network.Serveur.Serveur;

import java.util.Scanner;

public class Controleur
{
    IHM ihm;
    Partie p;
    public Controleur()
    {
        this.ihm = new IHM(this);
        Scanner sc = new Scanner(System.in);
        System.out.println("J/S");
        String rep = sc.nextLine();
        if(rep.equals("S"))
            new PartieServeur(this);
        else if(rep.equals("J1"))
        {
            MainFrame m1 = new MainFrame(new Joueur("Ah"));
            new Thread(m1).run();
        }
        else
        {
            MainFrame m2 = new MainFrame(new Joueur("Bon"));
            new Thread(m2).run();

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
       // ihm.afficherMap(p.getCurrent());
    }
    public Partie getPartie()
    {
        return p;
    }

    public void poserBateau(Joueur current, int ligneDep, int ligneArr, int colonneDep, int colonneArr)
    {
        //current.placerBateau(ligneDep, ligneArr, colonneDep, colonneArr);
    }

    public static void main(String[] argv)
    {
        Controleur c = new Controleur();
    }
}
