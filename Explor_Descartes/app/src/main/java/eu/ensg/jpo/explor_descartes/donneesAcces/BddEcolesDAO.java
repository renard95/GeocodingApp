package eu.ensg.jpo.explor_descartes.donneesAcces;

import okhttp3.OkHttpClient;

public abstract class BddEcolesDAO<T> {

    /**
     * Classe mère de toutes les DAO
     * @param urlServeur : url du serveur
     * @param client : client HTTP
     */

    protected String urlServeur;
    protected static final OkHttpClient client = new OkHttpClient();


    //Constructeur
    public BddEcolesDAO(String urlServeur) {
        this.urlServeur = urlServeur;
    }

    //Fonctions permettant de mettre à jour des données dans la base de onnées
    public abstract void create(T obj);  //création

    public abstract void update(T obj);  //mise à jour

    public abstract void delete(T obj);  //suppression

}