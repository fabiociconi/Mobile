package com.ciconi.labassignment4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * LOGIN Activity - MAIN ACTIVITY - INSERT VALUES INTO THE TABLES
 * Author : Fabio A. Ciconi & Rodrigo Geronimo
 * Nov/2017
 */
public class LoginActivity extends AppCompatActivity {

    private DBAdapter db;
    private EditText user;
    private EditText password;
    private Intent intentValue;

    /**
     * ON CREATE
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();
        setTitle(res.getString(R.string.TitleLogin));
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.txtLogin);
        password = findViewById(R.id.txtPassword);

    }

    /**
     * CLICK TO GENERATE DATABASE
     *
     * @param view
     */
    public void onClickGenerate(View view) {

        //call Dbadapter
        db = new DBAdapter(getApplicationContext());

        //OPEN database
        db.Open();

        //******************************************************
        // Insert Doctors
        //******************************************************
        Doctor doc1 = new Doctor(123, "Fabio", "Ciconi", "123", "123pass");
        Doctor doc2 = new Doctor(456, "Alberto", "Ciconi", "124", "333pass");
        Doctor doc3 = new Doctor(789, "Antonio", "Ciconi", "125", "333pass");

        //call Function
        long doc1_insert = db.CreateDoctor(doc1);
        long doc2_insert = db.CreateDoctor(doc2);
        long doc3_insert = db.CreateDoctor(doc3);

        //******************************************************
        // Insert Nurses
        //******************************************************

        Nurse nurse1 = new Nurse(444, "Miriam", "Ciconi",
                "123", "2312");
        Nurse nurse2 = new Nurse(555, "Aline", "Ciconi",
                "124", "2312");

        //call function
        long nur1_insert = db.CreateNurse(nurse1);
        long nur2_insert = db.CreateNurse(nurse2);


        //******************************************************
        // Insert Patients
        //******************************************************

        Patient patient1 = new Patient("Sasha", "Ciconi", "123",
                "124", 123, 123);

        Patient patient2 = new Patient("Whisky", "Ciconi", "111",
                "124", 123, 123);

        Patient patient3 = new Patient(003, "PATO", "ROUCO", "123",
                "124", 123, 123);

        //call function
        long pat_1 = db.CreatePatient(patient1);
        long pat_2 = db.CreatePatient(patient2);
        long pat_3 = db.CreatePatient(patient3);

        //******************************************************
        // Insert TESTS
        //******************************************************

        //Insert Test patients
        Test_Pat testPat1 = new Test_Pat(001, 001, 444, 0, 0, 34);
        Test_Pat testPat2 = new Test_Pat(002, 002, 555, 10, 10, 38);

        long test_1 = db.CreateTest(testPat1);
        long test_2 = db.CreateTest(testPat2);

        //******************************************************
        // Checking all results -- LOG CAT
        //******************************************************

        //Checking... DOCTORS
        Log.d("Get DOCTORS", "Getting All Doctors");
        List<Doctor> allToDos = db.GetAllDoctors();
        for (Doctor d : allToDos) {
            Log.d("DOCTORS: ", d.toString());
        }

        //Checking... PATIENTS
        Log.d("Get PATIENTS", "Getting All Patients");
        List<Patient> allPatients = db.GetAllPatients();
        for (Patient p : allPatients) {
            Log.d("PATIENTS: ", p.toString());
        }

        //Checking... TESTS
        Log.d("Get PATIENT_TESTS", "Getting All Patients Tests");
        List<Test_Pat> allPatientTest = db.GetAllTests();
        for (Test_Pat p : allPatientTest) {
            Log.d("PATIENT_Test: ", p.toString());
        }

        // Checking... NURSES
        Log.d("Get NURSES", "Getting All Nurses");
        List<Nurse> allNurses = db.GetAllNurses();
        for (Nurse d : allNurses) {
            Log.d("Nurses: ", d.toString());
        }

        // Don't forget to close database connection
        //CLOSE DATABASE CONNECTION
        db.Close();

        //Display Massage
        Toast.makeText(this, "Inserted Doctors, Nurses, Patients and Tests",
                Toast.LENGTH_SHORT).show();
    }

    /**
     * CLEAR USER AND PASSWORD FIELDS
     *
     * @param view
     */
    public void onClickClear(View view) {
        user.setText("");
        password.setText("");
    }

    /**
     * CLIBK LOGIN BUTTON
     *
     * @param view
     */
    public void onClickLogin(View view) {

        int userId;
        String userPassword;

        //VALIDATIONS
        try {
            userId = (Integer.parseInt(user.getText().toString()));
            userPassword = password.getText().toString();
            if (userPassword.isEmpty()) {
                Toast.makeText(this, "Please Insert a Password", Toast.LENGTH_SHORT).show();
                return;
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please Insert a valid User ID", Toast.LENGTH_LONG).show();
            return;
        }


        //call DBAdapter//or use this
        db = new DBAdapter(getApplicationContext());

        //open DB CONNECTION
        db.Open();

        final People doc = db.GetDoctor(userId, userPassword);
        final People nur = db.GetNurse(userId, userPassword);

        //THIS SHOWS THE PROGRESS DIALOG
        //Progress Dialog
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        //NULL FIELDS
        if (doc == null && nur == null) {
            Toast.makeText(this, "No User found", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
            return;

        } else if (doc != null) {

            //DOCTOR IS OK
            //go to doctor page
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            Send(doc);
                        }
                    }, 2000);

        } else {
            new android.os.Handler().postDelayed(new Runnable() {
                public void run() {
                    Send(nur);
                }
            }, 2000);
        }
        //close db connection
        db.Close();
    }

    /**
     * GO TO ANOTHER ACTIVITY
     *
     * @param dc
     * @param emp
     */
    private void Send(People dc) {



        if (dc instanceof Doctor)
            intentValue = new Intent(getApplicationContext(), DoctorActivity.class);
        else if (dc instanceof Nurse) {
            intentValue = new Intent(getApplicationContext(), NurseActivity.class);
        }

        SharedPreferences myPreference =
                getSharedPreferences("MyCustomSharedPreferences", 0);
        SharedPreferences.Editor prefEditor = myPreference.edit();
        prefEditor.putInt("user", dc.getId());
        prefEditor.putString("first", dc.getFirstname());
        prefEditor.putString("last", dc.getLastname());
        prefEditor.putString("dept", dc.getDepartment());
        prefEditor.commit();

        //using put Extra
        //intentValue.putExtra("user", dc.getId());
        //intentValue.putExtra("first", dc.getFirstname());
        //intentValue.putExtra("last", dc.getLastname());
        //intentValue.putExtra("dept", dc.getDepartment());
        startActivity(intentValue);
    }
}


