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
import com.svalero.fitstagerapplication.domain.Client;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.util.ImageUtils;

import java.util.Base64;
import java.util.List;

public class ClientAdapter extends BaseAdapter {

    private Context context;
    private List<Client> clientArrayList;
    private LayoutInflater inflater;

    public ClientAdapter(Activity context, List<Client> clientArrayList) {
        this.context = context;
        this.clientArrayList = clientArrayList;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Client client = (Client) getItem(position);

        convertView = inflater.inflate(R.layout.staff_and_gym_adapter, null);

        TextView clientName = convertView.findViewById(R.id.staff_gym_tv1);
        TextView clientSurname = convertView.findViewById(R.id.staff_gym_tv2);
        ImageView clientImage = (ImageView) convertView.findViewById(R.id.staff_gym_item_imageView);

        if (client.getClientImage() != null) {  // Valido si no es null la foto, si no sale fallo nullpoint...
            byte[] decode = Base64.getDecoder().decode(client.getClientImage());
            Log.i("gym",  Base64.getEncoder().encodeToString(decode));
            clientImage.setImageBitmap(ImageUtils.getBitmap(decode));
            //userImage.setImageBitmap(ImageUtils.getBitmap(user.getUserImage()));
        } else {
            clientImage.setImageResource(R.drawable.user_default);
        }
        clientName.setText(client.getName());
            clientSurname.setText(client.getSurname());



        return convertView;
    }










    @Override
    public int getCount() {
        return clientArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return clientArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
