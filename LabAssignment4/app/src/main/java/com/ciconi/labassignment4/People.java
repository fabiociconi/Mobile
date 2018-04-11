package com.ciconi.labassignment4;

/**
 * Abstract Class People - Common FIELDS
 * Author : Fabio A. Ciconi & Rodrigo Geronimo
 * Nov/2017
 */

public abstract class People {

    private int id;
    private String firstname;
    private String lastname;
    private String department;
    private String password;

    /**
     * Constructor
     *
     * @param id
     * @param firstname
     * @param lastname
     * @param department
     * @param password
     */
    public People(int id, String firstname, String lastname, String department, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.password = password;
    }

    /**
     * Constructor no ID
     *
     * @param firstname
     * @param lastname
     * @param department
     * @param password
     */
    public People(String firstname, String lastname, String department, String password) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.password = password;
    }

    /**
     * DEFAULT CONSTRUCTOR
     */
    public People() {
    }

    /**
     * GET ID
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * SET ID
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * GET FIRST NAME
     *
     * @return
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * SET FIRST NAME
     *
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * GET LAST NAME
     *
     * @return LASTNAME
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * SET LAST NAME
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * GET DEPARTMENT
     *
     * @return DEPT
     */
    public String getDepartment() {
        return department;
    }

    /**
     * SET DEPARTMENT
     *
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * GET PASSWORD
     *
     * @return PASSWORD
     */
    public String getPassword() {
        return password;
    }

    /**
     * SET PASSWORD
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * TO STRING METHOD
     *
     * @return LASTNAME, FIRSTNAME
     */
    @Override
    public String toString() {
        return lastname + ", " + firstname;
    }
}

