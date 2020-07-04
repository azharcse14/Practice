package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    String [] department = {"CSE","EEE","ICT","ECE","CIVIL", "IPE"};
//    AutoCompleteTextView autoCompleteTextView;

    TextView textView, switchTextView;
    Button button;
    DatePickerDialog datePickerDialog;

    ProgressBar progressBar;
    int progress;

    Switch aSwitch;

    @Override
    protected void onCreate(Bundle pi) {
        super.onCreate(pi);
        setContentView(R.layout.activity_main);

//        autoCompleteTextView = findViewById(R.id.aCTV);
//        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,department);
//        autoCompleteTextView.setAdapter(adapter);
//        autoCompleteTextView.setThreshold(1);

        textView = findViewById(R.id.text);
        button = findViewById(R.id.btn);
        button.setOnClickListener(this);

        progressBar = findViewById(R.id.progressBar);

        switchTextView = findViewById(R.id.switchTvId);
        aSwitch = findViewById(R.id.switchId);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
            }
        });
        thread.start();

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    switchTextView.setText("Wifi is ON");
                }else{
                    switchTextView.setText("Wifi is OFF");
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        DatePicker datePicker = new DatePicker(this);
        int currentDay = datePicker.getDayOfMonth();
        int currentMonth = (datePicker.getMonth())+1;
        int currentYear = datePicker.getYear();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textView.setText(dayOfMonth+"/"+ (month+1)+"/"+year);
            }
        }, currentYear, currentMonth, currentDay);

        datePickerDialog.show();
    }

    public void doWork(){
        for (progress = 1; progress<=100; progress=progress+1){
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


}
