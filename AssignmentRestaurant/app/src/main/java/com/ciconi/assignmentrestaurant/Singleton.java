package com.ciconi.assignmentrestaurant;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZeusAC on 2017-09-25.
 */

class Singleton extends OrderDetail {
    /**
     *
     */

    private static  Singleton ourInstance = null;
    private  List<OrderDetail> Orders=null;
    private  List<String> foods=null;
    private String mString;


    /**
     *
     */
    private Singleton() {
        Orders = new ArrayList<>();
        foods = new ArrayList<>();
        //Test
       // mString = "Hello";
        //Test

    }

    public List<String> getFoods() {
        return foods;
    }

    void addFoods(String food){
    foods.add(food);
    }
    /**
     *
     * @return
     */
    public static Singleton getInstance() {

        if (ourInstance == null)
        {
            ourInstance = new Singleton();
        }

        return ourInstance;
    }

    /**
     *
     * @param c
     */
    public void addCusines(String c){

        OrderDetail ds = new OrderDetail();
        setCuisine(c);

        Orders.add(ds);

    }

    /**
     *
     * @param c
     */
    public void addRestaurant(String c){
        OrderDetail ds = new OrderDetail();

        setRestaurant(c);
        Orders.add(ds);

    }

    /**
     *
     * @param c
     */
    public void addDishes(List<String> c){
        OrderDetail ds = new OrderDetail();
        setDishes(c);
        Orders.add(ds);
    }

    /**
     *
     * @return
     */
    public  List<OrderDetail> getAllCourses() {

        for (OrderDetail s : Orders)
        {
            Log.d("My array list content: ", s.toString());
        }
      return  Orders;
    }

    /**
     *
     * @return
     */
    public String getString(){
            return this.mString;
        }

    /**
     *
     * @param value
     */
    public void setString(String value){
            mString = value;
        }
}
