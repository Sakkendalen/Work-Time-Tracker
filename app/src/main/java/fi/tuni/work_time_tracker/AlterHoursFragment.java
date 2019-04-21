package fi.tuni.work_time_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.Objects;

/**
 * Created by User on 2/28/2017.
 */

public class AlterHoursFragment extends Fragment {
    private static final String TAG = "Tab3Fragment";

    private Button btnAlter;
    private Button btnDelete;
    private DatePicker date;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alterhours,container,false);

        btnAlter = view.findViewById(R.id.alterBtn);
        btnDelete = view.findViewById(R.id.delHours);
        date = (DatePicker) view.findViewById(R.id.dateAlter);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                delHour();
            }
        });
//        btnAlter.setOnClickListener(v -> alterHour());

        return view;
    }

    public void delHour() {
        Intent myIntent = new Intent(getActivity(), deleteRowsActivity.class);
        String dateString = "" + date.getDayOfMonth() +"."+ date.getMonth() +"."+ date.getYear();
        myIntent.putExtra("date", dateString);
        Log.d("Starting", "DeleteActivity.");
        startActivity(myIntent);
    }
//
//    public void alterHour() {
//        Intent myIntent = new Intent(getActivity(), deleteRowsActivity.class);
//        String dateString = ""+ date.getDayOfMonth()+"."+ (date.getMonth() + 1)+"."+date.getYear();
//        myIntent.putExtra("date", dateString);
//        Log.d("Starting", "DeleteActivity.");
//        startActivity(myIntent);
//    }
}
