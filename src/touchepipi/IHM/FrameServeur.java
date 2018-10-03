package touchepipi.IHM;

import touchepipi.metier.PartieServeur;

import javax.swing.*;

public class FrameServeur extends JFrame implements Runnable
{
    private PartieServeur partie;
    public FrameServeur(PartieServeur partie)
    {
        this.partie = partie;
        this.setVisible(true);
        this.add(new PanelServeur(this.partie));
        new Thread(this).start();

    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(1000 / 60);
                this.repaint();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
