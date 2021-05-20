package com.example.todolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "TaskName";
    private static final String ARG_PARAM2 = "CreatedAt";
    private static final String ARG_PARAM3 = "CreatedOn";
    private static final String ARG_PARAM4 = "Date";
    private static final String ARG_PARAM5 = "Priority";

    // TODO: Rename and change types of parameters
    private String mTaskName;
    private String mCreatedAt;
    private String mCreatedOn;
    private String mDate;
    private String mPriority;

    TextView textViewTaskName, textViewCreatedAt, textViewCreatedOn, textViewDate, textViewPriority;

    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @param param3 Parameter 3.
     * @param param4 Parameter 4.
     * @param param5 Parameter 5.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String param1, String param2, String param3, String param4, String param5) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTaskName = getArguments().getString(ARG_PARAM1);
            mCreatedAt = getArguments().getString(ARG_PARAM2);
            mCreatedOn = getArguments().getString(ARG_PARAM3);
            mDate = getArguments().getString(ARG_PARAM4);
            mPriority = getArguments().getString(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false); // link fragment layout
        textViewTaskName = view.findViewById(R.id.TaskName);
        textViewCreatedAt = view.findViewById(R.id.CreatedAt);
        textViewCreatedOn = view.findViewById(R.id.CreatedOn);
        textViewDate = view.findViewById(R.id.Date);
        textViewPriority = view.findViewById(R.id.Priority);

        // set text
        textViewTaskName.setText("TaskName : " + mTaskName);
        textViewCreatedAt.setText("CreatedAt : " + mCreatedAt);
        textViewCreatedOn.setText("CreatedOn : " + mCreatedOn);
        textViewDate.setText("Date : " + mDate);
        textViewPriority.setText("Priority : " + mPriority);


        return view;
    }
}