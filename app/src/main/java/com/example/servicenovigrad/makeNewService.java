package com.example.servicenovigrad;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class makeNewService extends AppCompatActivity {

    private String CurrentUser;
    Button button_Submit;
    EditText servicePrice;
    EditText serviceTitle;
    DatabaseManager DBM;
    CheckBox infFirstName;
    CheckBox infLastName;
    CheckBox infDOB;
    CheckBox infAddress;
    CheckBox docProofRes;
    CheckBox docProofStat;
    CheckBox docPicCust;

    String sServicePrice;
    String sServiceTitle;
    List<String> informationList = new ArrayList<String>();
    List<String> documentList = new ArrayList<String>();

    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("username", CurrentUser);
        i.putExtras(bundle);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_new_service);
        button_Submit = findViewById(R.id.button_Submit);
        servicePrice = findViewById(R.id.text_ServicePrice);
        servicePrice.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
        serviceTitle = findViewById(R.id.text_ServiceName);
        infFirstName = findViewById(R.id.checkBox_Form01);
        infLastName = findViewById(R.id.checkBox_Form02);
        infDOB = findViewById(R.id.checkBox_Form03);
        infAddress = findViewById(R.id.checkBox_Form04);
        docProofRes = findViewById(R.id.checkBox_Document01);
        docProofStat = findViewById(R.id.checkBox_Document02);
        docPicCust = findViewById(R.id.checkBox_Document03);
        DBM = DatabaseManager.getinstance();
        Bundle bundle = getIntent().getExtras();
        CurrentUser = bundle.getString("username", "blank");

        while (infFirstName.isChecked()) {
            informationList.add("First Name");
        }
        while (infLastName.isChecked()) {
            informationList.add("Last Name");
        }
        while (infDOB.isChecked()) {
            informationList.add("Date of birth");
        }
        while (infAddress.isChecked()) {
            informationList.add("Address");
        }
        while (docProofRes.isChecked()) {
            documentList.add("Proof of residence");
        }
        while (docProofStat.isChecked()) {
            documentList.add("Proof of status");
        }
        while (docPicCust.isChecked()) {
            documentList.add("Picture of Customer");
        }

        /*String delim = ", ";
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < informationList.size() - 1) {
            sb.append(informationList.get(i));
            sb.append(delim);
            i++;
        }
        sb.append(informationList.get(i));
        String res = sb.toString();

        StringBuilder sb1 = new StringBuilder();
        while (i < documentList.size() - 1) {
            sb.append(documentList.get(i));
            sb.append(delim);
            i++;
        }
        sb1.append(documentList.get(i));
        String res1 = sb.toString();*/

        button_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sServicePrice = servicePrice.getText().toString();
                sServiceTitle = serviceTitle.getText().toString();
                String[] information = informationList.toArray(new String[informationList.size()]);
                String[] document = documentList.toArray(new String[documentList.size()]);
                /*addService(CurrentUser, sServicePrice, sServiceTitle, Arrays.asList(information), Arrays.asList(document));*/
                addService(CurrentUser, sServicePrice, sServiceTitle, informationList, documentList);
                finish();
            }
        });
    }

    public void addService(String currentUser, String price, String title, List<String> informationList, List<String> documentList){
        if(!(sServicePrice.equals("") || sServiceTitle.equals(""))) {
            DBM.addService(currentUser, title, Double.parseDouble(price), getApplication(), informationList, documentList);
        } else{
            Toast.makeText(getApplicationContext(), "Invalid or Blank Information", Toast.LENGTH_LONG).show();
        }
    }
}