package com.example.servicenovigrad;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class seeAvailability extends AppCompatActivity {

    TextView Monday;
    TextView Tuesday;
    TextView Wednesday;
    TextView Thursday;
    TextView Friday;
    TextView Saturday;
    TextView Sunday;
    String SelectedUser;
    TextView Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_availability);
        Monday=findViewById(R.id.mon);
        Tuesday=findViewById(R.id.tues);
        Wednesday= findViewById(R.id.wed);
        Thursday=findViewById(R.id.thurs);
        Friday=findViewById(R.id.fri);
        Saturday = findViewById(R.id.sat);
        Sunday = findViewById(R.id.sun);
        Title = findViewById(R.id.Title);

        Bundle bundle = getIntent().getExtras();
        SelectedUser = bundle.getString("selectedUser", "blank");

        DatabaseReference databaseUsers;
        databaseUsers = FirebaseDatabase.getInstance().getReference();

        Query query = databaseUsers.child("hours").orderByChild("username").equalTo(SelectedUser);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot U1 : dataSnapshot.getChildren()) {
                        AvailableHours user = U1.getValue(AvailableHours.class);

                        if (user.username.equals(SelectedUser)) {
                            Title.setVisibility(View.VISIBLE);
                            Monday.setVisibility(View.VISIBLE);
                            Tuesday.setVisibility(View.VISIBLE);
                            Wednesday.setVisibility(View.VISIBLE);
                            Thursday.setVisibility(View.VISIBLE);
                            Friday.setVisibility(View.VISIBLE);
                            Saturday.setVisibility(View.VISIBLE);
                            Sunday.setVisibility(View.VISIBLE);
                            Title.setText(SelectedUser +"'s hours of availability");

                            Monday.setText("Monday: "+user.MonStart +"-" +user.MonEnd);
                            if(user.MonStart.equals("0:00") && user.MonEnd.equals("0:00")){
                                Monday.setText("Monday: " + "Unavailable");
                            }
                            Tuesday.setText("Tuesday: "+user.TuesStart +"-" +user.TuesEnd);
                            if(user.TuesStart.equals("0:00") && user.TuesEnd.equals("0:00")){
                                Tuesday.setText("Tuesday: " + "Unavailable");
                            }
                            Wednesday.setText("Wednesday: "+user.WedStart +"-" +user.WedEnd);
                            if(user.WedStart.equals("0:00") && user.WedEnd.equals("0:00")){
                                Wednesday.setText("Wednesday: " + "Unavailable");
                            }
                            Thursday.setText("Thursday: "+user.ThursStart +"-" +user.ThursEnd);
                            if(user.ThursStart.equals("0:00") && user.ThursEnd.equals("0:00")){
                                Thursday.setText("Thursday: " + "Unavailable");
                            }
                            Friday.setText("Friday: "+user.FriStart +"-" +user.FriEnd);
                            if(user.FriStart.equals("0:00") && user.FriEnd.equals("0:00")){
                                Friday.setText("Friday: " + "Unavailable");
                            }
                            Saturday.setText("Saturday: "+user.SatStart +"-" +user.SatEnd);
                            if(user.SatStart.equals("0:00") && user.SatEnd.equals("0:00")){
                                Saturday.setText("Saturday: " + "Unavailable");
                            }
                            Sunday.setText("Sunday: "+user.SunStart +"-" +user.SunEnd);
                            if(user.SunStart.equals("0:00") && user.SunEnd.equals("0:00")){
                                Sunday.setText("Sunday: " + "Unavailable");
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "no used logged in", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "User has not set their hours yet", Toast.LENGTH_LONG).show();

                    Title.setVisibility(View.VISIBLE);
                    Title.setText("User has not set their hours yet");
                    Monday.setVisibility(View.INVISIBLE);
                    Tuesday.setVisibility(View.INVISIBLE);
                    Wednesday.setVisibility(View.INVISIBLE);
                    Thursday.setVisibility(View.INVISIBLE);
                    Friday.setVisibility(View.INVISIBLE);
                    Saturday.setVisibility(View.INVISIBLE);
                    Sunday.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}