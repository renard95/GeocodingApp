package eu.ensg.jpo.explor_descartes.Menu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import eu.ensg.jpo.explor_descartes.NavigationActivity;
import eu.ensg.jpo.explor_descartes.RegisterActivity;
import eu.ensg.jpo.explor_descartes.SignInActivity;

public class Menu {

    private Context con;

    /**
     * Constructeur de la classe Menu qui répertorie toutes les activités
     * @param con
     */
    public Menu(Context con){
        this.con = con;
    }

    public void openNavigationActivity(){
        // Create intent
        Intent intent = new Intent(con, NavigationActivity.class);
        // Start activity
        con.startActivity(intent);
    }

    public void openSignInActivity() {
        // Create intent
        Intent intent = new Intent(con, SignInActivity.class);
        // Start activity
        con.startActivity(intent);
    }
    public void openRegisterActivity() {
        // Create intent
        Intent intent = new Intent(con, RegisterActivity.class);
        // Start activity
        con.startActivity(intent);
    }



    public void openTrain() {
        String url = "http://gisak.vsb.cz/stops/projet/train.html";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        con.startActivity(i);
    }

    public void openBusOth() {
        String url = "http://gisak.vsb.cz/stops/projet/bus_oth.html";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        con.startActivity(i);
    }
    public void openBusMuni() {
        String url = "http://gisak.vsb.cz/stops/projet/bus_muni.html";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        con.startActivity(i);
    }
}