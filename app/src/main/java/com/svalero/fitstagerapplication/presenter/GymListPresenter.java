package com.svalero.fitstagerapplication.presenter;

import com.svalero.fitstagerapplication.contract.GymListContract;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.model.GymListModel;
import com.svalero.fitstagerapplication.view.GymListView;


import java.util.List;

public class GymListPresenter implements GymListContract.Presenter, GymListContract.Model.OnLoadGymsListener, GymListContract.Model.OnDeleteGymListener {

    private GymListModel model;
    private GymListView view;

    public GymListPresenter(GymListView view) {
        model = new GymListModel();
        model.startDb(view.getApplicationContext());

        this.view = view;
    }

    @Override
    public void loadAllGyms() {
        model.loadAllGyms(this);
    }

    @Override
    public void loadGymsByName(String query) {
        model.loadGymsByName(this, query);
    }

    @Override
    public void loadGymsByCity(String query) {
        model.loadGymsByCity(this, query);
    }

    @Override
    public void loadGymsByStreet(String query) {
        model.loadGymsByStreet(this, query);
    }

    @Override   // OnLoadComputersListener SUCCESS
    public void onLoadGymsSuccess(List<Gym> gyms) {
        view.listGyms(gyms);
    }

    @Override   // OnLoadComputersListener ERROR
    public void onLoadGymsError(String message) {
        view.showMessage(message);
    }

    @Override
    public void deleteGym(Gym gym) {
        model.delete(this, gym);
    }

    @Override   // OnDeleteComputersListener SUCCESS
    public void onDeleteGymSuccess(String message) {
        view.showMessage(message);
    }

    @Override   // OnDeleteComputersListener ERROR
    public void onDeleteGymError(String message) {
        view.showMessage(message);
    }
}
