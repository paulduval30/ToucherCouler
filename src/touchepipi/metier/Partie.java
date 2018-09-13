package touchepipi.metier;

import touchepipi.Controlleur;

import java.util.Scanner;

public class Partie
{
    private Joueur j1;
    private Joueur j2;

    private Joueur current;
    private Controlleur ctrl;

    public Partie(Joueur j1, Joueur j2, Controlleur controlleur)
    {
        this.j1 = j1;
        this.j2 = j2;
        this.current = j1;
        this.ctrl = controlleur;
    }

    public void jouerTour()
    {
        ctrl.afficherCarte();
        while(true)
        {
            ctrl.lirePosistionBateau();

            if(this.current == j1)
                current = j2;
            else
                current = j1;
            ctrl.afficherCarte();
        }
    }

    public Joueur getJ1()
    {
        return j1;
    }

    public void setJ1(Joueur j1)
    {
        this.j1 = j1;
    }

    public Joueur getJ2()
    {
        return j2;
    }

    public void setJ2(Joueur j2)
    {
        this.j2 = j2;
    }

    public Joueur getCurrent()
    {
        return current;
    }

    public void setCurrent(Joueur current)
    {
        this.current = current;
    }


}
