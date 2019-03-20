package fi.tuni.work_time_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void hoursActivity(View view){
        Intent i = new Intent(this, InsertHoursActivity.class);
        startActivity(i);
    }

    public void calendarActivity(View view){
        Intent i = new Intent(this, HoursCalendar.class);
        startActivity(i);
    }
}
