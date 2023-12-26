package com.example.produits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

// la classe qui crée les éléments
public class ProduitAdapter extends ArrayAdapter<Produit> {
        private Context Context; //contexte
        private int Res ; //ressources
        Produit p ;
    public ProduitAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Produit> objects) {
        super(context, resource, objects);
        this.Context = context;
        this.Res = resource;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(Context);
        convertView = layoutInflater.inflate(Res,parent,false);
        ImageView imageView = convertView.findViewById(R.id.image);
        TextView nom = convertView.findViewById(R.id.nom);
        TextView desc = convertView.findViewById(R.id.desc);
        imageView.setImageResource(getItem(position).Image);
        nom.setText(getItem(position).nom);
        desc.setText(getItem(position).desc);
        this.p = new Produit(getItem(position).Image,getItem(position).nom ,getItem(position).prix,getItem(position).desc,getItem(position).quant,getItem(position).marque,getItem(position).id);
        return convertView;
    }


}
