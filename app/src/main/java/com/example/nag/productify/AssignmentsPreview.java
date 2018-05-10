package com.example.nag.productify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.util.List;


public class AssignmentsPreview extends AppCompatActivity {

    Button confirmBut, editBut, cancelBut;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments_preview);

        confirmBut = (Button) findViewById(R.id.confirmBut);
        editBut = (Button) findViewById(R.id.editBut);
        cancelBut = (Button) findViewById(R.id.cancelBut);

        TextView heading = findViewById(R.id.heading);
        TextView previewText = findViewById(R.id.previewText);
        TextView assignmentName = findViewById(R.id.assignmentName);
        TextView dueDateText = findViewById(R.id.dueDateText);
        TextView timeWorkHeading = findViewById(R.id.timeWorkHeading);
        TextView date1text = findViewById(R.id.date1text);
        TextView date2text = findViewById(R.id.date2text);
        TextView date3text = findViewById(R.id.date3text);
        TextView date4text = findViewById(R.id.date4text);

        //EventTask event1 = (EventTask) getIntent().getExtras().getSerializable("event");

        Bundle bundle = getIntent().getExtras();
        int sYear = bundle.getInt("sy");
        int sMonth = bundle.getInt("sm");
        int sDay = bundle.getInt("sd");
        int sHour = bundle.getInt("sh");
        int sMinute = bundle.getInt("smin");
        int dYear = bundle.getInt("dy");
        int dMonth = bundle.getInt("dm");
        int dDay = bundle.getInt("dd");
        int dHour = bundle.getInt("dh");
        int dMinute = bundle.getInt("dmin");
        String name = bundle.getString("nm");
        double predictedLength = bundle.getInt("predicted");
        Boolean mon = bundle.getBoolean("mo");
        Boolean tues = bundle.getBoolean("tu");
        Boolean wed = bundle.getBoolean("we");
        Boolean thurs = bundle.getBoolean("th");
        Boolean fri = bundle.getBoolean("fr");
        Boolean sat = bundle.getBoolean("sa");
        Boolean sun = bundle.getBoolean("su");

        EventTask event1 = new EventTask(sYear, sMonth, sDay, sHour, sMinute, dYear, dMonth, dDay, dHour, dMinute, name, predictedLength, mon, tues, wed, thurs, fri, sat, sun);
    }

    private void showToast (String text)
    {
        Toast.makeText(AssignmentsPreview.this,text,Toast.LENGTH_LONG).show();
    }

    public void goConfirm (View view)
    {
        Intent confirm =  new Intent (AssignmentsPreview.this, CalendarScreen.class);
        showToast("Your Assignment Has Been Added to the CalendarScreen.");
        startActivity(confirm);
    }

    public void goEdit (View view)
    {
        Intent edit = new Intent (AssignmentsPreview.this, Assignment.class);
        startActivity(edit);
    }

    public void goCancel (View view)
    {
        Intent cancel = new Intent (AssignmentsPreview.this, MainActivity.class);
        showToast("Canceling assignment creation...");
        startActivity(cancel);
    }
}
