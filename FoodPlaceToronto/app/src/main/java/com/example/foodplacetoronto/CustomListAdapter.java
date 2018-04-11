package com.example.foodplacetoronto;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Custom List Adapter
 * Author : Rodrigo Geronimo & Fabio A. Ciconi
 * Dez/2017
 */

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemName;
    private Integer[] imgId;
    private final String[] description;

    public CustomListAdapter(Activity context, String[] itemname, Integer[] imgid, String[] description) {
        super(context, R.layout.row_item, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemName=itemname;
        this.imgId=imgid;
        this.description = description;
    }

    public CustomListAdapter(Activity context, String[] itemname, String[] description) {
        super(context, R.layout.row_item, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemName=itemname;
        this.description = description;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView;
        TextView txtTitle;
        ImageView imageView;
        TextView extratxt;

        if(imgId != null) {
            rowView = inflater.inflate(R.layout.row_item, null,true);
            imageView = rowView.findViewById(R.id.icon);
            imageView.setImageResource(imgId[position]);
        }
        else{
            rowView = inflater.inflate(R.layout.row_restaurants, null,true);
        }

        txtTitle = rowView.findViewById(R.id.item);
        extratxt = rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemName[position]);
        extratxt.setText(description[position]);

        return rowView;
    }
}
