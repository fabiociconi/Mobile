package com.example.fabiociconi_comp304lab1_ex2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.fabiociconi.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMassage.class);
        String message = getString(R.string.program_name)+"\n" + getString(R.string.course_Name)+"\n" +
                getString(R.string.semester)+"\n"+getString(R.string.full_name)+"\n";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
