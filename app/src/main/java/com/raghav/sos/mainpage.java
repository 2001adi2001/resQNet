package com.raghav.sos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class mainpage extends AppCompatActivity {

    ImageView redbutton,emergency;
    public Button stop,chamt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        Objects.requireNonNull(getSupportActionBar()).hide();

        redbutton = findViewById(R.id.redbutton);
        stop = findViewById(R.id.stop);
        emergency=findViewById(R.id.emergency);
        chamt= findViewById(R.id.chamt);

        emergency.setOnClickListener(view -> {
            Intent intent=new Intent(mainpage.this,emergencycontact.class);
            startActivity(intent);

        });

        redbutton.setOnClickListener(view -> {
            Intent intent = new Intent(mainpage.this, MainActivity.class);
            startActivity(intent);
        });

        chamt.setOnClickListener(view -> {
            Intent intent = new Intent(mainpage.this, Chat.class);
            startActivity(intent);
        });

        stop.setOnClickListener(view -> finish());
    }

    @Override
    protected void onDestroy() {
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }
}