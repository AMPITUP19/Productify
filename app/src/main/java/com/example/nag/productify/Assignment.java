package com.example.nag.productify;

import android.app.AlarmManager;
import android.app.PendingIntent;
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

import java.util.Calendar;

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

                Integer syear = Integer.parseInt(assignmentStartYear.getText().toString());
                Integer smonth = Integer.parseInt(assignmentStartMonth.getText().toString());
                Integer sday = Integer.parseInt(assignmentStartDay.getText().toString());
                Integer shour = Integer.parseInt(assignmentStartHour.getText().toString());
                Integer sminute = Integer.parseInt(assignmentStartMinute.getText().toString());

                Integer dyear = Integer.parseInt(assignmentDueYear.getText().toString());
                Integer dmonth = Integer.parseInt(assignmentDueMonth.getText().toString());
                Integer dday = Integer.parseInt(assignmentDueDay.getText().toString());
                Integer dhour =  Integer.parseInt(assignmentDueHour.getText().toString());
                Integer dminute =  Integer.parseInt(assignmentDueMinute.getText().toString());

                Double predicted = Double.parseDouble(assignmentLength.getText().toString());

                Boolean monday = checkMon.isChecked();
                Boolean tuesday = checkTues.isChecked();
                Boolean wednesday = checkWed.isChecked();
                Boolean thursday = checkThurs.isChecked();
                Boolean friday = checkFri.isChecked();
                Boolean saturday = checkSat.isChecked();
                Boolean sunday = checkSun.isChecked();

                EventTask event  =  new EventTask (syear, smonth, sday, shour, sminute, dyear, dmonth, dday, dhour, dminute, name, predicted, monday, tuesday, wednesday, thursday, friday, saturday, sunday);

                Intent intent = new Intent (this, AssignmentsPreview.class);
                intent.putExtra("nm",name);
                intent.putExtra("sy",syear);
                intent.putExtra("sm",smonth);
                intent.putExtra("sh",shour);
                intent.putExtra("smin",sminute);
                intent.putExtra("dy",dyear);
                intent.putExtra("dm",dmonth);
                intent.putExtra("dd",dday);
                intent.putExtra("dh",dhour);
                intent.putExtra("dmin",dminute);
                intent.putExtra("pred",predicted);
                intent.putExtra("mo",monday);
                intent.putExtra("tu",tuesday);
                intent.putExtra("we",wednesday);
                intent.putExtra("th",thursday);
                intent.putExtra("fr",friday);
                intent.putExtra("sa",saturday);
                intent.putExtra("su",sunday);

                startActivity(intent);

                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.HOUR_OF_DAY,dhour );
                calendar.set(Calendar.MINUTE,dminute);
                calendar.set(Calendar.DAY_OF_MONTH,dday);
                calendar.set(Calendar.MONTH,dmonth);
                calendar.set(Calendar.YEAR,dyear);

                Intent intent1 =  new Intent(getApplicationContext(),NotificationReceiver.class);

                PendingIntent pendingIntent =  PendingIntent.getBroadcast(getApplicationContext(), 100, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);

                intent.putExtra("event", event);

                startActivity(intent);
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
