package com.example.nag.productify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Assignment extends AppCompatActivity {

    Button createBut, viewBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        createBut = (Button) findViewById(R.id.createBut);
        viewBut = (Button) findViewById(R.id.viewBut);
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
