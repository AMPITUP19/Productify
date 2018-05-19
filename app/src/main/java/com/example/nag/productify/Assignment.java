/**
 * Creates class Assignment which input the variables below and creates an EventTask object
 */
package com.example.nag.productify;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class Assignment extends Activity {

    Button createBut, viewBut;
    TextView assignmentText, newAssignmentText, dueDateText, daysText;
    EditText assignmentName, assignmentDueYear, assignmentDueMonth, assignmentDueDay, assignmentDueHour, assignmentDueMinute, assignmentLength, assignmentStartYear, assignmentStartMonth, assignmentStartDay, assignmentStartHour, assignmentStartMinute;
    CheckBox checkMon, checkTues, checkWed, checkThurs, checkFri, checkSat, checkSun;
    Boolean mon, tues, wed, thurs, fri, sat, sun;

    /**
     * Instantiates the Assignment activity
     * @param savedInstanceState the saved instance of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        createBut =  findViewById(R.id.createBut);
        viewBut = findViewById(R.id.viewBut);

        LinearLayout linearlayoutview1 = findViewById(R.id.linearlayout1);
        LinearLayout linearlayoutview2 = findViewById(R.id.linearlayout2);
        LinearLayout linearlayoutview3 = findViewById(R.id.linearlayout3);


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

        checkMon = linearlayoutview1.findViewById(R.id.checkMon);
        checkMon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                mon = b;
            }
        });

        checkTues = linearlayoutview1.findViewById(R.id.checkTues);
        checkTues.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                tues = b;
            }
        });

        checkWed = linearlayoutview2.findViewById(R.id.checkWed);
        checkWed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                wed =b;
            }
        });

        checkThurs = linearlayoutview2.findViewById(R.id.checkThurs);
        checkThurs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) { thurs = b;
            }
        });

        checkFri = linearlayoutview3.findViewById(R.id.checkFri);
        checkFri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                fri = b;
            }
        });

        checkSat = linearlayoutview3.findViewById(R.id.checkSat);
        checkSat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sat = b;
            }
        });

        checkSun = linearlayoutview3.findViewById(R.id.checkSun);
        checkSun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sun = b;
            }
        });
    }

    /**
     * Creates a toast to show to the user.
     * @param text the String shown to the user
     */
    private void showToast (String text)
        {
            Toast.makeText(Assignment.this,text,Toast.LENGTH_SHORT).show();
        }

    /**
     * Creates an EventTask object from user's input values
     * @param view the view the method is being used for
     */
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
            if (checkMon.isChecked() || checkTues.isChecked() ||checkWed.isChecked() ||checkThurs.isChecked()
                    ||checkFri.isChecked() || checkSat.isChecked() || checkSun.isChecked())
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

                Boolean monday = (checkMon.isChecked());
                Boolean tuesday = (checkTues.isChecked());
                Boolean wednesday = (checkWed.isChecked());
                Boolean thursday =(checkThurs.isChecked());
                Boolean friday = (checkFri.isChecked());
                Boolean saturday =  (checkSat.isChecked());
                Boolean sunday =(checkSun.isChecked());

                if ((dmonth<13 && dmonth > 0) && (smonth < 13 && smonth > 0))
                    {
                        if (sday == dday -1 && smonth == dmonth)
                        {
                         showToast("Your assignment is due tomorrow. Just do it all now.");
                        }

                        else
                        {
                            if (shour < 23 && shour>= 0 && dhour >= 0 && dhour < 23 && dminute >= 0 && dminute < 60 && sminute >= 0 && sminute < 60)
                            {
                                if (((dmonth == 4 || dmonth == 6 || dmonth == 9 || dmonth == 11 || smonth == 4
                                        || smonth == 6 || smonth == 9 || smonth == 11) && (dday > 31 || sday > 31 || dday < 0 || sday < 0))
                                        || ((dmonth == 1 || dmonth == 3 || dmonth == 5 || dmonth == 7 || dmonth == 8 || dmonth == 10
                                        || dmonth == 12 || smonth == 1 || smonth == 3 || smonth == 5 || smonth == 7 || smonth == 8 || smonth == 10
                                        || smonth == 12) && (dday > 32 || dday < 0 || sday < 0 || sday > 32)) || ((dmonth == 2 || smonth == 2) &&
                                        (sday > 29 || sday < 0 || dday > 29 || dday < 0))) //need to check days depending on month
                                {

                                    showToast("Please review the dates you have selected. That date does not exist.");
                                }
                                else {
                                    Intent intent = new Intent(Assignment.this, AssignmentsPreview.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("nm", name);
                                    bundle.putInt("sy", syear);
                                    bundle.putInt("sm", smonth);
                                    bundle.putInt("sd", sday);
                                    bundle.putInt("sh", shour);
                                    bundle.putInt("smin", sminute);
                                    bundle.putInt("dy", dyear);
                                    bundle.putInt("dm", dmonth);
                                    bundle.putInt("dd", dday);
                                    bundle.putInt("dh", dhour);
                                    bundle.putInt("dmin", dminute);
                                    bundle.putDouble("pred", predicted);
                                    bundle.putBoolean("mo", monday);
                                    bundle.putBoolean("tu", tuesday);
                                    bundle.putBoolean("we", wednesday);
                                    bundle.putBoolean("th", thursday);
                                    bundle.putBoolean("fr", friday);
                                    bundle.putBoolean("sa", saturday);
                                    bundle.putBoolean("su", sunday);

                                    intent.putExtras(bundle);

                                    Calendar calendar = Calendar.getInstance();

                                    calendar.set(Calendar.HOUR_OF_DAY, dhour);
                                    calendar.set(Calendar.MINUTE, dminute);
                                    calendar.set(Calendar.DAY_OF_MONTH, dday);
                                    calendar.set(Calendar.MONTH, dmonth);
                                    calendar.set(Calendar.YEAR, dyear);

                                    Intent intent1 = new Intent(getApplicationContext(), NotificationReceiver.class);

                                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

                                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

                                    startActivity(intent);
                                }

                            }
                            else
                                {
                                    showToast("You have inputted an invalid start and/or due date time of day.");
                                }
                        }

                    }

                else
                    {
                       showToast("Please check that your start and end months are between 1 and 12.");
                    }

            }
            else
                {
                   showToast ("Please select at least one day to work.");
                }
        }
    }

    /**
     * Sends the user to the Calendar screen when button is clicked
     * @param view view
     */
    public void goToExist (View view)
        {
            Intent exist  = new Intent (Assignment.this, CalendarScreen.class);
            startActivity(exist);
        }
}
