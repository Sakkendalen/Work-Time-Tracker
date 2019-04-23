package fi.tuni.work_time_tracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseHandler class handles given data of object Workhour and
 * saves, deletes or updates rows in SQLiteDatabase.
 *
 * @author      Saku Tynjala saku.tynjala@tuni.fi
 * @version     0.3
 * @since       0.1
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "WorkHourManager";
    private static final String TABLE_WORKHOUR = "WorkHour";
    private static final String KEY_ID = "id";
    private static final String KEY_DAY = "day";
    private static final String KEY_HOURS = "hours";
    private static final String KEY_COMMENT = "comment";

    /**
     * Constructor method.
     *
     * @param context to casted at super method.
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_WORKHOUR + "("
                + KEY_ID + "  INTEGER PRIMARY KEY, "
                + KEY_DAY + " TEXT, "
                + KEY_HOURS + " TEXT, "
                + KEY_COMMENT + " TEXT);"
        );
    }

    /**
     * Called when the database needs to be upgraded.
     *
     * @param db The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKHOUR);

        onCreate(db);
    }

    /**
     * Method for saving Workhour to database.
     *
     * @param workhour object Workhour to be saved.
     */
    public void addWorkHours(WorkHour workhour) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DAY, workhour.getDay());
        values.put(KEY_HOURS, workhour.getHours());
        values.put(KEY_COMMENT, workhour.getComment());

        db.insert(TABLE_WORKHOUR, null, values);
        db.close();
    }

    /**
     * Method for getting single row from database
     *
     * @param id ID of fetched Workhour.
     *
     * @return fetched Workhour.
     */
    public WorkHour getWorkhour(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WORKHOUR, new String[] { KEY_ID,
                        KEY_DAY, KEY_HOURS, KEY_COMMENT }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        WorkHour workhour = new WorkHour(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return workhour;
    }

    /**
     * Method for getting all rows from table.
     *
     * @return List of Workhour objects.
     */
    public List<WorkHour> getAllWorkHours() {
        List<WorkHour> workhourList = new ArrayList<WorkHour>();
        String selectQuery = "SELECT  * FROM " + TABLE_WORKHOUR;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                WorkHour workhour = new WorkHour();
                workhour.setID(Integer.parseInt(cursor.getString(0)));
                workhour.setDay(cursor.getString(1));
                workhour.setHours(cursor.getString(2));
                workhour.setComment(cursor.getString(3));
                workhourList.add(workhour);
            } while (cursor.moveToNext());
        }

        return workhourList;
    }

    /**
     * Method for getting all rows by given date parameter.
     *
     * @param date of Workhour
     *
     * @return List of Workhour objects.
     */
    public List<WorkHour> getAllWorkhourByDate(String date) {
        List<WorkHour> workhourList = new ArrayList<WorkHour>();
        String q = "SELECT * FROM " + TABLE_WORKHOUR + "  WHERE day = '" + date + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(q, null);

        if (cursor.moveToFirst()) {
            do {
                WorkHour workhour = new WorkHour();
                workhour.setID(Integer.parseInt(cursor.getString(0)));
                workhour.setDay(cursor.getString(1));
                workhour.setHours(cursor.getString(2));
                workhour.setComment(cursor.getString(3));
                workhourList.add(workhour);
            } while (cursor.moveToNext());
        }
        return workhourList;
    }

    /**
     * Method for updating row in database
     *
     * @param workhour updated Object Workhour.
     *
     * @return int of updated Object ID.
     */
    // code to update the single WorkHour
    public int updateWorkHour(WorkHour workhour) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DAY, workhour.getDay());
        values.put(KEY_HOURS, workhour.getHours());
        values.put(KEY_COMMENT, workhour.getComment());

        return db.update(TABLE_WORKHOUR, values, KEY_ID + " = ?",
                new String[] { String.valueOf(workhour.getID()) });
    }

    /**
     * Method for deleting row from database.
     *
     * @param workhour object which is wanted to be deleted.
     */
    // Deleting single WorkHour
    void deleteWorkHour(WorkHour workhour) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORKHOUR, KEY_ID + " = ?",
                new String[] { String.valueOf(workhour.getID()) });
        db.close();
    }

    /**
     * Method to get count of rows in database
     *
     * @return int of rows count.
     */
    public int getWorkHoursCount() {
        String countQuery = "SELECT  * FROM " + TABLE_WORKHOUR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

}
