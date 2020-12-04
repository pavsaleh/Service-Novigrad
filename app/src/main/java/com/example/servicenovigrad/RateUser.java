package com.example.servicenovigrad;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RateUser extends AppCompatActivity {
    RadioButton rate1;
    RadioButton rate2;
    RadioButton rate3;
    RadioButton rate4;
    RadioButton rate5;
    EditText RatingComment;
    Button SubmitRating;
    String CurrentUser;
    String CurrentUserType;
    String SelectedUser;
    DatabaseManager DBM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_user);
        findViews();
        Bundle bundle = getIntent().getExtras();
//        CurrentUser = bundle.getString("username", "blank");

        CurrentUser = bundle.getString("username", "blank");
        CurrentUserType = bundle.getString("userType","employee");
        SelectedUser = bundle.getString("selectedUser", CurrentUser);
        DBM = DatabaseManager.getinstance();
        addButtons();
    }

    private void findViews(){
        rate1 = findViewById(R.id.rate1);
        rate2 = findViewById(R.id.rate2);
        rate3 = findViewById(R.id.rate3);
        rate4 = findViewById(R.id.rate4);
        rate5 = findViewById(R.id.rate5);
        RatingComment = findViewById(R.id.comment);
        SubmitRating = findViewById(R.id.SubmitRating);
    }
    private void addButtons(){
        SubmitRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(CurrentUser.equals(SelectedUser))){
                    int Selectedscore = 0;
                    if(rate1.isChecked()){
                        Selectedscore = 1;
                    }
                    if(rate2.isChecked()){
                        Selectedscore = 2;
                    }
                    if(rate3.isChecked()){
                        Selectedscore = 3;
                    }
                    if(rate4.isChecked()){
                        Selectedscore = 4;
                    }
                    if(rate5.isChecked()){
                        Selectedscore = 5;
                    }

                    Rating rating = new Rating(CurrentUser, SelectedUser,  Selectedscore, RatingComment.getText().toString());
                    DBM.AddRating(rating, SelectedUser);
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "You Cant Rate Yourself!" ,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}