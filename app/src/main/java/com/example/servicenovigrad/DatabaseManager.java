package com.example.servicenovigrad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.servicenovigrad.MainActivity.hellomessage;

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
                            hellomessage.setText("Hello " + Username+ "\nYou are a user of type " + user.AccountType);
                        } else {
                            Toast.makeText(context, "no user logged in", Toast.LENGTH_LONG).show();
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
                        } else {}
                    }
                } else {
                    adminExists = false;
                    if(!adminExists){
                        CreateUser("admin","admin","admin","admin","admin",User.ADMIN);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
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
                            Log.d("Account creation", "User already existss");
                            Toast.makeText(context, "Created user already exists, try again", Toast.LENGTH_LONG).show();
                        } else {}
                    }
                } else {
                    Log.d("WTF", "User not found");
                    UserExists = false;
                    if(!UserExists){
                        CreateUser(username,email,password,FirstName,LastName,AccountType);
                        Intent intent = new Intent(context, Login.class);
                        context.startActivity(intent);
                        Toast.makeText(context, "Login using those new credentials", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    /*public String AccountType (String username ){
        Iterator<User> it = fakeDB.iterator();
        while(it.hasNext()){
            User temp = it.next();
            if(temp.username.equals(username)){
                Log.e("AccountType", temp.AccountType);
                return temp.AccountType;
            }
        }
        throw new NullPointerException("no user found");
    }

    public String AccountList (String username ) {
        Iterator<User> it = fakeDB.iterator();
        String userList = "\n Heres a list of all users \n";
        String tempuser;
        while (it.hasNext()) {
            User temp = it.next();
            tempuser = "User: " + temp.username + "Role: " + temp.AccountType;
            userList = userList + "\n";
        }
        return userList;
    }

    public  String getName(String username) {
        Iterator<User> it = fakeDB.iterator();
        while (it.hasNext()) {
            User temp = it.next();
            if (temp.username.equals(username)) {
                Log.e("firstname", temp.FirstName);
                return temp.FirstName;
            }
        }
        throw new NullPointerException("no user found");
    }

    public boolean verifyCredentials(String username, String Password){
        firebase.auth().
        Iterator<User> it = fakeDB.iterator();
        while(it.hasNext()){
            User temp = it.next();
            if(temp.username.equals(username)){
                Log.e("firstname", temp.FirstName);
                return temp.password.equals(Password);
            }
        }
        return false;
    }*/

    private DatabaseManager(){
        databaseUsers = FirebaseDatabase.getInstance().getReference();
    }
}
