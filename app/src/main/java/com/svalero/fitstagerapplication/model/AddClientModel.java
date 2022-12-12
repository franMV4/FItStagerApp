package com.svalero.fitstagerapplication.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.fitstagerapplication.api.FitStagerApi;
import com.svalero.fitstagerapplication.api.FitStagerApiInterface;
import com.svalero.fitstagerapplication.contract.AddClientContract;
import com.svalero.fitstagerapplication.contract.AddStaffContract;
import com.svalero.fitstagerapplication.database.AppDatabase;
import com.svalero.fitstagerapplication.domain.Client;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.domain.dto.ClientDTO;
import com.svalero.fitstagerapplication.domain.dto.StaffDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddClientModel implements AddClientContract.Model {

    private AppDatabase db;
    private FitStagerApiInterface api;
    private List<Gym> gyms;

    @Override
    public void startDb(Context context) {
        gyms = new ArrayList<>();
        api = FitStagerApi.buildInstance();
        db = Room.databaseBuilder(context,
                        AppDatabase.class, "client").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }


    @Override
    public void addClient(OnAddClientListener listener, Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName(client.getName());
        clientDTO.setSurname(client.getSurname());
        clientDTO.setDni(client.getDni());
        clientDTO.setPhone(client.getPhone());
        clientDTO.setClientImage(client.getClientImage());
        clientDTO.setGym(client.getGym().getId());

        Call<Client> clientCall = api.addClient(clientDTO);

        clientCall.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                listener.onAddClientSuccess("Cliente añadido con éxito");
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                listener.onAddClientError("No se ha podido añadir el cliente");
                t.printStackTrace();
            }
        });
    }




    @Override
    public void modifyClient(OnModifyClientListener listener, Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName(client.getName());
        clientDTO.setSurname(client.getSurname());
        clientDTO.setDni(client.getDni());
        clientDTO.setPhone(client.getPhone());
        clientDTO.setClientImage(client.getClientImage());
        clientDTO.setGym(client.getGym().getId());

        Call<Client> clientCall = api.modifyClient(client.getId(), clientDTO);

        clientCall.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                listener.onModifyClientSuccess("Cliente modificado con éxito");
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                listener.onModifyClientError("No se ha podido modificar el Cliente");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void loadAllGym(OnLoadGymsListener listener) {
        gyms.clear();

        Call<List<Gym>> gymCall = api.getGyms();

        gymCall.enqueue(new Callback<List<Gym>>() {
            @Override
            public void onResponse(Call<List<Gym>> call, Response<List<Gym>> response) {
                gyms = response.body();
                listener.onLoadGymsSuccess(gyms);
            }

            @Override
            public void onFailure(Call<List<Gym>> call, Throwable t) {
                listener.onLoadGymsError("Se ha producido un error");
            }
        });
    }

}
