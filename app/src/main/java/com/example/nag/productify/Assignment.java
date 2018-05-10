package com.example.nag.productify;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
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

        final CheckBox checkMon = findViewById(R.id.checkMon);

        checkMon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkMon.setChecked(true);
            }
        });

        final CheckBox checkTues = findViewById(R.id.checkTues);
        checkTues.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkTues.setChecked(true);
            }
        });

        final CheckBox checkWed = findViewById(R.id.checkWed);
        checkWed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkWed.setChecked(true);
            }
        });

        final  CheckBox checkThurs = findViewById(R.id.checkThurs);

        checkThurs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkThurs.setChecked(true);
            }
        });

        final CheckBox checkFri = findViewById(R.id.checkFri);
        checkFri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkFri.setChecked(true);
            }
        });

        final CheckBox checkSat = findViewById(R.id.checkSat);
        checkSat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkSat.setChecked(true);
            }
        });
        final CheckBox checkSun = findViewById(R.id.checkSun);

        checkSun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkSun.setChecked(true);
            }
        });

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
          //  if (checkMon.isChecked()==true|| checkTues.isChecked()==true||checkWed.isChecked()==true||checkThurs
           //       .isChecked()==true||checkFri.isChecked()==true||checkSat.isChecked()==true||checkSun.isChecked()==true)
            //{
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

                Boolean monday = true; //checkMon.isChecked();
                Boolean tuesday = true; //checkTues.isChecked();
                Boolean wednesday = true; //checkWed.isChecked();
                Boolean thursday = true; //checkThurs.isChecked();
                Boolean friday = true; //checkFri.isChecked();
                Boolean saturday = true; //checkSat.isChecked();
                Boolean sunday = true; //checkSun.isChecked();

                //EventTask event  =  new EventTask (syear, smonth, sday, shour, sminute, dyear, dmonth, dday, dhour, dminute, name, predicted, monday, tuesday, wednesday, thursday, friday, saturday, sunday);

                Intent intent = new Intent (Assignment.this, AssignmentsPreview.class);
                Bundle bundle  =  new Bundle();
                bundle.putString("nm",name);
                bundle.putInt("sy",syear);
                bundle.putInt("sm",smonth);
                bundle.putInt("sh",shour);
                bundle.putInt("smin",sminute);
                bundle.putInt("dy",dyear);
                bundle.putInt("dm",dmonth);
                bundle.putInt("dd",dday);
                bundle.putInt("dh",dhour);
                bundle.putInt("dmin",dminute);
                bundle.putDouble("pred",predicted);
                bundle.putBoolean("mo",monday);
                bundle.putBoolean("tu",tuesday);
                bundle.putBoolean("we",wednesday);
                bundle.putBoolean("th",thursday);
                bundle.putBoolean("fr",friday);
                bundle.putBoolean("sa",saturday);
                bundle.putBoolean("su",sunday);

                intent.putExtras(bundle);
                //startActivity(intent);

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

               // intent.putExtra("event", event); */

                if (intent == null)
                {
                    Log.d("Intent error", "the intent is empty");
                }
                else
                {
                    Log.d("Intent no error", "well at least something is in there");
                }

                startActivity(intent);
            }
           // else
            //{
              // showToast ("Please select at least one day to work.");
            //}
        //}
    }

    public void goToExist (View view)
    {
        Intent exist  = new Intent (Assignment.this, ExistingAssignmentsToday.class);
        startActivity(exist);
    }
}
