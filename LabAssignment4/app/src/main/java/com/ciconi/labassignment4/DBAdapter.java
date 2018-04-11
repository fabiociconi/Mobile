package com.ciconi.labassignment4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * DBAdapter Activity - Managed Database
 * Author : Fabio A. Ciconi & Rodrigo Geronimo
 * Nov/2017
 */

public class DBAdapter {

    // Database Name
    private static final String DATABASE_NAME = "Hospital_DB";

    // Logcat tag
    private static final String LOG = "DBAdapter";

    //DataBase version
    private static final int DATABASE_VERSION = 20;

    // Table Names
    private static final String TABLE_PATIENT = "patient_tbl";
    private static final String TABLE_TEST = "test_tbl";
    private static final String TABLE_NURSE = "nurse_tbl";
    private static final String TABLE_DOCTOR = "doctor_tbl";

    // Common column names
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String DEPT = "department";
    private static final String PASSWORD = "password";

    //TABLE_PATIENT
    private static final String KEY_PATIENT_ID = "patientId";
    private static final String ROOM = "room";

    //TABLE_TEST
    private static final String KEY_TEST_ID = "testId";
    private static final String BPL = "BPL";
    private static final String BPH = "BPH";
    private static final String TEMPERATURE = "temperature";

    //TABLE_NURSE
    private static final String KEY_NURSE_ID = "nurseId";

    //TABLE_DOCTOR
    private static final String KEY_DOCTOR_ID = "doctorId";

    //TABLE_Doctor Schema
    private static final String CREATE_TABLE_Doctor = "CREATE TABLE "
            + TABLE_DOCTOR +
            "(" + KEY_DOCTOR_ID + " INTEGER PRIMARY KEY,"
            + FIRST_NAME + " TEXT,"
            + LAST_NAME + " TEXT,"
            + DEPT + " TEXT,"
            + PASSWORD + " TEXT" + ")";

    //TABLE_Nurse Schema
    private static final String CREATE_TABLE_Nurse = "CREATE TABLE "
            + TABLE_NURSE +
            "(" + KEY_NURSE_ID + " INTEGER PRIMARY KEY,"
            + FIRST_NAME + " TEXT,"
            + LAST_NAME + " TEXT,"
            + DEPT + " TEXT,"
            + PASSWORD + " TEXT" + ")";

    //TABLE_PATIENT Schema
    private static final String CREATE_TABLE_PATIENT = "CREATE TABLE "
            + TABLE_PATIENT +
            "(" + KEY_PATIENT_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT,"
            + FIRST_NAME + " TEXT,"
            + LAST_NAME + " TEXT,"
            + DEPT + " INTEGER,"
            + KEY_DOCTOR_ID + " INTERGER,"
            + ROOM + " INTERGER" + ")";

    //TABLE_Test Schema
    private static final String CREATE_TABLE_Test = "CREATE TABLE "
            + TABLE_TEST +
            "(" + KEY_TEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_PATIENT_ID + " INTEGER,"
            + KEY_NURSE_ID + " INTEGER,"
            + BPL + " INTEGER,"
            + BPH + " INTEGER,"
            + TEMPERATURE + " INTEGER" + ")";

    //DataBase Helper
    private static DataBaseHelper DBHelper;

    //Context
    private final Context context;

    //SQL Lite
    private SQLiteDatabase db;

    //DBHelper Instance
    public DBAdapter(Context ctx) {

        this.context = ctx;
        DBHelper = new DataBaseHelper(context);

    }

    //Open Database
    public DBAdapter Open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //Close Database
    public void Close() throws SQLException {
        DBHelper.close();

    }

    //******************************************************** \\
    //  INSERTS                                                ||
    //*********************************************************//

    /**
     * Create Doctor
     */
    public long CreateDoctor(Doctor doctor) {

        ContentValues values = new ContentValues();

        values.put(KEY_DOCTOR_ID, doctor.getId());
        values.put(FIRST_NAME, doctor.getFirstname());
        values.put(LAST_NAME, doctor.getLastname());
        values.put(DEPT, doctor.getDepartment());
        values.put(PASSWORD, doctor.getPassword());

        return db.insert(TABLE_DOCTOR, null, values);

    }

    /**
     * Create Nurse
     */
    public long CreateNurse(Nurse nurse) {

        ContentValues values = new ContentValues();

        values.put(KEY_NURSE_ID, nurse.getId());
        values.put(FIRST_NAME, nurse.getFirstname());
        values.put(LAST_NAME, nurse.getLastname());
        values.put(DEPT, nurse.getDepartment());
        values.put(PASSWORD, nurse.getPassword());

        return db.insert(TABLE_NURSE, null, values);
    }

