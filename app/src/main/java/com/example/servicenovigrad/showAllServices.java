package com.example.servicenovigrad;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class showAllServices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_services);
        ArrayList<String> a = new ArrayList<String>();
        a.add("a");
        a.add("b");
    }
}