package com.example.servicenovigrad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText loginUsername;
    EditText loginPassword;
    Button buttonSignin;
    Button buttonRegister;
    Button buttonAbout;
    DatabaseManager DBM;

    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPassword);
        buttonSignin = findViewById(R.id.buttonSignin);
        buttonAbout =  findViewById(R.id.buttonAbout);
        buttonRegister = findViewById(R.id.buttonRegister);
        DBM = DatabaseManager.getinstance();
        DBM.CreateUser(User.ADMIN,User.ADMIN, User.ADMIN, User.ADMIN,User.ADMIN, User.ADMIN);

        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBM.loginFIREBASE(getApplicationContext(), loginUsername.getText().toString(), loginPassword.getText().toString());
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Let's sign up", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Login.this, signupPage.class);
                startActivity(intent);
            }
        });

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Let's get to know Service Novigrad", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Login.this, About.class);
                startActivity(intent);
            }
        });
    }
}