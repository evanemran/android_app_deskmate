package com.example.connectiontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class ClockActivity extends AppCompatActivity {
    private TextView txttime,txtday,txtdate;
    public static final String DATE_FORMAT_1 = "hh:mm:ss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_clock);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        txtday = findViewById(R.id.txtday);
        txtdate = findViewById(R.id.txtdate);


        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                // Current day is Sunday
                txtday.setText("SUNDAY");
                break;
            case Calendar.MONDAY:
                // Current day is Monday
                txtday.setText("MONDAY");
                break;
            case Calendar.TUESDAY:
                txtday.setText("TUESDAY");
                // etc.
                break;
            case Calendar.WEDNESDAY:
                txtday.setText("WEDNESDAY");
                // etc.
                break;
            case Calendar.THURSDAY:
                txtday.setText("THURSDAY");
                // etc.
                break;
            case Calendar.FRIDAY:
                txtday.setText("FRIDAY");
                // etc.
                break;
            case Calendar.SATURDAY:
                txtday.setText("SATURDAY");
                // etc.
                break;
        }


        final Handler ha=new Handler();
        ha.postDelayed(new Runnable() {

            @Override
            public void run() {
                //call function
                getTime();

                ha.postDelayed(this, 1000);
            }
        }, 1000);

        txttime = findViewById(R.id.txttime);

    }
    private void getTime()
    {
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        String currentTime = java.text.DateFormat.getTimeInstance().format(new Date());
        String currentDate = java.text.DateFormat.getDateInstance().format(new Date());
        txttime.setText(currentTime);
        txtdate.setText(currentDate);

    }

}
