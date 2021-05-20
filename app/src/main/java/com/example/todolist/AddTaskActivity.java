package com.example.todolist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AddTaskActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "com.example.a200020512_wongchikon.EXTRA_NAME";

    public static final String EXTRA_CREATED_AT = "com.example.a200020512_wongchikon.EXTRA_CREATED_AT";
    public static final String EXTRA_CREATED_ON = "com.example.a200020512_wongchikon.EXTRA_CREATED_ON";
    public static final String EXTRA_DATE = "com.example.a200020512_wongchikon.EXTRA_DATE";
    public static final String EXTRA_STATUS = "com.example.a200020512_wongchikon.EXTRA_STATUS";
    public static final String EXTRA_PRIORITY = "com.example.a200020512_wongchikon.EXTRA_PRIORITY";

    private EditText editTextTaskName;
    private String stringCreatedAt;
    private TextView textViewDate;
    private DatePickerDialog datePickerDialog;
    private String stringStatus, stringCurrentDate, stringCreatedOn;
    private Spinner spinnerPriority;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        // set title
        setTitle(("add"));

        editTextTaskName = findViewById(R.id.EditText_TaskName);
        stringCreatedAt = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date()); //get SystemDate
        stringCreatedOn = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()); //get SystemDate
        textViewDate = findViewById(R.id.EditText_Date);
        stringCurrentDate = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date()); //get SystemDat
        textViewDate.setText(stringCurrentDate);
        stringStatus = "1"; // 1 is active, 0 is inactive
        spinnerPriority = findViewById(R.id.Spinner_Priority);
        buttonAdd = findViewById(R.id.Button_AddTask);

        // onclick show date picker dialog
        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AddTaskActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            // add 0 if not tens digit
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                                monthOfYear = monthOfYear + 1;
                                String formattedMonth = "" + monthOfYear;
                                String formattedDayOfMonth = "" + dayOfMonth;
                                if (monthOfYear < 10) {
                                    formattedMonth = "0" + monthOfYear;
                                }
                                if (dayOfMonth < 10) {
                                    formattedDayOfMonth = "0" + dayOfMonth;
                                }
                                String date = year + "/" + formattedMonth + "/" + formattedDayOfMonth;
                                textViewDate.setText(date);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        // add button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check input is not empty
                if (editTextTaskName.getText().toString().isEmpty() ||
                        textViewDate.getText().toString().isEmpty() ||
                        spinnerPriority.getSelectedItem().toString().isEmpty()) {
                    Toast.makeText(AddTaskActivity.this, ("Please Fill In All The Grids!"), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // past data to main activity
                    String taskname = editTextTaskName.getText().toString();
                    String createdat = stringCreatedAt;
                    String createdon = stringCreatedOn;
                    String date = textViewDate.getText().toString();
                    String status = stringStatus;
                    int priority = Integer.parseInt((String) spinnerPriority.getSelectedItem());
                    // pass data to main activity
                    Intent data = new Intent();
                    data.putExtra(EXTRA_NAME, taskname);
                    data.putExtra(EXTRA_CREATED_AT, createdat);
                    data.putExtra(EXTRA_CREATED_ON, createdon);
                    data.putExtra(EXTRA_DATE, date);
                    data.putExtra(EXTRA_PRIORITY, priority);
                    data.putExtra(EXTRA_STATUS, status);
                    setResult(RESULT_OK, data);
                    finish();
                }
                // check forward data
                Toast.makeText(AddTaskActivity.this,
                        "TaskName : " + editTextTaskName.getText().toString() +
                                " CreatedAt : " + stringCreatedAt +
                                " Date : " + textViewDate.getText().toString() +
                                " Priority : " + spinnerPriority.getSelectedItem().toString() +
                                " Status : " + stringStatus
                        , Toast.LENGTH_LONG).show();
            }
        });
    }
}

