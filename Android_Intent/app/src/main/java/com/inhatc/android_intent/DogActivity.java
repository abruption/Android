package com.inhatc.android_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class DogActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnOK;
    private EditText edtSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);

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
