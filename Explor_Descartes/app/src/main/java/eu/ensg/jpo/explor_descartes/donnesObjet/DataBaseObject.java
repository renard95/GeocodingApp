package eu.ensg.jpo.explor_descartes.donnesObjet;

public abstract class DataBaseObject {

    /**
     * Classe abstraite représentant un objet dans la base de données
     * @param id : id
     */

    protected int id;


    //Constructeur
    DataBaseObject(int id) {
        this.id = id;
    }


    /*
    Getter et Setter
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
