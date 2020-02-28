package com.example.cleaningthecity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class RecordsActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ListView listView;
    private ArrayList<Player> arrayList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        listView = (ListView)(findViewById(R.id.listView_records));
        databaseHelper = new DatabaseHelper(this);
        arrayList= new ArrayList<>();
        loadDataInListView();
    }

    private void loadDataInListView() {
        arrayList = databaseHelper.getAllData();
        myAdapter = new MyAdapter(this,arrayList);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
}
