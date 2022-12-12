package com.svalero.fitstagerapplication.presenter;

import android.content.Context;

import com.svalero.fitstagerapplication.R;
import com.svalero.fitstagerapplication.contract.AddStaffContract;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.model.AddStaffModel;
import com.svalero.fitstagerapplication.view.AddStaffView;

import java.util.List;

public class AddStaffPresenter implements AddStaffContract.Presenter, AddStaffContract.Model.OnLoadGymsListener, AddStaffContract.Model.OnAddStaffListener, AddStaffContract.Model.OnModifyStaffListener {


    private AddStaffModel model;
    private AddStaffView view;
    private Context context;



    public AddStaffPresenter(AddStaffView view) {
        this.context = view.getApplicationContext();
        this.view = view;

        model = new AddStaffModel();
        model.startDb(context);
    }



    @Override
    public void loadGymsSpinner() {
        model.loadAllGym(this);
    }


    @Override
    public void addOrModifyStaff(Staff staff, Boolean modifyStaff) {
        if ((staff.getName().equals("")) || (staff.getSurname().equals("")) || (staff.getDni().equals(""))) {
            view.showMessage("Completa todos los campos");
        } else {
            if (modifyStaff) {
                view.setModifyStaff(false);
                view.getAddButton().setText("R.string.add_button");
                model.modifyStaff(this, staff);
            } else {
                staff.setId(0);
                model.addStaff(this, staff);
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
    public void onAddStaffSuccess(String message) {
        view.showMessage(message);
        view.cleanForm();
    }

    @Override
    public void onAddStaffError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onModifyStaffSuccess(String message) {
        view.showMessage(message);
        view.cleanForm();
    }

    @Override
    public void onModifyStaffError(String message) {
        view.showMessage(message);
    }
}




