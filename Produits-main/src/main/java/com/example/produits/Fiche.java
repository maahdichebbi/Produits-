package com.example.produits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Fiche extends AppCompatActivity {

    EditText Tnom ;
    EditText Tdesc ;
    ImageView image ;
    EditText Tquant ;
    EditText Tmarque ;
    EditText Tprix ;
    public Produit p ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fiche_produit);
        Intent i = this.getIntent();
        p = i.getParcelableExtra("Produit");
        Tnom = findViewById(R.id.nomP);
        Tdesc = findViewById(R.id.description);
        image = findViewById(R.id.imgV);
        Tprix =  findViewById(R.id.prix);
        Tquant =  findViewById(R.id.quant);
        Tmarque =  findViewById(R.id.marque);

        Tnom.setText(p.nom);
        image.setImageResource(p.Image);
        Tdesc.setText(p.desc);
        Tprix.setText(Double.toString(p.prix));
        Tquant.setText(Integer.toString(p.quant));
        Tmarque.setText(p.marque);







    }



    public void save(View view) { //bouton save
        super.onStop();
        p.nom = Tnom.getText().toString() ;
        if (!Tprix.getText().toString().isEmpty()){
            p.prix = Double.parseDouble(Tprix.getText().toString()) ;
        }
        if (!Tquant.getText().toString().isEmpty()){
            p.quant = Integer.parseInt(Tquant.getText().toString()) ;
        }


        p.desc = Tdesc.getText().toString() ;
        p.marque = Tmarque.getText().toString();
        System.out.println(p.id);
        Intent i = new Intent();
        i.putExtra("produit",p);
        setResult(RESULT_OK,i);
        finish();


    }
}
