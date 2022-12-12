package com.svalero.fitstagerapplication.presenter;



import com.svalero.fitstagerapplication.contract.ActivityListContract;
import com.svalero.fitstagerapplication.domain.Activitie;
import com.svalero.fitstagerapplication.model.ActivityListModel;
import com.svalero.fitstagerapplication.view.ActivityListView;

import java.util.List;

public class ActivityListPresenter implements ActivityListContract.Presenter, ActivityListContract.Model.OnLoadActivitiesListener, ActivityListContract.Model.OnDeleteActivityListener {


    private ActivityListModel model;
    private ActivityListView view;

    public ActivityListPresenter(ActivityListView view) {
        model = new ActivityListModel();
        model.startDb(view.getApplicationContext());

        this.view = view;
    }

    @Override
    public void loadAllActivities() {
        model.loadAllActivities(this);
    }

    @Override   // OnLoadComputersListener SUCCESS
    public void onLoadActivitiesSuccess(List<Activitie> activities) {
        view.listActivities(activities);
    }

    @Override   // OnLoadComputersListener ERROR
    public void onLoadActivitiesError(String message) {
        view.showMessage(message);
    }

    @Override
    public void deleteActivity(Activitie activity) {
        model.delete(this, activity);
    }

    @Override   // OnDeleteComputersListener SUCCESS
    public void onDeleteActivitySuccess(String message) {
        view.showMessage(message);
    }

    @Override   // OnDeleteComputersListener ERROR
    public void onDeleteActivityError(String message) {
        view.showMessage(message);
    }




}
