package touchepipi;

import touchepipi.IHM.IHM;
import touchepipi.metier.Joueur;
import touchepipi.metier.Partie;
import touchepipi.network.Serveur.Serveur;

public class Controleur
{
    IHM ihm;
    Partie p;
    public Controleur()
    {
        this.p = new Partie(new Joueur("Paul"), new Joueur("Thomas"), this);
        this.ihm = new IHM(this);
    }

    public void lancerPartie()
    {
        this.p.getJ1().setPartie(this.p);
        this.p.getJ2().setPartie(this.p);
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
        c.lancerPartie();
    }
}
