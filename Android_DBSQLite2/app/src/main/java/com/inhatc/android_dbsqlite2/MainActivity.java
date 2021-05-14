package com.inhatc.android_dbsqlite2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase myDB;                    // Database object
    SimpleAdapter myADT;                    // Adapter object
    ArrayList<String> aryMBRList;           // ArrayList object
    ArrayAdapter<String> adtMembers;        // ArrayAdapter object
    TextView[] objTV;                       // TextView object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create DB(DB Name : PhoneBook)
        myDB = this.openOrCreateDatabase("PhoneBook", MODE_PRIVATE, null);
        myDB.execSQL("Drop table if exists members");

        // Create Table(Table Name : members)
        myDB.execSQL("Create table members (" +
                "_id integer primary key autoincrement, " +
                "Name text not null, " + "Phone_No text not null);");

        // Store Data("kdhong", "011-8701-2320") to members table
        myDB.execSQL("Insert into members " + "(Name, Phone_No) values ('kdhong', '011-8701-2320')");

        // Store Data("Juliet", "010-123-1234") to members table
        ContentValues insertValue = new ContentValues();
        insertValue.put("Name", "Juliet");
        insertValue.put("Phone_No", "010-123-1234");
        myDB.insert("members", null, insertValue);

        insertValue.put("Name", "Romio");
        insertValue.put("Phone_No", "010-100-5678");
        myDB.insert("members", null, insertValue);

        // Get record Data from members table
        Cursor allRCD = myDB.query("members", null, null, null, null, null, null, null);

        // Create ArrayList
        aryMBRList = new ArrayList<String>();
        if(allRCD != null){
            if(allRCD.moveToFirst()){
                do{
                    aryMBRList.add((allRCD.getString(1)));
                    aryMBRList.add((allRCD.getString(2)));
                } while(allRCD.moveToNext());
            }
        }

        objTV = new TextView[8];        // Create instance of arrayList object
        objTV[0] = (TextView)findViewById(R.id.TextView1);
        objTV[1] = (TextView)findViewById(R.id.TextView2);
        objTV[2] = (TextView)findViewById(R.id.TextView3);
        objTV[3] = (TextView)findViewById(R.id.TextView4);
        objTV[4] = (TextView)findViewById(R.id.TextView5);
        objTV[5] = (TextView)findViewById(R.id.TextView6);
        objTV[6] = (TextView)findViewById(R.id.TextView7);
        objTV[7] = (TextView)findViewById(R.id.TextView7);

       // ArrayList -> TextView
       for(int i=0; i<aryMBRList.size(); i++)
           objTV[i].setText(aryMBRList.get(i).toString());

       if(myDB != null) myDB.close();   // Close DB
    }
}