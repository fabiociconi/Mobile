package com.ciconi.assignmentrestaurant;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ZeusAC on 2017-09-25.
 */
public class Restaurants extends Activity {

    /**
     * Global Variables
     */
    //private static ArrayList<String> MyArray = null;
    private ListView listView;
    private Intent intentValue;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Resources res = getResources();
        String[] valuesRest = res.getStringArray(R.array.restaurantsOptions);
        for (String rest : valuesRest) {
            menu.add(rest);

        }
        //menu.add("String");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem m) {
        int itemPosition = m.getItemId();

        String itemValue = (String) listView.getItemAtPosition(itemPosition);

        if (itemPosition == 0) {
            Send(itemValue);
        } else {
            // Show Alert
            Toast.makeText(getApplicationContext(),
                    "Under Construction" + "Position :"
                            + itemPosition + "  " +
                            "ListItem : " + itemValue,
                    Toast.LENGTH_LONG)
                    .show();
        }

        return true;
    }




    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        Intent intent = getIntent();
        String typeCousines = intent.getStringExtra("cuisine");

       // setTitle(typeCousines +" Restaurants");
        /*Save Restaurant*/
       // Save(typeCousines);

        listView = findViewById(R.id.listItalianRestaurants);
        Resources res = getResources();
        String[] valuesRest = res.getStringArray(R.array.restaurantsOptions);
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1,
                        android.R.id.text1, valuesRest);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                int itemPosition = position;

                String itemValue = (String) listView.getItemAtPosition(position);

                if (itemPosition == 0) {
                    Send(itemValue);
                }
                else
                    {
                    // Show Alert
                    Toast.makeText(getApplicationContext(),
                            "Under Construction" + "Position :"
                                    + itemPosition + "  " +
                                    "ListItem : " + itemValue,
                            Toast.LENGTH_LONG)
                            .show();
                    }
                }
            });
    }

    /**
     * @param itemPosition
     */
    private void Send(String itemPosition) {
        Save(itemPosition);
        intentValue = new Intent(getApplicationContext(), Food.class);
        intentValue.putExtra("Restaurant", itemPosition);
        startActivity(intentValue);
    }
    /**
     * @param c
     */
    private void Save(String c) {

        Singleton save = Singleton.getInstance();
        //Test
        System.out.println("Instance ID 2 :" + save.hashCode());
        //Test
        save.addRestaurant(c);
    }

}
