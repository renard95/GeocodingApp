package eu.ensg.jpo.explor_descartes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import eu.ensg.jpo.explor_descartes.donneesAcces.VisiteurDAO;
import eu.ensg.jpo.explor_descartes.donnesObjet.Visiteur;

public class RegisterActivity extends AppCompatActivity {

    /**
     * classe gérant la page de création d'un compte
     */

    Button validationB;
    Button connexionB;
    EditText prenomET;
    EditText nomET;
    EditText pseudoET;
    EditText mailET;
    EditText mdpET;
    EditText mdp_confirmET;

    public static boolean isValidEmailAddress(String email) {

        /**
         * On vérifie si l'adresse email entrée est valide
         *
         * @param email : l'email de l'utilisateur
         * @return un booléen indiquant si l'adresse mail est valide ou non
         */

        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public void openSignInActivity() {

        /**
         * Fonction qui ouvre la page d'enregistrement
         */

        // Create intent
        Intent intent = new Intent(this, SignInActivity.class);

        // Start activity
        startActivity(intent);
    }

    public void openAccueilActivity() {

        /**
         * Fonction qui ouvre la page d'accueil
         */

        // Create intent
        Intent intent = new Intent(this, NavigationActivity.class);

        // Start activity
        startActivity(intent);
    }

    private void inscription() {

        /**
         * Fonction qui permet de s'incrire en appuyant sur le bouton de validation
         * et qui enregistre les informations de l'utilisateur dans la base de données si les
         * champs sont correctement remplis
         */

        // Récupération des valeurs entrées :
        String prenom = prenomET.getText().toString();
        String nom = nomET.getText().toString();
        String pseudo = pseudoET.getText().toString();
        String mail = mailET.getText().toString().toLowerCase();
        String mdp = mdpET.getText().toString();
        String mdp_confirm = mdp_confirmET.getText().toString();

        // Vérification des valeurs entrées :
        if (pseudo.equals("")){
            Toast.makeText(this, "Le champ 'Pseudo' est obligatoire.", Toast.LENGTH_LONG).show();
            return;
        }
        if (mail.equals("")){
            Toast.makeText(this, "Le champ 'Mail' est obligatoire.", Toast.LENGTH_LONG).show();
            return;
        }
        if (!isValidEmailAddress(mail)){
            Toast.makeText(this, "Votre adresse mail n'est pas valide.", Toast.LENGTH_LONG).show();
            return;
        }
        if (mdp.equals("")){
            Toast.makeText(this, "Le champ 'Mot de passe' est obligatoire.", Toast.LENGTH_LONG).show();
            return;
        }
        if (!mdp.equals(mdp_confirm)){
            Toast.makeText(this, "Les mots de passe ne correspondent pas.", Toast.LENGTH_LONG).show();
            return;
        }

        // Enregistrement dans la base de données :
        try {
            Visiteur newVisiteur = new Visiteur(prenom, nom, pseudo, mail, SHA.encode(mdp));
            VisiteurDAO visiteurDAO = new VisiteurDAO("http://gisak.vsb.cz/stops/exemple/utilisateur.php");
            visiteurDAO.inscriptionBdd(this, newVisiteur);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // I - Instanciation des objets Java représentant les composants graphiques

        validationB = (Button)findViewById(R.id.validationB);
        connexionB  = (Button)findViewById(R.id.connexionB);

        prenomET      = (EditText) findViewById(R.id.prenomET);
        nomET         = (EditText) findViewById(R.id.nomET);
        pseudoET      = (EditText) findViewById(R.id.pseudoET);
        mailET        = (EditText) findViewById(R.id.mailET);
        mdpET         = (EditText) findViewById(R.id.mdpET);
        mdp_confirmET = (EditText) findViewById(R.id.mdp_confirmET);

        //Ajout de la redirection vers CGU
        TextView lienCGU = (TextView)findViewById(R.id.accord);
        lienCGU.setMovementMethod(LinkMovementMethod.getInstance());

        // II - Ajout des écouteurs d'événements aux composants graphiques représentés par des objets Java

        connexionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.this.openSignInActivity();
            }
        });

        validationB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.this.inscription();
            }
        });
    }


    public EditText getPseudoET() {
        return pseudoET;
    }

    public EditText getMailET() {
        return mailET;
    }
}