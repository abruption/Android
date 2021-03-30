package com.inhatc.android_tablayout;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.TabHost;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TabHost myTabHost = null;   // TabHost Object
    TabHost.TabSpec myTabSpec;  // TabSpec Object
    Drawable imgIcon = null;    // Drawable Object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        myTabHost = (TabHost)findViewById(R.id.tabhost);
        myTabHost.setup();  // Not use TabActivity Call
        
        // Add Tab
        myTabSpec = myTabHost.newTabSpec("Artists")
                .setIndicator("Artists")                // Tab Subject
                .setContent(R.id.tab1);                 // Tab Content
        myTabHost.addTab(myTabSpec);

        myTabSpec = myTabHost.newTabSpec("Albums")
                .setIndicator("Albums")                 // Tab Subject
                .setContent(R.id.tab2);                 // Tab Content
        myTabHost.addTab(myTabSpec);

        myTabSpec = myTabHost.newTabSpec("Songs")
                .setIndicator("Songs")                  // Tab Subject
                .setContent(R.id.tab3);                 // Tab Content
        myTabHost.addTab(myTabSpec);

        myTabHost.setCurrentTab(0);                     // Setting First Tab

        // Change Tab Background Color
        myTabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#FFF0000"));
        myTabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#00FF00"));
        myTabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#0000FF"));

        // Set Tab Height = 150
        for(int idx=0; idx<myTabHost.getTabWidget().getChildCount(); idx++)
            myTabHost.getTabWidget().getChildAt(idx).getLayoutParams().height = 150;
    }
}