package eu.ensg.jpo.explor_descartes.donnesObjet;

import java.util.ArrayList;

public class Visiteur extends DataBaseObject{

    /**
     * Classe représenatnt un compte pour un utilisateur engistré
     */

    private String prenom;
    private String nom;
    private String pseudo;
    private String email;
    private String mdp;


    //Constructeur
    public Visiteur(String prenom, String nom, String pseudo, String email, String mdp) {
        super(0);
        this.prenom = prenom;
        this.nom = nom;
        this.pseudo = pseudo;
        this.email = email;
        this.mdp = mdp;

    }


    /*
    Getter et Setter
     */

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp ;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }


}