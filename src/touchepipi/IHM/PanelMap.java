package touchepipi.IHM;

import touchepipi.metier.Joueur;

import javax.swing.*;
import java.awt.*;

public class PanelMap extends JPanel
{
    private Joueur j;

    PanelMap(Joueur j)
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
        this.paintMapAdverse(g);
    }

    private void paintMapAdverse(Graphics g)
    {
        int[][] carte = j.getMapAdverse().getCarte();
        g.setColor(Color.GREEN);
        Font f = new Font(Font.SANS_SERIF,Font.BOLD,30);
        g.setFont(f);
        g.drawString(j.getJ2(),100,30);
        for(int i = 0; i < carte.length; i++)
        {
            for(int j = 0; j < carte[i].length; j++)
            {
                if(carte[i][j] == 0)
                    g.setColor(Color.cyan);
                if(carte[i][j] == 1)
                    g.setColor(Color.gray);
                if(carte[i][j] == 2)
                    g.setColor(Color.red);
                if(carte[i][j] == 3)
                    g.setColor(Color.blue);
                g.fillRect(carte.length * 50 + 50 + j * 50,30 + i * 50, 50, 50);
            }
        }
    }

    private void paintMap(Graphics g)
    {
        int[][] carte = j.getMap().getCarte();
        g.setColor(Color.RED);
        Font f = new Font(Font.SANS_SERIF,Font.BOLD,30);
        g.setFont(f);
        g.drawString(j.getNom(),30,30);
        for(int i = 0; i < carte.length; i++)
        {
            for(int j = 0; j < carte[i].length; j++)
            {
                if(carte[i][j] == 0)
                    g.setColor(Color.cyan);
                if(carte[i][j] == 1)
                    g.setColor(Color.gray);
                if(carte[i][j] == 2)
                    g.setColor(Color.red);
                if(carte[i][j] == 3)
                    g.setColor(Color.blue);
                g.fillRect(j * 50, 30 + i * 50, 50, 50);
            }
        }
    }
}
