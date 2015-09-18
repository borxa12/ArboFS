package com.santirodriguezlorenzo.arbofs.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.santirodriguezlorenzo.arbofs.R;
import com.santirodriguezlorenzo.arbofs.activity.CalendarActivity;
import com.santirodriguezlorenzo.arbofs.activity.ContactoActivity;
import com.santirodriguezlorenzo.arbofs.activity.ContributorsActivity;
import com.santirodriguezlorenzo.arbofs.activity.RatingActivity;
import com.santirodriguezlorenzo.arbofs.activity.TemplateActivity;

/**
 * Created by Santi on 26/08/2015.
 */
public class InitFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View btnTemplate;
    private View btnRating;
    private View btnCalendar;
    private View btnContact;
    private View btnColaborators;


    public static InitFragment newInstance(String param1, String param2) {
        InitFragment fragment = new InitFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public InitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_init, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        btnTemplate = (View) getView().findViewById(R.id.btn_template);
        btnRating = (View) getView().findViewById(R.id.btn_rating);
        btnCalendar = (View) getView().findViewById(R.id.btn_calendar);
        btnContact = (View) getView().findViewById(R.id.btn_contact);
        btnColaborators = (View) getView().findViewById(R.id.btn_colaborators);

        btnTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TemplateActivity.class));
            }
        });
        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RatingActivity.class));
            }
        });
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CalendarActivity.class));
            }
        });
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ContactoActivity.class));
            }
        });
        btnColaborators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ContributorsActivity.class));
            }
        });


    }




}

