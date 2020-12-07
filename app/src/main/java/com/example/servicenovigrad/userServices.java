package com.example.servicenovigrad;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import android.widget.TextView;
import android.widget.Toast;

public class userServices extends AppCompatActivity {

    ListView lv;
    FirebaseListAdapter adapter;
    public static String CurrentUser;
    DatabaseManager DBM;
    static String CurrentUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_services);
        Bundle bundle = getIntent().getExtras();
        CurrentUser = bundle.getString("username", "blank");
        CurrentUserType = bundle.getString("userType","customer");
        DBM = DatabaseManager.getinstance();
        lv = (ListView) findViewById(R.id.listView);
        Query query = FirebaseDatabase.getInstance().getReference().child("services").orderByChild("username").equalTo(CurrentUser);
        FirebaseListOptions<Service> options = new FirebaseListOptions.Builder<Service>()
                .setLayout(R.layout.service)
                .setQuery(query, Service.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @SuppressLint("LongLogTag")
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView servicePrice = v.findViewById(R.id.servicePrice);
                TextView serviceProviderUsername = v.findViewById(R.id.serviceProviderUsername);
                TextView serviceType = v.findViewById(R.id.serviceType);
                TextView serviceHash = v.findViewById(R.id.serviceHash);

                final Service service = (Service) model;
                final User user = (User) model;
                servicePrice.setText("Price: $"+service.servicePrice.toString());
                serviceProviderUsername.setText("Service Provider: "+service.serviceProviderUsername.toString());
                serviceType.setText("Service Type: "+service.serviceType.toString());

                serviceHash.setText("Service Hash: "+service.serviceHash.toString());

                Button button_edit = v.findViewById(R.id.button_Edit);
                Button button_del = v.findViewById(R.id.button_Del);
                Button button_View_profile = v.findViewById(R.id.button_View_Profile);
                Button availability = v.findViewById(R.id.availability);
                Button copyService = v.findViewById(R.id.cloneAD);


                if (CurrentUser.equals(service.serviceProviderUsername.toString()) || CurrentUser.equals("admin")){
                    button_edit.setVisibility(View.VISIBLE);
                    button_del.setVisibility(View.VISIBLE);
                }

                if (CurrentUserType.equals("Employee") && service.serviceProviderUsername.equals("admin")) {
                    copyService.setVisibility(View.VISIBLE);
                }
                button_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBM.RemoveService(service.serviceHash.intValue(), service.serviceProviderUsername);
                        finish();
                        Toast.makeText(getApplicationContext(), "item deleted!" ,Toast.LENGTH_LONG).show();

                    }
                });
                button_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Bundle bundle = new Bundle();
                        Intent i = new Intent(getApplicationContext(), EditService.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        bundle.putString("username", CurrentUser);
                        bundle.putInt("ServiceHash", service.serviceHash.intValue());

                        i.putExtras(bundle);
                        startActivity(i);

                    }
                });
                button_View_profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        Intent i = new Intent(getApplicationContext(), seeProfile.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        bundle.putString("selectedUser", service.serviceProviderUsername);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                });
                availability.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        Intent i = new Intent(getApplicationContext(), seeAvailability.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        bundle.putString("selectedUser", service.serviceProviderUsername);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                });
                copyService.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBM.addService(CurrentUser,service.serviceType.toString(), service.servicePrice.doubleValue(),getApplicationContext(), service.informationList, service.documentList);

                    }
                });

            }
        };
        lv.setAdapter(adapter);
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

    private void getUserType(String username){
        DatabaseReference databaseUsers;
    }
}