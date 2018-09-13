package touchepipi.metier;

public class Map
{
    private int nbColonne;
    private int nbLigne;
    private int[][] carte;

    public Map(int nbColonne, int nbLigne)
    {
        this.nbColonne = nbColonne;
        this.nbLigne = nbLigne;
        this.carte = new int[nbColonne][nbLigne];

        for(int i = 0; i < nbLigne; i++)
        {
            for(int j = 0; j < nbColonne; j++)
            {
                this.carte[i][j] = 0;
            }
        }
    }

    public int getNbColonne()
    {
        return nbColonne;
    }

    public void setNbColonne(int nbColonne)
    {
        this.nbColonne = nbColonne;
    }

    public int getNbLigne()
    {
        return nbLigne;
    }

    public void setNbLigne(int nbLigne)
    {
        this.nbLigne = nbLigne;
    }

    public int[][] getCarte()
    {
        return carte;
    }

    public void setCarte(int[][] carte)
    {
        this.carte = carte;
    }

    public String afficherMap()
    {
        String ligne = " - - - - - - - - - \n";
        String sRet = ligne;
        for (int i = 0; i < nbLigne; i++)
        {
            sRet += "|";
            for(int j = 0; j < nbColonne; j++)
            {
                sRet += carte[i][j] + "|";
            }
            sRet +="\n" + ligne;
        }
        return sRet;
    }

    public int getCase(int ligne, int colonne)
    {
        return this.carte[ligne][colonne];
    }
}
