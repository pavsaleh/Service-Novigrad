package com.example.servicenovigrad;

import android.content.Intent;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.app.TimePickerDialog;
import android.widget.Toast;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteFragment;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;

public class CreateBooking extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    Button buttonBooking;
    public TextView editStartTime;
    public TextView editEndTime;
    DatabaseManager DBM;
    public CalendarView calendarView;
    public String CurrentUser;
    public String SelectedUser;
    public String serviceType;
    public EditText firstName;
    public EditText lastName;
    public EditText DOB;
    public EditText editAddress;

    boolean timeStart = false;
    boolean timeEnd = false;
    final String[] startTime = new String[1];
    final String[] endTime = new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_booking);
        Bundle bundle = getIntent().getExtras();
        DBM = DatabaseManager.getinstance();
        CurrentUser = DisplayServices.CurrentUser;
        CurrentUser = bundle.getString("username", "blank");
        SelectedUser = bundle.getString("selectedUser", "blank");
        serviceType = bundle.getString("serviceType", "Soccer Lessons");

        String apiKey = getString(R.string.api_key);

        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }

        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        autocompleteFragment.setHint("Select Address");

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("Location", "Place: " + place.getName() + ", " + place.getId());
                editAddress.setText(place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("Location", "An error occurred: " + status);
            }
        });
        findViews();
        setButtonListeners();
    }
    private void findViews() {
        calendarView = findViewById(R.id.calendarView);
        editStartTime = findViewById(R.id.editStartTime);
        editEndTime = findViewById(R.id.editEndTime);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        DOB = findViewById(R.id.DOB);
        editAddress = findViewById(R.id.seeAddress);
        AutocompleteFragment autocomplete_fragment = (AutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        editStartTime.setEnabled(false);
        editStartTime.setText("9:00");

        editEndTime.setEnabled(false);
        editEndTime.setText("16:00");

        editStartTime.setEnabled(true);
        editEndTime.setEnabled(true);
    }
    private void setButtonListeners() {
        editStartTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                DialogFragment timePickerStart = new TimePicker();
                timePickerStart.show(getSupportFragmentManager(),"Pick Time");
                timeStart = true;
            }
        });

        editEndTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                DialogFragment timePickerEnd = new TimePicker();
                timePickerEnd.show(getSupportFragmentManager(),"Pick Time");
                timeEnd = true;
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                buttonBooking = findViewById(R.id.buttonBooking);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");

                String splittedStart[] =editStartTime.getText().toString().split(":",2);
                String splittedEnd[] =editEndTime.getText().toString().split(":",2);

                Log.d("TIMEEE", "onSelectedDayChange: " + splittedEnd[0]);

                Log.d("TIMEEE", "onSelectedDayChange: " + splittedStart[0]);

                GregorianCalendar start = new GregorianCalendar(year, month, dayOfMonth, Integer.parseInt(splittedStart[0]), Integer.parseInt(splittedStart[1]), 00);
                GregorianCalendar end = new GregorianCalendar(year, month, dayOfMonth, Integer.parseInt(splittedEnd[0]), Integer.parseInt(splittedEnd[1]), 00);

                startTime[0] = sdf.format(start.getTime());
                endTime[0] = sdf.format(end.getTime());

                buttonBooking.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Booking appointment = new Booking (CurrentUser, SelectedUser, startTime[0], endTime[0],serviceType, firstName.getText().toString(), lastName.getText().toString(), DOB.getText().toString(), editAddress.getText().toString());
                        DBM.CreateBooking(appointment);
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtras(getIntent().getExtras());
                        Toast.makeText(getApplicationContext(), "You're booked!" ,Toast.LENGTH_LONG).show();
                        startActivity(i);
                    }
                });
            }
        });
    }

    @Override
    public void onTimeSet (android.widget.TimePicker view, int hourOfDay, int minute){
        if (timeStart) {
            if (minute < 10) {
                editStartTime.setText(hourOfDay + ":0" + minute);
            } else {
                editStartTime.setText(hourOfDay + ":" + minute);
            }
            timeStart = false;

        } else if (timeEnd) {
            if (minute < 10) {
                editEndTime.setText(hourOfDay + ":0" + minute);
            } else {
                editEndTime.setText(hourOfDay + ":" + minute);
            }
            timeEnd = false;
        }
    }
}
