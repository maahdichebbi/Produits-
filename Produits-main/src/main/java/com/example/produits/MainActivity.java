package com.example.produits;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Produit> ListeProduits ;
    ListView listeV;
    ProduitAdapter pA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        charger();
        setContentView(R.layout.activity_main);
        listeV = (ListView) findViewById(R.id.listV);
        pA = new ProduitAdapter(this, R.layout.list_row, ListeProduits);
        listeV.setAdapter(pA);
        listeV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produit p = ListeProduits.get(position);
                Intent intent = new Intent(MainActivity.this,Fiche.class);
                intent.putExtra("Produit", (Parcelable) p);
                startActivityForResult(intent,1);
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AjoutProduit.class);
                startActivityForResult(i,2);
            }
        });
    }
    // la methode de sauvegarde
    public void enregistrer() {
        SharedPreferences sp = getSharedPreferences("donnés", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ListeProduits);
        editor.putString("liste_de_Produits", json);
        editor.apply();
    }
    // la méthode qui charge les données
    private void charger() {
        SharedPreferences sp = getSharedPreferences("donnés", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("liste_de_Produits", null);
        Type type = new TypeToken<ArrayList<Produit>>() {
        }.getType(); //declaration de type de donnés
        ListeProduits = gson.fromJson(json, type);
        if (ListeProduits == null) {
            // les produits prédéfinis
            ListeProduits = new ArrayList<Produit>();
            ListeProduits.add(new Produit(R.drawable.casque_razer, "Casque Razer", 49.99, "Casque Réducteur de Bruit Gaming avec Micro Razer Kraken X - Noir", 40, "Razer",Produit.GenId()));
            ListeProduits.add(new Produit(R.drawable.souris_logitech, "LOGITECH G PRO", 25.50, "Souris ultralégère et résistante pour une maniabilité et une durabilité maximales", 40, "LOGITECH",Produit.GenId()));
            ListeProduits.add(new Produit(R.drawable.clavier_corsair, "Corsair K55 Clavier Gaming", 40.00, "Clavier Rgb  gaming personnalilser ", 35, "Corsair",Produit.GenId()));
            ListeProduits.add(new Produit(R.drawable.airpods, "Airpods", 200.00, "Les AirPods garantissent une expérience d’écoute inégalée avec tous vos appareils. Chaque modèle se connecte facilement et offre un son riche et de haute qualité dans un design sans fil innovant.", 20, "Apple",Produit.GenId()));
            ListeProduits.add(new Produit(R.drawable.manette_xbox, "Manette Xbox one ", 45.00, "manette xbox one compatible avec tous les consoles xbox et windows pc  ", 80, "Microsoft",Produit.GenId()));
            ListeProduits.add(new Produit(R.drawable.manette_ps5, "Manette Ps5 ", 65.00, "Manette sans fil DualSense pour PS5, Pour une expérience de gaming plus intense et innovante, Compatible avec PC via une connexion filaire en USB ", 2, "Sony",Produit.GenId()));
        }

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Modificaton de produit
        if (requestCode == 1  ){
            if (resultCode  == RESULT_OK ){
                Produit produit = data.getParcelableExtra("produit");
                for (Produit p : ListeProduits) {
                    if (p.id.equals(produit.id) ){
                        ListeProduits.set(ListeProduits.indexOf(p),produit); // modification du produit dans la liste
                    }
                }


            }else if (resultCode == RESULT_CANCELED){
                Toast.makeText( MainActivity.this, "Modifications non sauvegardées ", Toast.LENGTH_SHORT ).show();
            }

        }
        //Ajout de produit
    if (requestCode == 2){
            if (resultCode == RESULT_OK){
                Produit produit = data.getParcelableExtra("NouveauProduit");
                ListeProduits.add(produit);
                Toast.makeText( MainActivity.this, "Produit ajouté", Toast.LENGTH_SHORT ).show();
            }else{
                Toast.makeText( MainActivity.this, "Produit non ajouté ", Toast.LENGTH_SHORT ).show();
            }
        }
        enregistrer();
        pA.notifyDataSetChanged(); // pour mettre a jour l'interface

    }



}

