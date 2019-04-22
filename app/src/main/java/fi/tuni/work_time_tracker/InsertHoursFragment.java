package fi.tuni.work_time_tracker;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment class to build tab Insert in application GUI.
 *
 * Extends from Fragment.
 *
 * @author      Saku Tynjala saku.tynjala@tuni.fi
 * @version     0.3
 * @since       0.2
 */

public class InsertHoursFragment extends Fragment {

    private static final String TAG = "InsertHoursFragment";

    /**
     * Inflates View and add xml layout fragment_inserthours to view.
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
        View view = inflater.inflate(R.layout.fragment_inserthours,container,false);

        return view;
    }
}