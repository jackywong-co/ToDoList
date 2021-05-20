package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class EaActivity extends AppCompatActivity {
    public static final int EA_PassMassage_REQUEST = 2;
    private EditText editTextEa;
    private Button buttonEa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ea_main);
        setTitle("OOMP EA");


        editTextEa = findViewById(R.id.EditText_Ea);
        buttonEa = findViewById(R.id.Button_Ea);

        buttonEa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // intent to ea activity 2
                Intent intent = new Intent();
                intent.setClass(EaActivity.this, EaActivity2.class);
                Bundle bundle = new Bundle();
                // pass data through bundle
                bundle.putString("EaText", editTextEa.getText().toString());
                intent.putExtras(bundle);
                startActivityForResult(intent, EA_PassMassage_REQUEST);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_ea, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Exit:
                // close app
                moveTaskToBack(true);
                return true;

        }
        return super.onOptionsItemSelected(item);

    }
}