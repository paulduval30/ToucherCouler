package touchepipi.metier;

import touchepipi.network.Client.Client;
import touchepipi.network.Client.Paquet;

public class Joueur
{
    private String nom;
    private Map map;
    private Map mapAdverse;
    private Client client;
    private boolean current;
    private String j2;

    public Joueur()
    {
        this.current = false;
        this.map = new Map(10,10);
        this.mapAdverse = new Map(this.map.getNbColonne(), this.map.getNbLigne());
        this.client = new Client(this);
        client.connect("localhost",25565);
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

    public void placerJalon(Map map, int ligne, int colonne, int value)
    {
        this.map.getCarte()[ligne][colonne] = value;
    }

    public Map getMapAdverse()
    {
        return this.mapAdverse;
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

    public void setCurrent(boolean current)
    {
        this.current = current;
    }

    public void setJ2(String j2)
    {
        this.j2 = j2;
    }
}
