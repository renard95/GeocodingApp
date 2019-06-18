package eu.ensg.jpo.explor_descartes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;

import eu.ensg.jpo.explor_descartes.Menu.Menu;
import eu.ensg.jpo.explor_descartes.donneesAcces.VisiteurDAO;


public class MainActivity extends AppCompatActivity {

    ImageButton connexionB;
    Button inscriptionB;
    Button ignorerB;
    Context con = this;
    private Menu menu = new Menu(con);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Si déjà connecter :
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String pseudo = preferences.getString("pseudo", null);
        String mdp = preferences.getString("mdp", null);
        if (pseudo != null && mdp != null){
            System.out.println(pseudo);
            System.out.println(mdp);
            VisiteurDAO visiteurDAO = new VisiteurDAO("http://gisak.vsb.cz/stops/exemple/" + "utilisateur.php/");
            visiteurDAO.connexionAuto(this, pseudo, mdp);
        }

        setContentView(R.layout.activity_main);

        // 0 - Chargement des objets depuis la base de données
        chargerListeObjets();

        // I - Instanciation des objets Java représentant les composants graphiques

        ignorerB     = findViewById(R.id.ignorerB);
        //connexionB   = findViewById(R.id.connexionB);
        //inscriptionB = findViewById(R.id.inscriptionB);

        // II - Ajout des écouteurs d'événements aux composants graphiques représentés par des objets Java
        /**
        connexionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.openSignInActivity();
            }
        });

        inscriptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.openRegisterActivity();
            }
        });
        */
        ignorerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.openNavigationActivity();
            }
        });

    }

    public void chargerListeObjets(){

        /**
         * fonction chargeant tout les batiments et les écoles
         */

        String urlServeur = "http://gisak.vsb.cz/stops/exemple/";


    }

    public Menu getMenu() {
        return menu;
    }
}