package fi.tuni.work_time_tracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
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
    private DatePicker date;
    private EditText hours;
    private EditText comment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alterhours,container,false);

        btnAlter = view.findViewById(R.id.alterBtn);
        btnDelete = view.findViewById(R.id.delHours);
        date = view.findViewById(R.id.dateAlter);
        hours = view.findViewById(R.id.hoursAlter);
        comment = view.findViewById(R.id.comment);

        btnAlter.setOnClickListener(view1 -> {
            if(!(hours.getText() == null)){
                String dateString = ""+ date.getDayOfMonth()+"."+ (date.getMonth() + 1)+"."+date.getYear();
                ((MainActivity) Objects.requireNonNull(getActivity())).alterHour(dateString, hours.getText().toString(), comment.getText().toString());
            }
            else{
                Toast.makeText(getContext(), "Please enter date and Hours!", Toast.LENGTH_LONG).show();
            }
        });

        btnDelete.setOnClickListener(view1 -> {
                Toast.makeText(getContext(), "Deleting", Toast.LENGTH_LONG).show();
        });

        return view;
    }
}
