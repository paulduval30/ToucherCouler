package touchepipi;

import touchepipi.IHM.IHM;
import touchepipi.IHM.MainFrame;
import touchepipi.IHM.PanelChoix;
import touchepipi.metier.Joueur;
import touchepipi.metier.PartieServeur;

import java.util.Scanner;

public class Controleur
{
    private MainFrame m1;
    IHM ihm;
    public Controleur()
    {
        new PanelChoix(this);
    }


    public void lirePosistionBateau()
    {
        ihm.lirePositionBateau();
    }

    public void afficherCarte()
    {
       // ihm.afficherMap(p.getCurrent());
    }

    public void poserBateau(Joueur current, int ligneDep, int ligneArr, int colonneDep, int colonneArr)
    {
        //current.placerBateau(ligneDep, ligneArr, colonneDep, colonneArr);
    }

    public static void main(String[] argv)
    {
        Controleur c = new Controleur();
    }

    public void ajouterJoueur(String nom)
    {
        this.m1 = new MainFrame(this, new Joueur(nom));
        new Thread(this.m1).run();
    }

    public void demarerServeur()
    {
        new PartieServeur(this);
    }
}
