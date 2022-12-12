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
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.util.ImageUtils;

import java.util.Base64;
import java.util.List;

public class StaffAdapter extends BaseAdapter {


    private Context context;
    private List<Staff> staffArrayList;
    private LayoutInflater inflater;

    public StaffAdapter(Activity context, List<Staff> staffArrayList) {
        this.context = context;
        this.staffArrayList = staffArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Staff staff = (Staff) getItem(position);

        convertView = inflater.inflate(R.layout.staff_and_gym_adapter, null);
        ImageView staffImage = (ImageView) convertView.findViewById(R.id.staff_gym_item_imageView);
        TextView staffName = convertView.findViewById(R.id.staff_gym_tv1);
        TextView staffSurname = convertView.findViewById(R.id.staff_gym_tv2);

        if (staff.getStaffImage() != null) {  // Valido si no es null la foto, si no sale fallo nullpoint...
            byte[] decode = Base64.getDecoder().decode(staff.getStaffImage());
            Log.i("userr",  Base64.getEncoder().encodeToString(decode));
            staffImage.setImageBitmap(ImageUtils.getBitmap(decode));
            //userImage.setImageBitmap(ImageUtils.getBitmap(user.getUserImage()));
        } else {
            staffImage.setImageResource(R.drawable.user_default);
        }
        staffName.setText(staff.getName());
        staffSurname.setText(staff.getSurname());

        return convertView;
    }

    @Override
    public int getCount() {
        return staffArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return staffArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}







