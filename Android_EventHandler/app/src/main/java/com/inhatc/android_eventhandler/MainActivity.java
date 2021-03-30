package com.inhatc.android_eventhandler;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCall;     // Button Object
    private EditText objET;     // Button Object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCall = (Button) findViewById(R.id.button1);
        objET = (EditText)findViewById(R.id.editText1);

        btnCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0){
        if(arg0 == btnCall){
            Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("Tel : " + objET.getText()));
            dialIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(dialIntent);
        }
    }
}