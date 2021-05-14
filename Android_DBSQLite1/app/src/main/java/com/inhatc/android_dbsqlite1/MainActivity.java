package com.inhatc.android_dbsqlite1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase myDB;                // Database object
    SimpleAdapter myADT;                // Adapter object
    ArrayList<String> aryMBRList;       // ArrayList object
    ArrayAdapter<String> adtMembers;    // ArrayAdatper object
    ListView FirstView;                 // ListView object
    String strRecord = null;            // Record data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create DB(DB Name : PhoneBook)
        myDB = this.openOrCreateDatabase("PhoneBook", MODE_PRIVATE, null);
        myDB.execSQL("Drop table if exists members");

        // Create Table(Table Name : members)
        myDB.execSQL("Create table members(" +
                "_id integer primary key autoincrement, " +
                "Name text not null, " + "Phone_No text not null);");

        // Store Data("kdhong", "011-8701-2320") to members table
        myDB.execSQL("Insert into members " + " (Name, Phone_No) values ('kdhong', '011-8701-2320');");

        // Store Data("Juliet", "010-123-1234") to members table
        ContentValues insertValue = new ContentValues();
        insertValue.put("Name", "Juliet");
        insertValue.put("Phone_No", "010-123-1234");
        myDB.insert("members", null, insertValue);

        // Store Data("Romio", "010-100-5678") to members table
        insertValue.put("Name", "Romio");
        insertValue.put("Phone_No", "010-100-5678");
        myDB.insert("members", null, insertValue);


        // Get record Data from members table
        Cursor allRCD = myDB.query("members", null, null, null, null, null, null, null);

        // Create ArrayList
        aryMBRList = new ArrayList<String>();
        if (allRCD != null){
            if(allRCD.moveToFirst()){
                do{
                    strRecord = allRCD.getString(1) + "\t\t" + allRCD.getString(2);
                    aryMBRList.add(strRecord);
                } while (allRCD.moveToNext());
            }
        }
        adtMembers = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, aryMBRList);

        // Create ListView
        FirstView = (ListView) findViewById(R.id.FirstMember);
        FirstView.setAdapter(adtMembers);
        FirstView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        if(myDB != null) myDB.close();  // Close DB
    }
}