package fi.tuni.work_time_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

/**
 * Fragment class to build tab Alter in application GUI
 * and add Buttons onclickListener.
 *
 * Extends from Fragment.
 *
 * @author      Saku Tynjala saku.tynjala@tuni.fi
 * @version     0.3
 * @since       0.2
 */
public class AlterHoursFragment extends Fragment {
    private static final String TAG = "Tab3Fragment";

    private Button btnAlter;
    private Button btnDelete;
    private DatePicker date;

    /**
     * Inflates View and add xml layout fragment_alterhours to view.
     *
     * Assign buttons on clickListeners which starts new activities to delete and
     * edit inserted data to database.
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
        View view = inflater.inflate(R.layout.fragment_alterhours,container,false);

        btnAlter = view.findViewById(R.id.alterBtn);
        btnDelete = view.findViewById(R.id.delHours);
        date = (DatePicker) view.findViewById(R.id.dateAlter);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                delHour();
            }
        });
        btnAlter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alterHour();
            }
        });

        return view;
    }

    /**
     * Method to start DeleteRowsActivity and pass it
     * user selected date to delete data from database.
     *
     * Puts given date to Intent extra which can be used from DeleteRowsActivity.
     */
    public void delHour() {
        Intent myIntent = new Intent(getActivity(), DeleteRowsActivity.class);
        String dateString = "" + date.getDayOfMonth() +"."+ (date.getMonth() + 1) +"."+ date.getYear();
        myIntent.putExtra("date", dateString);
        startActivity(myIntent);
    }

    /**
     * Method to start AlterRowsActivity and pass it
     * user selected date to edit data from database.
     *
     * Puts given date to Intent extra which can be used from AlterRowsActivity.
     */
    public void alterHour() {
        Intent myIntent = new Intent(getActivity(), AlterRowsActivity.class);
        String dateString = ""+ date.getDayOfMonth()+"."+ (date.getMonth() + 1)+"."+date.getYear();
        myIntent.putExtra("date", dateString);
        startActivity(myIntent);
    }
}
