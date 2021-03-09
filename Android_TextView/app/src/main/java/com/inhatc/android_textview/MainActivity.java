package com.inhatc.android_textview;

import android.graphics.Color;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        TextView objTV = new TextView(this);
        objTV.setText("Android Programming !");
        objTV.setTextColor(Color.parseColor("FF0000FF"));
        objTV.setGravity(0x11);
        objTV.setTextSize(32);
        setContentView(objTV);
    }
}