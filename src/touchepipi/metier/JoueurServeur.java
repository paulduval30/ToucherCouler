package touchepipi.metier;

import touchepipi.network.Client.Client;
import touchepipi.network.Client.Paquet;
import touchepipi.network.Serveur.ClientSocket;

public class JoueurServeur
{
    private String nom;
    private Map map;
    private Map mapAdverse;

    public JoueurServeur()
    {
        this.map = new Map(10,10);
        this.mapAdverse = new Map(this.map.getNbColonne(), this.map.getNbLigne());
    }


    public boolean placerBateau(int ligneDep, int ligneArr, int colonneDep, int colonneArr)
    {
        int[][] carte = this.map.getCarte();
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

    public void setMap(Map map)
    {
        this.map = map;
    }

    public JoueurServeur getJoueur()
    {
        return this;
    }

}
