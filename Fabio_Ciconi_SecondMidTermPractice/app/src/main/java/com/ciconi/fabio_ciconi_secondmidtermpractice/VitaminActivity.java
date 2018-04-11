package com.ciconi.fabio_ciconi_secondmidtermpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

public class VitaminActivity extends AppCompatActivity {


    //to star second activity and pass some info to it
    private Intent intent;

    //to store the state od check boxes (0 or 1)
    private int[] checkedStates = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamin);
    }

    public void onCheckboxClicked(View view) {

        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.chkVitaminA:
                if (checked)
                    checkedStates[0] = 1;//assume that vitamin A is complete-- = 1 is checked
                break;
            case R.id.chkVitaminB:
                if (checked)
                    checkedStates[1] = 1;
                break;
            case R.id.chkVitaminC:
                if (checked)
                    checkedStates[2] = 1;
                break;
            case R.id.chkVitaminD:
                if (checked)
                    checkedStates[3] = 1;
                break;
            default:
                break;
        }
    }

    //
    //handling image button
    public void showChartActivity(View v) {
        //put selected items in Extras
        intent = new Intent(this, ChartActivity.class);
        intent.putExtra("checkedStates", checkedStates);

        //start chart activity
        startActivity(intent);
    }


}
