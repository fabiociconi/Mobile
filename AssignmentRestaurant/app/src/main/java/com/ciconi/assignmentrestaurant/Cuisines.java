package com.ciconi.assignmentrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by ZeusAC on 2017-09-25.
 */
public class Cuisines extends Activity {
    /**
     *
     */
    private RadioGroup  radioGroupCo;
    private RadioButton italianRadio;
    private RadioButton canadianRadio;
    private RadioButton frenchRadio;
    private RadioButton brazilianRadio;
    private Intent intentValue;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Types of Cuisines");
        setContentView(R.layout.activity_cuisines);
    }

    /**
     *
     * @param view
     */
    public void onClick(View view){

        //RadioGroupRadios
        radioGroupCo    = findViewById(R.id.radioGroupRest);

        //radioButtons
        italianRadio    = findViewById(R.id.radioButtonItalian);
        canadianRadio   = findViewById(R.id.radioButtonCanadian);
        frenchRadio     = findViewById(R.id.radioButtonFrench);
        brazilianRadio  = findViewById(R.id.radioButtonBrazilian);


        if(italianRadio.isChecked())
        {
            Send("Italian");
        }
        if(canadianRadio.isChecked())
        {
            Send("Canadian");
        }
        if(frenchRadio.isChecked())
        {
            Send("French");
        }
        if(brazilianRadio.isChecked())
        {
            Send("Brazilian");
        }

    }

    /**
     *
     * @param foodType
     */
    private void Send(String foodType)
    {
        Save(foodType);
        intentValue = new Intent(getApplicationContext(),Restaurants.class);
        intentValue.putExtra("cuisine",foodType);
        this.startActivity(intentValue);

    }
    /**
     * @param c
     */
    private void Save(String c) {

        Singleton save = Singleton.getInstance();
        //Test
        System.out.println("Instance ID 1 :" + save.hashCode());
        //Test
        save.addCusines(c);
    }

    /**
     *
     * @param msg
     */
    private void DisplayToast(String msg)
    {
        Toast.makeText(getBaseContext(), msg,
                Toast.LENGTH_SHORT).show();
    }

}
