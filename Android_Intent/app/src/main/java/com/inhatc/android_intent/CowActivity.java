package com.inhatc.android_intent;

import android.content.Intent;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CowActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnOK;       // Button Object
    private EditText edtSound;  // EditText Object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cow);

        btnOK = (Button) findViewById(R.id.btnOK);
        btnOK.setOnClickListener(this);     // [OK] Button Click Event Handler
    }

    public void onClick(View v){
        if (v == btnOK){
            Intent CallIntent = getIntent();    // Return Intent to Start Cow Activity
            edtSound = (EditText)findViewById(R.id.editInputSound);
            CallIntent.putExtra("Animal_Sound", edtSound.getText().toString());
            setResult(RESULT_OK, CallIntent);
            finish();               // Exit Activity
        }
    }
}
