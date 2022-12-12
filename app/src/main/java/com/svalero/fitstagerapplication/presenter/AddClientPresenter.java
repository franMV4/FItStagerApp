package com.svalero.fitstagerapplication.presenter;

import android.content.Context;

import com.svalero.fitstagerapplication.contract.AddClientContract;
import com.svalero.fitstagerapplication.contract.AddStaffContract;
import com.svalero.fitstagerapplication.domain.Client;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.model.AddClientModel;
import com.svalero.fitstagerapplication.view.AddClientView;


import java.util.List;

public class AddClientPresenter implements AddClientContract.Presenter, AddClientContract.Model.OnLoadGymsListener, AddClientContract.Model.OnAddClientListener, AddClientContract.Model.OnModifyClientListener {


    private AddClientModel model;
    private AddClientView view;
    private Context context;



    public AddClientPresenter(AddClientView view) {
        this.context = view.getApplicationContext();
        this.view = view;

        model = new AddClientModel();
        model.startDb(context);
    }



    @Override
    public void loadGymsSpinner() {
        model.loadAllGym(this);
    }


    @Override
    public void addOrModifyClient(Client client, Boolean modifyClient) {
        if ((client.getName().equals("")) || (client.getSurname().equals("")) || (client.getDni().equals(""))) {
            view.showMessage("Completa todos los campos");
        } else {
            if (modifyClient) {
                view.setModifyClient(false);
                view.getAddButton().setText("R.string.add_button");
                model.modifyClient(this, client);
            } else {
                client.setId(0);
                model.addClient(this, client);
            }
        }
    }


    @Override
    public void onLoadGymsSuccess(List<Gym> gyms) {
        view.loadGymSpinner(gyms);
    }

    @Override
    public void onLoadGymsError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onAddClientSuccess(String message) {
        view.showMessage(message);
        view.cleanForm();
    }

    @Override
    public void onAddClientError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onModifyClientSuccess(String message) {
        view.showMessage(message);
        view.cleanForm();
    }

    @Override
    public void onModifyClientError(String message) {
        view.showMessage(message);
    }
}




