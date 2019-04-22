package fi.tuni.work_time_tracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import java.util.Objects;

/**
 * Fragment class to build tab Hours in application GUI
 * and add Calendar setOnDateChangeListener.
 *
 * Extends from Fragment.
 *
 * @author      Saku Tynjala saku.tynjala@tuni.fi
 * @version     0.3
 * @since       0.2
 */

public class HoursCalendarFragment extends Fragment {

    private static final String TAG = "HoursCalendarFragment";

    private CalendarView simpleCalendarView;
    private TextView week1;
    private TextView week2;
    private TextView week3;
    private TextView week4;
    private TextView week5;


    /**
     * Inflates View and add xml layout fragment_hourscalendar to view.
     *
     * Assigns simpleCalendarView today as date to be chosen and creates
     * OnDateChangleListener which fetches that day sum of inserted Workhour hours data
     * from database.
     *
     * @param inflater to inflate view with container and layout.
     * @param container nullable Viewgroup.
     * @param savedInstanceState nullable Bundle.
     *
     * @return View of xml file.
     */
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