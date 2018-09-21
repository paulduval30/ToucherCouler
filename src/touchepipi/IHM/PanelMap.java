package touchepipi.IHM;

import touchepipi.metier.Joueur;
import touchepipi.network.Client.Paquet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelMap extends JPanel implements MouseListener
{
    private final int RECT_SIZE = 50;
    private final int DISTANCE_BETWEEN_MAPS = 50;
    private final int VERTICAL_PADDING = 30;
    private final int HORIZONTAL_PADDING = 0;

    private Joueur j;

    PanelMap(Joueur j)
    {
        this.j = j;
        this.addMouseListener(this);
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
        g.drawString(j.getJ2(),carte.length * 50 + 50,30);
        for(int i = 0; i < carte.length; i++)
        {
            for(int j = 0; j < carte[i].length; j++)
            {
                g.setColor(Color.white);
                g.drawRect(carte.length * RECT_SIZE + DISTANCE_BETWEEN_MAPS + 5 * 50, VERTICAL_PADDING + i * 50, 50, 50);
                if(carte[i][j] == 0)
                    g.setColor(Color.cyan);
                if(carte[i][j] == 1)
                    g.setColor(Color.gray);
                if(carte[i][j] == 2)
                    g.setColor(Color.red);
                if(carte[i][j] == 3)
                    g.setColor(Color.blue);
                g.fillRect(carte.length * RECT_SIZE + DISTANCE_BETWEEN_MAPS + j * RECT_SIZE + 1,
                        VERTICAL_PADDING + i * RECT_SIZE + 1, RECT_SIZE - 1, RECT_SIZE - 1);
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
                g.fillRect(j * RECT_SIZE + 1 , VERTICAL_PADDING + i * RECT_SIZE + 1,
                        RECT_SIZE-1, RECT_SIZE-1);
            }
        }
    }

    private int getRectCol(int mousePosX)
    {
        System.out.println("X : " + mousePosX);
        if (mousePosX > HORIZONTAL_PADDING + RECT_SIZE*10 + DISTANCE_BETWEEN_MAPS)
            return (mousePosX - HORIZONTAL_PADDING + RECT_SIZE*10 + DISTANCE_BETWEEN_MAPS) / RECT_SIZE;
        else
            return mousePosX / RECT_SIZE;
    }

    private int getRectLig(int mousePosY)
    {
        System.out.println( "Y : " + mousePosY);
        return (mousePosY - VERTICAL_PADDING) / RECT_SIZE;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        int rectCol = getRectCol(e.getX());
        int rectLig = getRectLig(e.getY());
        System.out.println("lig : " + rectLig + " col : " + rectCol);
        if(j.getCurrent())
            j.envoyerTir(rectLig, rectCol);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}
