package com.example.nag.productify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExistingAssignmentsToday extends AppCompatActivity {

    Button homeBut, calendarBut2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_assignments_today);

        homeBut = (Button) findViewById(R.id.homeBut);
        calendarBut2 = (Button) findViewById(R.id.calendarBut2);
    }

    public void goToHome (View view)
    {
        Intent home = new Intent (ExistingAssignmentsToday.this,MainActivity.class );
        startActivity(home);
    }

    public void goToCalendar (View view)
    {
        Intent cal = new Intent (ExistingAssignmentsToday.this, Calendar.class);
        startActivity(cal);
    }
}
