package com.example.servicenovigrad;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

public class Requests extends AppCompatActivity {
    ListView lv;
    FirebaseListAdapter adapter;
    public static String CurrentUser;
    DatabaseManager DBM;
    static String CurrentUserType;
    public String SelectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_requests);
        Bundle bundle = getIntent().getExtras();
        CurrentUser = bundle.getString("username", "blank");
        CurrentUserType = bundle.getString("userType", "employee");
        SelectedUser = bundle.getString("selectedUser", "blank");
        DBM = DatabaseManager.getinstance();
        lv = (ListView) findViewById(R.id.listView);
        Query query = FirebaseDatabase.getInstance().getReference().child("Bookings");

        FirebaseListOptions<Booking> options = new FirebaseListOptions.Builder<Booking>()
                .setLayout(R.layout.requests)
                .setQuery(query, Booking.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @SuppressLint("LongLogTag")
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                final TextView firstName = v.findViewById(R.id.firstName);
                final TextView lastName = v.findViewById(R.id.lastName);
                final TextView DOB = v.findViewById(R.id.DOB);
                final TextView address = v.findViewById(R.id.address);
               /* final TextView serviceName = v.findViewById(R.id.serviceName);
                TextView customerName = v.findViewById(R.id.customerName);
                TextView providerName = v.findViewById(R.id.providerName);
                final TextView startTime = v.findViewById(R.id.startTime);
                final TextView endTime = v.findViewById(R.id.endTime);*/

                final Booking booking = (Booking) model;

                firstName.setText("First Name: " + booking.firstName);
                lastName.setText("Last Name: " + booking.lastName);
                //customerName.setText("Username: " + customerName.getText().toString());
                DOB.setText("Date of birth: " + booking.DOB);
                address.setText("Address: " + booking.address);
                //serviceName.setText("Service name: " + serviceName.getText().toString());
                //providerName.setText("Service provider: " + providerName.getText().toString());
                //startTime.setText("Service provider: " + startTime.getText().toString());
                //endTime.setText("Service provider: " + endTime.getText().toString());

                Button button_no = v.findViewById(R.id.button_no);
                Button button_yes = v.findViewById(R.id.button_yes);

                if (CurrentUserType.equals("EMPLOYEE")) {
                    button_no.setVisibility(View.VISIBLE);
                    button_yes.setVisibility(View.VISIBLE);
                }

                button_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Booking appointment = new Booking (CurrentUser, SelectedUser, startTime[0], endTime[0],serviceType, firstName.getText().toString(), lastName.getText().toString(), DOB.getText().toString(), editAddress.getText().toString());
                        DBM.DeleteServiceRequest(booking.firstName);
                        finish();
                        Toast.makeText(getApplicationContext(), "Request rejected!", Toast.LENGTH_LONG).show();
                    }
                });
                button_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Booking appointment = new Booking (booking.customerName, booking.providerName, booking.startTime, booking.endTime, booking.serviceName, booking.firstName, booking.lastName, booking.DOB, booking.address);
                        DBM.AcceptServiceRequests(appointment);
                        finish();
                        Toast.makeText(getApplicationContext(), "Request accepted!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        };
        lv.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}