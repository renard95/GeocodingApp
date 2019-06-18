package eu.ensg.jpo.explor_descartes.donneesAcces;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.MainActivity;
import eu.ensg.jpo.explor_descartes.RegisterActivity;
import eu.ensg.jpo.explor_descartes.SignInActivity;
import eu.ensg.jpo.explor_descartes.donnesObjet.Visiteur;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class VisiteurDAO extends BddEcolesDAO<Visiteur> {

    /**
     * Classe permettant d'implementer un utilisateur enregistré en java à partir des données de la base de donnée
     * @param urlServeur : url du serveur
     */

    //Constructeur
    public VisiteurDAO(String urlServeur) {
        super(urlServeur);
    }

    //Fonctions permettant de mettre à jour des informations sur unr école dans la base données
    @Override
    public void create(Visiteur newVisiteur) {

    } //création

    @Override
    public void update(Visiteur visiteur) {


    } //mise à jour

    @Override
    public void delete(Visiteur visiteur) {


    } //suppresion




    public void connexionAuto(final MainActivity activity, String mailOrPseudo, String mdp) {

        /**
         * Fonction permettant de se reconnecter automatiquement à chaque réouverture de l'application
         * @param activity : page principale
         * @param mailOrPseudo : adresse e-mail ou pseudo utilisé pour la connexion
         * @param mdp : mot de passe associé au compte aulequel on souhaite se connecter
         */

        // Construction de la requete
        String url = this.urlServeur + "?request=connexion";
        String donnees = "&&nom=" + mailOrPseudo + "&&mdp=" + mdp;
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("VisiteurDAO connexionAuto: Connexion etablie avec succes !");
                final Visiteur visiteur = new Gson().fromJson(response.body().string(), Visiteur.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //Identifiants incorrects
                        if (visiteur == null){
                            Toast.makeText(activity, "Votre compte semble avoir été modifié ! Reconnectez-vous avec vos nouveaux identifiants." , Toast.LENGTH_LONG).show();
                        }

                        //Identifiants corrects
                        else{
                            // Instanciation du visiteur
                            ListeObjets.visiteur = visiteur;
                            Toast.makeText(activity, "Bonjour " + visiteur.getPseudo() + " !", Toast.LENGTH_LONG).show();
                            activity.getMenu().openNavigationActivity();

                        }
                    }
                });
            }

            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println("VisiteurDAO connexionAuto: Echec de la connection !");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "Problème de connexion au serveur..." , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public void connexionBdd(final SignInActivity activity, String mailOrPseudo, String mdp) {

        /**
         * Fonction permettant de se connecter à l'aide de la méthode classique
         * @param activity : page de connexion
         * @param mailOrPseudo : adresse e-mail ou pseudo utilisé pour la connexion
         * @param mdp : mot de passe associé au compte auquel on souhaite se connecter
         */

        // Construction de la requete
        String url = this.urlServeur + "?request=connexion";
        String donnees = "&&nom=" + mailOrPseudo + "&&mdp=" + mdp;
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("VisiteurDAO connexionBdd : Connexion etablie avec succes !");
                final Visiteur visiteur = new Gson().fromJson(response.body().string(), Visiteur.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //Identifiants incorrects
                        if (visiteur == null){
                            Toast.makeText(activity, "Identifiants incorrects" , Toast.LENGTH_LONG).show();
                            activity.getMdpET().getText().clear();
                        }

                        //Identifiants corrects
                        else{
                            // Instanciation du visiteur
                            ListeObjets.visiteur = visiteur;
                            // Enregistrement dans les données de l'application
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.remove("pseudo");
                            editor.remove("mdp");
                            editor.putString("pseudo", visiteur.getPseudo());
                            editor.putString("mdp", visiteur.getMdp());
                            editor.commit();
                            Toast.makeText(activity, "Bonjour " + visiteur.getPseudo() + " !", Toast.LENGTH_LONG).show();
                            activity.openAccueilActivity();



                        }
                    }
                });
            }

            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println("VisiteurDAO connexionBdd : Echec de la connection !");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "Problème de connexion au serveur..." , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public void firstConnexionBdd(final RegisterActivity activity, Visiteur newVisteur) {

        /**
         * Fonction permettant de se connecter pour la première fois, une fois que l'inscription est validé
         * @param activity : page d'inscription
         * @param newVisiteur : compte crée lors de l'inscription du visiteur
         */

        // Construction de la requete
        String url = this.urlServeur + "?request=connexion";
        String donnees = "&&nom=" + newVisteur.getEmail() + "&&mdp=" + newVisteur.getMdp();
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("VisiteurDAO firstConnexionBdd : Connexion etablie avec succes !");
                final Visiteur visiteur = new Gson().fromJson(response.body().string(), Visiteur.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ListeObjets.visiteur = visiteur;
                        // Enregistrement dans les données de l'application
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("pseudo");
                        editor.remove("mdp");
                        editor.putString("pseudo", visiteur.getPseudo());
                        editor.putString("mdp", visiteur.getMdp());
                        editor.commit();
                        Toast.makeText(activity, "Inscription réussie !", Toast.LENGTH_LONG).show();
                        activity.openAccueilActivity();
                    }
                });
            }

            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println("VisiteurDAO firstConnexionBdd : Echec de la connection !");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "Problème de connexion au serveur..." , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public void inscriptionBdd(final RegisterActivity activity, final Visiteur newVisiteur){

        /**
         * Fonction permettant de s'inscrire à l'aide de la méthode classique
         * @param activity : page d'inscription
         * @param newVisiteur : nouveau compte
         */

        // Construction de la requete
        String url = this.urlServeur + "?request=saveUtilisateur";
        String donnees = "&&prenom=" + newVisiteur.getPrenom() + "&&nom=" + newVisiteur.getNom() + "&&pseudo=" + newVisiteur.getPseudo() + "&&email=" + newVisiteur.getEmail() + "&&mdp=" + newVisiteur.getMdp();
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("VisiteurDAO inscriptionBdd : Connexion etablie avec succes !");
                String reponseBdd = response.body().string();
                // Pseudo déjà utilisé
                if (reponseBdd.equals("Pseudo déjà utilisé !")){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "Pseudo déjà utilisé !" , Toast.LENGTH_LONG).show();
                            activity.getPseudoET().getText().clear();
                        }
                    });
                }
                // Email déjà utilisé
                else if (reponseBdd.equals("Email déjà utilisé !")){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "Email déjà utilisé !" , Toast.LENGTH_LONG).show();
                            activity.getMailET().getText().clear();
                        }
                    });
                }
                // Pseudo et email corrects
                else{
                    firstConnexionBdd(activity, newVisiteur);
                }

            }

            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println(" VisiteurDAO inscriptionBdd : Echec de la connection !");
            }
        });
    }


}