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

public class AlterRowsActivity extends AppCompatActivity {

    ListView listView;
    List<WorkHour> hours=new ArrayList<>();
    private DatabaseHandler db;
    CustomListAdapterAlterRows alterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String dateString = intent.getStringExtra("date");
        db = (DatabaseHandler) MainActivity.getDb();
        setContentView(R.layout.activity_delete_rows);

        hours = db.getAllWorkhourByDate(dateString);
        alterAdapter = new CustomListAdapterAlterRows(this, hours, db);
        listView = (ListView) findViewById(R.id.listviewdeleteID);
        listView.setAdapter(alterAdapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Alter Hours from " + dateString);
        }
    }


    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
