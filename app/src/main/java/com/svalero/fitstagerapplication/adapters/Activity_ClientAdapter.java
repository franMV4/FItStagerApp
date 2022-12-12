package com.svalero.fitstagerapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.svalero.fitstagerapplication.R;
import com.svalero.fitstagerapplication.domain.Activity_Client;
import com.svalero.fitstagerapplication.domain.Staff;

import java.util.List;

public class Activity_ClientAdapter extends BaseAdapter {


    private Context context;
    private List<Activity_Client> activity_clientArrayList;
    private LayoutInflater inflater;

    public Activity_ClientAdapter(Activity context, List<Activity_Client> activity_clientArrayList) {
        this.context = context;
        this.activity_clientArrayList = activity_clientArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity_Client activity_client = (Activity_Client) getItem(position);

        convertView = inflater.inflate(R.layout.staff_and_gym_adapter, null);
        TextView activity_clientActivity = convertView.findViewById(R.id.staff_gym_tv1);
        TextView activity_clientSurname = convertView.findViewById(R.id.staff_gym_tv2);

        //if (!computer.getComputerImage().equalsIgnoreCase(""))
            //computerImage.setImageBitmap(ImageUtils.getBitmap(Base64.getDecoder().decode(computer.getComputerImage())));

        activity_clientActivity.setText(activity_client.getActivity().getName());
        activity_clientSurname.setText(activity_client.getClient().getName());

        return convertView;
    }

    @Override
    public int getCount() {
        return activity_clientArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return activity_clientArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}







