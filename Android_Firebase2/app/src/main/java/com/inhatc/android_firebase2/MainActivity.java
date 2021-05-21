package com.inhatc.android_firebase2;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.*;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseDatabase myFirebase;
    DatabaseReference myDB_Reference = null;

    HashMap<String, Object> Customer_Value = null;
    CustomerInfo objCustomerInfo = null;

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
        Customer_Value = new HashMap<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInsert:
                strCName = edtCustomerName.getText().toString();
                if(!strCName.equals("")){
                    Customer_Value.put("Name", strCName);
                    mSet_FirebaseDatabase(true);
                    mGet_FirebaseDatabase();
                }
                edtCustomerName.setText("");
                break;
            default:
                break;
        }
    }

    private void mSet_FirebaseDatabase(boolean bFlag) {
        if(bFlag)
            myDB_Reference.child(strHeader).child(strCName).setValue(Customer_Value);
    }

    private void mGet_FirebaseDatabase() {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtFirebase.setText("Firebase Value : ");
                for(DataSnapshot postSnapshot: snapshot.getChildren()){
                    String strKey = postSnapshot.getKey();
                    txtFirebase.append("\n - Name : " + strKey);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("TAG: ", "Failed to read value.", error.toException());
            }
        };
        Query sortbyName = FirebaseDatabase.getInstance().getReference().child(strHeader).orderByChild(strCName);
        sortbyName.addListenerForSingleValueEvent(postListener);
    }
}