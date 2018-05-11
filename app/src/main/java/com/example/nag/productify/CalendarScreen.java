package com.example.nag.productify;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.util.Calendar;

public class CalendarScreen extends AppCompatActivity {

    Button returnHomeBut;
    CalendarView mCalendarView;

    /**
     *
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        returnHomeBut = findViewById(R.id.returnHomeBut);
        mCalendarView = findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                Log.d("methodCheck", "IT WORKS");
                //i is year
                //i1 is month where January is 0
                //i2 is day
                mCalendarView.getDate();
                int monthint = i1+1;
                String month = "";
                switch (monthint) {
                    case 1:  month = "January";
                        break;
                    case 2:  month = "February";
                        break;
                    case 3:  month = "March";
                        break;
                    case 4:  month = "April";
                        break;
                    case 5:  month = "May";
                        break;
                    case 6:  month = "June";
                        break;
                    case 7:  month = "July";
                        break;
                    case 8:  month = "August";
                        break;
                    case 9:  month = "September";
                        break;
                    case 10: month = "October";
                        break;
                    case 11: month = "November";
                        break;
                    case 12: month = "December";
                        break;
                    default: month = "Month";
                        break;
                }
                int day= i2;

                int year  = i;

                Calendar c = Calendar.getInstance();
                c.set(year, monthint, day);

                int dayofwint = c.get(Calendar.DAY_OF_WEEK);
                String dayofweek = "";

                switch (dayofwint) {
                    case 1:  dayofweek = "Friday";
                        break;
                    case 2:  dayofweek = "Saturday";
                        break;
                    case 3:  dayofweek = "Sunday"; //Sunday
                        break;
                    case 4:  dayofweek = "Monday"; //Monday
                        break;
                    case 5:  dayofweek = "Tuesday"; //Tuesday
                        break;
                    case 6:  dayofweek = "Wednesday";
                        break;
                    case 7:  dayofweek = "Thursday";
                        break;
                    default: dayofweek = "D.O.W";
                        break;
                }

                Intent intent  = new Intent (CalendarScreen.this,Date.class );
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                intent.putExtra("year", year);
                intent.putExtra("dayofweek", dayofweek);
                startActivity(intent);

            }
        });

    }

    public void goHome(View view)
    {
        Intent home = new Intent (CalendarScreen.this, MainActivity.class);
        startActivity(home);
    }
}

