package com.example.servicenovigrad;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;


public class makeProfile extends AppCompatActivity {
    Button button_submitProfile;
    EditText editAddress;
    EditText editCompany;
    EditText phoneNum;
    EditText Desc;
    DatabaseManager DBM;
    CheckBox Licensed;
    View Frame;

    private String CurrentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_profile);
        Bundle bundle = getIntent().getExtras();
        CurrentUser = bundle.getString("username", "blank");
        DBM = DatabaseManager.getinstance();

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder().setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS).build();

        autocompleteFragment.setFilter(autocompleteFilter);

        autocompleteFragment.setHint("Select Address");

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("Location", "Place: " + place.getAddress());
                editAddress.setText(place.getAddress());
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
                if(editAddress.getText().toString().length()>0 && editAddress.getText().toString().length()>0 && phoneNum.getText().toString().length()>0) {
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