package com.example.masters.testsql12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ListUpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_update);

        final myDBClass myDb = new myDBClass(this);
        final ArrayList<HashMap<String, String>> MebmerList = myDb.SelectAllData();

        // listView1
        ListView lisView1 = (ListView)findViewById(R.id.listView1);

        SimpleAdapter sAdap;
        sAdap = new SimpleAdapter(ListUpdateActivity.this, MebmerList, R.layout.activity_column,
                new String[] {"MemberID", "Name", "Tel"}, new int[] {R.id.ColMemberID, R.id.ColName, R.id.ColTel});
        lisView1.setAdapter(sAdap);

        lisView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {

                // Show on new activity
                Intent newActivity = new Intent(ListUpdateActivity.this,UpdateActivity.class);
                newActivity.putExtra("MemID", MebmerList.get(position).get("MemberID").toString());
                startActivity(newActivity);

            }
        });


        // btnCancel (Cancel)
        final Button cancel = (Button) findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                Intent newActivity = new Intent(ListUpdateActivity.this,Main2Activity.class);
                startActivity(newActivity);
            }
        });

    }


}

