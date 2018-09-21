package touchepipi.network.Serveur;

import touchepipi.metier.JoueurServeur;

/**
 * Classe servant a traiter les données reçus par le serveur.
 * Les méthodes sont appellées dans serveur.run()
 */
public class Reception
{
    public static Serveur instance;
    public static void recevoirTir(String data, ClientSocket clientSocket)
    {
        String[] sData = data.split(",");
        int ligne = Integer.parseInt(sData[0]);
        int colonne = Integer.parseInt(sData[1]);
        String nom = sData[2];

        if(!nom.equals(clientSocket.getJoueur().getNom()))
        {
            int[][] carte = clientSocket.getJoueur().getMap().getCarte();
            if(carte[ligne][colonne] == 1)
                carte[ligne][colonne] = -1;
            else
                carte[ligne][colonne] = -2;
            Paquet.tir(ligne, colonne, carte[ligne][colonne], clientSocket.getJoueur());
        }
    }

    public static void recevoirBateau(String data, ClientSocket clientSocket)
    {

        String[] sData = data.split(",");
        int ligneDep = Integer.parseInt(sData[0]);
        int colonneDep = Integer.parseInt(sData[1]);
        int ligneArr = Integer.parseInt(sData[2]);
        int colonneArr = Integer.parseInt(sData[3]);
        int taille = Integer.parseInt(sData[4]);
        String nom = sData[5];

        JoueurServeur j1 = instance.getPartie().getJ1();
        JoueurServeur j2 = instance.getPartie().getJ2();

        int[][] carte;
        if(nom.equals(j1.getNom()))
            carte = j1.getMap().getCarte();
        else
            carte = j2.getMap().getCarte();
        for(int i = ligneDep; i <= ligneArr; i++)
            for(int j = colonneDep; j <= colonneArr; j++)
            {
                carte[i][j] = taille;
                if(colonneArr !=  colonneDep && (j == colonneArr || j == colonneDep)
                        || ligneArr != ligneDep && (i == ligneArr || i == ligneDep))
                    carte[i][j] += 100;
            }

    }

    public static void recevoirConnection(String data)
    {
        if(instance.getPartie().getJ1() == null)
        {
            instance.getPartie().setJ1(new JoueurServeur());
            instance.getPartie().getJ1().setNom(data);
        }
        else if (instance.getPartie().getJ2() == null)
        {
            instance.getPartie().setJ2(new JoueurServeur());
            instance.getPartie().getJ2().setNom(data);
        }

        instance.ajouterJoueur();

        if(instance.getNbJoueur() == 2)
        {
            instance.getPartie().setCurrent(instance.getPartie().getJ1());
            instance.getClients().get(0).setJoueur(instance.getPartie().getJ1());
            instance.getClients().get(1).setJoueur(instance.getPartie().getJ2());
            instance.setRunning(false);
            instance.getPartie().setCurrent(instance.getPartie().getJ1());
            Paquet.envoyerNom();
            Paquet.demarerPartie();
            Paquet.envoyerTour(instance.getPartie());
        }

    }

    public static void recevoirFinTour(String data, ClientSocket clientSocket)
    {
        JoueurServeur j1 = instance.getPartie().getJ1();
        JoueurServeur j2 = instance.getPartie().getJ2();

        if(data.equals(j1.getNom()))
            instance.getPartie().setCurrent(j2);
        else
            instance.getPartie().setCurrent(j1);
        Paquet.envoyerTour(instance.getPartie() );
    }


}
