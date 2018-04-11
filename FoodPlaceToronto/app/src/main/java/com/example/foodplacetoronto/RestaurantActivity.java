package com.example.foodplacetoronto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {

    String selectedCuisine;
    private DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        setTitle(getString(R.string.app_name));

        SharedPreferences myPref = getSharedPreferences("MyCustomSharedPreferences", MODE_PRIVATE);
        selectedCuisine = myPref.getString("selectedCuisine","");

        TextView txtRestaurant = findViewById(R.id.txtRestaurant);
        ListView listRestaurants = findViewById(R.id.listRestaurants);

        txtRestaurant.setText(selectedCuisine + " Restaurants");

        db = new DBAdapter(getApplicationContext());
        db.Open();

        List<Restaurant> restaurantList = db.GetAllRestaurants(selectedCuisine);
        ArrayList<String> restaurants = new ArrayList<>();
        ArrayList<String> addresses = new ArrayList<>();

        for ( int i = 0; i < restaurantList.size(); i++ )
        {
            restaurants.add(restaurantList.get(i).getName());
            addresses.add(restaurantList.get(i).getAddress());
        }
        db.Close();


        String[] allRestaurants = restaurants.toArray(new String[restaurants.size()]);
        String[] allAddress = addresses.toArray(new String[addresses.size()]);

        CustomListAdapter adapter = new CustomListAdapter(this, allRestaurants, allAddress);
        listRestaurants.setAdapter(adapter);

        listRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Object item = arg0.getItemAtPosition(position);
                String selectedRestaurant = item.toString();

                db = new DBAdapter(getApplicationContext());
                db.Open();

                Restaurant restaurant = db.GetRestaurant(selectedRestaurant);

                db.Close();

                SharedPreferences myPreference =
                        getSharedPreferences("MyCustomSharedPreferences", 0);
                SharedPreferences.Editor editor = myPreference.edit();
                editor.putString("restaurantName", restaurant.getName());
                editor.putString("restaurantAddress", restaurant.getAddress());
                editor.putFloat("restaurantLat", (float) restaurant.getLat());
                editor.putFloat("restaurantLng", (float) restaurant.getLng());

                editor.apply();
                editor.commit();

                Intent i = new Intent(getBaseContext(), MapsActivity.class);

                startActivity(i);
            }
        });
    }
}
