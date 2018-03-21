package com.example.masters.testsql12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Read var from Intent
        Intent intent= getIntent();
        String MemID = intent.getStringExtra("MemID");

        // Show Data
        ShowData(MemID);

        // btnCancel (Cancel)
        final Button cancel = (Button) findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Show
                Intent newActivity = new Intent(DetailActivity.this,ShowActivity.class);
                startActivity(newActivity);
            }
        });

    }

    public void ShowData(String MemID)
    {
        // txtMemberID, txtName, txtTel
        final TextView tMemberID = (TextView) findViewById(R.id.txtMemberID);
        final TextView tName = (TextView) findViewById(R.id.txtName);
        final TextView tTel = (TextView) findViewById(R.id.txtTel);

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

}