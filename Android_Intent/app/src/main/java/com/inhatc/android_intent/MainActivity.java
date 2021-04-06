package com.inhatc.android_intent;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnCow, btnDog;       // Button Object
    private Toast objToast;             // Toast Object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCow = (Button) findViewById(R.id.btnCow);
        btnDog = (Button) findViewById(R.id.btnDog);
        btnCow.setOnClickListener(this);     // Cow Button Click Event Handler
        btnDog.setOnClickListener(this);     // Cow Button Click Event Handler
    }

    public void onClick(View v){
        if (v == btnCow){                // Call Cow Activity in Cow.java
            Intent cowIntent = new Intent(MainActivity.this, CowActivity.class);
            //startActivity(cowIntent);       // Start Cow Activity
            startActivityForResult(cowIntent, 1);
        } else if(v == btnDog){
            Intent dogIntent = new Intent(MainActivity.this, DogActivity.class);
            //startActivity(dogIntent);
            startActivityForResult(dogIntent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            String strData = data.getStringExtra("Animal_Sound");
            objToast = Toast.makeText(this, strData, Toast.LENGTH_LONG);
            objToast.show();
        }
    }
}