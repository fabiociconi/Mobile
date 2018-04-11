package com.example.foodplacetoronto;

/**
 * Cuisine Activity
 * Author : Rodrigo Geronimo & Fabio A. Ciconi
 * Dez/2017
 */

public class Cuisine {

    private int id;
    private String name;
    private String description;

    /**
     * @param id
     * @param name
     * @param description
     */

    public Cuisine(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Cuisine() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Cuisines{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
