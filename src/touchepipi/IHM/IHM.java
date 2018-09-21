package touchepipi.IHM;

import touchepipi.Controleur;
import touchepipi.util.Utilitaire;

import java.util.Scanner;

public class IHM
{
    private Controleur ctrl;

    public IHM(Controleur controlleur)
    {
        this.ctrl = controlleur;
    }

    public void lirePositionBateau()
    {
        boolean diagonale = true;
        Scanner sc = new Scanner(System.in);
        int ligneDep;
        int ligneArr;
        int colonneDep;
        int colonneArr;
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

              //  ctrl.poserBateau(partie.getCurrent(), ligneDep, ligneArr, colonneDep, colonneArr);
              //  this.afficherMap(this.ctrl.getPartie().getCurrent());
                sc.nextLine();
                sc.nextLine();

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static int lireInt()
    {
        Scanner sc = new Scanner(System.in);
        int ret = sc.nextInt();
        return ret;
    }

    public static char lireDir()
    {
        Scanner sc = new Scanner(System.in);
        char ret = sc.nextLine().charAt(0);
        return ret;
    }
}
