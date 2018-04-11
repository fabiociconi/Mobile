package com.ciconi.labassignment4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

/**
 * Nurse Activity - Nurse Main Activity
 * Author : Fabio A. Ciconi & Rodrigo Geronimo
 * Nov/2017
 */
public class NurseActivity extends AppCompatActivity {

    private ListView listViewPatients;
    private Intent intentValue;
    private DBAdapter db;
    private TextView stringTextView;
    private int userId;
    private String firstName;
    private String lastName;
    private String dept;


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse);

        //taking info of previews activity
        //intentValue = getIntent();
        //userId      = intentValue.getStringExtra("user");
        //firstName   = intentValue.getStringExtra("first");
        //lastName    = intentValue.getStringExtra("last");
        //dept        = intentValue.getStringExtra("dept");

        SharedPreferences myPref = getSharedPreferences("MyCustomSharedPreferences", MODE_PRIVATE);
        userId = myPref.getInt("user",0);
        firstName = myPref.getString("first","");
        lastName = myPref.getString("last","");
        dept = myPref.getString("dept","");

        setTitle("Welcome Nurse. " + lastName + ", " + firstName);

        listViewPatients = findViewById(R.id.listViewPatients);
        stringTextView = findViewById(R.id.NurseInfo);

        NurseCompleteInfo(firstName, lastName, dept);
        MyPatients(dept);

        listViewPatients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg) {

                Patient PatientInfo = (Patient) listViewPatients.getItemAtPosition(position);

                SendPatientDetails(PatientInfo);
            }
        });
    }

    /**
     * @param first
     * @param Last
     * @param dept
     */
    private void NurseCompleteInfo(String first, String Last, String dept) {

        db = new DBAdapter(getApplicationContext());
        db.Open();

        stringTextView.setText((Html.fromHtml
                ("<h1 style= text-align:center' align = 'center' >INFO</h1>" +
                        "<h2>Nurse: " + Last + ", " + first + "</h2>" +
                        "Department : " + dept
                        + "<br>Number of Patients: " + db.GetAllPatients(dept).size())));
        db.Close();
    }

    /**
     * @param depart
     */
    //ver patients list//
    private void MyPatients(String depart) {
        //call DBAdapter//or use this
        db = new DBAdapter(getApplicationContext());

        db.Open();

        // Patient pp = new Patient();
        List<Patient> allPatients = db.GetAllPatients(depart);
        ArrayAdapter<Patient> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, allPatients);

        // Assign adapter to ListView
        listViewPatients.setAdapter(adapter);
    }

    /**
     * @param PatientInfo
     */
    private void SendPatientDetails(Patient PatientInfo) {

        intentValue = new Intent(getApplicationContext(), PatientActivity.class);
        intentValue.putExtra("userId", PatientInfo.getId());
        intentValue.putExtra("docId", PatientInfo.getDoctorId());
        intentValue.putExtra("nurseId",userId );
        intentValue.putExtra("vision","nurse");
        startActivity(intentValue);
    }

    /**
     * @param view
     */
    public void SendNewPatient(View view) {
        intentValue = new Intent(this, PatientDetailActivity.class);
        startActivity(intentValue);
    }
}
