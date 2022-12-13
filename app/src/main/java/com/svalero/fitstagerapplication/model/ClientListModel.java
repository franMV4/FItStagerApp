package com.svalero.fitstagerapplication.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.fitstagerapplication.api.FitStagerApi;
import com.svalero.fitstagerapplication.api.FitStagerApiInterface;
import com.svalero.fitstagerapplication.contract.ClientListContract;
import com.svalero.fitstagerapplication.contract.StaffListContract;
import com.svalero.fitstagerapplication.database.AppDatabase;
import com.svalero.fitstagerapplication.domain.Client;
import com.svalero.fitstagerapplication.domain.Staff;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientListModel implements ClientListContract.Model {


    private AppDatabase db;
    private Context context;
    private FitStagerApiInterface api;
    private List<Client> clients;

    @Override
    public void startDb(Context context) {
        this.context = context;
        clients = new ArrayList<>();
        api = FitStagerApi.buildInstance();
        db = Room.databaseBuilder(context,
                        AppDatabase.class, "client").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }

    @Override
    public void loadAllClients(OnLoadClientsListener listener) {
        clients.clear();

        Call<List<Client>> clientCall = api.getClients();

        loadClientsCallEnqueue(listener, clientCall);
    }


    private void loadClientsCallEnqueue(OnLoadClientsListener listener, Call<List<Client>> call) {
        call.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                clients = response.body();
                listener.onLoadClientsSuccess(clients);
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                listener.onLoadClientsError("Se ha producido un error");
                t.printStackTrace();
            }
        });
    }


    @Override
    public void delete(OnDeleteClientListener listener, Client client) {
        Call<Void> clientCall = api.deleteClient(client.getId());

        clientCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                listener.onDeleteClientSuccess("Cliente eliminado correctamente");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onDeleteClientError("No se ha podido eliminar el cliente");
                t.printStackTrace();
            }
        });
    }



}
