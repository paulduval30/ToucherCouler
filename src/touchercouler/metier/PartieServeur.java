package touchercouler.metier;

import touchercouler.Controleur;
import touchercouler.IHM.FrameServeur;
import touchercouler.network.Serveur.Serveur;

public class PartieServeur
{
    //TODO Gérer les plateau de tir
    private JoueurServeur j1;
    private JoueurServeur j2;

    private JoueurServeur current;
    private Controleur ctrl;
    private Serveur serveur;

    public PartieServeur(Controleur controleur)
    {
        this.j1 = j1;
        this.j2 = j2;
        this.current = j1;
        this.ctrl = controleur;
        this.serveur = new Serveur(this);
        serveur.listen();
        new FrameServeur(this);
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

    public void setCurrent(JoueurServeur current)
    {
        this.current = current;
    }


}
