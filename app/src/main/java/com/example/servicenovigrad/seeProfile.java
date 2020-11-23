package com.example.servicenovigrad;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.servicenovigrad.MainActivity.editHours;
import static com.example.servicenovigrad.MainActivity.editProfile;
import static com.example.servicenovigrad.MainActivity.hellomessage;
import static com.example.servicenovigrad.MainActivity.makePost;

public class seeProfile extends AppCompatActivity {
    TextView textAddress;
    TextView phoneNum;
    TextView branchName;
    TextView TitleText;
    String SelectedUser;
    Button SeeUserAds;
    String CurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        SelectedUser = bundle.getString("selectedUser", "blank");


        setContentView(R.layout.activity_see_profile);
        textAddress = findViewById(R.id.textAddress);
        phoneNum = findViewById(R.id.phoneNum);
        branchName = findViewById(R.id.branchName);
        TitleText = findViewById(R.id.TitleText);
        SeeUserAds = findViewById(R.id.button_userAds);

        DatabaseReference databaseUsers;
        databaseUsers = FirebaseDatabase.getInstance().getReference();

        Query query = databaseUsers.child("profile").orderByChild("username").equalTo(SelectedUser);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot U1 : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"
                        userProfile user = U1.getValue(userProfile.class);

                        if (user.username.equals(SelectedUser)) {
                            TitleText.setVisibility(View.VISIBLE);
                            textAddress.setVisibility(View.VISIBLE);
                            phoneNum.setVisibility(View.VISIBLE);
                            branchName.setVisibility(View.VISIBLE);

                            TitleText.setText(user.username.toString() + "'s Profile");
                            textAddress.setText("Address: "+ user.Address);
                            phoneNum.setText("Phone Number: " +user.Phonenumber);
                            branchName.setText("Branch Name: " + user.CompanyName.toString());
                        } else {
                            Toast.makeText(getApplicationContext(), "no user logged in", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "User's profile not found", Toast.LENGTH_LONG).show();
                    TitleText.setText(SelectedUser + " does not have a profile yet!");
                    TitleText.setVisibility(View.VISIBLE);
                    textAddress.setVisibility(View.INVISIBLE);
                    phoneNum.setVisibility(View.INVISIBLE);
                    branchName.setVisibility(View.INVISIBLE);
                }
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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}