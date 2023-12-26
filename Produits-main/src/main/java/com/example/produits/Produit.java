package com.example.produits;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Produit implements Parcelable {
    public String  id ;
    int Image ;
    public String nom ;
    public double prix ;
    public String desc ;
    public int quant ;
    public String marque ;


    public Produit(int image, String nom , double prix , String desciption , int quauntité , String marque ,String id){

        this.Image = image ;
        this.nom = nom ;
        this.prix = prix ;
        this.desc = desciption ;
        this.quant = quauntité ;
        this.marque = marque ;
        this.id = id ;

    }


    protected Produit(Parcel in) {
        id = in.readString();
        Image = in.readInt();
        nom = in.readString();
        prix = in.readDouble();
        desc = in.readString();
        quant = in.readInt();
        marque = in.readString();
    }

    // methode statique pour generer les id
    public static String GenId(){
        int n = ThreadLocalRandom.current().nextInt(1, 1000 + 1);
        return "P"+ Integer.toString(n);

    }

    public static final Creator<Produit> CREATOR = new Creator<Produit>() {
        @Override
        public Produit createFromParcel(Parcel in) {
            return new Produit(in);
        }

        @Override
        public Produit[] newArray(int size) {
            return new Produit[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(Image);
        dest.writeString(nom);
        dest.writeDouble(prix);
        dest.writeString(desc);
        dest.writeInt(quant);
        dest.writeString(marque);
    }
}
