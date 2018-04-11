package com.ciconi.assignmentrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;


/**
 * Created by ZeusAC on 2017-09-25.
 */
public class CheckOut extends Activity {
    /**
     * Variables
     */
    private TextView stringTextView;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent intent = getIntent();
        String nameSr = intent.getStringExtra("name");
        String addressSr = intent.getStringExtra("address");
        String emailSr = intent.getStringExtra("email");
        String cardNumberSr = intent.getStringExtra("cardNumber");
        stringTextView = findViewById(R.id.textView2);
        stringTextView.setText(stringTextView.getText()
                + "Client Information\n"+
                  "------------------\n"
                        +"Client: "+ nameSr
                +"\n\nAddress: "+addressSr
                +"\n\nE-mail: "+emailSr
                +"\n\nCard Number: "+cardNumberSr
                );


        stringTextView = findViewById(R.id.textView1);

        Singleton ds = Singleton.getInstance();
        String s = "";

        for (String  foods : ds.getFoods())
            {
                s+=foods+"\t";
            }
            stringTextView.setText(stringTextView.getText()+
                            "Order Details\n"+
                            "------------------\n"+
                    "Type of Cuisine: "+ ds.getCuisine()+
                    "\n\nRestaurant: "+ ds.getRestaurant()+
                    "\n\nItems: "+ s );
    }
}
