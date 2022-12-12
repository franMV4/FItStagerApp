package com.svalero.fitstagerapplication.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.svalero.fitstagerapplication.R;
import com.svalero.fitstagerapplication.util.ImageUtils;

import java.time.LocalDate;


public class DetailFragment extends Fragment {

    private closeDetails closeDetails;
    private String activity;
    private final String VIEW_STAFF_ACTIVITY = "StaffListView";
    private final String VIEW_CLIENT_ACTIVITY = "ClientListView";
    private final String VIEW_GYM_ACTIVITY = "GymListView";
    private final String VIEW_ACTIVITY_ACTIVITY = "ActivityListView";

    public FloatingActionButton closeButton;

    public DetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View detailView = inflater.inflate(R.layout.fragment_staff_detail, container, false);

        Activity thisActivity = getActivity();
        if (thisActivity != null) {
            if (thisActivity.toString().contains(VIEW_STAFF_ACTIVITY)) {
                activity = VIEW_STAFF_ACTIVITY;
            } else if (thisActivity.toString().contains(VIEW_CLIENT_ACTIVITY)) {
                activity = VIEW_CLIENT_ACTIVITY;
            } else if (thisActivity.toString().contains(VIEW_GYM_ACTIVITY)) {
                activity = VIEW_GYM_ACTIVITY;
            }else if (thisActivity.toString().contains(VIEW_ACTIVITY_ACTIVITY)) {
                activity = VIEW_ACTIVITY_ACTIVITY;
            }
        }

        closeButton = detailView.findViewById(R.id.close_detail_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDetails.hiddeDetails();
            }
        });

        return detailView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = view.findViewById(R.id.fragment_detail_imageview);
        TextView textView1 = view.findViewById(R.id.fragment_detail_textview1);
        TextView textView2 = view.findViewById(R.id.fragment_detail_textview2);
        TextView textView3 = view.findViewById(R.id.fragment_detail_textview3);
        TextView textView4 = view.findViewById(R.id.fragment_detail_textview4);

        switch (activity) {
            case VIEW_STAFF_ACTIVITY:

                if (getArguments() != null) {
                    if (getArguments().getByteArray("staff_image") != null)
                        imageView.setImageBitmap(ImageUtils.getBitmap(getArguments().getByteArray("staff_image")));
                    textView1.setText(getArguments().getString("name") + " " + getArguments().getString("surname"));
                    textView2.setText(getArguments().getString("dni") + " || " + getArguments().getString("phone"));
                }

                break;
            case VIEW_GYM_ACTIVITY:
                if (getArguments() != null) {
                    if (getArguments().getByteArray("gym_image") != null)

                        imageView.setImageBitmap(ImageUtils.getBitmap(getArguments().getByteArray("gym_image")));
                    textView1.setText(getArguments().getString("name") + " || " + getArguments().getString("city"));
                    textView2.setText(getArguments().getString("street") + " || " + getArguments().getString("horary"));
                }
                break;

            case VIEW_CLIENT_ACTIVITY:
                if (getArguments() != null) {
                    if (getArguments().getByteArray("client_image") != null)
                        imageView.setImageBitmap(ImageUtils.getBitmap(getArguments().getByteArray("client_image")));
                    textView1.setText(getArguments().getString("name") + " " + getArguments().getString("surname"));
                    textView2.setText(getArguments().getString("dni") + " || " + getArguments().getString("phone"));
                }
                break;
            case VIEW_ACTIVITY_ACTIVITY:
                if (getArguments() != null) {
                    if (getArguments().getByteArray("activity_image") != null)
                        imageView.setImageBitmap(ImageUtils.getBitmap(getArguments().getByteArray("activity_image")));
                    textView1.setText(getArguments().getString("name") + " " + getArguments().getString("difficulty"));
                    textView2.setText(getArguments().getString("room") + " || " + getArguments().getString("style"));
                }
                break;
        }   // End switch
    }



    public interface closeDetails {
        void hiddeDetails();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof closeDetails) {
            closeDetails = (DetailFragment.closeDetails) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }



















}