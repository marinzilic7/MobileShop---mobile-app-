package com.example.mobileshop.models;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Mobile {
    public String id;
    public String imeMobitela;
    public String model;
    public String cijena;
    public String slika;


    public Mobile(String id, String imeMobitela, String model, String cijena, String slika) {
        this.id = id;
        this.imeMobitela = imeMobitela;
        this.model = model;
        this.cijena = cijena;
        this.slika = slika;
    }

}