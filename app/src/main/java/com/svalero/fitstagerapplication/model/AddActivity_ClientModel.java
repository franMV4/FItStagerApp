package com.svalero.fitstagerapplication.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.fitstagerapplication.api.FitStagerApi;
import com.svalero.fitstagerapplication.api.FitStagerApiInterface;
import com.svalero.fitstagerapplication.contract.AddActivity_ClientContract;
import com.svalero.fitstagerapplication.contract.AddStaffContract;
import com.svalero.fitstagerapplication.database.AppDatabase;
import com.svalero.fitstagerapplication.domain.Activitie;
import com.svalero.fitstagerapplication.domain.Activity_Client;
import com.svalero.fitstagerapplication.domain.Client;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.domain.dto.Activity_ClientDTO;
import com.svalero.fitstagerapplication.domain.dto.StaffDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity_ClientModel implements AddActivity_ClientContract.Model {

    private AppDatabase db;
    private FitStagerApiInterface api;
    private List<Client> clients;
    private List<Activitie> activities;

    @Override
    public void startDb(Context context) {
        clients = new ArrayList<>();
        activities = new ArrayList<>();
        api = FitStagerApi.buildInstance();
        db = Room.databaseBuilder(context,
                        AppDatabase.class, "activity_client").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }


    @Override
    public void addActivity_Client(OnAddActivity_ClientListener listener, Activity_Client activity_client) {
        Activity_ClientDTO activity_clientDTO = new Activity_ClientDTO();
        activity_clientDTO.setActivity(activity_client.getActivity().getId());
        activity_clientDTO.setClient(activity_client.getClient().getId());

        Call<Activity_Client> activity_clientCall = api.addActivity_Client(activity_clientDTO);

        activity_clientCall.enqueue(new Callback<Activity_Client>() {
            @Override
            public void onResponse(Call<Activity_Client> call, Response<Activity_Client> response) {
                listener.onAddActivity_ClientSuccess("Registro añadido con éxito");
            }

            @Override
            public void onFailure(Call<Activity_Client> call, Throwable t) {
                listener.onAddActivity_ClientError("No se ha podido añadir el registro");
                t.printStackTrace();
            }
        });
    }




    @Override
    public void modifyActivity_Client(OnModifyActivity_ClientListener listener, Activity_Client activity_client) {
        Activity_ClientDTO activity_clientDTO = new Activity_ClientDTO();
        activity_clientDTO.setActivity(activity_client.getActivity().getId());
        activity_clientDTO.setClient(activity_client.getClient().getId());

        Call<Activity_Client> activity_clientCall = api.modifyActivity_Client(activity_client.getId(), activity_clientDTO);

        activity_clientCall.enqueue(new Callback<Activity_Client>() {
            @Override
            public void onResponse(Call<Activity_Client> call, Response<Activity_Client> response) {
                listener.onModifyActivity_ClientSuccess("Registro modificado con éxito");
            }

            @Override
            public void onFailure(Call<Activity_Client> call, Throwable t) {
                listener.onModifyActivity_ClientError("No se ha podido modificar el registro");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void loadAllActivity(OnLoadActivitiesListener listener) {
        activities.clear();

        Call<List<Activitie>> activityCall = api.getActivities();

        activityCall.enqueue(new Callback<List<Activitie>>() {
            @Override
            public void onResponse(Call<List<Activitie>> call, Response<List<Activitie>> response) {
                activities = response.body();
                listener.onLoadActivitiesSuccess(activities);
            }

            @Override
            public void onFailure(Call<List<Activitie>> call, Throwable t) {
                listener.onLoadActivitiesError("Se ha producido un error");
            }
        });
    }


 @Override
    public void loadAllClient(OnLoadClientsListener listener) {
     clients.clear();

        Call<List<Client>> clientCall = api.getClients();

     clientCall.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                clients = response.body();
                listener.onLoadClientsSuccess(clients);
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                listener.onLoadClientsError("Se ha producido un error");
            }
        });
    }




}
