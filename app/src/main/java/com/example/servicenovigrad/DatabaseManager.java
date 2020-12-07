package com.example.servicenovigrad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.List;
import static android.content.ContentValues.TAG;
import static com.example.servicenovigrad.MainActivity.hellomessage;
import static com.example.servicenovigrad.MainActivity.makePost;
import static com.example.servicenovigrad.MainActivity.button_seMyAds;
import static com.example.servicenovigrad.MainActivity.editHours;
import static com.example.servicenovigrad.MainActivity.editProfile;
import static com.example.servicenovigrad.MainActivity.userType1;
import static com.example.servicenovigrad.MainActivity.deleteUsers;
import static com.example.servicenovigrad.MainActivity.requests;

public class DatabaseManager {
    private static DatabaseManager instance;
    DatabaseReference databaseUsers;

    public static DatabaseManager getinstance() {
        if (instance == null)
            instance = new DatabaseManager();
        return instance;
    }

    public void CreateUser(String username, String email, String password, String FirstName, String LastName, String AccountType) {
        User user = new User(username, email, password, FirstName, LastName, AccountType);
        String userID = databaseUsers.push().getKey();
        databaseUsers.child("users").child(username).setValue(user);
    }

    public void AddRating(Rating r, String username){
        int Hash = Math.abs ((username+ r.toString()).hashCode());// unique hashcode
        databaseUsers.child("Ratings").child(username).child(Hash+"").setValue(r);
        UpdateRatingCounter(username, r.Score);
        UpdateAverageRating(username, r.Score);
    }

