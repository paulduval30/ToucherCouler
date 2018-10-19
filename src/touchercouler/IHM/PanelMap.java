package touchercouler.IHM;

import touchercouler.Controleur;
import touchercouler.metier.Joueur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        try
        {
            this.paintMap(g);
            this.paintMapAdverse(g);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void paintMapAdverse(Graphics g) throws IOException
    {
        BufferedImage img = ImageIO.read(new File("res/mer.png"));

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
                    img = ImageIO.read(new File("res/mer.png"));
                if(carte[i][j] == -1)
                    img = ImageIO.read(new File("res/toucher.png"));;
                if(carte[i][j] == -2)
                    img = ImageIO.read(new File("res/plouf.png"));
                g.drawImage(img, carte.length * 50 + 50 + j * 50 + 1,30 + i * 50 + 1, 49, 49, this);
                img = null;
            }
        }
    }

    private void paintMap(Graphics g) throws IOException
    {
        BufferedImage img = ImageIO.read(new File("res/mer.png"));
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
                    img = ImageIO.read(new File("res/mer.png"));
                if(carte[i][j] / 10 >= 1)
                    img = ImageIO.read(new File("res/bateau.png"));
                if(carte[i][j] == -1)
                    img = ImageIO.read(new File("res/toucher.png"));
                if(carte[i][j] == -2)
                    img = ImageIO.read(new File("res/plouf.png"));
                g.drawImage(img, j * RECT_SIZE + 1 , 30 + i * RECT_SIZE + 1, RECT_SIZE-1, RECT_SIZE-1, this);
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
            if(j.getBateaux() == null)
                e.consume();
            Integer taille = j.getBateaux().get(0) / 10;

            String[] options = {"Horizontale" , "Verticale"};
            int retour = JOptionPane.showOptionDialog(this, "Choisir l'orientation \n taille : "
                    + taille, "Orientationn ? ", JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,null, options, options[0]);

            if(retour == 1)
               ctrl.placerBateau(this.j, rectLig, rectCol, 'V');
            else
                ctrl.placerBateau(this.j, rectLig, rectCol, 'H');
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
