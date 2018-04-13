package com.example.nag.productify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Calendar extends AppCompatActivity {

    Button returnHomeBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        returnHomeBut = (Button) findViewById(R.id.returnHomeBut);
    }

    public void goHome(View view)
    {
        Intent home = new Intent (Calendar.this, MainActivity.class);
        startActivity(home);
    }
}

