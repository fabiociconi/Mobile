package com.ciconi.labassignment4;

/**
 * Doctor Activity - Doctor Details
 * Author : Fabio A. Ciconi & Rodrigo Geronimo
 * Nov/2017
 */

public class Doctor extends People {


    /**
     * @param id
     * @param firstname
     * @param lastname
     * @param department
     * @param password
     */
    public Doctor(int id, String firstname, String lastname, String department, String password) {
        super(id, firstname, lastname, department, password);
    }

    /**
     * DEFAULT CONSTRUCTOR
     */
    public Doctor() {
        super();
    }

}
