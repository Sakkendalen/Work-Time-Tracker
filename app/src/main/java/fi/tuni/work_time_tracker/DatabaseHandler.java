package fi.tuni.work_time_tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WorkHourManager";
    private static final String TABLE_WORKHOURS = "WorkHours";
    private static final String KEY_ID = "id";
    private static final String KEY_DAY = "day";
    private static final String KEY_HOURS = "hours";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WORKHOURS_TABLE = "CREATE TABLE " + TABLE_WORKHOURS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DAY + " TEXT,"
                + KEY_HOURS + " TEXT" + ")";
        db.execSQL(CREATE_WORKHOURS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKHOURS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new WorkHours
    void addWorkHours(WorkHour workhour) {
        SQLiteDatabase db = this.getWritableDatabase();

        //HOX! HOX! TRY TO CHANGE THESE TO DATE AND TIME FORMAT! WORKHOUR RETURNS STRING!
        ContentValues values = new ContentValues();
        values.put(KEY_DAY, workhour.getDay()); // WorkHour Day
        values.put(KEY_HOURS, workhour.getHours()); // WorkHour Hour

        // Inserting Row
        db.insert(TABLE_WORKHOURS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single WorkHour
    WorkHour getWorkhour(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WORKHOURS, new String[] { KEY_ID,
                        KEY_DAY, KEY_HOURS }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        WorkHour workhour = new WorkHour(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return WorkHour
        return workhour;
    }

    // code to get all WorkHour in a list view
    public List<WorkHour> getAllWorkHours() {
        List<WorkHour> workhourList = new ArrayList<WorkHour>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_WORKHOURS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                WorkHour workhour = new WorkHour();
                workhour.setID(Integer.parseInt(cursor.getString(0)));
                workhour.setDay(cursor.getString(1));
                workhour.setHours(cursor.getString(2));
                // Adding WorkHour to list
                workhourList.add(workhour);
            } while (cursor.moveToNext());
        }

        // return WorkHour list
        return workhourList;
    }

    // code to update the single WorkHour
    public int updateWorkHour(WorkHour workhour) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DAY, workhour.getDay());
        values.put(KEY_HOURS, workhour.getHours());

        // updating row
        return db.update(TABLE_WORKHOURS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(workhour.getID()) });
    }

    // Deleting single WorkHour
    public void deleteWorkHour(WorkHour workhour) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORKHOURS, KEY_ID + " = ?",
                new String[] { String.valueOf(workhour.getID()) });
        db.close();
    }

    // Getting WorkHours Count
    public int getWorkHoursCount() {
        String countQuery = "SELECT  * FROM " + TABLE_WORKHOURS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
