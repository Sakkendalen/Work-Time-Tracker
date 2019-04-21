package fi.tuni.work_time_tracker;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

/**
 * Created by
 */

public class HoursCalendarFragment extends Fragment {

    private static final String TAG = "HoursCalendarFragment";

    private CalendarView simpleCalendarView;
    private TextView week1;
    private TextView week2;
    private TextView week3;
    private TextView week4;
    private TextView week5;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hourscalendar,container,false);

        simpleCalendarView = (CalendarView) view.findViewById(R.id.simpleCalendarView);    // get the reference of CalendarView
        simpleCalendarView.setDate(System.currentTimeMillis(),true,true);  // set date to today.
        week1 = (TextView) view.findViewById(R.id.week1);
        week2 = (TextView) view.findViewById(R.id.week2);
        week3 = (TextView) view.findViewById(R.id.week3);
        week4 = (TextView) view.findViewById(R.id.week4);
        week5 = (TextView) view.findViewById(R.id.week5);

        // perform setOnDateChangeListener event on CalendarView.
        // clicking selected day fethces given date workhours sum.
        simpleCalendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            String date = "" + dayOfMonth + "." + (month + 1) + "." + year;
            ((MainActivity) Objects.requireNonNull(getActivity())).fetchHours(date);
        });

        return view;
    }
}