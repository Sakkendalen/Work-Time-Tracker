package fi.tuni.work_time_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapterAlterRows extends BaseAdapter {

    Context c;
    List<WorkHour> hours;
    DatabaseHandler db;

    public CustomListAdapterAlterRows(Context c, List<WorkHour> hours, DatabaseHandler db) {
        this.c = c;
        this.hours = hours;
        this.db = db;
    }

    @Override
    public int getCount() {
        return hours.size();
    }

    @Override
    public Object getItem(int i) {
        return hours.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

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
                db.addWorkHours(hour);
                hours.remove(i);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}