package com.example.nag.productify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Date extends AppCompatActivity {

    TextView currentYearText, currentMonthText, currentDayText, dayOfWeekText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        Bundle bundle = getIntent().getExtras();
        int month  = bundle.getInt("month");
        int year  = bundle.getInt("year");
        int day = bundle.getInt("day");
        int dayofweek = bundle.getInt("dayofweek");

        currentDayText = findViewById(R.id.currentDayText);
        currentMonthText = findViewById(R.id.currentMonthText);
        currentYearText = findViewById(R.id.currentYearText);
        dayOfWeekText = findViewById(R.id.dayOfWeekText);

        currentDayText.setText();
    }
}
