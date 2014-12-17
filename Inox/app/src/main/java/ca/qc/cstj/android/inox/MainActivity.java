package ca.qc.cstj.android.inox;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.apache.http.HttpStatus;

<<<<<<< HEAD
import java.io.FileOutputStream;
=======
>>>>>>> origin/master
import java.util.ArrayList;

import ca.qc.cstj.android.inox.models.Explorateur;
import ca.qc.cstj.android.inox.services.ServicesURI;
<<<<<<< HEAD
import ca.qc.cstj.android.inox.helpers.VarGlobales;
=======
>>>>>>> origin/master


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        InventaireFragment.OnFragmentInteractionListener
{
    //Variables
    Button btnConnexion;
    EditText txtUsager;
    EditText txtMdp;

    ProgressDialog progressDialog;
<<<<<<< HEAD
=======

    Explorateur explorateur;
>>>>>>> origin/master

    private CharSequence mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Le contexte de l'application
        final Context ctx = this;

        //On attribut le contenu aux variables de storage - Anthony
        btnConnexion = (Button) findViewById(R.id.btnConnexion);
        txtUsager = (EditText) findViewById(R.id.txtUsager);
        txtMdp = (EditText) findViewById(R.id.txtMdp);

        //Lorsque le bouton est cliqué - Anthony
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //On affiche le progrès - Anthony
                progressDialog = ProgressDialog.show(ctx, "Inox", "Connexion en cours...",true,false);


                //On fait une requête au WebService - Anthony
                Ion.with(getApplicationContext())
                        .load(ServicesURI.AUTHENTIFICATION_SERVICE_URI)
                        .setHeader("Content-Type", "application/json")
                        .setHeader("usager", txtUsager.getText().toString())
                        .setHeader("mdp", txtMdp.getText().toString())
                        .asJsonObject()
                        .withResponse()
                        .setCallback(new FutureCallback<Response<JsonObject>>() {
                            @Override
                            public void onCompleted(Exception e, Response<JsonObject> jsonObjectResponse) {
                                progressDialog.dismiss();
                                //200 - OK - Anthony
                                if(jsonObjectResponse.getHeaders().getResponseCode() == HttpStatus.SC_OK){
<<<<<<< HEAD
                                    //On sauvegarde le href dans le fichier interne puis si cela fonctionne, on affiche l'inventaire
                                    try {
                                        //On sauvegarde l'explorateur dans la variable globale
                                        VarGlobales.explorateurConnecte = new Explorateur();
                                        VarGlobales.explorateurConnecte.setHref(jsonObjectResponse.getResult().get("href").toString());
                                        VarGlobales.explorateurConnecte.setUsager(jsonObjectResponse.getResult().get("usager").toString());

                                        //On sauvegarde le token dans le fichier interne du cellulaire
                                        //Ceci facilite la vérification lors de la réouverture de l'application
                                        FileOutputStream fichier = openFileOutput("storage_token", ctx.MODE_PRIVATE);
                                        fichier.write(jsonObjectResponse.getResult().get("token").toString().getBytes());

                                        Toast message = Toast.makeText(ctx, "Bonjour "+ jsonObjectResponse.getResult().get("usager").toString(), Toast.LENGTH_SHORT);
                                        message.show();

                                        Intent intent = new Intent(getApplicationContext(), NavigationDrawer.class);
                                        startActivity(intent);
                                    } //Si on attrape une exception, on affiche un message d'erreur
                                    catch (Exception excep)
                                    {
                                        Toast message = Toast.makeText(ctx, "Erreur lors de la sauvegarde interne.", Toast.LENGTH_SHORT);
                                        txtMdp.setText("");
                                        txtUsager.setText("");
                                    }
=======
                                    Toast message = Toast.makeText(ctx, "Connexion réussie!", Toast.LENGTH_SHORT);
                                    message.show();
                                    Intent intent = new Intent(getApplicationContext(), NavigationDrawer.class);
                                    startActivity(intent);
>>>>>>> origin/master
                                }
                                //401 - Mauvais login - Anthony
                                else if(jsonObjectResponse.getHeaders().getResponseCode() == HttpStatus.SC_UNAUTHORIZED){
                                    String description = jsonObjectResponse.getResult().get("description").toString();
                                    Toast message = Toast.makeText(ctx, description, Toast.LENGTH_SHORT);
                                    txtMdp.setText("");
                                    txtUsager.setText("");
                                    message.show();
                                }
                                //503 - Service non-disponible - Anthony
                                else if(jsonObjectResponse.getHeaders().getResponseCode() == HttpStatus.SC_SERVICE_UNAVAILABLE)
                                {
                                    String description = jsonObjectResponse.getResult().get("description").toString();
                                    Toast message = Toast.makeText(ctx, description, Toast.LENGTH_SHORT);
                                    txtMdp.setText("");
                                    txtUsager.setText("");
                                    message.show();
                                }
                                //500 - Internal error - Anthony
<<<<<<< HEAD
                                else if(jsonObjectResponse.getHeaders().getResponseCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR)
=======
                                else
>>>>>>> origin/master
                                {
                                    String description = jsonObjectResponse.getResult().get("description").toString();
                                    Toast message = Toast.makeText(ctx, description, Toast.LENGTH_SHORT);
                                    txtMdp.setText("");
                                    txtUsager.setText("");
                                    message.show();
                                }
<<<<<<< HEAD
                                //Tout autre problèmes - Anthony
                                else
                                {
                                    Toast message = Toast.makeText(ctx, "Erreur, veuillez essayer à nouveau.", Toast.LENGTH_SHORT);
                                    txtMdp.setText("");
                                    txtUsager.setText("");
                                    message.show();
                                }
=======
>>>>>>> origin/master
                            }
                        });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_inventaire);
                break;
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        switch (position){
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, InventaireFragment.newInstance(position + 1)).addToBackStack("")
                        .commit();
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
