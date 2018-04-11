package com.example.foodplacetoronto;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }

                if (position == 1) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }

                if (position == 2) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }

            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        SharedPreferences myPref = getSharedPreferences("MyCustomSharedPreferences", MODE_PRIVATE);
        String restaurantName = myPref.getString("restaurantName", "");
        String restaurantAddress = myPref.getString("restaurantAddress", "");
        Float restaurantLat = myPref.getFloat("restaurantLat", 0);
        Float restaurantLng = myPref.getFloat("restaurantLng", 0);

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);


        LatLng restaurantLocation = new LatLng(restaurantLat, restaurantLng);
        mMap.addMarker(new MarkerOptions().position(restaurantLocation).title(restaurantName + " - " + restaurantAddress));

        // Move the camera instantly to toronto with a zoom of 16.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurantLocation, 16));

    }
}
