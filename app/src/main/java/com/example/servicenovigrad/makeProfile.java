package com.example.servicenovigrad;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.api.model.Place;
import java.util.Arrays;

public class makeProfile extends AppCompatActivity {
    Button button_submitProfile;
    public EditText editAddress;
    public EditText editCompany;
    public EditText phoneNum;
    DatabaseManager DBM;
    View Frame;
    public String CurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_profile);
        Bundle bundle = getIntent().getExtras();
        CurrentUser = bundle.getString("username", "blank");
        DBM = DatabaseManager.getinstance();

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
    private void findViews(){
        editAddress = findViewById(R.id.seeAddress);
        editCompany=findViewById(R.id.editCompany);
        phoneNum = findViewById(R.id.editPhoneNumber);
        button_submitProfile = findViewById(R.id.button_submitProfile);
        Frame = findViewById(R.id.frame);
    }

    private void setButtonListeners() {

        button_submitProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editAddress.getText().toString().length()>0 && editCompany.getText().toString().length()>0 && phoneNum.getText().toString().length()>0) {
                    userProfile p = new userProfile(editAddress.getText().toString(), phoneNum.getText().toString(), editCompany.getText().toString(), CurrentUser);
                    DBM.CreateProfile(CurrentUser, p);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", CurrentUser);
                    Toast.makeText(getApplicationContext(), "Profile Created", Toast.LENGTH_LONG).show();
                    i.putExtras(bundle);
                    startActivity(i);
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "Required entry is incomplete", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("username", CurrentUser);
        i.putExtras(bundle);
        startActivity(i);
        finish();
    }
}