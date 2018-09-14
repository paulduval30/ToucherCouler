package touchepipi.IHM;

import touchepipi.Controleur;
import touchepipi.metier.Joueur;
import touchepipi.metier.Partie;
import touchepipi.util.Utilitaire;

import java.util.Scanner;

public class IHM
{
    private Controleur ctrl;
    private MainFrame main;

    public IHM(Controleur controlleur)
    {
        this.ctrl = controlleur;
    }

    public void lirePositionBateau()
    {
        boolean diagonale = true;
        this.afficherMap(ctrl.getPartie().getCurrent());
        Scanner sc = new Scanner(System.in);
        int ligneDep;
        int ligneArr;
        int colonneDep;
        int colonneArr;
        Partie partie = ctrl.getPartie();
        while(diagonale)
        {
            try
            {
                System.out.print("Saisir ligne Départ : ");
                ligneDep = sc.nextInt();
                System.out.println();
                System.out.print("Saisir colonne Départ : ");
                colonneDep = sc.nextInt();
                System.out.println();
                System.out.print("Saisir ligne Arrivée : ");
                ligneArr = sc.nextInt();
                System.out.println();
                System.out.print("Saisir colonne Arrivée : ");
                colonneArr = sc.nextInt();
                System.out.println();
                diagonale = Utilitaire.estDiagonale(ligneDep, ligneArr, colonneDep, colonneArr);
                if(diagonale)
                    continue;

                ctrl.poserBateau(partie.getCurrent(), ligneDep, ligneArr, colonneDep, colonneArr);
                this.afficherMap(this.ctrl.getPartie().getCurrent());
                sc.nextLine();
                sc.nextLine();

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void afficherMap(Joueur j)
    {
        //main.repaintMap(j);
    }
}
