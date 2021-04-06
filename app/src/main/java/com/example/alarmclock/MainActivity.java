package com.example.alarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText timehr,timemin;
    Button settime,setalarm;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int curhr,curmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timehr = findViewById(R.id.etHour);
        timemin = findViewById(R.id.etMin);
        settime = findViewById(R.id.btnTime);
        setalarm = findViewById(R.id.btnAlarm);



        settime.setOnClickListener((v) ->  {
            calendar = Calendar.getInstance();
            curhr = calendar.get(Calendar.HOUR_OF_DAY);
            curmin = calendar.get(Calendar.MINUTE);



            timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    timehr.setText(String.format("%02d", hourOfDay));
                    timemin.setText(String.format("%02d", minute));
                }
            },curhr,curmin,false);
            timePickerDialog.show();
        });
        setalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timehr.getText().toString().isEmpty() && !timemin.getText().toString().isEmpty()) {
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(timehr.getText().toString()));
                    intent.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(timemin.getText().toString()));
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Set alarm for morning walk");
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "There is no app that support this action", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Please choose time", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
