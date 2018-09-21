package touchepipi.IHM;

import touchepipi.Controleur;
import touchepipi.metier.Joueur;

import javax.swing.*;

public class MainFrame extends JFrame implements Runnable
{
    private PanelMap map;
    private Joueur joueur;

    public MainFrame(Joueur j)
    {
        this.joueur = j;
        this.setVisible(true);
        this.setSize(800, 800);
        //this.setTitle("Touche Pipi Game Pro Dev Incroyable Suce Moi !");
        this.map = new PanelMap(joueur);
        this.getContentPane().add(map);
    }

    public void repaintMap()
    {
        this.map.setJoueur(this.joueur);
        this.map.repaint();
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
}
