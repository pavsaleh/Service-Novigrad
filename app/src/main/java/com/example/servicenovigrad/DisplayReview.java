package com.example.servicenovigrad;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class DisplayReview extends AppCompatActivity {

    ListView lv;
    FirebaseListAdapter adapter;
    public static String CurrentUser;
    DatabaseManager DBM;
    static String CurrentUserType;
    static String SelectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_review);
        Bundle bundle = getIntent().getExtras();
        CurrentUser = bundle.getString("username", "blank");
        CurrentUserType = bundle.getString("userType","homeowner");
        SelectedUser = bundle.getString("selectedUser", CurrentUser);
        DBM = DatabaseManager.getinstance();
        lv = (ListView) findViewById(R.id.listView);
        Query query = FirebaseDatabase.getInstance().getReference().child("Ratings").child(SelectedUser);
        FirebaseListOptions<Rating> options = new FirebaseListOptions.Builder<Rating>()
                .setLayout(R.layout.review)
                .setQuery(query, Rating.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @SuppressLint("LongLogTag")
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView rate = v.findViewById(R.id.rating);
                TextView reviewComment = v.findViewById(R.id.reviewComment);

                final Rating rating = (Rating) model;
                rate.setText("Rating: "+rating.Score+" out of 5");
                reviewComment.setText("Comment: "+rating.Comment.toString());
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
}
