package com.androidtutorialshub.letzgoapp.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.androidtutorialshub.letzgoapp.R;
import com.androidtutorialshub.letzgoapp.adapters.ExpandableListAdapter;
import com.androidtutorialshub.letzgoapp.sql.DatabaseHelper;

public class List_Activity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private DatabaseHelper databaseHelper;
    private Activity activity = List_Activity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        databaseHelper=new DatabaseHelper(activity);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Toiletry");
        listDataHeader.add("Clothing");
        listDataHeader.add("Essential");
        listDataHeader.add("Footwear");
        listDataHeader.add("Electronics");
        listDataHeader.add("Misc.");

        // Adding child data
        Bundle bundle=getIntent().getExtras();
        String seas=bundle.getString("SEASON");
        String type1=bundle.getString("TYPE");

        List<String> Toiletry =databaseHelper.getItems("Toiletry",seas,type1);

        List<String> Clothing = databaseHelper.getItems("Clothing",seas,type1);

        List<String> Essential = databaseHelper.getItems("Essential",seas,type1);

        List<String> Footwear = databaseHelper.getItems("Footwear",seas,type1);

        List<String> Electronics = databaseHelper.getItems("Electronics",seas,type1);

        List<String> Misc = databaseHelper.getItems("Misc.",seas,type1);


        listDataChild.put(listDataHeader.get(0), Toiletry); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Clothing);
        listDataChild.put(listDataHeader.get(2), Essential);
        listDataChild.put(listDataHeader.get(3), Footwear); // Header, Child data
        listDataChild.put(listDataHeader.get(4), Electronics);
        listDataChild.put(listDataHeader.get(5), Misc);
    }
}