package com.example.servicenovigrad;

import android.app.TimePickerDialog;
import android.content.Intent;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HourSelect extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    Switch monday;
    TextView EditTextMonStart;
    TextView EditTextMonEnd;
    Switch tuesday;
    TextView EditTextTuesStart;
    TextView EditTextTuesEnd;
    Switch wednesday;
    TextView EditTextWedStart;
    TextView EditTextWedEnd;
    Switch thursday;
    TextView EditTextThursStart;
    TextView EditTextThursEnd;
    Switch friday;
    TextView EditTextFriStart;
    TextView EditTextFriEnd;
    Switch saturday;
    TextView EditTextSatStart;
    TextView EditTextSatEnd;
    Switch sunday;
    TextView EditTextSunStart;
    TextView EditTextSunEnd;
    Button SubmitTime;
    DatabaseManager DBM;


    private static String CurrentUser;

    boolean timeMonStart = false;
    boolean timeMonEnd = false;

    boolean timeTuesStart = false;
    boolean timeTuesEnd = false;

    boolean timeWedStart = false;
    boolean timeWedEnd = false;

    boolean timeThursStart = false;
    boolean timeThursEnd = false;

    boolean timeFriStart = false;
    boolean timeFriEnd = false;

    boolean timeSatStart = false;
    boolean timeSatEnd = false;

    boolean timeSunStart = false;
    boolean timeSunEnd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour_select);
        Bundle bundle = getIntent().getExtras();
        CurrentUser = bundle.getString("username", "blank");
        DBM = DatabaseManager.getinstance();
        Initialization();

        EditTextMonStart = findViewById(R.id.editTextMonStart);
        EditTextMonEnd = findViewById(R.id.editTextMonEnd);

        EditTextTuesStart = findViewById(R.id.editTextTuesStart);
        EditTextTuesEnd = findViewById(R.id.editTextTuesEnd);

        EditTextWedStart = findViewById(R.id.editTextWedStart);
        EditTextWedEnd = findViewById(R.id.editTextWedEnd);

        EditTextThursStart = findViewById(R.id.editTextThursStart);
        EditTextThursEnd = findViewById(R.id.editTextThursEnd);

        EditTextFriStart = findViewById(R.id.editTextFriStart);
        EditTextFriEnd = findViewById(R.id.editTextFriEnd);

        EditTextSatStart = findViewById(R.id.editTextSatStart);
        EditTextSatEnd = findViewById(R.id.editTextSatEnd);

        EditTextSunStart = findViewById(R.id.editTextSunStart);
        EditTextSunEnd = findViewById(R.id.editTextSunEnd);

        EditTextMonStart.setEnabled(false);
        EditTextMonStart.setText("0:00");

        EditTextMonEnd.setEnabled(false);
        EditTextMonEnd.setText("0:00");

        EditTextTuesStart.setEnabled(false);
        EditTextTuesStart.setText("0:00");

        EditTextTuesEnd.setEnabled(false);
        EditTextTuesEnd.setText("0:00");

        EditTextWedStart.setEnabled(false);
        EditTextWedStart.setText("0:00");

        EditTextWedEnd.setEnabled(false);
        EditTextWedEnd.setText("0:00");

        EditTextThursStart.setEnabled(false);
        EditTextThursStart.setText("0:00");

        EditTextThursEnd.setEnabled(false);
        EditTextThursEnd.setText("0:00");

        EditTextFriStart.setEnabled(false);
        EditTextFriStart.setText("0:00");

        EditTextFriEnd.setEnabled(false);
        EditTextFriEnd.setText("0:00");

        EditTextSatStart.setEnabled(false);
        EditTextSatStart.setText("0:00");

        EditTextSatEnd.setEnabled(false);
        EditTextSatEnd.setText("0:00");

        EditTextSunStart.setEnabled(false);
        EditTextSunStart.setText("0:00");

        EditTextSunEnd.setEnabled(false);
        EditTextSunEnd.setText("0:00");

        EditTextMonStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(),"Pick Time");
                timeMonStart = true;
            }
        });

        EditTextMonEnd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(),"Pick Time");
                timeMonEnd = true;
            }
        });

        EditTextTuesStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(),"Pick Time");
                timeTuesStart = true;
            }
        });

        EditTextTuesEnd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(),"Pick Time");
                timeTuesEnd = true;
            }
        });

        EditTextWedStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(),"Pick Time");
                timeWedStart = true;
            }
        });

        EditTextWedEnd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(),"Pick Time");
                timeWedEnd = true;
            }
        });

        EditTextThursStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(),"Pick Time");
                timeThursStart = true;
            }
        });

        EditTextThursEnd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(),"Pick Time");
                timeThursEnd = true;
            }
        });

        EditTextFriStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(),"Pick Time");
                timeFriStart = true;
            }
        });

        EditTextFriEnd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(),"Pick Time");
                timeFriEnd = true;
            }
        });

        EditTextSatStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(),"Pick Time");
                timeSatStart = true;
            }
        });

        EditTextSatEnd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(),"Pick Time");
                timeSatEnd = true;
            }
        });

        EditTextSunStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(),"Pick Time");
                timeSunStart = true;
            }
        });

        EditTextSunEnd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(),"Pick Time");
                timeSunEnd = true;
            }
        });
        addButtonListener();
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        if (timeMonStart) {
            if (minute < 10) {
                EditTextMonStart.setText(hourOfDay + ":0" + minute);
            } else {
                EditTextMonStart.setText(hourOfDay + ":" + minute);
            }
            timeMonStart = false;

        }else if (timeMonEnd) {
            if (minute<10) {
                EditTextMonEnd.setText(hourOfDay + ":0" + minute);
            }else{
                EditTextMonEnd.setText(hourOfDay + ":" + minute);
            }
            timeMonEnd = false;

        }else if (timeTuesStart) {
            if (minute<10) {
                EditTextTuesStart.setText(hourOfDay + ":0" + minute);
            }else{
                EditTextTuesStart.setText(hourOfDay + ":" + minute);
            }
            timeTuesStart = false;

        }else if (timeTuesEnd) {
            if (minute<10) {
                EditTextTuesEnd.setText(hourOfDay + ":0" + minute);
            }else{
                EditTextTuesEnd.setText(hourOfDay + ":" + minute);
            }
            timeTuesEnd = false;

        }else if (timeWedStart) {
            if (minute<10) {
                EditTextWedStart.setText(hourOfDay + ":0" + minute);
            }else{
                EditTextWedStart.setText(hourOfDay + ":" + minute);
            }
            timeWedStart = false;

        }else if (timeWedEnd){
            if (minute<10) {
                EditTextWedEnd.setText(hourOfDay + ":0" + minute);
            }else{
                EditTextWedEnd.setText(hourOfDay + ":" + minute);
            }
            timeWedEnd =false;

        }else if (timeThursStart){
            if (minute<10) {
                EditTextThursStart.setText(hourOfDay + ":0" + minute);
            }else{
                EditTextThursStart.setText(hourOfDay + ":" + minute);
            }
            timeThursStart =false;

        }else if (timeThursEnd){
            if (minute<10) {
                EditTextThursEnd.setText(hourOfDay + ":0" + minute);
            }else{
                EditTextThursEnd.setText(hourOfDay + ":" + minute);
            }
            timeThursEnd =false;

        }else if (timeFriStart){
            if (minute<10) {
                EditTextFriStart.setText(hourOfDay + ":0" + minute);
            }else{
                EditTextFriStart.setText(hourOfDay + ":" + minute);
            }
            timeFriStart =false;

        }else if (timeFriEnd){
            if (minute<10) {
                EditTextFriEnd.setText(hourOfDay + ":0" + minute);
            }else{
                EditTextFriEnd.setText(hourOfDay + ":" + minute);
            }
            timeFriEnd =false;

        }else if (timeSatStart){
            if (minute<10) {
                EditTextSatStart.setText(hourOfDay + ":0" + minute);
            }else{
                EditTextSatStart.setText(hourOfDay + ":" + minute);
            }
            timeSatStart =false;

        }else if (timeSatEnd){
            if (minute<10) {
                EditTextSatEnd.setText(hourOfDay + ":0" + minute);
            }else{
                EditTextSatEnd.setText(hourOfDay + ":" + minute);
            }
            timeSatEnd =false;

        }else if (timeSunStart){
            if (minute<10) {
                EditTextSunStart.setText(hourOfDay + ":0" + minute);
            }else{
                EditTextSunStart.setText(hourOfDay + ":" + minute);
            }
            timeSunStart =false;

        }else if (timeSunEnd){
            if (minute<10) {
                EditTextSunEnd.setText(hourOfDay + ":0" + minute);
            }else{
                EditTextSunEnd.setText(hourOfDay + ":" + minute);
            }
            timeSunEnd =false;
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtras(getIntent().getExtras());
        startActivity(i);
        finish();
    }

    private void Initialization() {
        monday = findViewById(R.id.switchMonday);
        tuesday = findViewById(R.id.switchTuesday);
        wednesday = findViewById(R.id.switchWednesday);
        thursday = findViewById(R.id.switchThursday);
        friday = findViewById(R.id.switchFriday);
        saturday = findViewById(R.id.switchSaturday);
        sunday = findViewById(R.id.switchSunday);
        SubmitTime = findViewById(R.id.SubmitTime);
    }

    private void addButtonListener() {
        monday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    EditTextMonStart.setVisibility(EditText.VISIBLE);
                    EditTextMonStart.setText("8:00");
                    EditTextMonStart.setEnabled(true);
                    EditTextMonEnd.setVisibility(EditText.VISIBLE);
                    EditTextMonEnd.setText("20:00");
                    EditTextMonEnd.setEnabled(true);

                }else{
                    EditTextMonStart.setEnabled(false);
                    EditTextMonStart.setText("0:00");
                    EditTextMonEnd.setEnabled(false);
                    EditTextMonEnd.setText("0:00");
                }
            }
        });
        tuesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    EditTextTuesStart.setVisibility(EditText.VISIBLE);
                    EditTextTuesStart.setText("8:00");
                    EditTextTuesStart.setEnabled(true);
                    EditTextTuesEnd.setVisibility(EditText.VISIBLE);
                    EditTextTuesEnd.setText("20:00");
                    EditTextTuesEnd.setEnabled(true);

                }else{
                    EditTextTuesStart.setEnabled(false);
                    EditTextTuesStart.setText("0:00");
                    EditTextTuesEnd.setEnabled(false);
                    EditTextTuesEnd.setText("0:00");
                }
            }
        });
        wednesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    EditTextWedStart.setVisibility(EditText.VISIBLE);
                    EditTextWedStart.setText("8:00");
                    EditTextWedStart.setEnabled(true);
                    EditTextWedEnd.setVisibility(EditText.VISIBLE);
                    EditTextWedEnd.setText("20:00");
                    EditTextWedEnd.setEnabled(true);

                }else{
                    EditTextWedStart.setEnabled(false);
                    EditTextWedStart.setText("0:00");
                    EditTextWedEnd.setEnabled(false);
                    EditTextWedEnd.setText("0:00");
                }
            }
        });
        thursday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    EditTextThursStart.setVisibility(EditText.VISIBLE);
                    EditTextThursStart.setText("8:00");
                    EditTextThursStart.setEnabled(true);
                    EditTextThursEnd.setVisibility(EditText.VISIBLE);
                    EditTextThursEnd.setText("20:00");
                    EditTextThursEnd.setEnabled(true);

                }else{
                    EditTextThursStart.setEnabled(false);
                    EditTextThursStart.setText("0:00");
                    EditTextThursEnd.setEnabled(false);
                    EditTextThursEnd.setText("0:00");
                }
            }
        });
        friday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    EditTextFriStart.setVisibility(EditText.VISIBLE);
                    EditTextFriStart.setText("8:00");
                    EditTextFriStart.setEnabled(true);
                    EditTextFriEnd.setVisibility(EditText.VISIBLE);
                    EditTextFriEnd.setText("20:00");
                    EditTextFriEnd.setEnabled(true);

                }else{
                    EditTextFriStart.setEnabled(false);
                    EditTextFriStart.setText("0:00");
                    EditTextFriEnd.setEnabled(false);
                    EditTextFriEnd.setText("0:00");
                }
            }
        });
        saturday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    EditTextSatStart.setVisibility(EditText.VISIBLE);
                    EditTextSatStart.setText("8:00");
                    EditTextSatStart.setEnabled(true);
                    EditTextSatEnd.setVisibility(EditText.VISIBLE);
                    EditTextSatEnd.setText("20:00");
                    EditTextSatEnd.setEnabled(true);

                }else{
                    EditTextSatStart.setEnabled(false);
                    EditTextSatStart.setText("0:00");
                    EditTextSatEnd.setEnabled(false);
                    EditTextSatEnd.setText("0:00");
                }
            }
        });
        sunday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    EditTextSunStart.setVisibility(EditText.VISIBLE);
                    EditTextSunStart.setText("8:00");
                    EditTextSunStart.setEnabled(true);
                    EditTextSunEnd.setVisibility(EditText.VISIBLE);
                    EditTextSunEnd.setText("20:00");
                    EditTextSunEnd.setEnabled(true);

                }else{
                    EditTextSunStart.setEnabled(false);
                    EditTextSunStart.setText("0:00");
                    EditTextSunEnd.setEnabled(false);
                    EditTextSunEnd.setText("0:00");
                }
            }
        });
        SubmitTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AvailableHours Sub = new AvailableHours(
                        EditTextMonStart.getText().toString(),  EditTextMonEnd.getText().toString(),
                        EditTextTuesStart.getText().toString(),  EditTextTuesEnd.getText().toString(),
                        EditTextWedStart.getText().toString(), EditTextWedEnd.getText().toString(),
                        EditTextThursStart.getText().toString(),  EditTextThursEnd.getText().toString(),
                        EditTextFriStart.getText().toString(),  EditTextFriEnd.getText().toString(),
                        EditTextSatStart.getText().toString(),  EditTextSatEnd.getText().toString(),
                        EditTextSunStart.getText().toString(),  EditTextSunEnd.getText().toString(), CurrentUser
                );

                DBM.CreateHours(CurrentUser, Sub );

                Bundle bundle = new Bundle();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                bundle.putString("username", CurrentUser);
                i.putExtras(bundle);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Added Hours!", Toast.LENGTH_LONG).show();
            }
        });
    }
}