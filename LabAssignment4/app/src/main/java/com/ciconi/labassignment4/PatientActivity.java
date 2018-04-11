package com.ciconi.labassignment4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * Patients Activity - Main Screen
 * Author : Fabio A. Ciconi & Rodrigo Geronimo
 * Nov/2017
 */
public class PatientActivity extends AppCompatActivity {

    private Intent intentValue;
    private DBAdapter db;
    private TextView stringTextView;
    private TextView stringPatExams;
    private int userId;
    private int nurseId;
    private int docId;
    private String visionType;
    private Spinner spinner;


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        setTitle("Patient Details");

        intentValue = getIntent();

        userId = intentValue.getIntExtra("userId", 0);
        docId = intentValue.getIntExtra("docId", 0);
        nurseId = intentValue.getIntExtra("nurseId", 0);
        visionType = intentValue.getStringExtra("vision");


        db = new DBAdapter(getApplicationContext());
        db.Open();

        stringTextView = findViewById(R.id.PatientInfo);

        //Patient Details
        Patient pat = db.GetPatient(userId);

        stringTextView.setText((Html.fromHtml
                ("<h1 style= text-align:center' align = 'center' >INFO</h1>" +
                        "<h2>Patient: " + pat.getLastname() + ", " + pat.getFirstname() + "</h2>" +
                        "Department : " + pat.getDepartment()
                        + "<br>Room : " + pat.getRoom())));


        if (visionType.equals("doctor")) {
            findViewById(R.id.editTest).setVisibility(View.INVISIBLE);
            GetTests();

        } else {
            findViewById(R.id.testlist).setVisibility(View.INVISIBLE);
            GetTestNurse();
        }

        db.Close();
    }

    /**
     * Go to Edit Patients Information
     * @param view
     */
    public void EditPatient(View view) {
        intentValue = new Intent(getApplicationContext(), PatientDetailActivity.class);
        intentValue.putExtra("userId", userId);
        startActivity(intentValue);
    }

    /**
     * Get test from patient id
     */
    private void GetTests() {

        db.Open();

        spinner = findViewById(R.id.testlist);

        List<Test_Pat> listTest = db.GetAllTests(userId);

        if (listTest.isEmpty()) {
            stringPatExams.setText((Html.fromHtml("<h2 style= text-align:center' align = 'center' >Test Results</h1>")));
        }
        ArrayAdapter<Test_Pat> dataAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listTest);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Test_Pat tt = (Test_Pat) spinner.getItemAtPosition(position);
                ShowTests(tt);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        db.Close();
    }

    /**
     * Show Test Results - Doctor vision
     * @param tt
     */
    private void ShowTests(Test_Pat tt) {
        stringPatExams = findViewById(R.id.Tests);

        stringPatExams.setText((Html.fromHtml("<h2 style= text-align:center' align = 'center' >Tests</h1>" +
                "<hr>" +
                "BPH : " + tt.getBPH() + "<br>" +
                "BPL : " + tt.getBPL() + "<br>" +
                "Temperature : " + tt.getTemperature() +
                "<hr>")));

    }

    /**
     * Nurse Vision of the Results
     */
    private void GetTestNurse() {

        stringPatExams = findViewById(R.id.Tests);
        stringPatExams.setText((Html.fromHtml("<h2 style= text-align:center' align = 'center' >Test Results</h1>")));

        stringPatExams.setText((Html.fromHtml("<h2 style= text-align:center' align = 'center' >Tests</h1>" +
                "<hr>" +
                "ONLY DOCTORS CAN CONSULT" +
                "<br>" +
                "<br>" +
                "BPH : " + "N/D" + "<br>" +
                "BPL : " + "N/D" + "<br>" +
                "Temperature : " + "N/D" +
                "<hr>")));
    }

    /**
     * Add New Test to the Patient
     * @param view
     */
    public void AddTest(View view) {

        intentValue = new Intent(this, TestDetailActivity.class);
        intentValue.putExtra("userId", userId);
        intentValue.putExtra("nurseId", nurseId);
        startActivity(intentValue);

    }
}
