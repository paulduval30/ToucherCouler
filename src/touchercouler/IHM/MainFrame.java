package touchercouler.IHM;

import touchercouler.Controleur;
import touchercouler.metier.Joueur;

import javax.swing.*;

public class MainFrame extends JFrame implements Runnable
{
    private PanelMap map;
    private Joueur joueur;
    Controleur ctrl;

    private JPanel principale;

    public MainFrame(Controleur ctrl, Joueur j)
    {
        this.joueur = j;
        this.ctrl = ctrl;
        this.setVisible(true);
        this.setSize(800, 800);
        this.setTitle("La bataille nasale");
        this.map = new PanelMap(this.joueur);;
        this.getContentPane().add(this.map);
        new Thread(this).start();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void setJoueur(Joueur joueur)
    {
        this.joueur = joueur;
    }

    public void showMapPane()
    {
        System.out.println("Je change de Panel");
        if(this.joueur == null)
        {
            System.out.println("Error : Player unset");
            return;
        }
        this.map = new PanelMap(this.joueur);;
        this.principale.add(this.map);
        this.principale.repaint();
    }


    public Controleur getCtrl()
    {
        return this.ctrl;
    }


    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep(1000/60);
                this.repaintMap();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void repaintMap()
    {
        this.map.setJoueur(joueur);
        this.map.repaint();
    }
}
