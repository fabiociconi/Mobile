package com.mycompany.calllogtest;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    String contacts="Call Log:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        textView.setText(contacts);

    }

    public void displayContacts(View view)
    {
        textView.append("Contacts:");
        Uri allContacts = ContactsContract.Contacts.CONTENT_URI;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        try {
            Cursor c = getContentResolver().query(allContacts, null, null, null, null);
            while (c.moveToNext()) {
                contacts += c.getString(c.getColumnIndex(DISPLAY_NAME));
                Log.i("Number: ", contacts);
                textView.append(System.getProperty("line.separator"));
                textView.append(c.getString(c.getColumnIndex(DISPLAY_NAME))+"\n");
            }

        }
        catch (SecurityException sc)
        {
            Log.i("exception: ",sc.getMessage());
        }

    }
    public void displayCallLog(View view)
    {

        String strOrder = android.provider.CallLog.Calls.DATE + " DESC";
        Uri allCalls = Uri.parse("content://call_log/calls");
        try {
            Cursor c = getContentResolver().query(allCalls, null, null, null, strOrder);
            while (c.moveToNext()) {
                contacts += c.getString(c.getColumnIndex(android.provider.CallLog.Calls.NUMBER));
                Log.i("Number: ", contacts);
                textView.append(System.getProperty("line.separator"));
                textView.append(c.getString(c.getColumnIndex(android.provider.CallLog.Calls.NUMBER))+"\n");
            }

        }
        catch (SecurityException sc)
        {
            Log.i("exception: ",sc.getMessage());
        }
    }
}
