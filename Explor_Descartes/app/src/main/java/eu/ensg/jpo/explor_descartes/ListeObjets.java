package eu.ensg.jpo.explor_descartes;

import android.widget.BaseAdapter;

import java.util.ArrayList;

import eu.ensg.jpo.explor_descartes.donnesObjet.Visiteur;

public final class ListeObjets {

    /**
     * classe stockant des objets afin de permettre leur utilisation à travers les différentes activités
     */


    public static ArrayList<Integer> listeNotif_prev = new ArrayList<Integer>();
    public static Visiteur visiteur = null;
    public static String dateJPO = "26/04/2019";
    public static BaseAdapter adapterFavoris = null;
    public static BaseAdapter adapterPlanning = null;





}
