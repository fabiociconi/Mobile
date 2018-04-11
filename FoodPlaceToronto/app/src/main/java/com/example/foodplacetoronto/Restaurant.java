package com.example.foodplacetoronto;

/**
 * Restaurant Activity
 * Author : Rodrigo Geronimo & Fabio A. Ciconi
 * Dez/2017
 */

public class Restaurant {

    private int id;
    private String name;
    private String address;
    private double lat;
    private double lng;
    private String cuisine;

    /**
     * @param id
     * @param name
     * @param address
     * @param lat
     * @param lng
     * @param cuisine
     */

    public Restaurant(int id, String name, String address, double lat, double lng, String cuisine) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.cuisine = cuisine;
    }


    public Restaurant() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
}
