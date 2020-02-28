package com.example.cleaningthecity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class RecordsActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        listView = (ListView)(findViewById(R.id.listView_records));
        databaseHelper = new DatabaseHelper(this);

    }
}
