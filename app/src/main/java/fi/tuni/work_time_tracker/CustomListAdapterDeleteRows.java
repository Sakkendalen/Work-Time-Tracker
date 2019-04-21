package fi.tuni.work_time_tracker;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class CustomListAdapterDeleteRows extends BaseAdapter {

    Context c;
    List<WorkHour> hours;
    DatabaseHandler db;

    public CustomListAdapterDeleteRows(Context c, List<WorkHour> hours, DatabaseHandler db) {
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