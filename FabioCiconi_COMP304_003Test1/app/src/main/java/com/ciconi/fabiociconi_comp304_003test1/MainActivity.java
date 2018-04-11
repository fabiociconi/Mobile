package com.ciconi.fabiociconi_comp304_003test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //to star second activity and pass some info to it
    private Intent intent;
   // private int[] checkedStates = new int[4];

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout ll = findViewById(R.id.linearBoard);
        String[] stock = getResources().getStringArray(R.array.stocks);

        //add checkboxes
        for(int i = 0; i < stock.length; i++) {
            CheckBox cb = new CheckBox(this);
            cb.setText(stock[i]);
            ll.addView(cb);
        }
    }
    public  void onCLickSubmit(View view) {

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < ((LinearLayout)findViewById(R.id.linearBoard)).getChildCount(); i++) {
            if (((LinearLayout)findViewById(R.id.linearBoard)).getChildAt(i) instanceof CheckBox)
            {
                CheckBox cc = (CheckBox)((LinearLayout)findViewById(R.id.linearBoard)).getChildAt(i);

                if (cc.isChecked()) {

                     list.add((String) cc.getText());
                }
            }
        }

        intent = new Intent(this, StockPriceActivity.class);
        intent.putExtra("checkedStates", list);
        startActivity(intent);


    }



}
