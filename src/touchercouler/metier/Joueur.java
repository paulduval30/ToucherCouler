package touchercouler.metier;

import touchercouler.IHM.MainFrame;
import touchercouler.network.Client.Client;
import touchercouler.network.Client.Paquet;

import java.util.ArrayList;

public class Joueur
{
    private String nom;
    private Map map;
    private Map mapAdverse;
    private Client client;
    private boolean current;
    private String j2;

    private ArrayList<Integer> bateau;

    public Joueur(String nom)
    {
        this.bateau = new ArrayList<>();
        bateau.add(50);
        bateau.add(40);
        bateau.add(30);
        bateau.add(20);
        bateau.add(10);
        bateau.add(10);
        this.j2 = "";
        this.nom = nom;
        this.current = false;
        this.map = new Map(10,10);
        this.mapAdverse = new Map(this.map.getNbColonne(), this.map.getNbLigne());
        this.client = new Client(this);
        client.connect("localhost",25565);
        Paquet.connection(this);
    }

    public void initPartie()
    {
/**        for(Integer i : bateau)
        {
            int ligne = IHM.lireInt();
            int colonne = IHM.lireInt();
            char dir = IHM.lireDir();
            placerBateau(ligne, colonne, i, dir);
        }*/
    }
    public boolean placerBateau(int ligneDep, int colonneDep, char dir)
    {
        int taille = bateau.get(0) / 10;
        int[][] carte = map.getCarte();
        if(dir == 'H')
        {
            if(colonneDep + taille >= carte.length)
                return false;
            for(int i = colonneDep; i < colonneDep +  taille; i++)
            {
                if(carte[ligneDep][i] != 0)
                    return false;
            }

            for(int i = colonneDep; i < colonneDep +  taille; i++)
            {
                carte[ligneDep][i] = taille * 10;
                if(i == colonneDep || i == colonneDep + taille)
                    carte[ligneDep][i] += 100;
            }
            Paquet.envoyerBateau(this, ligneDep, ligneDep, colonneDep, colonneDep +  taille, taille * 10 );
        }
        if(dir == 'V')
        {
            if(ligneDep + taille >= carte.length)
                return false;
            for(int i = ligneDep; i < ligneDep + taille; i++)
            {
                if(carte[i][colonneDep] != 0)
                    return false;
            }

            for(int i = ligneDep; i < ligneDep + taille; i++)
            {
                carte[i][colonneDep] = taille * 10;
                if(i == colonneDep || i == colonneDep + taille)
                    carte[i][colonneDep] += 100;
            }
            Paquet.envoyerBateau(this, ligneDep, ligneDep + taille, colonneDep, colonneDep, taille * 10 );
        }
        bateau.remove(0);
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

    public Map getMapAdverse()
    {
        return this.mapAdverse;
    }


    public void placerJalon(Map map, int ligne, int colonne, int value)
    {
        map.getCarte()[ligne][colonne] = value;
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

    public String getJ2()
    {
        return j2;
    }

    public void jouerTour()
    {

    }

    public boolean getCurrent()
    {
        return current;
    }

    public void envoyerTir(int rectLig, int rectCol)
    {
        Paquet.envoyerTir(this, rectLig, rectCol);
        this.finTour();
    }

    private void finTour()
    {
        Paquet.finDeTour(this);
    }

    public ArrayList<Integer> getBateaux()
    {
        return this.bateau;
    }
}
