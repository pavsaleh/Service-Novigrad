package com.example.servicenovigrad;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;

public class ShowSearchedResultsAddress extends AppCompatActivity {

    ListView lv;
    FirebaseListAdapter adapter;
    public static String CurrentUser;
    DatabaseManager DBM;
    static String CurrentUserType;
    static String SelectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_services);
        Bundle bundle = getIntent().getExtras();
        CurrentUser = bundle.getString("username", "blank");
        CurrentUserType = bundle.getString("userType","employee");
        SelectedUser = bundle.getString("selectedUser", CurrentUser);
        DBM = DatabaseManager.getinstance();
        lv = (ListView) findViewById(R.id.listView);
        String queryText = bundle.getString("SearchString","");
        Query query = FirebaseDatabase.getInstance().getReference().child("profile").orderByChild("Address");
        FirebaseListOptions<userProfile> options = new FirebaseListOptions.Builder<userProfile>()
                .setLayout(R.layout.activity_see_profile)
                .setQuery(query, userProfile.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @SuppressLint("LongLogTag")
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {

                final TextView textAddress = findViewById(R.id.textAddress);
                TextView phoneNum = findViewById(R.id.phoneNum);
                TextView branchName = findViewById(R.id.branchName);
                TextView TitleText = findViewById(R.id.TitleText);
                TextView ratingbox = findViewById(R.id.ratingbox);

                final userProfile user = (userProfile) model;
                TitleText.setText(user.username.toString() + "'s Profile");
                textAddress.setText("Address: " + user.Address.toString());
                phoneNum.setText("Phone Number: " + user.Phonenumber.toString());
                branchName.setText("Branch Name: " + user.CompanyName.toString());

                Button seeRate = findViewById(R.id.seeRate);
                Button SeeUserAds = findViewById(R.id.button_userAds);
                Button RateMe = findViewById(R.id.RateMe);

                SeeUserAds.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        Intent i = new Intent(getApplicationContext(), specificServices.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        bundle.putString("selectedUser",SelectedUser);
                        bundle.putString("username",CurrentUser);
                        i.putExtras(bundle);
                        startActivity(i);
                        finish();
                    }
                });
                RateMe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        Intent i = new Intent(getApplicationContext(), RateUser.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        bundle.putString("selectedUser",SelectedUser);
                        bundle.putString("username",CurrentUser);
                        i.putExtras(bundle);
                        startActivity(i);
                        finish();
                    }
                });
                seeRate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        Intent i = new Intent(getApplicationContext(), DisplayReview.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        bundle.putString("username", SelectedUser);
                        i.putExtras(bundle);
                        startActivity(i);
                        finish();
                    }
                });
            }
        };
        //lv.setAdapter(adapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    /* public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), DisplayServices.class);
        Bundle bundle = new Bundle();
        bundle.putString("username", CurrentUser);
        i.putExtras(bundle);
        startActivity(i);
        finish();
    }

    private void getUserType(String username){
        DatabaseReference databaseUsers;
    } */
}