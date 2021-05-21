package com.inhatc.android_firebase1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.google.firebase.database.*;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase myFirebase;
    DatabaseReference myDB_Reference = null;

    TextView txtFirebase;
    Button btnInsert;
    EditText edtCustomerName;
    String strHeader = "Customer_Information";
    String strCName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFirebase = findViewById(R.id.txtFirebase);
        edtCustomerName = findViewById(R.id.edtCustomerName);

        btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(this);

        myFirebase = FirebaseDatabase.getInstance();
        myDB_Reference = myFirebase.getReference();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInsert:
                strCName = edtCustomerName.getText().toString();
                if(!strCName.equals("")){
                    mSet_FirebaseDatabase(true);
                    mGet_FirebaseDatabase();
                }
                edtCustomerName.setText("");
                break;
            default:
                break;
        }
    }

    public void mSet_FirebaseDatabase(boolean bFlag) {
        if(bFlag)
            myDB_Reference.child(strHeader).push().setValue(strCName);
    }

    public void mGet_FirebaseDatabase() {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txtFirebase.setText("Firebase Value : ");
                Object objValue = dataSnapshot.getValue(Object.class);
                txtFirebase.append("\n - Name : " + objValue.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG: ", "Failed to read value.", databaseError.toException());
            }
        };
        Query sortbyName = FirebaseDatabase.getInstance().getReference().child(strHeader).orderByChild(strCName);
        sortbyName.addListenerForSingleValueEvent(postListener);
    }
}