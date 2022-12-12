package com.svalero.fitstagerapplication.presenter;

import android.content.Context;

import com.svalero.fitstagerapplication.contract.AddActivity_ClientContract;

import com.svalero.fitstagerapplication.domain.Activitie;
import com.svalero.fitstagerapplication.domain.Activity_Client;
import com.svalero.fitstagerapplication.domain.Client;

import com.svalero.fitstagerapplication.model.AddActivity_ClientModel;
import com.svalero.fitstagerapplication.view.AddActivity_ClientView;


import java.util.List;

public class AddActivity_ClientPresenter implements AddActivity_ClientContract.Presenter, AddActivity_ClientContract.Model.OnLoadActivitiesListener, AddActivity_ClientContract.Model.OnLoadClientsListener, AddActivity_ClientContract.Model.OnAddActivity_ClientListener, AddActivity_ClientContract.Model.OnModifyActivity_ClientListener {


    private AddActivity_ClientModel model;
    private AddActivity_ClientView view;
    private Context context;



    public AddActivity_ClientPresenter(AddActivity_ClientView view) {
        this.context = view.getApplicationContext();
        this.view = view;

        model = new AddActivity_ClientModel();
        model.startDb(context);
    }



    @Override
    public void loadActivitiesSpinner() {
        model.loadAllActivity(this);
    }
    @Override
    public void loadClientsSpinner() {
        model.loadAllClient(this);
    }


    @Override
    public void addOrModifyActivity_Client(Activity_Client activity_client, Boolean modifyActivity_Client) {
        if ((activity_client.getClient().equals("")) || (activity_client.getActivity().equals(""))) {
            view.showMessage("Completa todos los campos");
        } else {
            if (modifyActivity_Client) {
                view.setModifyActivity_Client(false);
                view.getAddButton().setText("R.string.add_button");
                model.modifyActivity_Client(this, activity_client);
            } else {
                activity_client.setId(0);
                model.addActivity_Client(this, activity_client);
            }
        }
    }


    @Override
    public void onLoadActivitiesSuccess(List<Activitie> activities) {
        view.loadActivitySpinner(activities);
    }
    @Override
    public void onLoadClientsSuccess(List<Client> clients) {
        view.loadClientSpinner(clients);
    }



    @Override
    public void onLoadActivitiesError(String message) {
        view.showMessage(message);
    }
    @Override
    public void onLoadClientsError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onAddActivity_ClientSuccess(String message) {
        view.showMessage(message);
        view.cleanForm();
    }

    @Override
    public void onAddActivity_ClientError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onModifyActivity_ClientSuccess(String message) {
        view.showMessage(message);
        view.cleanForm();
    }

    @Override
    public void onModifyActivity_ClientError(String message) {
        view.showMessage(message);
    }
}




