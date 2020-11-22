package com.example.servicenovigrad;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    static TextView hellomessage;
    DatabaseManager DBM;
    Button seePosts;
    public String CurrentUser;
    static Button makePost;
    static Button editPost;
    static Button editHours;
    static Button editProfile;
    static TextView userType1;
    static Button button_seMyAds;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hellomessage = findViewById(R.id.welcomeName);
        DBM = DatabaseManager.getinstance();
        makePost = findViewById(R.id.Button_PostService);
        seePosts = findViewById(R.id.button_SeeServices);
        editPost = findViewById(R.id.Button_EditService);
        editHours = findViewById(R.id. button_Hours);
        editProfile=findViewById(R.id.button_editProfile);
        userType1=findViewById(R.id.userType);
        button_seMyAds = findViewById(R.id.button_seMyServices);
        DBM.SetNameandInfo(getApplicationContext(),bundle.getString("username", "blank"));
        CurrentUser = bundle.getString("username", "blank");
        Log.d("Current user is: ", CurrentUser);

        makePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                Intent i = new Intent(getApplicationContext(), makeNewService.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                bundle.putString("username", CurrentUser);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        seePosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                Intent i = new Intent(getApplicationContext(), DisplayServices.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                bundle.putString("username", CurrentUser);
                bundle.putString("userType", userType1.getText().toString() );
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        editHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                Intent i = new Intent(getApplicationContext(), HourSelect.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                bundle.putString("username", CurrentUser);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                Intent i = new Intent(getApplicationContext(), makeProfile.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                bundle.putString("username", CurrentUser);
                i.putExtras(bundle);
                startActivity(i);
                finish();
            }
        });
        button_seMyAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                Intent i = new Intent(getApplicationContext(), specificServices.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                bundle.putString("username", CurrentUser);
                i.putExtras(bundle);
                startActivity(i);
                finish();
            }
        });
    }
}