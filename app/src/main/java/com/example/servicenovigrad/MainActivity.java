package com.example.servicenovigrad;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    static TextView hellomessage;
    DatabaseManager DBM;
    private String CurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hellomessage = findViewById(R.id.welcomeName);
        DBM = DatabaseManager.getinstance();
        DBM.SetNameandInfo(getApplicationContext(),bundle.getString("username", "blank"));
        CurrentUser = bundle.getString("username", "blank");
        Log.d("Current user is: ", CurrentUser);
    }
}