package com.example.produits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AjoutProduit extends AppCompatActivity {


        EditText Tnom ;
        EditText Tdesc ;
        ImageView image ;
        EditText Tquant ;
        EditText Tmarque ;
        EditText Tprix ;
        ;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fiche_produit);
            Intent i = this.getIntent();
            Tnom = findViewById(R.id.nomP);
            Tdesc = findViewById(R.id.description);
            image = findViewById(R.id.imgV);
            Tprix =  findViewById(R.id.prix);
            Tquant =  findViewById(R.id.quant);
            Tmarque =  findViewById(R.id.marque);
            image.setImageResource(R.drawable.no_img);



        }



        public void save(View view) {
            super.onStop();
            double prix ;
            int quant ;
            String nom = Tnom.getText().toString() ;
            if (Tprix.getText().toString().isEmpty()){
                 prix = 0 ;
            }else{
                 prix = Double.parseDouble(Tprix.getText().toString()) ;
            }

            if ( Tquant.getText().toString().isEmpty() ){
                quant = 0;
            }else {
                quant = Integer.parseInt(Tquant.getText().toString());
            }

            String desc = Tdesc.getText().toString() ;
            String marque = Tmarque.getText().toString();
            Intent i = new Intent();
            Produit p = new Produit(R.drawable.no_img,nom,prix,desc,quant,marque,Produit.GenId());
            i.putExtra("NouveauProduit",p);
            setResult(RESULT_OK,i);
            finish();


        }
    }


