package com.svalero.fitstagerapplication.adapters;

import android.app.Activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.svalero.fitstagerapplication.R;
import com.svalero.fitstagerapplication.domain.Activitie;
import com.svalero.fitstagerapplication.util.ImageUtils;

import java.util.Base64;
import java.util.List;

public class ActivityAdapter extends BaseAdapter {


    private Context context;
    private List<Activitie> activityArrayList;
    private LayoutInflater inflater;

    public ActivityAdapter(Activity context, List<Activitie> activityArrayList) {
        this.context = context;
        this.activityArrayList = activityArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activitie activity = (Activitie) getItem(position);

        convertView = inflater.inflate(R.layout.staff_and_gym_adapter, null);
        TextView activityName = convertView.findViewById(R.id.staff_gym_tv1);
        TextView activityStyle = convertView.findViewById(R.id.staff_gym_tv2);
        ImageView activityImage = (ImageView) convertView.findViewById(R.id.staff_gym_item_imageView);

        if (activity.getActivityImage() != null) {  // Valido si no es null la foto, si no sale fallo nullpoint...
            byte[] decode = Base64.getDecoder().decode(activity.getActivityImage());
            Log.i("gym",  Base64.getEncoder().encodeToString(decode));
            activityImage.setImageBitmap(ImageUtils.getBitmap(decode));
            //userImage.setImageBitmap(ImageUtils.getBitmap(user.getUserImage()));
        } else {
            activityImage.setImageResource(R.drawable.activity_default);
        }
        activityName.setText(activity.getName());
        activityStyle.setText(activity.getStyle());

        return convertView;
    }

    @Override
    public int getCount() {
        return activityArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return activityArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}







