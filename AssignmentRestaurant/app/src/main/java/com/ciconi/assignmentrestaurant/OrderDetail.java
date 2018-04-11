package com.ciconi.assignmentrestaurant;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZeusAC on 2017-09-28.
 */

class OrderDetail implements Serializable {

    /**
     *
     */
    private String restaurant;
    private String cuisine;
    private List<String> dishes;

    /**
     * Constructor
     */
    OrderDetail(){
    super();
    }

    /**
     * Constructor
     * @param restaurant
     * @param cuisine
     * @param dishes
     */
    public OrderDetail(String restaurant, String cuisine, List<String> dishes) {
        this.restaurant = restaurant;
        this.cuisine = cuisine;
        this.dishes = dishes;
    }

    /**
     *
     * @return
     */
    public String getRestaurant() {
        return restaurant;
    }

    /**
     *
     * @param restaurant
     */
    void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    /**
     *
     * @return
     */
    String getCuisine() {
        return cuisine;
    }

    /**
     *
     * @param cuisine
     */
    void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    /**
     *
     * @return
     */
    public List<String> getDishes() {
        return dishes;
    }

    /**
     *
     * @param dishes
     */
    void setDishes(List<String> dishes) {
        this.dishes = dishes;
    }


    /**
     *
     * @return
     */
    public String toString() {
        return  "Cuisine :  " + getCuisine() +
                "\nRestaurant : " + restaurant+
                "\nDishes: " + dishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetail)) return false;

        OrderDetail that = (OrderDetail) o;

        if (restaurant != null ? !restaurant.equals(that.restaurant) : that.restaurant != null)
            return false;
        if (cuisine != null ? !cuisine.equals(that.cuisine) : that.cuisine != null) return false;
        return dishes != null ? dishes.equals(that.dishes) : that.dishes == null;

    }

    @Override
    public int hashCode() {
        int result = restaurant != null ? restaurant.hashCode() : 0;
        result = 31 * result + (cuisine != null ? cuisine.hashCode() : 0);
        result = 31 * result + (dishes != null ? dishes.hashCode() : 0);
        return result;
    }
}

