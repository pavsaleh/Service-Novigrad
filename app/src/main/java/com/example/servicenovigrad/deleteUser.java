package com.example.servicenovigrad;

import android.annotation.SuppressLint;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class deleteUser extends AppCompatActivity {
    ListView lv;
    FirebaseListAdapter adapter;
    public static String CurrentUser;
    DatabaseManager DBM;
    static String CurrentUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_users);
        Bundle bundle = getIntent().getExtras();
        CurrentUser = bundle.getString("username", "blank");
        CurrentUserType = bundle.getString("userType", "admin");
        DBM = DatabaseManager.getinstance();
        lv = (ListView) findViewById(R.id.listView);
        Query query = FirebaseDatabase.getInstance().getReference().child("users");

        FirebaseListOptions<User> options = new FirebaseListOptions.Builder<User>()
                .setLayout(R.layout.user)
                .setQuery(query, User.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @SuppressLint("LongLogTag")
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView userType = v.findViewById(R.id.userType);
                TextView userFirstName = v.findViewById(R.id.userFirstName);
                TextView userLastName = v.findViewById(R.id.userLastName);
                TextView userEmail = v.findViewById(R.id.userEmail);
                final TextView userUsername = v.findViewById(R.id.userUsername);

                final User user = (User) model;
                if (!"admin".equals(user.username)) {
                    userType.setText("Account Type: " + user.AccountType.toString());
                    userFirstName.setText("First Name: " + user.FirstName.toString());
                    userLastName.setText("Last Name: " + user.LastName.toString());
                    userEmail.setText("Email: " + user.email.toString());
                    userUsername.setText("Username: " + user.username.toString());

                    Button button_del = v.findViewById(R.id.button_Del);

                    if (CurrentUser.equals(user.username.toString()) || CurrentUser.equals("admin")) {
                        button_del.setVisibility(View.VISIBLE);
                    }

                    button_del.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DBM.DeleteUser(user.username.toString());
                            finish();
                            Toast.makeText(getApplicationContext(), "User deleted!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
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