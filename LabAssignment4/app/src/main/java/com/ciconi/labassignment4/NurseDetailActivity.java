package com.ciconi.labassignment4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Nurse DETAILS Activity - show and edit Nurse
 * Author : Fabio A. Ciconi & Rodrigo Geronimo
 * Nov/2017
 */
public class NurseDetailActivity extends AppCompatActivity {

    private Intent intentValue;
    private DBAdapter db;
    private EditText stringEditTextFirstName;
    private EditText stringEditTextLastName;
    private EditText stringEditTextDept;
    private int userId;
    private String firstName;
    private String lastName;
    private String dept;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_detail);
        setTitle("Nurse Detail");

        //taking info of previews activity
        intentValue = getIntent();
        userId = intentValue.getIntExtra("userId", 0);

        db = new DBAdapter(getApplicationContext());

        db.Open();

        //Patient Details
        Nurse nurse = db.GetNurse(userId);

        stringEditTextFirstName = findViewById(R.id.NurseName);
        stringEditTextLastName = findViewById(R.id.NurseLastName);
        stringEditTextDept = findViewById(R.id.depart);

        stringEditTextFirstName.setText(nurse.getFirstname());
        stringEditTextLastName.setText(nurse.getLastname());
        stringEditTextDept.setText(nurse.getDepartment());

    }

    /**
     * Edit Nurse
     * @param view
     */
    public void editNurse(View view) {
        findViewById(R.id.btnEdit).setVisibility(View.INVISIBLE);
        findViewById(R.id.btnSave).setVisibility(View.VISIBLE);

        findViewById(R.id.NurseName).setEnabled(true);
        findViewById(R.id.NurseLastName).setEnabled(true);
        findViewById(R.id.depart).setEnabled(true);
    }

    /**
     * Save changes
     * @param view
     */
    public void saveNurse(View view) {

        stringEditTextFirstName = findViewById(R.id.NurseName);
        stringEditTextLastName = findViewById(R.id.NurseLastName);
        stringEditTextDept = findViewById(R.id.depart);

        firstName = stringEditTextFirstName.getText().toString();
        lastName = stringEditTextLastName.getText().toString();
        dept = stringEditTextDept.getText().toString();

        db = new DBAdapter(getApplicationContext());
        db.Open();

        db.UpdateNurse(userId,firstName,lastName,dept);
        db.Close();
        findViewById(R.id.NurseName).setEnabled(false);
        findViewById(R.id.NurseLastName).setEnabled(false);
        findViewById(R.id.depart).setEnabled(false);
        findViewById(R.id.btnSave).setVisibility(View.INVISIBLE);

    }
}
