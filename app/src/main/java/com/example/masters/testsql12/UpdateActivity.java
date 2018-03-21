package com.example.masters.testsql12;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // Read var from Intent
        Intent intent= getIntent();
        final String MemID = intent.getStringExtra("MemID");

        // Show Data
        ShowData(MemID);

        // btnSave (Save)
        final Button save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // If Save Complete
                if(UpdateData(MemID))
                {
                    // Open Form ListUpdate
                    Intent newActivity = new Intent(UpdateActivity.this,ListUpdateActivity.class);
                    startActivity(newActivity);
                }
            }
        });

        // btnCancel (Cancel)
        final Button cancel = (Button) findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form ListUpdate
                Intent newActivity = new Intent(UpdateActivity.this,ListUpdateActivity.class);
                startActivity(newActivity);
            }
        });

    }

    public void ShowData(String MemID)
    {
        // txtMemberID, txtName, txtTel
        final TextView tMemberID = (TextView) findViewById(R.id.txtMemberID);
        final EditText tName = (EditText) findViewById(R.id.txtName);
        final EditText tTel = (EditText) findViewById(R.id.txtTel);

        // new Class DB
        final myDBClass myDb = new myDBClass(this);

        // Show Data
        String arrData[] = myDb.SelectData(MemID);
        if(arrData != null)
        {
            tMemberID.setText(arrData[0]);
            tName.setText(arrData[1]);
            tTel.setText(arrData[2]);
        }

    }

    public boolean UpdateData(String MemID)
    {

        // txtName, txtTel
        final EditText tName = (EditText) findViewById(R.id.txtName);
        final EditText tTel = (EditText) findViewById(R.id.txtTel);

        // Dialog
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();

        // Check Name
        if(tName.getText().length() == 0)
        {
            ad.setMessage("Please input [Name] ");
            ad.show();
            tName.requestFocus();
            return false;
        }

        // Check Tel
        if(tTel.getText().length() == 0)
        {
            ad.setMessage("Please input [Tel] ");
            ad.show();
            tTel.requestFocus();
            return false;
        }

        // new Class DB
        final myDBClass myDb = new myDBClass(this);

        // Save Data
        long saveStatus = myDb.UpdateData(MemID,
                tName.getText().toString(),
                tTel.getText().toString());
        if(saveStatus <=  0)
        {
            ad.setMessage("Error!! ");
            ad.show();
            return false;
        }

        Toast.makeText(UpdateActivity.this,"Update Data Successfully. ", Toast.LENGTH_SHORT).show();

        return true;

    }

}
