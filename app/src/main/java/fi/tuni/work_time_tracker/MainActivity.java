package fi.tuni.work_time_tracker;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    private EditText hours;

    private EditText comment;

    private DatePicker date;

    private DatabaseHandler db;

    private TextView fetchedHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        //InsertHoursActivity
        db = new DatabaseHandler(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new InsertHoursFragment(), "Insert");
        adapter.addFragment(new HoursCalendarFragment(), "Hours");
        adapter.addFragment(new AlterHoursFragment(), "Alter");
        viewPager.setAdapter(adapter);
    }

    public void saveHours(View view) {

        hours = (EditText) findViewById(R.id.hours);
        comment = (EditText) findViewById(R.id.comment);
        date = (DatePicker) findViewById(R.id.date);

        String dateString = ""+ date.getDayOfMonth()+"."+ (date.getMonth() + 1)+"."+date.getYear();

        // Inserting WorkHour
        db.addWorkHours(new WorkHour(dateString, hours.getText().toString(), comment.getText().toString()));
        Toast.makeText(this, "Workhour added to date " +dateString, Toast.LENGTH_LONG).show();
    }

//    public void delHour(String date) {
//        Intent myIntent = new Intent(this, deleteRowsActivity.class);
//        myIntent.putExtra("date", date);
//        Log.d("Starting", "DeleteActivity.");
//        startActivity(myIntent);
//    }
//
//    public void alterHour(String date) {
//        Intent myIntent = new Intent(this, deleteRowsActivity.class);
//        myIntent.putExtra("date", date);
//        Log.d("Starting", "AlterActivity.");
//        startActivity(myIntent);
//    }

//    public void delHour(String date){
//
//        List<WorkHour> contacts = db.getAllWorkHours();
//
//        for (WorkHour cn : contacts) {
//            if(cn.getDay().equals(date)) {
//                Log.d("DeleteHours:", "Deleting" + cn.getID() + " " + cn.getDay() + " " + cn.getHours() );
//                db.deleteWorkHour(cn);
//            }
//        }
//
//    }
//
//    public void alterHour(String date, String hour, String comment){
//
//        List<WorkHour> contacts = db.getAllWorkHours();
//
//        for (WorkHour cn : contacts) {
//            if(cn.getDay().equals(date)) {
//                Log.d("Altering:", "Altering" + cn.getID() + " " + cn.getDay() + " " + cn.getHours() + " " + cn.getComment() );
//                cn.setDay(date);
//                cn.setHours(hour);
//                cn.setHours(comment);
//                db.updateWorkHour(cn);
//            }
//        }
//
//    }

    public void fetchHours(String date){

        fetchedHours = (TextView) findViewById(R.id.fetchedHours);
        fetchedHours.setText("");

        // get all workhours
        List<WorkHour> contacts = db.getAllWorkhourByDate(date);
        int total = 0;

        //go though fetched hours and sum up workhours.
        for (WorkHour cn : contacts) {
                total += Integer.parseInt(cn.getHours());
        }

        //Display sum of given date workhours below CalendarView
        fetchedHours.append(date);
        String show = "\nTotal Hours: " + total;
        fetchedHours.append(show);
    }
}