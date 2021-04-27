package com.inhatc.android_alertdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ToggleButton objTGButton;   // ToggleButton object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objTGButton = (ToggleButton) findViewById(R.id.tgbtnSwitch);
        objTGButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Call onCreateDialog(int), onPrepareDialog(int) method
        showDialog(0);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        super.onCreateDialog(id);

        AlertDialog dlgAlert;   // AlertDialog object
        final String items[] = {"통화 계속", "통화 종료"};

        dlgAlert = new AlertDialog.Builder(this)
                .setIcon(R.drawable.question)
                .setTitle("알림 !")                   // Setting Title
                .setMessage("통화시간 3분 초과!")
                .setView(createCustomView())    // Setting CustomView를 통해 ListView로 구현하는 방법

// Alert Dialog를 RadioButton으로 구현하는 방법
//                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int index) {
//                        String strData = null;
//                        if (index == 0) strData = "통화중....";
//                        else strData = "통화를 종료합니다.";
//                        Toast.makeText(MainActivity.this, strData, Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setView(null)                      // Setting CustomView

                // Create Listener object for AlertDialog Button event handler
                .setPositiveButton("확인(OK)", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();  // dlgAlert에 AlertView return

        return dlgAlert;
    }

    // Create AlertDialog CustomView
    private LinearLayout createCustomView() {
        LinearLayout objLayoutView;         // LinearLayout object
        ListView lstViewCalling;            // ListView object
        ArrayList<String> lstItmes;         // ArrayList object
        ArrayAdapter<String> aryADTItems;   // ArrayAdapter object

        objLayoutView = new LinearLayout(this);
        lstViewCalling = new ListView(this);
        lstItmes = new ArrayList<String>();
        lstItmes.add("통화 계속");
        lstItmes.add("통화 종료");

        aryADTItems = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, lstItmes);

        lstViewCalling.setAdapter(aryADTItems);
        lstViewCalling.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lstViewCalling.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                String strData = null;
                if (index == 0) strData = "통화중....";
                else strData = "통화를 종료합니다.";
                Toast.makeText(MainActivity.this, strData, Toast.LENGTH_SHORT).show();
            }
        });
        objLayoutView.setOrientation(LinearLayout.VERTICAL);
        objLayoutView.addView(lstViewCalling);
        return objLayoutView;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
    }
}