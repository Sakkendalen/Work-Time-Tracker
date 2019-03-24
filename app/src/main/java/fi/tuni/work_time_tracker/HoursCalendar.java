package fi.tuni.work_time_tracker;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

public class HoursCalendar extends Activity {

    CalendarView simpleCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourscalendar);
        simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView);          // get the reference of CalendarView
        simpleCalendarView.setDate(System.currentTimeMillis(),false,true);  // set date to todáy.

        // perform setOnDateChangeListener event on CalendarView.
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                // for some reason month is 1 behind, Jan -> 0, Feb -> 1 etc....
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
    }


}