    private void UpdateAverageRating(final String username, final int newScore){
        Query query = databaseUsers.child("users").orderByChild("username").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double newAverageScore;
                for (DataSnapshot U1 : dataSnapshot.getChildren()) {
                    User user = U1.getValue(User.class);
                    if (user.RatingCount >= 1) {
                        newAverageScore = ((user.RatingCount - 1) * user.CurrentRating + newScore) / user.RatingCount;
                        databaseUsers.child("users").child(username).child("CurrentRating").setValue(newAverageScore);
                    }
                    else{
                        databaseUsers.child("users").child(username).child("CurrentRating").setValue(newScore);
                    }
                }
                return;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });
    }

    private  void UpdateRatingCounter(final String username, final int score){
        Query query = databaseUsers.child("users").orderByChild("username").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot U1 : dataSnapshot.getChildren()) {
                    User user = U1.getValue(User.class);
                    databaseUsers.child("users").child(username).child("RatingCount").setValue(user.RatingCount + 1);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });
    }
    private void GetRating(){
    }

    public void addService(String serviceProviderUsername, String serviceType, Double servicePrice, Context C, List<String> informationList, List<String> documentList){
        int serviceHash = Math.abs ((serviceProviderUsername.toString()+serviceType+servicePrice.toString()).hashCode());
        Service service = new Service(serviceProviderUsername, serviceType, servicePrice, serviceHash, informationList, documentList);
        databaseUsers.child("services").child(Integer.toString(serviceHash)).setValue(service);
        attachServicetoUser(serviceProviderUsername, serviceHash, service);
        Toast.makeText(C, "Your service was added", Toast.LENGTH_LONG).show();
        Bundle bundle = new Bundle();
        Intent i = new Intent(C, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        bundle.putString("username", serviceProviderUsername);
        i.putExtras(bundle);
        C.startActivity(i);
    }

    public void loginFIREBASE(final Context context, final String Username, final String pword) {
        Query query = databaseUsers.child("users").orderByChild("username").equalTo(Username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Log.d("datasnapExists", "Made it here");
                    for (DataSnapshot U1 : dataSnapshot.getChildren()) {
                        User user = U1.getValue(User.class);

                        if (user.password.equals(pword)) {
                            Toast.makeText(context, "Logging in", Toast.LENGTH_LONG).show();
                            Bundle bundle = new Bundle();
                            Intent i = new Intent(context, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            bundle.putString("username", Username);
                            i.putExtras(bundle);
                            context.startActivity(i);
                        } else {
                            Toast.makeText(context, "Wrong Password", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(context, "User not found", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    public void SetNameandInfo(final Context context, final String Username) {
        Query query = databaseUsers.child("users").orderByChild("username").equalTo(Username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot U1 : dataSnapshot.getChildren()) {
                        User user = U1.getValue(User.class);
                        if (user.username.equals(Username)) {
                            hellomessage.setText("Hello " + user.FirstName + "\nYou are a user of type " + user.AccountType);
                            userType1.setText(user.AccountType);
                            if (user.AccountType.toString().equals("Employee") || user.AccountType.toString().equals("admin")) {

                                editHours.setVisibility(Button.VISIBLE);
                                editProfile.setVisibility(Button.VISIBLE);
                                button_seMyAds.setVisibility(Button.VISIBLE);
                                requests.setVisibility(Button.VISIBLE);
                                if (user.AccountType.toString().equals("admin")) {
                                    editHours.setVisibility(Button.INVISIBLE);
                                    editProfile.setVisibility(Button.INVISIBLE);
                                    makePost.setVisibility(Button.VISIBLE);
                                    deleteUsers.setVisibility(Button.VISIBLE);
                                }
                            }
                        } else {
                            Toast.makeText(context, "No user logged in", Toast.LENGTH_LONG).show();
                            makePost.setVisibility(Button.INVISIBLE);
                        }
                    }
                } else {
                    Toast.makeText(context, "User not found", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    public void checkAndSetAdmin() {
        Query query = databaseUsers.child("users").equalTo("admin");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean adminExists;
                if (dataSnapshot.exists()) {
                    for (DataSnapshot U1 : dataSnapshot.getChildren()) {
                        User user = U1.getValue(User.class);
                        if (user.username.equals("Admin")) {
                            adminExists = true;
                        } else {
                        }
                    }
                } else {
                    adminExists = false;
                    if(!adminExists){
                        CreateUser("admin","admin",
                                "admin","admin","admin",User.ADMIN);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void checkAndSetUser(final String username, final String email, final String password, final String FirstName, final String LastName, final String AccountType, final Context context) { //checks if user exists, if it doesnt, it creates one
        Query query = databaseUsers.child("users").orderByChild("username").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean UserExists;
                if (dataSnapshot.exists()) {
                    for (DataSnapshot U1 : dataSnapshot.getChildren()) {
                        User user = U1.getValue(User.class);
                        if (user.username.equals(username)) {
                            Log.d("WTF", user.AccountType);
                            UserExists = true;
                            Log.d("Account creation", "User already exists");
                            Toast.makeText(context, "Created user already exists, try again", Toast.LENGTH_LONG).show();
                        } else {}
                    }
                } else {
                    Log.d("WTF", "User not found");
                    UserExists = false;
                    if(!UserExists){
                        CreateUser(username,email,password,FirstName,LastName,AccountType);
                        Intent intent = new Intent(context, Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(context, "Login using those new credentials", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    public void RemoveService(int serviceID, String Username) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference().child("services").child(Integer.toString(serviceID));
        dR.removeValue();
        DatabaseReference dR2 = FirebaseDatabase.getInstance().getReference().child("users").child(Username).child("so").child(Integer.toString(serviceID));
        dR2.removeValue();
    }

    public void DeleteUser (String username){
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference().child("users").child((username));
        dR.removeValue();
    }

    public void DeleteServiceRequest(String firstName) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference().child("Bookings").child(firstName);
        dR.removeValue();
    }

    private void attachServicetoUser(final String username, final int ServiceHash, final Service service) {
        Query query = databaseUsers.child("users").orderByChild("username").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot U1 : dataSnapshot.getChildren()) {
                        User user = U1.getValue(User.class);
                        if (user.username.equals(username)) {
                            databaseUsers.child("users").child(username).child("so").child(String.valueOf(ServiceHash)).setValue(service);
                        } else {
                        }
                    }
                } else {

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void CreateHours(String username, AvailableHours availableHours){
        databaseUsers.child("hours").child(username).setValue(availableHours);
    }

    public void CreateProfile(String username, userProfile profile){
        databaseUsers.child("profile").child(username).setValue(profile);
    }

    public void AcceptServiceRequests(Booking appointment){
        int bookingHash = Math.abs ((appointment.providerName.toString()+appointment.customerName+appointment.startTime.toString()+appointment.endTime.toString()+appointment.serviceName.toString()).hashCode());// unique hashcode
        databaseUsers.child("Bookings").child("Accepted bookings").child(bookingHash +"").setValue(appointment);
    }

    public void CreateBooking(Booking appointment){
        int bookingHash = Math.abs ((appointment.providerName.toString()+appointment.customerName+appointment.startTime.toString()+appointment.endTime.toString()+appointment.serviceName.toString()).hashCode());// unique hashcode
        databaseUsers.child("Bookings").child(appointment.providerName).child(bookingHash +"").setValue(appointment);
    }

    private DatabaseManager(){
        databaseUsers = FirebaseDatabase.getInstance().getReference();
    }
}