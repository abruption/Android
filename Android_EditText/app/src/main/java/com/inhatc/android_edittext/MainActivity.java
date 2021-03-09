package com.inhatc.android_edittext;

import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText objET = (EditText)findViewById(R.id.EditText);
        objET.setText("Google : ");
        objET.setGravity(0x01);

        objET.append("Android Programming");
    }
}