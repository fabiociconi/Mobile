package com.ciconi.labassignment4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

/**
 * Patient DETAILS Activity - show and edit Patient
 * Author : Fabio A. Ciconi & Rodrigo Geronimo
 * Nov/2017
 */
public class PatientDetailActivity extends AppCompatActivity {

    private Intent intentValue;
    private DBAdapter db;
    private int userId;
    private EditText stringEditTextFirstName;
    private EditText stringEditTextLastName;
    private EditText stringEditTextDept;
    private EditText intEditTextRoom;
    private Spinner spinner;
    private int newDocId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);
        setTitle("Patient Detail");

        intentValue = getIntent();
        userId = intentValue.getIntExtra("userId", 0);


        db = new DBAdapter(this);
        db.Open();

        stringEditTextFirstName = findViewById(R.id.PatientName);
        stringEditTextLastName = findViewById(R.id.PatientLastName);
        stringEditTextDept = findViewById(R.id.depart);
        intEditTextRoom = findViewById(R.id.room);
        spinner = findViewById(R.id.doctors);


        Patient pat = db.GetPatient(userId);

        stringEditTextFirstName.setText(pat.getFirstname());
        stringEditTextLastName.setText(pat.getLastname());
        stringEditTextDept.setText(pat.getDepartment());
        intEditTextRoom.setText(String.valueOf(pat.getRoom()));


        List<Doctor> listDoc = db.GetAllDoctors(pat.getDoctorId());
        ArrayAdapter<Doctor> dataAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listDoc);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                People dc = (Doctor) spinner.getItemAtPosition(position);
                newDocId = dc.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        db.Close();
    }

    /**
     * Edit Patient - go to enable form
     *
     * @param view
     */
    public void EditPatient(View view) {
        EnableForm();
    }

    /**
     * Save changes
     *
     * @param view
     */
    public void savePatient(View view) {

        stringEditTextFirstName = findViewById(R.id.PatientName);
        stringEditTextLastName = findViewById(R.id.PatientLastName);
        stringEditTextDept = findViewById(R.id.depart);
        intEditTextRoom = findViewById(R.id.room);
        spinner = findViewById(R.id.doctors);

        String firstName = String.valueOf(stringEditTextFirstName.getText());
        String lastName = String.valueOf(stringEditTextLastName.getText());
        String dept = String.valueOf(stringEditTextDept.getText());
        int room = (Integer.parseInt(intEditTextRoom.getText().toString()));

        db = new DBAdapter(getApplicationContext());
        db.Open();

        if (userId != 0) {
            db.UpdatePatient(userId, firstName, lastName, dept, room, newDocId);
        } else {
            //deixei o password fixo depois altero
            db.CreatePatient(new Patient(firstName, lastName, dept, "123", room, newDocId));
        }
        db.Close();


        findViewById(R.id.btnEdit).setVisibility(View.VISIBLE);
        findViewById(R.id.btnSave).setVisibility(View.INVISIBLE);

        findViewById(R.id.PatientName).setEnabled(false);
        findViewById(R.id.PatientLastName).setEnabled(false);
        findViewById(R.id.depart).setEnabled(false);
        findViewById(R.id.room).setEnabled(false);
        findViewById(R.id.doctors).setEnabled(false);
    }

    /**
     * Enable all fields of the form
     */
    private void EnableForm() {
        findViewById(R.id.btnEdit).setVisibility(View.INVISIBLE);
        findViewById(R.id.btnSave).setVisibility(View.VISIBLE);

        findViewById(R.id.PatientName).setEnabled(true);
        findViewById(R.id.PatientLastName).setEnabled(true);
        findViewById(R.id.depart).setEnabled(true);
        findViewById(R.id.room).setEnabled(true);
        findViewById(R.id.doctors).setEnabled(true);

        db = new DBAdapter(this);
        db.Open();

        List<Doctor> listDoc = db.GetAllDoctors();
        ArrayAdapter<Doctor> dataAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listDoc);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        db.Close();
    }
}
