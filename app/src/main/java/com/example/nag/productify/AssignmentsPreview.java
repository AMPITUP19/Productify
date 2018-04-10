package com.example.nag.productify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AssignmentsPreview extends AppCompatActivity {

    Button confirmBut, editBut, cancelBut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments_preview);


        confirmBut = (Button) findViewById(R.id.confirmBut);
        editBut = (Button) findViewById(R.id.editBut);
        cancelBut = (Button) findViewById(R.id.cancelBut);

    }

    public void goConfirm (View view)
    {
        
    }
}
