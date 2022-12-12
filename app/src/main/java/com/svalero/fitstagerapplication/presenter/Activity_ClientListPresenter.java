package com.svalero.fitstagerapplication.presenter;

import com.svalero.fitstagerapplication.contract.Activity_ClientListContract;
import com.svalero.fitstagerapplication.contract.StaffListContract;
import com.svalero.fitstagerapplication.domain.Activity_Client;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.model.Activity_ClientListModel;
import com.svalero.fitstagerapplication.model.StaffListModel;
import com.svalero.fitstagerapplication.view.Activity_ClientListView;
import com.svalero.fitstagerapplication.view.StaffListView;

import java.util.List;

public class Activity_ClientListPresenter implements Activity_ClientListContract.Presenter, Activity_ClientListContract.Model.OnLoadActivities_ClientsListener, Activity_ClientListContract.Model.OnDeleteActivity_ClientListener {


    private Activity_ClientListModel model;
    private Activity_ClientListView view;

    public Activity_ClientListPresenter(Activity_ClientListView view) {
        model = new Activity_ClientListModel();
        model.startDb(view.getApplicationContext());

        this.view = view;
    }

    @Override
    public void loadAllActivities_Clients() {
        model.loadAllActivities_Clients(this);
    }

    @Override   // OnLoadComputersListener SUCCESS
    public void onLoadActivities_ClientsSuccess(List<Activity_Client> activities_clients) {
        view.listActivities_Clients(activities_clients);
    }

    @Override   // OnLoadComputersListener ERROR
    public void onLoadActivities_ClientsError(String message) {
        view.showMessage(message);
    }

    @Override
    public void deleteActivity_Client(Activity_Client activity_client) {
        model.delete(this, activity_client);
    }

    @Override   // OnDeleteComputersListener SUCCESS
    public void onDeleteActivity_ClientSuccess(String message) {
        view.showMessage(message);
    }

    @Override   // OnDeleteComputersListener ERROR
    public void onDeleteActivity_ClientError(String message) {
        view.showMessage(message);
    }




}
