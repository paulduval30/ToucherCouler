package touchercouler.IHM;

import touchercouler.metier.PartieServeur;

import javax.swing.*;
import java.awt.*;

public class PanelServeur extends JPanel
{
    private final int RECT_SIZE = 50;
    private final PartieServeur partieServeur;

    public PanelServeur(PartieServeur partieServeur)
    {
        this.partieServeur = partieServeur;
        this.setVisible(true);

    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponents(g);
        this.paintMap(g);
        this.paintMapAdverse(g);
    }

    private void paintMapAdverse(Graphics g)
    {
        int[][] carte = partieServeur.getJ2().getMap().getCarte();
        g.setColor(Color.GREEN);
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        g.setFont(f);
        g.drawString(partieServeur.getJ2().getNom(), carte.length * 50 + 50, 30);
        for (int i = 0; i < carte.length; i++)
        {
            for (int j = 0; j < carte[i].length; j++)
            {
                g.setColor(Color.white);
                g.drawRect(carte.length * 50 + 50 + 5 * 50, 30 + i * 50, 50, 50);
                if (carte[i][j] == 0)
                    g.setColor(Color.cyan);
                if (carte[i][j] == -1)
                    g.setColor(Color.red);
                if (carte[i][j] == -2)
                    g.setColor(Color.blue);
                g.fillRect(carte.length * 50 + 50 + j * 50 + 1, 30 + i * 50 + 1, 49, 49);
                g.setColor(Color.GREEN);
                g.setFont(null);
                g.drawString(carte[i][j] + "", j * RECT_SIZE, i * RECT_SIZE);
            }
        }
    }

    private void paintMap(Graphics g)
    {
        int[][] carte = partieServeur.getJ1().getMap().getCarte();
        g.setColor(Color.RED);
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        g.setFont(f);
        g.drawString(partieServeur.getJ1().getNom(), 30, 30);
        for (int i = 0; i < carte.length; i++)
        {
            for (int j = 0; j < carte[i].length; j++)
            {
                if (carte[i][j] == 0)
                    g.setColor(Color.cyan);
                if (carte[i][j] / 10 >= 1)
                    g.setColor(Color.gray);
                if (carte[i][j] == -1)
                    g.setColor(Color.red);
                if (carte[i][j] == -2)
                    g.setColor(Color.pink);
                g.fillRect(j * RECT_SIZE + 1, 30 + i * RECT_SIZE + 1, RECT_SIZE - 1, RECT_SIZE - 1);
                g.setColor(Color.GREEN);
                g.setFont(null);
                g.drawString(carte[i][j] + "", j * RECT_SIZE, i * RECT_SIZE);
            }
        }
    }


}
