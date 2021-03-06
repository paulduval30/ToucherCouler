package touchercouler.network.Serveur;

import touchercouler.metier.JoueurServeur;

/**
 * Classe servant a traiter les données reçus par le serveur.
 * Les méthodes sont appellées dans serveur.run()
 */
public class Reception
{
    public static void recevoirTir(String data, ClientSocket clientSocket)
    {
        System.out.println("TIR");
        String[] sData = data.split(",");
        int ligne = Integer.parseInt(sData[0]);
        int colonne = Integer.parseInt(sData[1]);
        String nom = sData[2];
        JoueurServeur j;
        int value;

        for(ClientSocket c : Serveur.instance.getClients())
        {
            String pseudo = c.getJoueur().getNom();
            if(!nom.equals(pseudo))
            {
                System.out.println(pseudo);
                int[][] carte = c.getJoueur().getMap().getCarte();
                if(carte[ligne][colonne] / 10 >= 1)
                    value = -1;
                else
                    value = -2;
                if(c.getJoueur() == Serveur.instance.getPartie().getJ1())
                    j = Serveur.instance.getPartie().getJ2();
                else
                    j = Serveur.instance.getPartie().getJ1();

                Paquet.tir(ligne, colonne, value, j);
            }
        }
    }

    public static void recevoirBateau(String data)
    {
        System.out.println("YAAAAR");
        String[] sData = data.split(",");
        int ligneDep = Integer.parseInt(sData[0]);
        int colonneDep = Integer.parseInt(sData[1]);
        int ligneArr = Integer.parseInt(sData[2]);
        int colonneArr = Integer.parseInt(sData[3]);
        int taille = Integer.parseInt(sData[4]);
        String nom = sData[5];

        JoueurServeur j1 = Serveur.instance.getPartie().getJ1();
        JoueurServeur j2 = Serveur.instance.getPartie().getJ2();

        int[][] carte;
        if(nom.equals(j1.getNom()))
            carte = j1.getMap().getCarte();
        else
            carte = j2.getMap().getCarte();
        if(ligneDep == ligneArr)
        {
            for(int j = colonneDep; j < colonneArr; j++)
            {
                carte[ligneDep][j] = taille;
                if(j == colonneArr - 1 || j == colonneDep)
                    carte[ligneDep][j] += 100;
            }
        }
        else
            for(int i = ligneDep; i < ligneArr; i++)
            {
                carte[i][colonneDep] = taille;
                if(i == ligneArr - 1 || i == ligneDep)
                    carte[i][colonneDep] += 100;
            }



    }

    public static void recevoirConnection(String data)
    {
        if(Serveur.instance.getPartie().getJ1() == null)
        {
            Serveur.instance.getPartie().setJ1(new JoueurServeur());
            Serveur.instance.getPartie().getJ1().setNom(data);
        }
        else if (Serveur.instance.getPartie().getJ2() == null)
        {
            Serveur.instance.getPartie().setJ2(new JoueurServeur());
            Serveur.instance.getPartie().getJ2().setNom(data);
        }

        Serveur.instance.ajouterJoueur();

        if(Serveur.instance.getNbJoueur() == 2)
        {
            Serveur.instance.getPartie().setCurrent(Serveur.instance.getPartie().getJ1());
            Serveur.instance.getClients().get(0).setJoueur(Serveur.instance.getPartie().getJ1());
            Serveur.instance.getClients().get(1).setJoueur(Serveur.instance.getPartie().getJ2());
            Serveur.instance.setRunning(false);
            Serveur.instance.getPartie().setCurrent(Serveur.instance.getPartie().getJ1());
            Paquet.envoyerNom();
            Paquet.demarerPartie();
            Paquet.envoyerTour(Serveur.instance.getPartie());
        }

    }

    public static void recevoirFinTour(String data, ClientSocket clientSocket)
    {
        JoueurServeur j1 = Serveur.instance.getPartie().getJ1();
        JoueurServeur j2 = Serveur.instance.getPartie().getJ2();

        if(data.equals(j1.getNom()))
            Serveur.instance.getPartie().setCurrent(j2);
        else
            Serveur.instance.getPartie().setCurrent(j1);
        Paquet.envoyerTour(Serveur.instance.getPartie() );
    }


}
