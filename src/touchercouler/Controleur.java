package touchercouler;

import touchercouler.IHM.MainFrame;
import touchercouler.IHM.PanelChoix;
import touchercouler.metier.Joueur;
import touchercouler.metier.PartieServeur;

public class Controleur
{
    private MainFrame m1;
    public Controleur()
    {
        new PanelChoix(this);
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

    public static void main(String[] argv)
    {
        new Controleur();
    }

    public boolean placerBateau(Joueur j, int rectLig, int rectCol, char dir)
    {
        return j.placerBateau(rectLig, rectCol, dir);
    }

    public void envoyerTir(Joueur j, int rectLig, int i)
    {
        j.envoyerTir(rectLig, i);
    }
}
