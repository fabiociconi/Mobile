package com.ciconi.labassignment4;

/**
 * Test Patient Class - Test Details
 * Author : Fabio A. Ciconi & Rodrigo Geronimo
 * Nov/2017
 */

class Test_Pat {

    private int testId;
    private int patientId;
    private int nurseId;
    private int BPL;
    private int BPH;
    private int temperature;

    /**
     * CONSTRUCTOR
     *
     * @param testId
     * @param patientId
     * @param nurseId
     * @param BPL
     * @param BPH
     * @param temperature
     */
    public Test_Pat(int testId, int patientId, int nurseId, int BPL, int BPH, int temperature) {
        this.testId = testId;
        this.patientId = patientId;
        this.nurseId = nurseId;
        this.BPL = BPL;
        this.BPH = BPH;
        this.temperature = temperature;
    }

    public Test_Pat(int patientId, int nurseId, int BPL, int BPH, int temperature) {
        this.patientId = patientId;
        this.nurseId = nurseId;
        this.BPL = BPL;
        this.BPH = BPH;
        this.temperature = temperature;
    }

    /**
     * DEFAULT CONSTRUCTOR
     */
    public Test_Pat() {

    }

    /**
     * GET TEST ID
     *
     * @return ID
     */
    public int getTestId() {
        return testId;
    }

    /**
     * SET TEST ID
     *
     * @param testId
     */
    public void setTestId(int testId) {
        this.testId = testId;
    }

    /**
     * GET PATIENT ID
     *
     * @return PATIENT ID
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * SET PATIENT ID
     *
     * @param patientId
     */
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    /**
     * GET NURSE ID
     *
     * @return NURSE ID
     */
    public int getNurseId() {
        return nurseId;
    }

    /**
     * SET NURSE ID
     *
     * @param nurseId
     */
    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    /**
     * GET BPL
     *
     * @return BPL
     */
    public int getBPL() {
        return BPL;
    }

    /**
     * SET BPL
     *
     * @param BPL
     */
    public void setBPL(int BPL) {
        this.BPL = BPL;
    }

    /**
     * GET BPH
     *
     * @return
     */
    public int getBPH() {
        return BPH;
    }

    /**
     * SET BPH
     *
     * @param BPH
     */
    public void setBPH(int BPH) {
        this.BPH = BPH;
    }

    /**
     * GET TEMPERATURE
     *
     * @return TEMPERATURE
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * SET TEMPERATURE
     *
     * @param temperature
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "TestId = " + testId;
    }
}
