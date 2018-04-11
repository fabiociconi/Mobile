package com.ciconi.labassignment4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * TEST DETAILS Activity - show and edit Tests
 * Author : Fabio A. Ciconi & Rodrigo Geronimo
 * Nov/2017
 */
public class TestDetailActivity extends AppCompatActivity {

    private DBAdapter db;
    private Intent intentValue;
    private int userId;
    private int nurseId;
    private EditText stringEditTexBPH;
    private EditText stringEditTextBPL;
    private EditText stringEditTextTEMP;

    /**
     * On Create
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_detail);
        setTitle("Tests Available");

        intentValue = getIntent();
        userId = intentValue.getIntExtra("userId", 0);
        nurseId = intentValue.getIntExtra("nurseId", 0);

//        db = new DBAdapter(getApplicationContext());
//        db.Open();
//
//        stringEditTexBPH = findViewById(R.id.BPH);
//        stringEditTextBPL = findViewById(R.id.BPL);
//        stringEditTextTEMP = findViewById(R.id.temp);
//
//        List<Test_Pat> testP = db.GetAllTests(userId);
//        if(!testP.isEmpty())
//        {
//            for ( Test_Pat item : testP){
//                stringEditTexBPH.setText(String.valueOf("BPH : "+item.getBPH()));
//                stringEditTextBPL.setText(String.valueOf("BPL : "+ item.getBPL()));
//                stringEditTextTEMP.setText(String.valueOf("Temperature : "+ item.getTemperature()));
//            }
//        }
//        db.Close();
    }

    /**
     * Save new Tests Added
     * @param view
     */
    public void saveTest(View view){
        stringEditTexBPH = findViewById(R.id.BPH);
        stringEditTextBPL = findViewById(R.id.BPL);
        stringEditTextTEMP = findViewById(R.id.temp);

        int bpl ;
        int bph ;
        int temp ;

        try {
              bpl = (Integer.parseInt(stringEditTextBPL.getText().toString()));
              bph = (Integer.parseInt(stringEditTexBPH.getText().toString()));
             temp = (Integer.parseInt(stringEditTextTEMP.getText().toString()));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please Insert all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        db = new DBAdapter(getApplicationContext());
        db.Open();

        db.CreateTest(new Test_Pat(userId,nurseId, bpl, bph, temp));
        db.Close();

        findViewById(R.id.btnSave).setVisibility(View.INVISIBLE);

        findViewById(R.id.BPH).setEnabled(false);
        findViewById(R.id.BPL).setEnabled(false);
        findViewById(R.id.temp).setEnabled(false);

    }
}
