package com.example.nag.productify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Shows the user's tasks for the current day
 */
public class ExistingAssignmentsToday extends AppCompatActivity {

    Button homeBut, calendarBut2;

    @Override
    /**
     * Instantiates the ExistingAssignmentsToday activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_assignments_today);

        homeBut = (Button) findViewById(R.id.homeBut);
        calendarBut2 = (Button) findViewById(R.id.calendarBut2);
    }

    /**
     * Brings the user to the MainActivity
     * @param view the view used for the method
     */
    public void goToHome (View view)
    {
        Intent home = new Intent (ExistingAssignmentsToday.this,MainActivity.class );
        startActivity(home);
    }

    /**
     * Brings the user to the CalendarScreen activity
     * @param view the view used for the method
     */
    public void goToCalendar (View view)
    {
        Intent cal = new Intent (ExistingAssignmentsToday.this, CalendarScreen.class);
        startActivity(cal);
    }
}
