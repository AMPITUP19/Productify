package com.example.nag.productify;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.util.Calendar;

public class CalendarScreen extends Activity {

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

                int day= i2;

                int year  = i;

                Calendar c = Calendar.getInstance();
                c.set(year, monthint, day);

                int dayofwint = c.get(Calendar.DAY_OF_WEEK);
                String dayofweek = "";

                switch (dayofwint) {
                    case 1:  dayofweek = "Thursday";
                        break;
                    case 2:  dayofweek = "Friday";
                        break;
                    case 3:  dayofweek = "Saturday";
                        break;
                    case 4:  dayofweek = "Sunday";
                        break;
                    case 5:  dayofweek = "Monday";
                        break;
                    case 6:  dayofweek = "Tuesday";
                        break;
                    case 7:  dayofweek = "Wednesday";
                        break;
                    default: dayofweek = "D.O.W";
                        break;
                }

                Intent intent  = new Intent (CalendarScreen.this,Date.class );
                intent.putExtra("month", monthint);
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

