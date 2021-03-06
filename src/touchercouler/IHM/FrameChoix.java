package touchercouler.IHM;

import touchercouler.Controleur;
import touchercouler.metier.Joueur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class FrameChoix extends JFrame implements ActionListener
{
    JButton startServer;
    JButton startUser;

    Controleur ctrl;

    public FrameChoix(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setTitle("Bataille Nasale : Main Menu");
        this.setLayout(new FlowLayout());

        this.setSize(800,800);
        this.startServer = new JButton("Serveur");
        this.startServer.addActionListener(this);

        this.startUser = new JButton("Joueur");
        this.startUser.addActionListener(this);

        this.add(startServer);
        this.add(startUser);


        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == startUser)
        {
            String nom = JOptionPane.showInputDialog("Entrer le nom joueur");
            MainFrame m1 = new MainFrame(ctrl, new Joueur(nom));
            ctrl.setM1(m1);
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        }
        else
        {
            ctrl.demarerServeur();
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        }
    }
}
