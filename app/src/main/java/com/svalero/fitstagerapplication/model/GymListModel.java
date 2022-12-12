package com.svalero.fitstagerapplication.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.fitstagerapplication.api.FitStagerApi;
import com.svalero.fitstagerapplication.api.FitStagerApiInterface;
import com.svalero.fitstagerapplication.contract.GymListContract;
import com.svalero.fitstagerapplication.database.AppDatabase;
import com.svalero.fitstagerapplication.domain.Gym;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GymListModel implements GymListContract.Model {

    private AppDatabase db;
    private Context context;
    private FitStagerApiInterface api;
    private List<Gym> gyms;

    @Override
    public void startDb(Context context) {
        this.context = context;
        gyms = new ArrayList<>();
        api = FitStagerApi.buildInstance();
        db = Room.databaseBuilder(context,
                        AppDatabase.class, "gym").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }

    @Override
    public void loadAllGyms(OnLoadGymsListener listener) {
        gyms.clear();

        Call<List<Gym>> gymCall = api.getGyms();

        loadGymsCallEnqueue(listener, gymCall);
    }





    @Override
    public void loadGymsByName(OnLoadGymsListener listener, String query) {
        gyms.clear();

        Call<List<Gym>> gymCall = api.getGymsByName(query);

        loadGymsCallEnqueue(listener, gymCall);
    }

    @Override
    public void loadGymsByCity(OnLoadGymsListener listener, String query) {
        gyms.clear();

        Call<List<Gym>> gymCall = api.getGymsByCity(query);

        loadGymsCallEnqueue(listener, gymCall);
    }

    @Override
    public void loadGymsByStreet(OnLoadGymsListener listener, String query) {
        gyms.clear();

        Call<List<Gym>> gymCall = api.getGymsByStreet(query);

        loadGymsCallEnqueue(listener, gymCall);
    }

    /**
     * Envía la solicitud de forma asíncrona y notifica la devolución de llamada de su respuesta
     * o si se produjo un error al hablar con el servidor, crear la solicitud o procesar la respuesta.
     *
     * @param listener OnLoadComputersListener
     * @param call     Lista de Computers
     */
    private void loadGymsCallEnqueue(OnLoadGymsListener listener, Call<List<Gym>> call) {
        call.enqueue(new Callback<List<Gym>>() {
            @Override
            public void onResponse(Call<List<Gym>> call, Response<List<Gym>> response) {
                gyms = response.body();
                listener.onLoadGymsSuccess(gyms);
            }

            @Override
            public void onFailure(Call<List<Gym>> call, Throwable t) {
                listener.onLoadGymsError("Se ha producido un error");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void delete(OnDeleteGymListener listener, Gym gym) {
        Call<Void> gymCall = api.deleteGym(gym.getId());

        gymCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                listener.onDeleteGymSuccess("Gimnasio eliminado correctamente");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onDeleteGymError("No se ha podido eliminar el gimnasio");
                t.printStackTrace();
            }
        });
    }
}
