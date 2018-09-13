package touchepipi;

import touchepipi.IHM.IHM;
import touchepipi.metier.Joueur;
import touchepipi.metier.Partie;

public class Controlleur
{
    IHM ihm;
    Partie p;
    public Controlleur()
    {
        this.p = new Partie(new Joueur("Paul"), new Joueur("Thomas"), this);
        this.ihm = new IHM(this);
    }

    public void lancerPartie()
    {
        p.jouerTour();
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
        Controlleur c = new Controlleur();
        c.lancerPartie();
    }
}
