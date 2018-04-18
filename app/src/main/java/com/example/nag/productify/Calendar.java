package com.example.nag.productify;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.google.api.services.calendar.model.CalendarListEntry;

public class Calendar extends AppCompatActivity {

    Button returnHomeBut;
    CalendarView mCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        returnHomeBut = findViewById(R.id.returnHomeBut);
        mCalendarView = findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                //i is year
                //i1 is month where January is 0
                //i2 is day

                int month = i1+1;
                int day = i2;
                int year  = i;

               Intent intent  = new Intent (Calendar.this,Date.class );
               intent.putExtra("month", month);
               intent.putExtra("day", day);
               intent.putExtra("year", year);
               startActivity(intent);

            }
        });

    }

    public void goHome(View view)
    {
        Intent home = new Intent (Calendar.this, MainActivity.class);
        startActivity(home);
    }
}

