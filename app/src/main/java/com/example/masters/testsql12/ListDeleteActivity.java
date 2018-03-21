package com.example.masters.testsql12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListDeleteActivity extends AppCompatActivity {
    ArrayList<HashMap<String, String>> MebmerList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_delete);

        // Call Show List All Data
        ShowListData();

        // btnCancel (Cancel)
        final Button cancel = (Button) findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                Intent newActivity = new Intent(ListDeleteActivity.this,Main2Activity.class);
                startActivity(newActivity);
            }
        });

    }

    // Show List data
    public void ShowListData()
    {
        myDBClass myDb = new myDBClass(this);
        MebmerList = myDb.SelectAllData();

        // listView1
        ListView lisView1 = (ListView)findViewById(R.id.listView1);

        SimpleAdapter sAdap;
        sAdap = new SimpleAdapter(ListDeleteActivity.this, MebmerList, R.layout.activity_column,
                new String[] {"MemberID", "Name", "Tel"}, new int[] {R.id.ColMemberID, R.id.ColName, R.id.ColTel});
        lisView1.setAdapter(sAdap);
        registerForContextMenu(lisView1);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        //if (v.getId()==R.id.list) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle("Command for : " + MebmerList.get(info.position).get("Name").toString());
        String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
        for (int i = 0; i<menuItems.length; i++) {
            menu.add(Menu.NONE, i, i, menuItems[i]);
        }
        //}
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
        String CmdName = menuItems[menuItemIndex];
        String MemID = MebmerList.get(info.position).get("MemberID").toString();
        //String MemName = MebmerList.get(info.position).get("Name").toString();

        // Check Event Command
        if ("Edit".equals(CmdName)) {

            // Show on new activity
            Intent newActivity = new Intent(ListDeleteActivity.this,UpdateActivity.class);
//            newActivity.putExtra("MemID", MebmerList.get(info.position).get("ID").toString());
            startActivity(newActivity);

            // for Delete Command
        } else if ("Delete".equals(CmdName)) {

            myDBClass myDb = new myDBClass(this);

            long flg = myDb.DeleteData(MemID);
            if(flg > 0)
            {
                Toast.makeText(ListDeleteActivity.this,"Delete Data Successfully",
                        Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(ListDeleteActivity.this,"Delete Data Failed.",
                        Toast.LENGTH_LONG).show();
            }

            // Call Show Data again
            ShowListData();
        }

        return true;
    }


}