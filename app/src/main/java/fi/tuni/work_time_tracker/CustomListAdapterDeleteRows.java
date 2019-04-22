package fi.tuni.work_time_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

/**
 * Adapter for listing data from database to context in activity_delete_rows.
 *
 * @author      Saku Tynjala saku.tynjala@tuni.fi
 * @version     0.3
 * @since       0.3
 */

public class CustomListAdapterDeleteRows extends BaseAdapter {

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
    public CustomListAdapterDeleteRows(Context c, List<WorkHour> hours, DatabaseHandler db) {
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
     * method to return View of listviewalter_row and assign delete button a anonymoys method
     * for deleting Workhour in database and List.
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
            view= LayoutInflater.from(c).inflate(R.layout.listviewdelete_row,viewGroup,false);
        }

        TextView mtextView1 = (TextView) view.findViewById(R.id.textView1);
        TextView mtextView2 = (TextView) view.findViewById(R.id.textView2);
        TextView mtextView3 = (TextView) view.findViewById(R.id.textView3);
        Button deleteBtn = (Button) view.findViewById(R.id.button1);

        final WorkHour hour= (WorkHour) this.getItem(i);
        mtextView1.setText(hour.getDay());
        mtextView2.setText(hour.getHours());
        mtextView3.setText(hour.getComment());

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                db.deleteWorkHour(hour);
                hours.remove(i);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}