package com.ciconi.assignmentrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 *  Created by ZeusAC on 2017-09-25.
 *  Food Class
 */
public class Food extends Activity {

    /**
     * Global Variables
     */
    private List<String> foodListOpt;
    private Intent intentValue;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

			
        String rest = intent.getStringExtra("Restaurant");
        setTitle(rest);
        setContentView(R.layout.activity_food);



    }

    /**
     *
     * @param view
     */
    public  void onCLick(View view){

        for (int i = 0; i < ((LinearLayout)findViewById(R.id.foodList)).getChildCount(); i++) {
            if (((LinearLayout)findViewById(R.id.foodList)).getChildAt(i) instanceof CheckBox)
            {
                CheckBox cc = (CheckBox)((LinearLayout)findViewById(R.id.foodList)).getChildAt(i);

                if (cc.isChecked()) {
                    Singleton save = Singleton.getInstance();
                    save.addFoods(cc.getText().toString());

                }
            }
        }

        //save.addDishes(foodListOpt);
        Send(foodListOpt);
    }
//    public void onClick2(View view) {
//
//       // boolean checked = ((CheckBox) view).isChecked();
//
//        //int totalamount=0;
//
//        //StringBuilder totalResult=new StringBuilder();
//        System.out.println("zzzzz zzzzzzz :");
//        //
//       // Singleton save = Singleton.getInstance();
//
//        if (((CheckBox) view).isChecked()){
//            DisplayToast("CheckBox is checked");
//    }
//        //totalResult.append("Selected Items:");
////        switch (view.getId()) {
////            case R.id.checkbox_meat:
////                if (checked) {
////          //          totalResult.append("\nfood1 100Rs");
////            //        totalamount+=100;
////                    //Send();
////
////                    foodListOpt.add("Meet");
////                }
////            case R.id.checkbox_cheese:
////                if (checked) {
////                    foodListOpt.add("Cheese");
////              //      totalResult.append("\nfood1 100Rs");
////                //    totalamount+=100;
////
////                }
////                break;
////
////        }
//
//        //save.addDishes(foodListOpt);
//       // Send();
////        totalResult.append("\nTotal: "+totalamount+"Rs");
////        //Displaying the message on the toast
////        Toast.makeText(getApplicationContext(), totalResult.toString(), Toast.LENGTH_LONG).show();
//    }

    /**
     *
     */
    private void Send(List dd) {

        Save(dd);
        //Singleton save =  Singleton.getInstance();
        //save.getAllCourses();
        intentValue = new Intent(getApplicationContext(), CustomerInfo.class);
        startActivity(intentValue);

    }

    /**
     *
     * @param c
     */
    private void Save(List c){
        Singleton save =  Singleton.getInstance();
        System.out.println("Instance ID 3 :"+ save.hashCode());
        save.addDishes(c);
    }
}
