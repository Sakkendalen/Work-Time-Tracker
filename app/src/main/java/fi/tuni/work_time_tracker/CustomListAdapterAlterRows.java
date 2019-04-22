package fi.tuni.work_time_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

/**
 * Adapter for listing data from database to context in activity_alter_rows.
 *
 * @author      Saku Tynjala saku.tynjala@tuni.fi
 * @version     0.3
 * @since       0.3
 */

public class CustomListAdapterAlterRows extends BaseAdapter {

    Context c;
    List<WorkHour> hours;
    DatabaseHandler db;

    /**
     * Constructor method.
     *
     * @param c context of where class is functioning.
     * @param db database where data is stored.
     * @param hours List of hours to be displayed.
     */
    public CustomListAdapterAlterRows(Context c, List<WorkHour> hours, DatabaseHandler db) {
        this.c = c;
        this.hours = hours;
        this.db = db;
    }

    /**
     * Method to get size of hours List.
     *
     * @return int of hours List size.
     */
    @Override
    public int getCount() {
        return hours.size();
    }

    /**
     * Method to return object from hours List
     *
     * @param i int of index of item in list.
     *
     * @return Workhour object.
     */
    @Override
    public Object getItem(int i) {
        return hours.get(i);
    }

    /**
     * Method to return object ID.
     *
     * @param i int of item ID.
     *
     * @return int of item ID.
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * method to return View of listviewalter_row and assign edit button a anonymoys method
     * for editing Workhour in database and List.
     *
     * @param i int to getting item in hours List.
     * @param view View inflated to set components in it.
     * @param viewGroup Viewgroup used to inflate.
     *
     * @return View to display fetched data from database.
     */
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.listviewalter_row,viewGroup,false);
        }

        EditText mtextView1 = (EditText) view.findViewById(R.id.textView1);
        EditText mtextView2 = (EditText) view.findViewById(R.id.textView2);
        EditText mtextView3 = (EditText) view.findViewById(R.id.textView3);
        Button deleteBtn = (Button) view.findViewById(R.id.button1);

        final WorkHour hour= (WorkHour) this.getItem(i);
        mtextView1.setText(hour.getDay());
        mtextView2.setText(hour.getHours());
        mtextView3.setText(hour.getComment());

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mtextView1.setText(mtextView1.getText());
                mtextView2.setText(mtextView2.getText());
                mtextView3.setText(mtextView3.getText());
                hour.setDay(mtextView1.getText().toString());
                hour.setHours(mtextView2.getText().toString());
                hour.setComment(mtextView3.getText().toString());

                db.updateWorkHour(hour);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}