package touchercouler.metier;

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


    public boolean placerBateau(int ligneDep, int colonneDep, char dir, int taille)
    {
        int[][] carte = map.getCarte();
        if(dir == 'H')
            for(int i = colonneDep; i < colonneDep +  taille; i++)
            {
                carte[ligneDep][i] = taille * 10;
                if(i == colonneDep || i == colonneDep + taille)
                    carte[ligneDep][i] += 100;
            }
        if(dir == 'V')
            for(int i = ligneDep; i < ligneDep + taille; i++)
            {
                carte[i][colonneDep] = taille * 10;
                if(i == colonneDep || i == colonneDep + taille)
                    carte[i][colonneDep] += 100;
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
