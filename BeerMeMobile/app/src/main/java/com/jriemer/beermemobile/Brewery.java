package com.jriemer.beermemobile;

public class Brewery {

    private String brewery_name;
    private String brewery_logo;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String url;


    public Brewery(String brewery_name, String brewery_logo, String address, String city, String state, String zip, String phone, String url) {
        this.brewery_name = brewery_name;
        this.brewery_logo = brewery_logo;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.url = url;
    }

    public String getBrewery_name() {
        return brewery_name;
    }

    public void setBrewery_name(String brewery_name) {
        this.brewery_name = brewery_name;
    }

    public String getBrewery_logo() {
        return brewery_logo;
    }

    public void setBrewery_logo(String brewery_logo) {
        this.brewery_logo = brewery_logo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
