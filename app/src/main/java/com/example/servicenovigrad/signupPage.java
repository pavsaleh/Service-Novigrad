package com.example.servicenovigrad;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class signupPage extends AppCompatActivity {
    EditText FirstName;
    EditText LastName;
    EditText EmailAddr;
    EditText Password;
    CheckBox isEmployee;
    EditText userName;

    String sFirstName;
    String sLastName;
    String sEmailAddr;
    String sPassword;
    String sUserName;

    Button Register;

    DatabaseManager DBM;

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        FirstName = findViewById(R.id.signup_Firstname);
        LastName = findViewById(R.id.signup_lastname);
        EmailAddr = findViewById(R.id.signup_Email);
        Password = findViewById(R.id.signup_Password);
        isEmployee = findViewById(R.id.checkBox_Employee);
        Register = findViewById(R.id.buttonRegister);
        addButtonListener();
        userName = findViewById(R.id.signup_Username);
        DBM = DatabaseManager.getinstance();
    }
    private void addButtonListener() {
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Registering Account", Toast.LENGTH_LONG).show();
                String ROLE;
                if(isEmployee.isChecked()){
                    ROLE = User.EMPLOYEE;
                } else{
                    ROLE = User.CUSTOMER;
                }

                sFirstName = FirstName.getText().toString();
                sLastName = LastName.getText().toString();
                sEmailAddr = EmailAddr.getText().toString();
                sPassword = Password.getText().toString();
                sUserName = userName.getText().toString();

                if(!(sUserName.equals("") || sFirstName.equals("")|| sLastName.equals("")||(!sEmailAddr.contains("@")))) {
                    DBM.checkAndSetUser(sUserName, sEmailAddr, sPassword, sFirstName, sLastName, ROLE, getApplicationContext());
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid or Blank Information", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}