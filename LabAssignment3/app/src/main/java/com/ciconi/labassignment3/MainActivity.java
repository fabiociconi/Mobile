package com.ciconi.labassignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Author Fabio A. Ciconi - 300930989
 * Menu to choose amongst the exercises
 */


public class MainActivity extends AppCompatActivity {


    private Intent intentValue = null;

    /**
     * onCreate Method
     *
     * @param savedInstanceState //
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set title
        setTitle("Lab Assignment 3 - Fall 2017");

        ListView listOptions = (ListView) findViewById(R.id.options);


        //get from resource

        //Resources res = getResources();
        //get array in the Values
        //String[] valuesRest = res.getStringArray(R.array.main_menu_Options);

        //or get resource//
        String[] valuesRest = getResources().getStringArray(R.array.main_menu_Options);
        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1,
                        android.R.id.text1, valuesRest);

        // Assign adapter to ListView
        listOptions.setAdapter(adapter);

        //Show List View
        // ListView Item Click Listener
        listOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //String itemValue = (String) listOptions.getItemAtPosition(position);
                if (position == 0) {
                    Send(position);
                } else if (position == 1) {
                    Send(position);
                } else if (position == 2) {
                    Send(position);
                } else if (position == 3)
                    finish();
            }
        });
    }

    private void Send(int itemValue) {

        switch (itemValue) {
            case 0:
                intentValue = new Intent(this, Exercise1.class);
                break;
            case 1:
                intentValue = new Intent(this, Exercise2.class);
                break;
            case 2:
                intentValue = new Intent(this, Exercise3.class);
                break;
        }
        startActivity(intentValue);

    }

}
