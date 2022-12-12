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
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.util.ImageUtils;

import java.util.Base64;
import java.util.List;

public class GymAdapter extends BaseAdapter {

    private Context context;
    private List<Gym> gymArrayList;
    private LayoutInflater inflater;

    public GymAdapter(Activity context, List<Gym> gymArrayList) {
        this.context = context;
        this.gymArrayList = gymArrayList;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Gym gym = (Gym) getItem(position);

        convertView = inflater.inflate(R.layout.staff_and_gym_adapter, null);
        TextView gymName = convertView.findViewById(R.id.staff_gym_tv1);
        TextView gymCity = convertView.findViewById(R.id.staff_gym_tv2);
        ImageView gymImage = (ImageView) convertView.findViewById(R.id.staff_gym_item_imageView);

            if (gym.getGymImage() != null) {  // Valido si no es null la foto, si no sale fallo nullpoint...
            byte[] decode = Base64.getDecoder().decode(gym.getGymImage());
            Log.i("gym",  Base64.getEncoder().encodeToString(decode));
            gymImage.setImageBitmap(ImageUtils.getBitmap(decode));
            //userImage.setImageBitmap(ImageUtils.getBitmap(user.getUserImage()));
        } else {
            gymImage.setImageResource(R.drawable.weight_default);
        }
        gymName.setText(gym.getName());
        gymCity.setText(gym.getCity());



        return convertView;
    }










    @Override
    public int getCount() {
        return gymArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return gymArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
