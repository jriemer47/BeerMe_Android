package com.jriemer.beermemobile;

public class Beer {

    private String beer_name;
    private String style;
    private String abv;
    private String ibu;
    private String description;
    private String beer_label;


    public Beer(String beer_name, String style, String abv, String ibu, String description, String beer_label) {
        this.beer_name = beer_name;
        this.style = style;
        this.abv = abv;
        this.ibu = ibu;
        this.description = description;
        this.beer_label = beer_label;
    }

    public String getBeer_name() {
        return beer_name;
    }

    public void setBeer_name(String beer_name) {
        this.beer_name = beer_name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeer_label() {
        return beer_label;
    }

    public void setBeer_labell(String beer_label) {
        this.beer_label = beer_label;
    }
}


// for each element in the object, make a constructor for each, and have getters and setters for each
