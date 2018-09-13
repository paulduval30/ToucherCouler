package touchepipi.IHM;

import touchepipi.Controleur;
import touchepipi.metier.Joueur;

import javax.swing.*;

public class MainFrame extends JFrame
{
    private Controleur ctrl;
    private PanelMap map;

    public MainFrame(Controleur c)
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
