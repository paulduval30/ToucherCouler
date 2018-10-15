package touchercouler.IHM;

import touchercouler.Controleur;
import touchercouler.metier.Joueur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelMap extends JPanel implements MouseListener
{
    private final int RECT_SIZE = 50;

    private Joueur j;
    private Controleur ctrl;

    PanelMap(Joueur j, Controleur ctrl)
    {
        this.ctrl = ctrl;
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
                g.drawRect(carte.length * 50 + 50 + 5 * 50, 30 + i * 50, 50, 50);
                if(carte[i][j] == 0)
                    g.setColor(Color.cyan);
                if(carte[i][j] == -1)
                    g.setColor(Color.red);
                if(carte[i][j] == -2)
                    g.setColor(Color.blue);
                g.fillRect(carte.length * 50 + 50 + j * 50 + 1,30 + i * 50 + 1, 49, 49);
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
                if(carte[i][j] / 10 >= 1)
                    g.setColor(Color.gray);
                if(carte[i][j] == -1)
                    g.setColor(Color.red);
                if(carte[i][j] == -2)
                    g.setColor(Color.pink);
                g.fillRect(j * RECT_SIZE + 1 , 30 + i * RECT_SIZE + 1, RECT_SIZE-1, RECT_SIZE-1);
            }
        }
    }

    private int getRectCol(int mousePosX)
    {
        return mousePosX / RECT_SIZE;
    }

    private int getRectLig(int mousePosY)
    {
        return (mousePosY - 30) / RECT_SIZE;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e)
    {
        int rectCol = getRectCol(e.getX());
        int rectLig = getRectLig(e.getY());
        if(j.getCurrent() && rectCol >= 11)
        {
            if(j.getBateaux().size() != 0)
            {
                JOptionPane.showMessageDialog(this, "Il vous reste " + j.getBateaux().size() +
                        " à placer", "ATTENTION", JOptionPane.PLAIN_MESSAGE);
            }
            else
            {
                ctrl.envoyerTir(j, rectLig, rectCol - 11);
            }
        }
        if(rectCol < 10)
        {
            if(j.getBateaux().size() == 0)
                e.consume();
            Integer taille = j.getBateaux().get(0) / 10;

            String[] options = {"Horizontale" , "Verticale"};
            JOptionPane jOptionPane = new JOptionPane();
            int retour = jOptionPane.showOptionDialog(this, "Choisir l'orientation \n taille : "
                    + taille, "Orientationn ? ", JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,null, options, options[0]);

            if(retour == 1)
               if(!ctrl.placerBateau(this.j, rectLig, rectCol, 'V'))
                   JOptionPane.showMessageDialog(this,"Vous ne pouvez pas mettre votre bateau ici",
                           "ATTENTION", JOptionPane.PLAIN_MESSAGE);
            else
                if(!ctrl.placerBateau(this.j, rectLig, rectCol, 'H'))
                    JOptionPane.showMessageDialog(this,"Vous ne pouvez pas mettre votre bateau ici",
                            "ATTENTION", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