    /**
     * Create Patient
     */
    public long CreatePatient(Patient patient) {

        ContentValues values = new ContentValues();

        //values.put(KEY_PATIENT_ID, patient.getId());
        values.put(FIRST_NAME, patient.getFirstname());
        values.put(LAST_NAME, patient.getLastname());
        values.put(DEPT, patient.getDepartment());
        values.put(KEY_DOCTOR_ID, patient.getDoctorId());
        values.put(ROOM, patient.getRoom());

        return db.insert(TABLE_PATIENT, null, values);

    }

    /**
     * Create Test_Pat
     */
    public long CreateTest(Test_Pat test_pat) {

        ContentValues values = new ContentValues();

        //values.put(KEY_TEST_ID, test_pat.getTestId());
        values.put(KEY_PATIENT_ID, test_pat.getPatientId());
        values.put(KEY_NURSE_ID, test_pat.getNurseId());
        values.put(BPL, test_pat.getBPL());
        values.put(BPH, test_pat.getBPH());
        values.put(TEMPERATURE, test_pat.getTemperature());

        return db.insert(TABLE_TEST, null, values);
    }

    //******************************************************** \\
    //  Select - one row                                       ||
    //*********************************************************//

    /**
     * GET A DOCTOR by ID and Password
     *
     * @param id
     * @param pass
     * @return
     */
    public People GetDoctor(int id, String pass) {

        //SQLiteDatabase db = this.getReadableDatabase();

        String selectQueryDoc = "SELECT  * FROM " + TABLE_DOCTOR + " WHERE "
                + KEY_DOCTOR_ID + " = " + id + " and " + PASSWORD + " = '" + pass + "' ;";


        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQueryDoc, null);


