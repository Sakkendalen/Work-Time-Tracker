package fi.tuni.work_time_tracker;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Main Class for starting Application and using other classes for
 * saving, deleting and editing workhours, Setting up Framgents to
 * UI.
 *
 * @author      Saku Tynjala saku.tynjala@tuni.fi
 * @version     0.3
 * @since       0.1
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    private EditText hours;

    private EditText comment;

    private DatePicker date;

    private static DatabaseHandler db;

    private TextView fetchedHours;

    /**
     * onCreate Method to Override super Method and define fxml file of layout,
     * Defining SectionsPageAdapter and Databasehandler to application.
     *
     * @param savedInstanceState Bundle, passed to super method.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        //InsertHoursActivity
        db = new DatabaseHandler(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * Method for Fragment setup for container.
     * Initializes and adds fragments InsertHoursFragment,
     * HoursCalendarFarment and AlterHoursFragment to viewPager.
     *
     * @param viewPager used in conjunction with Fragment.
     */
    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new InsertHoursFragment(), "Insert");
        adapter.addFragment(new HoursCalendarFragment(), "Hours");
        adapter.addFragment(new AlterHoursFragment(), "Alter");
        viewPager.setAdapter(adapter);
    }

    /**
     * Method for saving Workhour and passing it to Databasehandler to be saved in
     * database.
     *
     * @param view of fetching component of view. Not used.
     */
    public void saveHours(View view) {

        hours = (EditText) findViewById(R.id.hours);
        comment = (EditText) findViewById(R.id.comment);
        date = (DatePicker) findViewById(R.id.date);

        String dateString = ""+ date.getDayOfMonth()+"."+ (date.getMonth() + 1)+"."+date.getYear();

        // Inserting WorkHour
        db.addWorkHours(new WorkHour(dateString, hours.getText().toString(), comment.getText().toString()));
        Toast.makeText(this, "Workhour added to date " +dateString, Toast.LENGTH_LONG).show();
    }

    /**
     * Method for Fetching Sum of Workhours hours data from database and showing it to
     * user by given date.
     *
     * @param date Wanted day Workhours.
     */
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

    /**
     * Method for getting Application Databasehandler.
     *
     * @return DatabaseHandler of application
     */
    public static DatabaseHandler getDb(){
        return db;
    }
}