package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EaActivity2 extends AppCompatActivity {

    private TextView textViewEa;
    private Intent intent;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ea2_main);
        setTitle("OOMP EA");

        // get data
        intent = this.getIntent();
        bundle = intent.getExtras();
        textViewEa = findViewById(R.id.TextView_Ea);
        // set text
        textViewEa.setText(bundle.getString("EaText"));


    }
}