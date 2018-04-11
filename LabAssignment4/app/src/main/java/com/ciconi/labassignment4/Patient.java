package com.ciconi.labassignment4;

/**
 * Nurse Activity - Nurse Details
 * Author : Fabio A. Ciconi & Rodrigo Geronimo
 * Nov/2017
 */

public class Patient extends People {

    private int doctorId;
    private int room;

    /**
     * DEFAULT CONSTRUCTOR
     */
    public Patient() { }

    /**
     * @param id
     * @param firstname
     * @param lastname
     * @param department
     * @param password
     * @param doctorId
     * @param room
     */
    public Patient(int id, String firstname, String lastname, String department, String password, int doctorId, int room) {
        super(id, firstname, lastname, department, password);
        this.doctorId = doctorId;
        this.room = room;
    }

    /**
     * CONSTRUCTOR NO ID - TO USE AUTOINCREMENT
     * @param firstname
     * @param lastname
     * @param department
     * @param password
     * @param doctorId
     * @param room
     */
    public Patient(String firstname, String lastname, String department, String password, int doctorId, int room) {
        super(firstname, lastname, department, password);
        this.doctorId = doctorId;
        this.room = room;
    }

    /**
     * GET DOC_ID
     * @return
     */
    public int getDoctorId() {
        return doctorId;
    }

    /**
     * SET DOCID
     * @param doctorId
     */
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * GET ROOM
     * @return
     */
    public int getRoom() { return room;}

    /**
     * SET ROOM
     * @param room
     */
    public void setRoom(int room) {
        this.room = room;
    }
}
