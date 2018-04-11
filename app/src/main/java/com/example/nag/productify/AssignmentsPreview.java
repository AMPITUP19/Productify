package com.example.nag.productify;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    private void showToast (String text)
    {
        Toast.makeText(AssignmentsPreview.this,text,Toast.LENGTH_LONG).show();
    }

    public void goConfirm (View view)
    {
        Intent confirm =  new Intent (AssignmentsPreview.this, Calendar.class);
        showToast("Your Assignment Has Been Added to the Calendar.");
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
