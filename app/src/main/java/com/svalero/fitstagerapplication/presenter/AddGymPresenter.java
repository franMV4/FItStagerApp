package com.svalero.fitstagerapplication.presenter;

import android.content.Context;

import com.svalero.fitstagerapplication.contract.AddGymContract;
import com.svalero.fitstagerapplication.contract.AddStaffContract;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.model.AddGymModel;
import com.svalero.fitstagerapplication.model.AddStaffModel;
import com.svalero.fitstagerapplication.view.AddGymView;
import com.svalero.fitstagerapplication.view.AddStaffView;

import java.util.List;

public class AddGymPresenter implements AddGymContract.Presenter,  AddGymContract.Model.OnAddGymListener, AddGymContract.Model.OnModifyGymListener {

    private AddGymModel model;
    private AddGymView view;
    private Context context;


    public AddGymPresenter(AddGymView view) {
        this.context = view.getApplicationContext();
        this.view = view;

        model = new AddGymModel();
        model.startDb(context);
    }

    @Override
    public void addOrModifyGym(Gym gym, Boolean modifyGym) {
        if ((gym.getName().equals("")) || (gym.getCity().equals("")) || (gym.getStreet().equals("")) || (gym.getHorary().equals(""))) {
            view.showMessage("Completa todos los campos");
        } else {
            if (modifyGym) {
                view.setModifyGym(false);
                view.getAddButton().setText("R.string.add_button");
                model.modifyGym(this, gym);
                //Comprobar si hay localizacion seleccionada
            }else if (gym.getLatitude() == 0 && gym.getLongitude() == 0) {
                view.showMessage("Selecciona una posición en el mapa");
            }
            else {
                gym.setId(0);
                model.addGym(this, gym);
            }
        }
    }



    @Override
    public void onAddGymSuccess(String message) {
        view.showMessage(message);
        view.cleanForm();
    }

    @Override
    public void onAddGymError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onModifyGymSuccess(String message) {
        view.showMessage(message);
        view.cleanForm();
    }

    @Override
    public void onModifyGymError(String message) {
        view.showMessage(message);
    }
}










