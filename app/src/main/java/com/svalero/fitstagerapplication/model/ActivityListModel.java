package com.svalero.fitstagerapplication.model;

import android.app.Activity;
import android.content.Context;

import androidx.room.Room;

import com.svalero.fitstagerapplication.api.FitStagerApi;
import com.svalero.fitstagerapplication.api.FitStagerApiInterface;
import com.svalero.fitstagerapplication.contract.ActivityListContract;
import com.svalero.fitstagerapplication.contract.StaffListContract;
import com.svalero.fitstagerapplication.database.AppDatabase;
import com.svalero.fitstagerapplication.domain.Activitie;
import com.svalero.fitstagerapplication.domain.Staff;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityListModel implements ActivityListContract.Model {


    private AppDatabase db;
    private Context context;
    private FitStagerApiInterface api;
    private List<Activitie> activities;

    @Override
    public void startDb(Context context) {
        this.context = context;
        activities = new ArrayList<>();
        api = FitStagerApi.buildInstance();
        db = Room.databaseBuilder(context,
                        AppDatabase.class, "activity").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }

    @Override
    public void loadAllActivities(OnLoadActivitiesListener listener) {
        activities.clear();

        Call<List<Activitie>> activityCall = api.getActivities();

        loadActivitiesCallEnqueue(listener, activityCall);
    }


    private void loadActivitiesCallEnqueue(OnLoadActivitiesListener listener, Call<List<Activitie>> call) {
        call.enqueue(new Callback<List<Activitie>>() {
            @Override
            public void onResponse(Call<List<Activitie>> call, Response<List<Activitie>> response) {
                activities = response.body();
                listener.onLoadActivitiesSuccess(activities);
            }

            @Override
            public void onFailure(Call<List<Activitie>> call, Throwable t) {
                listener.onLoadActivitiesError("Se ha producido un error");
                t.printStackTrace();
            }
        });
    }


    @Override
    public void delete(OnDeleteActivityListener listener, Activitie activity) {
        Call<Void> activityCall = api.deleteActivity(activity.getId());

        activityCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                listener.onDeleteActivitySuccess("Actividad eliminada correctamente");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onDeleteActivityError("No se ha podido eliminar la actividad");
                t.printStackTrace();
            }
        });
    }



}
