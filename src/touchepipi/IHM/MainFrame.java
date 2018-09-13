package touchepipi.IHM;

import touchepipi.Controlleur;
import touchepipi.metier.Joueur;

import javax.swing.*;

public class MainFrame extends JFrame
{
    private  Controlleur ctrl;
    private PanelMap map;

    public MainFrame(Controlleur c)
    {
        this.ctrl = c;
        this.setVisible(true);
        this.setSize(800, 800);
        this.setTitle("Touche Pipi Game Pro Dev Incroyable Suce Moi");
        this.map = new PanelMap(c.getPartie().getCurrent());
        this.getContentPane().add(map);
    }

    public void repaintMap(Joueur j)
    {
        this.map.setJoueur(j);
        this.map.repaint();
    }
}
