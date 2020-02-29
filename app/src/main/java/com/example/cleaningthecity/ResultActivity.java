package com.example.cleaningthecity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    private DatabaseHelper db;
    ArrayList<Player> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        db = new DatabaseHelper(this);

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);

        String name = getIntent().getStringExtra("PlayerName");
        int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText(score + "");
        arrayList= new ArrayList<>();
        arrayList = db.getAllData();
        /*int selected = db.getOldRecord();

        if ((arrayList.size()<10) && (score>selected)){
            //Delete old record
            deleteOldRecord(String.valueOf(selected));
        }
        arrayList = db.getAllData();
        if(arrayList.size()<10){
            db.insertDataRecords(score,name);
        }*/

        db.insertDataRecords(score,name);

        SharedPreferences settings = getSharedPreferences("HIGH_SCORE", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);

        if (score > highScore) {
            highScoreLabel.setText(getString(R.string.high_score) + highScore);

            // Update High Score
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();

        } else {
            highScoreLabel.setText(getString(R.string.high_score) + highScore);

        }

    }

    public void backMenu(View view) {
        startActivity(new Intent(getApplicationContext(), StartActivity.class));
    }

    public void tryAgain(View view) {
        String name = getIntent().getStringExtra("PlayerName");
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        Bundle extras = new Bundle();
        extras.putString("PlayerName",name);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /*public Integer deleteOldRecord(String selected){
        db = new DatabaseHelper(this);
        SQLiteDatabase da = db.getReadableDatabase();
        return da.delete(DatabaseHelper.TABLE_NAME, "score = ?", new String[]{selected});
    }*/
    

    // Disable Return Button
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }
}
