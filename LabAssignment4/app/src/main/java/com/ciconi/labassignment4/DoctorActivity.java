package com.ciconi.labassignment4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Doctor Activity - shows Menu - Nurse and Patients
 * Author : Fabio A. Ciconi & Rodrigo Geronimo
 * Nov/2017
 */
public class DoctorActivity extends AppCompatActivity {

    private ListView listViewNurses;
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
        setContentView(R.layout.activity_doctor);

        //using Intent
        //intentValue = getIntent();
        //userId = intentValue.getStringExtra("user");
        //firstName = intentValue.getStringExtra("first");
        //lastName = intentValue.getStringExtra("last");
        //dept = intentValue.getStringExtra("dept");

        SharedPreferences myPref = getSharedPreferences("MyCustomSharedPreferences", MODE_PRIVATE);
        userId = myPref.getInt("user", 0);
        firstName = myPref.getString("first", "");
        lastName = myPref.getString("last", "");
        dept = myPref.getString("dept", "");

        setTitle("Welcome Dr. " + lastName + ", " + firstName);

        listViewNurses = findViewById(R.id.listViewNur);
        listViewPatients = findViewById(R.id.listViewPat);

        stringTextView = findViewById(R.id.DocInfo);

        DoctorCompleteInfo(userId, firstName, lastName, dept);
    }

    /**
     * Doc Info
     *
     * @param userid
     * @param first
     * @param Last
     * @param dept
     */
    private void DoctorCompleteInfo(int userid, String first, String Last, String dept) {

        db = new DBAdapter(getApplicationContext());

        db.Open();

        stringTextView.setText((Html.fromHtml("<h1 style= text-align:center' align = 'center' >INFO</h1>" +
                "<h2>Dr. " + Last + ", " + first + "</h2>" +
                "Department : " + dept
                + "<br>Number of Nurses   - Dept: " + db.GetAllNurses(dept).size()
                + "<br>Number of Patients - Dept: " + db.GetAllPatients(dept).size()
                + "<br>Doctor Patients: " + db.GetAllPatients(userId).size())));

        db.Close();
    }

    /**
     * Create Menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //
        Resources res = getResources();
        String[] valuesRest = res.getStringArray(R.array.actions);

        for (String item : valuesRest) {
            menu.add(item);
        }
        return true;
    }

    /**
     * Action Menu
     * @param m
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem m) {

        db = new DBAdapter(getApplicationContext());

        db.Open();

        //ARRUMAR PARA USAR APENAS UM LIST VIEW//

        listViewNurses.setVisibility(View.INVISIBLE);
        listViewPatients.setVisibility(View.INVISIBLE);

        listViewNurses.setVisibility(View.GONE);
        listViewPatients.setVisibility(View.GONE);

        switch (m.toString()) {

            case "Dept-Nurses": {
                List<Nurse> listOfNurses = db.GetAllNurses(dept);

                ArrayAdapter<Nurse> adapter = new ArrayAdapter<Nurse>(
                        this, android.R.layout.simple_list_item_1, android.R.id.text1, listOfNurses);

                // Assign adapter to ListView
                listViewNurses.setAdapter(adapter);

                if (adapter.isEmpty()) {
                    db.Close();
                    return true;
                }

                listViewNurses.setVisibility(View.VISIBLE);

                listViewNurses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                        People NurseInfo = (Nurse) listViewNurses.getItemAtPosition(position);
                        Send(NurseInfo);
                    }
                });
                break;
            }

            case "My Patients": {
                List<Patient> listOfPatients = db.GetAllPatients(userId);
                ArrayAdapter<Patient> adapter = new ArrayAdapter<Patient>(
                        this, android.R.layout.simple_list_item_1, android.R.id.text1, listOfPatients);
                // Assign adapter to ListView
                listViewPatients.setAdapter(adapter);

                if (adapter.isEmpty()) {
                    db.Close();
                    return true;
                }

                listViewPatients.setVisibility(View.VISIBLE);
                listViewPatients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        People PatientInfo = (Patient) listViewPatients.getItemAtPosition(position);
                        Send(PatientInfo);

                    }
                });
                break;
            }
            case "Dept-Patients":{
                List<Patient> deptlistOfPatients = db.GetAllPatients(dept);
                ArrayAdapter<Patient> adapter = new ArrayAdapter<Patient>(
                        this, android.R.layout.simple_list_item_1, android.R.id.text1, deptlistOfPatients);
                // Assign adapter to ListView
                listViewPatients.setAdapter(adapter);

                if (adapter.isEmpty()) {
                    db.Close();
                    return true;
                }
                listViewPatients.setVisibility(View.VISIBLE);
                listViewPatients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        People PatientInfo = (Patient) listViewPatients.getItemAtPosition(position);
                        Send(PatientInfo);

                    }
                });
                break;
            }
        }

        db.Close();
        return true;
    }

    /**
     * Send to another activity
     *
     * @param info
     */
    private void Send(People info) {

        if (info instanceof Nurse) {
            intentValue = new Intent(getApplicationContext(), NurseDetailActivity.class);
            intentValue.putExtra("userId", info.getId());
            startActivity(intentValue);

        } else if (info instanceof Patient) {
            intentValue = new Intent(getApplicationContext(), PatientActivity.class);
            intentValue.putExtra("userId", info.getId());
            intentValue.putExtra("docId", ((Patient) info).getDoctorId());
            intentValue.putExtra("vision", "doctor");
            startActivity(intentValue);
        }
    }

}
