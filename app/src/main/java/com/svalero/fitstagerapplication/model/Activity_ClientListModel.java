package com.svalero.fitstagerapplication.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.fitstagerapplication.api.FitStagerApi;
import com.svalero.fitstagerapplication.api.FitStagerApiInterface;
import com.svalero.fitstagerapplication.contract.Activity_ClientListContract;
import com.svalero.fitstagerapplication.contract.StaffListContract;
import com.svalero.fitstagerapplication.database.AppDatabase;
import com.svalero.fitstagerapplication.domain.Activity_Client;
import com.svalero.fitstagerapplication.domain.Staff;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_ClientListModel implements Activity_ClientListContract.Model {


    private AppDatabase db;
    private Context context;
    private FitStagerApiInterface api;
    private List<Activity_Client> activities_clients;

    @Override
    public void startDb(Context context) {
        this.context = context;
        activities_clients = new ArrayList<>();
        api = FitStagerApi.buildInstance();
        db = Room.databaseBuilder(context,
                        AppDatabase.class, "activity_client").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }

    @Override
    public void loadAllActivities_Clients(OnLoadActivities_ClientsListener listener) {
        activities_clients.clear();

        Call<List<Activity_Client>> activity_clientCall = api.getActivities_Clients();

        loadActivities_ClientsCallEnqueue(listener, activity_clientCall);
    }


    private void loadActivities_ClientsCallEnqueue(OnLoadActivities_ClientsListener listener, Call<List<Activity_Client>> call) {
        call.enqueue(new Callback<List<Activity_Client>>() {
            @Override
            public void onResponse(Call<List<Activity_Client>> call, Response<List<Activity_Client>> response) {
                activities_clients = response.body();
                listener.onLoadActivities_ClientsSuccess(activities_clients);
            }

            @Override
            public void onFailure(Call<List<Activity_Client>> call, Throwable t) {
                listener.onLoadActivities_ClientsError("Se ha producido un error");
                t.printStackTrace();
            }
        });
    }


    @Override
    public void delete(OnDeleteActivity_ClientListener listener, Activity_Client activity_client) {
        Call<Void> activity_clientCall = api.deleteActivity_Client(activity_client.getId());

        activity_clientCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                listener.onDeleteActivity_ClientSuccess("Ordenador eliminado correctamente");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onDeleteActivity_ClientError("No se ha podido eliminar el ordenador");
                t.printStackTrace();
            }
        });
    }



}

