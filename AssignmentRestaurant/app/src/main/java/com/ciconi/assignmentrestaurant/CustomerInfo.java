package com.ciconi.assignmentrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by ZeusAC on 2017-09-25.
 */
public class CustomerInfo extends Activity {
    /**
     * Variables
     */

    private EditText name;
    private EditText address;
    private EditText cardNumber;
    private EditText email;
    private EditText age;

    private Intent intentValue;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Customer Information");
        setContentView(R.layout.activity_client_form);
    }

    /**
     *
     * @param view
     */
    public void onClick(View view) {

        name   = findViewById(R.id.signup_input_name);
        address =findViewById(R.id.signup_input_addr);
        cardNumber = findViewById(R.id.signup_input_Card);
        email = findViewById(R.id.signup_input_email);
        age = findViewById(R.id.signup_input_age);

        //fill out all fields
        if(name.getText().toString().equals("")){
            DisplayToast("Please insert your Full Name");
        }else if(address.getText().toString().equals("")){
            DisplayToast("Please insert your Address");
        }
        else if(cardNumber.getText().toString().equals("")){
            DisplayToast("Please insert your Card Number");
        }else if(email.getText().toString().equals(""))
        {
            DisplayToast("Please insert your E-mail");
        }else if(age.getText().toString().equals(""))
        {
            DisplayToast("Please insert your Age");
        }
        else {
            //go to next activity
            intentValue = new Intent(getApplicationContext(), CheckOut.class);

            intentValue.putExtra("name",name.getText().toString());
            intentValue.putExtra("address",address.getText().toString());
            intentValue.putExtra("email",email.getText().toString());
            intentValue.putExtra("cardNumber",cardNumber.getText().toString());


            startActivity(intentValue);
        }

    }
    private void DisplayToast(String msg)
    {
        Toast.makeText(getBaseContext(), msg,
                Toast.LENGTH_SHORT).show();
    }
}
