package com.example.nag.productify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;

public class Assignment extends AppCompatActivity {

    Button createBut, viewBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        createBut = (Button) findViewById(R.id.createBut);
        viewBut = (Button) findViewById(R.id.viewBut);

        EditText assignment = findViewById(R.id.assignmentName);
        EditText dueDate = findViewById(R.id.assignmentDueDate);
        EditText assignmentLength = findViewById(R.id.assignmentLength);

        // GridView chosenDates =

        CheckBox mon = findViewById(R.id.checkMon);
        CheckBox tue = findViewById(R.id.checkTues);
        CheckBox wed = findViewById(R.id.checkWed);
        CheckBox thurs = findViewById(R.id.checkThurs);
        CheckBox fri = findViewById(R.id.checkFri);
        CheckBox sat = findViewById(R.id.checkSat);
        CheckBox sun = findViewById(R.id.checkSun);

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
