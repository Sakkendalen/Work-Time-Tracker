package fi.tuni.work_time_tracker;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    private EditText hours;
    private EditText date;
    private DatabaseHandler db;

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
        date = (EditText) findViewById(R.id.date);

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