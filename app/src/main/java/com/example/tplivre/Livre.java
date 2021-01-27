package com.example.tplivre;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Livre {
    private  String titre;
    private String auteur;
    private int nbpages;
    private String hall;
    private  int rating;
    private String  id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getNbpages() {
        return nbpages;
    }

    public void setNbpages(int nbpages) {
        this.nbpages = nbpages;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public Livre(String titre, String auteur, int nbpages, String hall, int rating, String id) {
        this.titre = titre;
        this.auteur = auteur;
        this.nbpages = nbpages;
        this.hall = hall;
        this.rating = rating;
        this.id=id;
    }

    public Livre() {
    }
}
