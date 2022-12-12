package com.svalero.fitstagerapplication.presenter;

import android.content.Context;

import com.svalero.fitstagerapplication.contract.AddActivityContract;
import com.svalero.fitstagerapplication.contract.AddStaffContract;
import com.svalero.fitstagerapplication.domain.Activitie;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.model.AddActivityModel;
import com.svalero.fitstagerapplication.model.AddStaffModel;
import com.svalero.fitstagerapplication.view.AddActivityView;
import com.svalero.fitstagerapplication.view.AddStaffView;

import java.util.List;

public class AddActivityPresenter implements AddActivityContract.Presenter, AddActivityContract.Model.OnLoadGymsListener, AddActivityContract.Model.OnLoadStaffsListener, AddActivityContract.Model.OnAddActivityListener, AddActivityContract.Model.OnModifyActivityListener {


    private AddActivityModel model;
    private AddActivityView view;
    private Context context;



    public AddActivityPresenter(AddActivityView view) {
        this.context = view.getApplicationContext();
        this.view = view;

        model = new AddActivityModel();
        model.startDb(context);
    }



    @Override
    public void loadGymsSpinner() {
        model.loadAllGym(this);
    }

 @Override
    public void loadStaffsSpinner() {
        model.loadAllStaff(this);
    }


    @Override
    public void addOrModifyActivity(Activitie activity, Boolean modifyActivity) {
        if ((activity.getName().equals("")) || (activity.getRoom().equals("")) || (activity.getDifficulty().equals(""))) {
            view.showMessage("Completa todos los campos");
        } else {
            if (modifyActivity) {
                view.setModifyActivity(false);
                view.getAddButton().setText("R.string.add_button");
                model.modifyActivity(this, activity);
            } else {
                activity.setId(0);
                model.addActivity(this, activity);
            }
        }
    }


    @Override
    public void onLoadGymsSuccess(List<Gym> gyms) {
        view.loadGymSpinner(gyms);
    }

    @Override
    public void onLoadStaffsSuccess(List<Staff> staffs) {
        view.loadStaffSpinner(staffs);
    }

    @Override
    public void onLoadGymsError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onLoadStaffsError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onAddActivitySuccess(String message) {
        view.showMessage(message);
        view.cleanForm();
    }

    @Override
    public void onAddActivityError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onModifyActivitySuccess(String message) {
        view.showMessage(message);
        view.cleanForm();
    }

    @Override
    public void onModifyActivityError(String message) {
        view.showMessage(message);
    }
}




