package com.example.foodplacetoronto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebStorage;

public class MainActivity extends AppCompatActivity {

    private DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.app_name));
    }

    public void enterApp(View view) {

        //call Dbadapter
        db = new DBAdapter(getApplicationContext());

        //OPEN database
        db.Open();


        //******************************************************
        // Insert Cuisines
        //******************************************************
        Cuisine cuisine_italian = new Cuisine(1, "Italian", "Delicious Italian Food! Pizza!!!");
        Cuisine cuisine_greek = new Cuisine(2, "Greek", "Try the incredible Greek Cuisine");
        Cuisine cuisine_brazilian = new Cuisine(3, "Brazilian", "The best food in the world!");
        Cuisine cuisine_chinese = new Cuisine(4, "Chinese", "Would you like to try Chinese food?");
        Cuisine cuisine_indian = new Cuisine(5, "Indian", "Find out the taste from India!");

        //call Function
        db.CreateCuisine(cuisine_italian);
        db.CreateCuisine(cuisine_greek);
        db.CreateCuisine(cuisine_brazilian);
        db.CreateCuisine(cuisine_chinese);
        db.CreateCuisine(cuisine_indian);


        //******************************************************
        // Insert Restaurants
        //******************************************************
        Restaurant restaurant_nove_trattoria = new Restaurant(1, "Nove Trattoria", "1406 Yonge Street", 43.6867341 , -79.3937883, "Italian");
        Restaurant restaurant_libretto = new Restaurant(2, "Pizzeria Libretto", "221 Ossington Avenue", 43.6490017, -79.4203433, "Italian");
        Restaurant restaurant_donatello = new Restaurant(3, "Donatello", "37 Elm Street", 43.6573166, -79.3834791, "Italian");
        Restaurant restaurant_spaghetti_factory = new Restaurant(4, "Old Spaghetti Factory", "54 The Esplanade", 43.6471077, -79.3744259, "Italian");
        Restaurant restaurant_trattoria_taverniti = new Restaurant(5, "Trattoria Taverniti", "591 College Street", 43.655147, -79.413513, "Italian");

        Restaurant restaurant_mykonos = new Restaurant(6, "Mykonos Mediterranean Grill", "881 Yonge Street", 43.6743209, -79.3880618, "Greek");
        Restaurant restaurant_jimmy = new Restaurant(7, "Jimmy the Greek", "220 Yonge Street", 43.6546609, -79.3805654, "Greek");
        Restaurant restaurant_athens = new Restaurant(8, "Athens Pastry", "509 Danforth Avenue", 43.6779323, -79.3488237, "Greek");
        Restaurant restaurant_pan_danforth = new Restaurant(9, "Pan on the Danforth", "516 Danforth Avenue", 43.6783585, -79.3486848, "Greek");
        Restaurant restaurant_mamakas = new Restaurant(10, "Mamakas", "80 Ossington Avenue", 43.6458917, -79.4198642, "Greek");

        Restaurant restaurant_rio40 = new Restaurant(11, "Rio 40 Graus", "1256 St. Clair Avenue West", 43.6774457, -79.4464837, "Brazilian");
        Restaurant restaurant_sabor_brasil = new Restaurant(12, "Sabor Brasil", "1702 St. Clair Avenue West", 43.67465, -79.45931, "Brazilian");
        Restaurant restaurant_copacabana = new Restaurant(13, "Copacabana Steakhouse", "150 Eglinton Avenue East", 43.7078909, -79.394018, "Brazilian");
        Restaurant restaurant_rodeo = new Restaurant(14, "Rodeo Brazilian Steakhouse", "95 Danforth Avenue", 43.6760886, -79.358458, "Brazilian");

        Restaurant restaurant_lee_chen_asian_bistro = new Restaurant(15, "Lee Chen Asian Bistro", "832 Yonge Street", 43.6712787 , -79.3876158, "Chinese");
        Restaurant restaurant_pearl = new Restaurant(16, "Pearl Harbourfront", "200-207 Queens Quay West", 43.6388378 , -79.3806105, "Chinese");
        Restaurant restaurant_mandarin = new Restaurant(17, "Mandarin", "2200 Yonge Street", 43.7060951 , -79.3985366, "Chinese");
        Restaurant restaurant_asian_legend = new Restaurant(18, "Asian Legend", "418 Dundas Street West", 43.6538619, -79.3951973, "Chinese");
        Restaurant restaurant_south_china = new Restaurant(19, "South China", "513 Mount Pleasant Road", 43.701907, -79.3873693, "Chinese");

        Restaurant restaurant_bindia = new Restaurant(20, "Bindia Indian Bistro", "16 Market Street", 43.6484485, -79.3720459, "Indian");
        Restaurant restaurant_bombay = new Restaurant(21, "Bombay Palace", "71 Jarvis Street", 43.6511816, -79.3719289, "Indian");
        Restaurant restaurant_delhi = new Restaurant(22, "Delhi Bistro", "2214 Queen Street East", 43.6725772, -79.2885159, "Indian");
        Restaurant restaurant_maurya = new Restaurant(23, "Maurya East Indian Roti", "150 East Liberty Street", 43.6387753, -79.4162573, "Indian");
        Restaurant restaurant_kairali = new Restaurant(24, "Kairali - Taste of Kerala", "1210 Kennedy Road", 43.754842, -79.2772453, "Indian");

        //call Function
        db.CreateRestaurant(restaurant_nove_trattoria);
        db.CreateRestaurant(restaurant_libretto);
        db.CreateRestaurant(restaurant_donatello);
        db.CreateRestaurant(restaurant_spaghetti_factory);
        db.CreateRestaurant(restaurant_trattoria_taverniti);

        db.CreateRestaurant(restaurant_mykonos);
        db.CreateRestaurant(restaurant_jimmy);
        db.CreateRestaurant(restaurant_athens);
        db.CreateRestaurant(restaurant_pan_danforth);
        db.CreateRestaurant(restaurant_mamakas);

        db.CreateRestaurant(restaurant_rio40);
        db.CreateRestaurant(restaurant_sabor_brasil);
        db.CreateRestaurant(restaurant_copacabana);
        db.CreateRestaurant(restaurant_rodeo);

        db.CreateRestaurant(restaurant_lee_chen_asian_bistro);
        db.CreateRestaurant(restaurant_pearl);
        db.CreateRestaurant(restaurant_mandarin);
        db.CreateRestaurant(restaurant_asian_legend);
        db.CreateRestaurant(restaurant_south_china);

        db.CreateRestaurant(restaurant_bindia);
        db.CreateRestaurant(restaurant_bombay);
        db.CreateRestaurant(restaurant_delhi);
        db.CreateRestaurant(restaurant_maurya);
        db.CreateRestaurant(restaurant_kairali);

        //CLOSE DATABASE CONNECTION
        db.Close();

        Intent intent = new Intent(this, CuisineActivity.class);
        startActivity(intent);
    }
}
