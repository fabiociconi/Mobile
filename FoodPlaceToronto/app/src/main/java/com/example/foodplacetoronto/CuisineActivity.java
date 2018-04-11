package com.example.foodplacetoronto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class CuisineActivity extends AppCompatActivity {

    private DBAdapter db;
    public Integer[] imgId={
            R.drawable.italy,
            R.drawable.greece,
            R.drawable.brazil,
            R.drawable.china,
            R.drawable.india
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine);
        setTitle(getString(R.string.app_name));

        ListView listCuisines = findViewById(R.id.listCuisines);

        db = new DBAdapter(getApplicationContext());
        db.Open();

        List<Cuisine> cuisineList = db.GetAllCuisines();
        ArrayList<String> cuisines = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();

        for ( int i = 0; i < cuisineList.size(); i++ )
        {
            cuisines.add(cuisineList.get(i).getName());
            descriptions.add(cuisineList.get(i).getDescription());
        }

        String[] allCuisines = cuisines.toArray(new String[cuisines.size()]);
        String[] allDescriptions = descriptions.toArray(new String[descriptions.size()]);

        db.Close();

        CustomListAdapter adapter = new CustomListAdapter(this, allCuisines, imgId, allDescriptions);
        listCuisines.setAdapter(adapter);

        listCuisines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Object item = arg0.getItemAtPosition(position);
                String selectedCuisine = item.toString();

                SharedPreferences myPreference =
                        getSharedPreferences("MyCustomSharedPreferences", 0);
                SharedPreferences.Editor editor = myPreference.edit();
                editor.putString("selectedCuisine", selectedCuisine);
                editor.apply();
                editor.commit();

                Intent i = new Intent(getBaseContext(), RestaurantActivity.class);
                startActivity(i);
            }
        });
    }
}
