package fi.tuni.work_time_tracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * Created by
 */

public class HoursCalendarFragment extends Fragment {

    private static final String TAG = "HoursCalendarFragment";

    CalendarView simpleCalendarView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hourscalendar,container,false);

        simpleCalendarView = (CalendarView) view.findViewById(R.id.simpleCalendarView);          // get the reference of CalendarView
        simpleCalendarView.setDate(System.currentTimeMillis(),true,true);  // set date to today.

        // perform setOnDateChangeListener event on CalendarView.
        simpleCalendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            // display the selected date by using a toast
            // for some reason month is 1 behind, Jan -> 0, Feb -> 1 etc....
            Toast.makeText(getContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
        });

        return view;
    }
}