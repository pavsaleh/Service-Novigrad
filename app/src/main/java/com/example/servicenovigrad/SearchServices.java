package com.example.servicenovigrad;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteFragment;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.api.model.Place;
import java.util.Arrays;

public class SearchServices extends AppCompatActivity {
    Button SearchSubmit;
    EditText SearchText;
    String CurrentUser;
    RadioButton ratingSearch;
    RadioButton serviceSearch;
    RadioButton addressSearch;
    RadioGroup radioGroup;
    public EditText editAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_services);
        Bundle bundle = getIntent().getExtras();
        CurrentUser = bundle.getString("username", "blank");

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
        SearchSubmit  = findViewById(R.id.SearchSubmit);
        SearchText = findViewById(R.id.SearchText);
        ratingSearch = findViewById(R.id.ratingSearch);
        radioGroup = findViewById(R.id.radioGroup);
        serviceSearch = findViewById(R.id.radioButtonName);
        addressSearch = findViewById(R.id.addressSearch);
        editAddress = findViewById(R.id.seeAddress);
        AutocompleteFragment autocomplete_fragment = (AutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);
    }
    private void setButtonListeners() {
        addressSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout rlSearchPlace = (RelativeLayout) findViewById(R.id.rlSearchPlacement);
                rlSearchPlace.setVisibility (View.VISIBLE);
                radioGroup.setVisibility(View.INVISIBLE);
                SearchText.setVisibility(View.INVISIBLE);
            }
        });

        serviceSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), CurrentUser, Toast.LENGTH_LONG).show();

                RelativeLayout rlSearchPlace = (RelativeLayout) findViewById(R.id.rlSearchPlacement);
                rlSearchPlace.setVisibility (View.INVISIBLE);
                radioGroup.setVisibility(View.INVISIBLE);
                SearchText.setVisibility(View.VISIBLE);
            }
        });

        ratingSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), CurrentUser, Toast.LENGTH_LONG).show();

                RelativeLayout rlSearchPlace = (RelativeLayout) findViewById(R.id.rlSearchPlacement);
                rlSearchPlace.setVisibility (View.INVISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                SearchText.setVisibility(View.INVISIBLE);
            }
        });

        SearchSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serviceSearch.isChecked()) {
                    Bundle bundle = new Bundle();
                    Intent i = new Intent(getApplicationContext(), ShowSearchedResults.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    bundle.putString("SearchString", SearchText.getText().toString());
                    bundle.putString("username", CurrentUser);

                    i.putExtras(bundle);
                    startActivity(i);
                    finish();
                }
                else if (addressSearch.isChecked()) {
                    Bundle bundle = new Bundle();
                    Intent i = new Intent(getApplicationContext(), ShowSearchedResultsAddress.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    bundle.putString("SearchString", editAddress.getText().toString());
                    bundle.putString("username", CurrentUser);

                    i.putExtras(bundle);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Search by rating is not yet implemented", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtras(getIntent().getExtras());
        startActivity(i);
        finish();
    }
}