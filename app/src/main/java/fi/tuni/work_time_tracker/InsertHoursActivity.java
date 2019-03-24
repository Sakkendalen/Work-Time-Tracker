package fi.tuni.work_time_tracker;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class InsertHoursActivity extends Activity {

    DatabaseHandler db;
    EditText hours;
    EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserthours);

        db = new DatabaseHandler(this);

        hours = (EditText) findViewById(R.id.hours);
        date = (EditText) findViewById(R.id.date);
    }

    public void saveHours(View view) {
        // Inserting WorkHour
        Log.d("WorkHourDebug: ", "Inserting ..");
        db.addWorkHours(new WorkHour(date.getText().toString(), hours.getText().toString()));

        // Reading all workhours
        Log.d("WorkHourDebug: ", "Reading all contacts..");
        List<WorkHour> contacts = db.getAllWorkHours();

        for (WorkHour cn : contacts) {
            String log = "WorkHourDebug: " + cn.getID() + " ,Date: " + cn.getDay() + " ,Hours: " +
                    cn.getHours();
            // Writing WorkHours to log
            Log.d("WorkHourDebug: ", log);
        }
    }
}
