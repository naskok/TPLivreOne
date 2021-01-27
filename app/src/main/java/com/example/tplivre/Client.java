package com.example.tplivre;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Client {
    private  String Nom;
    private  String Prenom;
    private String id;

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Client(String nom, String prenom, String id) {
        Nom = nom;
        Prenom = prenom;
        this.id = id;
    }

    public Client() {
    }
}
