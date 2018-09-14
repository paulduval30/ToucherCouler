package touchepipi.metier;

import touchepipi.Controleur;
import touchepipi.network.Serveur.Serveur;

public class Partie
{
    private JoueurServeur j1;
    private JoueurServeur j2;

    private JoueurServeur current;
    private Controleur ctrl;
    private Serveur serveur;

    public Partie(Controleur controleur)
    {
        this.j1 = j1;
        this.j2 = j2;
        this.current = j1;
        this.ctrl = controleur;
        this.serveur = new Serveur(this);
        serveur.listen();


    }

    public void jouerTour()
    {
        ctrl.afficherCarte();
        while(true)
        {
            ctrl.lirePosistionBateau();
            ctrl.afficherCarte();
        }
    }

    public JoueurServeur getJ1()
    {
        return j1;
    }

    public void setJ1(JoueurServeur j1)
    {
        this.j1 = j1;
    }

    public JoueurServeur getJ2()
    {
        return j2;
    }

    public void setJ2(JoueurServeur j2)
    {
        this.j2 = j2;
    }

    public JoueurServeur getCurrent()
    {
        return current;
    }

    public void changerTour()
    {
        if(j1 == current)
            this.current = j2;
        else
            this.current = j1;
    }

    public void setCurrent(JoueurServeur current)
    {
        this.current = current;
    }


}
