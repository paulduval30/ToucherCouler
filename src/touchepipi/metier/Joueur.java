package touchepipi.metier;

import touchepipi.network.Client.Client;
import touchepipi.network.Client.Paquet;

public class Joueur
{
    private String nom;
    private Map map;
    Client client;

    public Joueur(String nom)
    {
        this.nom = nom;
        this.map = new Map(10,10);
        this.client = new Client();
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
        Paquet.envoyerTire(this.client, ligne, colonne);

    }

    public void setMap(Map map)
    {
        this.map = map;
    }
}
