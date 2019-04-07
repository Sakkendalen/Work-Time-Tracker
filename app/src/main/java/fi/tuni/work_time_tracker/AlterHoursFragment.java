package fi.tuni.work_time_tracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

/**
 * Created by User on 2/28/2017.
 */

public class AlterHoursFragment extends Fragment {
    private static final String TAG = "Tab3Fragment";

    private Button btnAlter;
    private Button btnDelete;
    private EditText date;
    private EditText hours;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alterhours,container,false);

        btnAlter = view.findViewById(R.id.alterBtn);
        btnDelete = view.findViewById(R.id.delHours);
        date = view.findViewById(R.id.dateAlter);
        hours = view.findViewById(R.id.hoursAlter);

        btnAlter.setOnClickListener(view1 -> {
            if(!(date.getText() == null) || !(hours.getText() == null)){
                ((MainActivity) Objects.requireNonNull(getActivity())).alterHour(date.getText().toString(), hours.getText().toString());
            }
            else{
                Toast.makeText(getContext(), "Please enter date and Hours!", Toast.LENGTH_LONG).show();
            }
        });

        btnDelete.setOnClickListener(view1 -> {
            if(!(date.getText() == null)){
                ((MainActivity) Objects.requireNonNull(getActivity())).delHour(date.getText().toString());
            }
            else{
                Toast.makeText(getContext(), "Please enter date!", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
