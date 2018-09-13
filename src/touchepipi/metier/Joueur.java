package touchepipi.metier;

import touchepipi.network.Client.Client;
import touchepipi.network.Client.Paquet;

public class Joueur
{
    private String nom;
    private Map map;
    private Map mapAdverse;
    private Client client;
    private Partie partie;

    public Joueur(String nom)
    {
        this.nom = nom;
        this.map = new Map(10,10);
        this.mapAdverse = this.partie.getJ2().getMap();
        this.client = new Client();
    }

    public void setPartie(Partie partie)
    {
        this.partie = partie;
    }

    public boolean placerBateau(int ligneDep, int ligneArr, int colonneDep, int colonneArr)
    {
        int[][] carte = map.getCarte();
        if(ligneDep == ligneArr)
            for(int i = colonneDep; i <= colonneArr; i++)
            {
                carte[ligneDep][i] = 1;
            }
        if(colonneDep == colonneArr)
            for(int i = ligneDep; i <= ligneArr; i++)
            {
                carte[i][colonneDep] = 1;
            }
        Paquet.envoyerBateau(this, ligneDep, ligneArr, colonneDep, colonneArr);
        return true;

    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public Map getMap()
    {
        return map;
    }

    public void tirer(int ligne, int colonne)
    {
        Paquet.envoyerTir(this, ligne, colonne);
    }

    public void placerJalon(int ligne, int colonne, int value)
    {
        this.mapAdverse.getCarte()[ligne][colonne] = value;
    }

    public void setMap(Map map)
    {
        this.map = map;
    }

    public Joueur getJoueur()
    {
        return this;
    }

    public Client getClient()
    {
        return this.client;
    }
}
