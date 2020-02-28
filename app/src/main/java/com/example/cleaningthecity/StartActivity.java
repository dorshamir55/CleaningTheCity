package com.example.cleaningthecity;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.InterstitialAd;

public class StartActivity extends AppCompatActivity {

    private Button startButton;
    private EditText nameEditText;
    private Button recordsButton;
    //private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        startButton = (Button)(findViewById(R.id.btn_start));
        recordsButton = (Button)(findViewById(R.id.btn_records));
        nameEditText = (EditText)(findViewById(R.id.editText_name));

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEditText.getText().toString().trim().length()==0) { //trim=remove spaces to avoid blank name
                    nameEditText.setError(getString(R.string.check_enter_name));
                } else {
                    startGame();
                }
            }
        });

        recordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, RecordsActivity.class);
                startActivity(intent);
            }
        });
    }


    public void startGame() {
        //startActivity(new Intent(getApplicationContext(), MainActivity.class));
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        Bundle extras = new Bundle();
        extras.putString("PlayerName", nameEditText.getText().toString());
        intent.putExtras(extras);
        startActivity(intent);
    }

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
