package fi.tuni.work_time_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity class to Edit data at database and sets layout from activity_alter_rows.xml
 *
 * Extends from AppCompatActivity
 *
 * @author      Saku Tynjala saku.tynjala@tuni.fi
 * @version     0.3
 * @since       0.3
 */

public class AlterRowsActivity extends AppCompatActivity {

    ListView listView;
    List<WorkHour> hours=new ArrayList<>();
    private DatabaseHandler db;
    CustomListAdapterAlterRows alterAdapter;

    /**
     * onCreate Method to Override super Method and define fxml file of layout.
     * Fetches used DatabaseHandler from MainActivity class to fetch and edit
     * data at database.
     *
     * @param savedInstanceState Bundle, passed to super method.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String dateString = intent.getStringExtra("date");
        db = (DatabaseHandler) MainActivity.getDb();
        setContentView(R.layout.activity_alter_rows);

        hours = db.getAllWorkhourByDate(dateString);
        alterAdapter = new CustomListAdapterAlterRows(this, hours, db);
        listView = (ListView) findViewById(R.id.listviewdeleteID);
        listView.setAdapter(alterAdapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Edit Hours from " + dateString);
        }
    }


    /**
     * When successfully handle a menu item, return true.
     * If doesn't handle the menu item, calls the superclass
     * implementation of onOptionsItemSelected() (the default implementation returns false).
     *
     * @param item selected item.
     *
     * @return boolean of handling menu item.
     */
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Initialize the contents of the Activity's standard options menu.
     *
     * @param menu The options menu in which you place your items.
     *
     * @return boolean always true.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