        //verifica se cursor esta fazio/// tem outros jeitos de fazer tb
        // if (c != null) {
        if (c.moveToFirst()) {

            People td = new Doctor();
            td.setId(c.getInt(c.getColumnIndex(KEY_DOCTOR_ID)));
            td.setFirstname((c.getString(c.getColumnIndex(FIRST_NAME))));
            td.setLastname(c.getString(c.getColumnIndex(LAST_NAME)));
            td.setDepartment(c.getString(c.getColumnIndex(DEPT)));
            td.setPassword(c.getString(c.getColumnIndex(PASSWORD)));

            return td;
        }
        return null;
    }

    /**
     * Get Doctor by ID
     *
     * @param id
     * @return
     */
    public People GetDoctor(int id) {
        String selectQueryDoc = "SELECT  * FROM " + TABLE_DOCTOR + " WHERE "
                + KEY_DOCTOR_ID + " = " + id + " ;";

        Cursor c = db.rawQuery(selectQueryDoc, null);

        if (c.moveToFirst()) {

            People td = new Doctor();
            td.setId(c.getInt(c.getColumnIndex(KEY_DOCTOR_ID)));
            td.setFirstname((c.getString(c.getColumnIndex(FIRST_NAME))));
            td.setLastname(c.getString(c.getColumnIndex(LAST_NAME)));
            td.setDepartment(c.getString(c.getColumnIndex(DEPT)));
            td.setPassword(c.getString(c.getColumnIndex(PASSWORD)));
            return td;
        }
        return null;
    }

    /**
     * GET A NURSE by ID and Password
     *
     * @param id
     * @param pass
     * @return
     */
    public People GetNurse(int id, String pass) {
        String selectQueryNur = "SELECT  * FROM " + TABLE_NURSE + " WHERE "
                + KEY_NURSE_ID + " = " + id + " and " + PASSWORD + " = '" + pass + "' ;";

        Cursor c = db.rawQuery(selectQueryNur, null);
        if (c.moveToFirst()) {

            People td = new Nurse();

            td.setId(c.getInt(c.getColumnIndex(KEY_NURSE_ID)));
            td.setFirstname((c.getString(c.getColumnIndex(FIRST_NAME))));
            td.setLastname(c.getString(c.getColumnIndex(LAST_NAME)));
            td.setDepartment(c.getString(c.getColumnIndex(DEPT)));
            td.setPassword(c.getString(c.getColumnIndex(PASSWORD)));
            return td;
        }
        return null;
    }

    /**
     * GET A NURSE by ID
     *
     * @param id
     * @return nurse
     */
    public Nurse GetNurse(int id) {
        String selectQueryNur = "SELECT  * FROM " + TABLE_NURSE + " WHERE "
                + KEY_NURSE_ID + " = " + id + " ;";

        Cursor c = db.rawQuery(selectQueryNur, null);
        if (c.moveToFirst()) {

            Nurse td = new Nurse();

            td.setId(c.getInt(c.getColumnIndex(KEY_NURSE_ID)));
            td.setFirstname((c.getString(c.getColumnIndex(FIRST_NAME))));
            td.setLastname(c.getString(c.getColumnIndex(LAST_NAME)));
            td.setDepartment(c.getString(c.getColumnIndex(DEPT)));
            td.setPassword(c.getString(c.getColumnIndex(PASSWORD)));
            return td;
        }
        return null;
    }

    /**
     * GET PATIENT by ID
     *
     * @param id
     * @return
     */
    public Patient GetPatient(int id) {
        //List<Patient> pat = new ArrayList<>();
        String selectQuery = "Select * from " + TABLE_PATIENT + " WHERE "
                + KEY_PATIENT_ID + " = " + id + " ;";


        Cursor cu = db.rawQuery(selectQuery, null);

        if (cu.moveToFirst()) {

            Patient patient = new Patient();
            patient.setId(cu.getInt((cu.getColumnIndex(KEY_PATIENT_ID))));
            patient.setFirstname((cu.getString(cu.getColumnIndex(FIRST_NAME))));
            patient.setLastname(cu.getString(cu.getColumnIndex(LAST_NAME)));
            patient.setDepartment(cu.getString(cu.getColumnIndex(DEPT)));
            patient.setDoctorId(cu.getInt(cu.getColumnIndex(KEY_DOCTOR_ID)));
            patient.setRoom(cu.getInt(cu.getColumnIndex(ROOM)));
            return patient;
        }
        return null;
    }

    //******************************************************** \\
    //  Select - Cursors - WHERE                               ||
    //*********************************************************//

    // one result -- return list
    /**
     * GET ALL PATIENTS BY DOCTOR ID
     *
     * @param docId
     * @return
     */
    public List<Patient> GetAllPatients(int docId) {
        List<Patient> array = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_PATIENT + " WHERE "
                + KEY_DOCTOR_ID + " = " + docId + " ;";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            do {
                Patient td = new Patient();
                td.setId(cursor.getInt(cursor.getColumnIndex(KEY_PATIENT_ID)));
                td.setFirstname((cursor.getString(cursor.getColumnIndex(FIRST_NAME))));
                td.setLastname(cursor.getString(cursor.getColumnIndex(LAST_NAME)));
                td.setDepartment(cursor.getString(cursor.getColumnIndex(DEPT)));
                td.setDoctorId(cursor.getInt(cursor.getColumnIndex(KEY_DOCTOR_ID)));
                td.setRoom(cursor.getInt(cursor.getColumnIndex(ROOM)));
                array.add(td);
            } while (cursor.moveToNext());
        }
        return array;

    }

    /**
     * GET ALL PATIENTS BY DEPT
     *
     * @param dept
     * @return
     */
    public List<Patient> GetAllPatients(String dept) {
        List<Patient> pat = new ArrayList<>();
        String selectQuery = "Select * from " + TABLE_PATIENT + " WHERE "
                + DEPT + " = '" + dept + "' ;";


        Cursor cu = db.rawQuery(selectQuery, null);
        if (cu.moveToFirst()) {
            do {
                Patient patient = new Patient();
                patient.setId(cu.getInt((cu.getColumnIndex(KEY_PATIENT_ID))));
                patient.setFirstname((cu.getString(cu.getColumnIndex(FIRST_NAME))));
                patient.setLastname(cu.getString(cu.getColumnIndex(LAST_NAME)));
                patient.setDepartment(cu.getString(cu.getColumnIndex(DEPT)));
                patient.setDoctorId(cu.getInt(cu.getColumnIndex(KEY_DOCTOR_ID)));
                patient.setRoom(cu.getInt(cu.getColumnIndex(ROOM)));
                pat.add(patient);
            } while (cu.moveToNext());

        }
        return pat;
    }

    /**
     * GET ALL NURSES BY DEPT
     *
     * @param dept
     * @return
     */
    public List<Nurse> GetAllNurses(String dept) {
        List<Nurse> nur = new ArrayList<>();
        String selectQuery = "Select * from " + TABLE_NURSE + " WHERE "
                + DEPT + " = '" + dept + "' ;";

        Cursor cu = db.rawQuery(selectQuery, null);
        if (cu.moveToFirst()) {
            do {
                Nurse nurse = new Nurse();
                nurse.setId(cu.getInt((cu.getColumnIndex(KEY_NURSE_ID))));
                nurse.setFirstname((cu.getString(cu.getColumnIndex(FIRST_NAME))));
                nurse.setLastname(cu.getString(cu.getColumnIndex(LAST_NAME)));
                nurse.setDepartment(cu.getString(cu.getColumnIndex(DEPT)));
                nurse.setPassword(cu.getString(cu.getColumnIndex(PASSWORD)));
                nur.add(nurse);
            } while (cu.moveToNext());

        }
        return nur;
    }

    // one result -- return list
    /**
     * GET ALL TESTS BY PATIENT ID
     *
     * @param idPatient
     * @return
     */
    public List<Test_Pat> GetAllTests(int idPatient) {
        List<Test_Pat> array = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_TEST + " WHERE "
                + KEY_PATIENT_ID + " = " + idPatient + " ;";
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Test_Pat td = new Test_Pat();
                td.setTestId(cursor.getInt(cursor.getColumnIndex(KEY_TEST_ID)));
                td.setNurseId((cursor.getInt(cursor.getColumnIndex(KEY_NURSE_ID))));
                td.setBPH(cursor.getInt(cursor.getColumnIndex(BPH)));
                td.setBPL(cursor.getInt(cursor.getColumnIndex(BPL)));
                td.setTemperature(cursor.getInt(cursor.getColumnIndex(TEMPERATURE)));
                td.setPatientId(cursor.getInt(cursor.getColumnIndex(KEY_PATIENT_ID)));
                array.add(td);
            } while (cursor.moveToNext());
        }
        return array;
    }

    // one result -- return list
    /**
     * Get Doctor by ID and Return List
     *
     * @param docId
     * @return
     */
    public List<Doctor> GetAllDoctors(int docId) {
        List<Doctor> doc = new ArrayList<Doctor>();
        String selectQuery = "Select * from " + TABLE_DOCTOR + " WHERE "
                + KEY_DOCTOR_ID + " = " + docId + " ;";
        Cursor cu = db.rawQuery(selectQuery, null);
        if (cu.moveToFirst()) {
            do {
                Doctor doctor = new Doctor();
                doctor.setId(cu.getInt((cu.getColumnIndex(KEY_DOCTOR_ID))));
                doctor.setFirstname((cu.getString(cu.getColumnIndex(FIRST_NAME))));
                doctor.setLastname(cu.getString(cu.getColumnIndex(LAST_NAME)));
                doctor.setDepartment(cu.getString(cu.getColumnIndex(DEPT)));
                doctor.setPassword(cu.getString(cu.getColumnIndex(PASSWORD)));
                doc.add(doctor);
            } while (cu.moveToNext());

        }
        return doc;
    }

    //Example To Study

    /**
     * ANOTHER WAY TO DO A CURSOR
     *
     * @param user
     * @return
     * @throws SQLException
     */
    public Cursor GetDocInfo(long user) throws SQLException {

        //SQLiteDatabase db = this.getReadableDatabase();

        Cursor mcu = db.query(true, TABLE_DOCTOR,
                new String[]
                        {KEY_DOCTOR_ID,
                                PASSWORD,
                                FIRST_NAME,
                                LAST_NAME,
                                DEPT},
                KEY_DOCTOR_ID + "=" + user,
                null,
                null,
                null,
                null,
                null);

        if (mcu != null) {
            mcu.moveToFirst();
        }
        return mcu;
    }
    //

    //******************************************************** \\
    //  Select - Cursors - GET ALL TABLE                       ||
    //*********************************************************//

    /**
     * ALL DOCTORS
     *
     * @return
     */
    public List<Doctor> GetAllDoctors() {
        List<Doctor> doc = new ArrayList<Doctor>();
        String selectQuery = "Select * from " + TABLE_DOCTOR;
        //LOG.e(Log,SelectQuery);
        //SQLiteDatabase db = this.getReadableDatabase();
        Cursor cu = db.rawQuery(selectQuery, null);
        if (cu.moveToFirst()) {
            do {
                Doctor doctor = new Doctor();
                doctor.setId(cu.getInt((cu.getColumnIndex(KEY_DOCTOR_ID))));
                doctor.setFirstname((cu.getString(cu.getColumnIndex(FIRST_NAME))));
                doctor.setLastname(cu.getString(cu.getColumnIndex(LAST_NAME)));
                doctor.setDepartment(cu.getString(cu.getColumnIndex(DEPT)));
                doctor.setPassword(cu.getString(cu.getColumnIndex(PASSWORD)));
                doc.add(doctor);
            } while (cu.moveToNext());

        }
        return doc;
    }

    /**
     * ALL NURSES
     *
     * @return
     */
    public List<Nurse> GetAllNurses() {
        List<Nurse> nur = new ArrayList<>();
        String selectQuery = "Select * from " + TABLE_NURSE;

        Cursor cu = db.rawQuery(selectQuery, null);
        if (cu.moveToFirst()) {
            do {
                Nurse nurse = new Nurse();
                nurse.setId(cu.getInt((cu.getColumnIndex(KEY_NURSE_ID))));
                nurse.setFirstname((cu.getString(cu.getColumnIndex(FIRST_NAME))));
                nurse.setLastname(cu.getString(cu.getColumnIndex(LAST_NAME)));
                nurse.setDepartment(cu.getString(cu.getColumnIndex(DEPT)));
                nurse.setPassword(cu.getString(cu.getColumnIndex(PASSWORD)));
                nur.add(nurse);
            } while (cu.moveToNext());

        }
        return nur;
    }

    /**
     * ALL PATIENTS
     *
     * @return
     */
    public List<Patient> GetAllPatients() {
        List<Patient> pat = new ArrayList<>();
        String selectQuery = "Select * from " + TABLE_PATIENT;

        Cursor cu = db.rawQuery(selectQuery, null);
        if (cu.moveToFirst()) {
            do {
                Patient patient = new Patient();
                patient.setId(cu.getInt((cu.getColumnIndex(KEY_PATIENT_ID))));
                patient.setFirstname((cu.getString(cu.getColumnIndex(FIRST_NAME))));
                patient.setLastname(cu.getString(cu.getColumnIndex(LAST_NAME)));
                patient.setDepartment(cu.getString(cu.getColumnIndex(DEPT)));
                patient.setDoctorId(cu.getInt(cu.getColumnIndex(KEY_DOCTOR_ID)));
                patient.setRoom(cu.getInt(cu.getColumnIndex(ROOM)));
                pat.add(patient);
            } while (cu.moveToNext());

        }
        return pat;
    }

    /**
     * ALL TESTS
     *
     * @return
     */
    public List<Test_Pat> GetAllTests() {

        List<Test_Pat> array = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_TEST;
        Cursor cu = db.rawQuery(selectQuery, null);
        if (cu.moveToFirst()) {
            do {


                Test_Pat td = new Test_Pat();
                td.setTestId(cu.getInt(cu.getColumnIndex(KEY_TEST_ID)));
                td.setPatientId(cu.getInt(cu.getColumnIndex(KEY_PATIENT_ID)));
                td.setNurseId((cu.getInt(cu.getColumnIndex(KEY_NURSE_ID))));
                td.setBPL(cu.getInt(cu.getColumnIndex(BPL)));
                td.setBPH(cu.getInt(cu.getColumnIndex(BPH)));
                td.setTemperature(cu.getInt(cu.getColumnIndex(TEMPERATURE)));

                array.add(td);
            } while (cu.moveToNext());
        }
        return array;
    }

    //******************************************************//
    // Updates                                             *//
    //******************************************************//
    public boolean UpdateNurse(int id, String last, String first, String dept)
    {
        ContentValues con = new ContentValues();
        con.put(FIRST_NAME, first);
        con.put(LAST_NAME, last );
        con.put(DEPT, dept);
        db.update(TABLE_NURSE, con, KEY_NURSE_ID + "=" + id,null);
        return true;
    }
    public boolean UpdatePatient(int id, String last, String first, String dept,int room, int docId)
    {
        ContentValues con = new ContentValues();
        con.put(FIRST_NAME, first);
        con.put(LAST_NAME, last );
        con.put(DEPT, dept);
        con.put(ROOM,room);
        con.put(KEY_DOCTOR_ID,docId);
        db.update(TABLE_PATIENT, con, KEY_PATIENT_ID + "=" + id,null);
        return true;
    }

    //******************************************************//
    // Deletes                                             *//
    //******************************************************//
    public boolean DeleteTestPatient(int idPatient) {
        return false;
    }

    /**
     * DATABASEHELPER CLASS
     */
    private static class DataBaseHelper extends SQLiteOpenHelper {

        /**
         * Take the COntext and Database
         *
         * @param context
         */
        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /**
         * CREATE TABLES
         *
         * @param db
         */
        @Override
        public void onCreate(SQLiteDatabase db) {

            // creating required tables
            db.execSQL(CREATE_TABLE_Doctor);
            db.execSQL(CREATE_TABLE_Nurse);
            db.execSQL(CREATE_TABLE_PATIENT);
            db.execSQL(CREATE_TABLE_Test);
        }

        /**
         * DATABASE VERSION
         * DROP EXISTENT TABLES
         *
         * @param db
         * @param oldVersion
         * @param newVersion
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w(LOG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");

            // on upgrade drop older tables
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NURSE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST);

            // create new tables
            onCreate(db);
        }
    }

}

