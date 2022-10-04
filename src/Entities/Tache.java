package Entities;

public class Tache
{
    private String nomTache;
    private String nomDeveloppeur;
    private boolean estTerminee;

    public String getNomTache() {
        return nomTache;
    }
    public void setNomTache(String nomTache) {
        this.nomTache = nomTache;
    }
    public String getNomDeveloppeur() {
        return nomDeveloppeur;
    }
    public void setNomDeveloppeur(String nomDeveloppeur) {
        this.nomDeveloppeur = nomDeveloppeur;
    }
    public boolean isEstTerminee() {
        return estTerminee;
    }
    public void setEstTerminee(boolean estTerminee) {
        this.estTerminee = estTerminee;
    }
    public Tache(String nomTache, String nomDeveloppeur, boolean estTerminee) {
        this.nomTache = nomTache;
        this.nomDeveloppeur = nomDeveloppeur;
        this.estTerminee = estTerminee;
    }
}
