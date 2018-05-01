package com.example.nag.productify;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.services.calendar.model.Event;

import org.w3c.dom.Text;

public class Assignment extends AppCompatActivity {

    Button createBut, viewBut;
    TextView assignmentText, newAssignmentText, dueDateText, daysText;
    EditText assignmentName, assignmentDueYear, assignmentDueMonth, assignmentDueDay, assignmentDueHour, assignmentDueMinute, assignmentLength, assignmentStartYear, assignmentStartMonth, assignmentStartDay, assignmentStartHour, assignmentStartMinute;
    CheckedTextView checkMon, checkTues, checkWed, checkThurs, checkFri, checkSat, checkSun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        createBut =  findViewById(R.id.createBut);
        viewBut = findViewById(R.id.viewBut);

        assignmentText =  findViewById(R.id.assignmentText);
        newAssignmentText =  findViewById(R.id.newAssignmentText);
        dueDateText = findViewById(R.id.dueDateText);
        daysText =  findViewById(R.id.daysText);

        assignmentStartYear = findViewById(R.id.assignmentStartYear);
        assignmentStartMonth =  findViewById(R.id.assignmentStartMonth);
        assignmentStartDay =  findViewById(R.id.assignmentStartDay);
        assignmentStartHour = findViewById(R.id.assignmentStartHour);
        assignmentStartMinute = findViewById(R.id.assignmentStartMinute);

        assignmentName = findViewById(R.id.assignmentName);
        assignmentDueYear =  findViewById(R.id.assignmentDueYear);
        assignmentDueMonth = findViewById(R.id.assignmentDueMonth);
        assignmentDueDay =  findViewById(R.id.assignmentDueDay);
        assignmentDueHour = findViewById(R.id.assignmentDueHour);
        assignmentDueMinute =  findViewById(R.id.assignmentDueMinute);
        assignmentLength = findViewById(R.id.assignmentLength);

        CheckBox checkMon = findViewById(R.id.checkMon);
        CheckBox checkTues = findViewById(R.id.checkTues);
        CheckBox checkWed = findViewById(R.id.checkWed);
        CheckBox checkThurs = findViewById(R.id.checkThurs);
        CheckBox checkFri = findViewById(R.id.checkFri);
        CheckBox checkSat = findViewById(R.id.checkSat);
        CheckBox checkSun = findViewById(R.id.checkSun);
    }

    private void showToast (String text)
    {
        Toast.makeText(Assignment.this,text,Toast.LENGTH_SHORT).show();
    }


    public void create (View view)
    {
        if (assignmentName.getText().toString().isEmpty()||assignmentDueYear.getText().toString().isEmpty()||assignmentDueMonth
                .getText().toString().isEmpty()||assignmentDueDay.getText().toString().isEmpty()||assignmentDueMinute.getText().toString().isEmpty()||
                assignmentDueHour.getText().toString().isEmpty()||assignmentLength.getText().toString().isEmpty()||assignmentStartYear.getText().toString().isEmpty()||
                assignmentStartMonth.getText().toString().isEmpty()||assignmentStartDay.getText().toString().isEmpty()||assignmentStartHour.getText().toString().isEmpty()
                ||assignmentStartMinute.getText().toString().isEmpty())
        {

               showToast("Please fill in all fields.");
        }
        else
        {
            if (checkMon.isChecked()|| checkTues.isChecked()||checkWed.isChecked()||checkThurs
                    .isChecked()||checkFri.isChecked()||checkSat.isChecked()||checkSun.isChecked())
            {
                String name = assignmentName.getText().toString();

                int syear = Integer.parseInt(assignmentStartYear.getText().toString());
                int smonth = Integer.parseInt(assignmentStartMonth.getText().toString());
                int sday = Integer.parseInt(assignmentStartDay.getText().toString());
                int shour = Integer.parseInt(assignmentStartHour.getText().toString());
                int sminute = Integer.parseInt(assignmentStartMinute.getText().toString());

                int dyear = Integer.parseInt(assignmentDueYear.getText().toString());
                int dmonth = Integer.parseInt(assignmentDueMonth.getText().toString());
                int dday = Integer.parseInt(assignmentDueDay.getText().toString());
                int dhour =  Integer.parseInt(assignmentDueHour.getText().toString());
                int dminute =  Integer.parseInt(assignmentDueMinute.getText().toString());

                double predicted = Double.parseDouble(assignmentLength.getText().toString());

                EventTask event  =  new EventTask (syear, smonth, sday, shour, sminute, dyear, dmonth, dday, dhour, dminute, name, predicted, true, true, true, true, true, true, true);

                Intent intent = new Intent (Assignment.this, AssignmentsPreview.class);

            }
            else
            {
                showToast ("Please select at least one day to work.");
            }
        }
    }
    public void goToPreview (View view)
    {

        Intent prev = new Intent (Assignment.this, AssignmentsPreview.class);
        startActivity(prev);
    }

    public void goToExist (View view)
    {
        Intent exist  = new Intent (Assignment.this, ExistingAssignmentsToday.class);
        startActivity(exist);
    }
}
