package touchepipi.IHM;

import touchepipi.metier.Joueur;
import touchepipi.metier.Map;

import javax.swing.*;
import java.awt.*;

public class PanelMap extends JPanel
{
    private Joueur j;

    public PanelMap(Joueur j)
    {
        this.j = j;
    }

    public void setJoueur(Joueur j)
    {
        this.j = j;
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.paintMap(g);
    }

    private void paintMap(Graphics g)
    {
        int[][] carte = j.getMap().getCarte();
        g.setColor(Color.GREEN);
        Font f = new Font(Font.SANS_SERIF,Font.BOLD,30);
        g.setFont(f);
        g.drawString(j.getNom(),30,30);
        for(int i = 0; i < carte.length; i++)
        {
            for(int j = 0; j < carte[i].length; j++)
            {
                if(carte[i][j] == 0)
                    g.setColor(Color.cyan);
                else
                    g.setColor(Color.gray);
                g.fillRect(j * 50, 30 + i * 50, 50, 50);
            }
        }
    }
}
