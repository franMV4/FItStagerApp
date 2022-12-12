package com.svalero.fitstagerapplication.presenter;

import com.svalero.fitstagerapplication.contract.StaffListContract;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.model.StaffListModel;
import com.svalero.fitstagerapplication.view.StaffListView;


import java.util.List;

public class StaffListPresenter implements StaffListContract.Presenter, StaffListContract.Model.OnLoadStaffsListener, StaffListContract.Model.OnDeleteStaffListener {


    private StaffListModel model;
    private StaffListView view;

    public StaffListPresenter(StaffListView view) {
        model = new StaffListModel();
        model.startDb(view.getApplicationContext());

        this.view = view;
    }

    @Override
    public void loadAllStaffs() {
        model.loadAllStaffs(this);
    }

    @Override   // OnLoadComputersListener SUCCESS
    public void onLoadStaffsSuccess(List<Staff> staffs) {
        view.listStaffs(staffs);
    }

    @Override   // OnLoadComputersListener ERROR
    public void onLoadStaffsError(String message) {
        view.showMessage(message);
    }

    @Override
    public void deleteStaff(Staff staff) {
        model.delete(this, staff);
    }

    @Override   // OnDeleteComputersListener SUCCESS
    public void onDeleteStaffSuccess(String message) {
        view.showMessage(message);
    }

    @Override   // OnDeleteComputersListener ERROR
    public void onDeleteStaffError(String message) {
        view.showMessage(message);
    }




}
