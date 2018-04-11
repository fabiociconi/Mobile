package com.ciconi.assignmentrestaurant;
import android.view.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by ZeusAC on 2017-09-25.
 */
public class MainActivity extends Activity {

    /**
     * Oncreate Method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Simply Food APP");
        setContentView(R.layout.activity_main);
    }

    /**
     * Click to go to next activity
     * @param view
     */
    public void onClick(View view){
        startActivity(new Intent("com.ciconi.assignmentrestaurant.Cuisines"));

    }
}
