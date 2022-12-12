package com.svalero.fitstagerapplication.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.fitstagerapplication.api.FitStagerApi;
import com.svalero.fitstagerapplication.api.FitStagerApiInterface;
import com.svalero.fitstagerapplication.contract.AddGymContract;
import com.svalero.fitstagerapplication.contract.AddStaffContract;
import com.svalero.fitstagerapplication.database.AppDatabase;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.domain.dto.StaffDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddGymModel implements AddGymContract.Model {

    private AppDatabase db;
    private FitStagerApiInterface api;

    @Override
    public void startDb(Context context) {
        api = FitStagerApi.buildInstance();
        db = Room.databaseBuilder(context,
                        AppDatabase.class, "gym").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }


    @Override
    public void addGym (AddGymContract.Model.OnAddGymListener listener, Gym gym) {
        Gym gymDTO = new Gym();
        gymDTO.setName(gym.getName());
        gymDTO.setCity(gym.getCity());
        gymDTO.setHorary(gym.getHorary());
        gymDTO.setStreet(gym.getStreet());
        gymDTO.setGymImage(gym.getGymImage());

        Call<Gym> gymCall = api.addGym(gymDTO);

        gymCall.enqueue(new Callback<Gym>() {
            @Override
            public void onResponse(Call<Gym> call, Response<Gym> response) {
                listener.onAddGymSuccess("Ordenador añadido con éxito");
            }

            @Override
            public void onFailure(Call<Gym> call, Throwable t) {
                listener.onAddGymError("No se ha podido añadir el staff");
                t.printStackTrace();
            }
        });
    }




    @Override
    public void modifyGym(AddGymContract.Model.OnModifyGymListener listener, Gym gym) {
        Gym gymDTO = new Gym();
        gymDTO.setName(gym.getName());
        gymDTO.setCity(gym.getCity());
        gymDTO.setHorary(gym.getHorary());
        gymDTO.setStreet(gym.getStreet());
        gymDTO.setGymImage(gym.getGymImage());


        Call<Gym> staffCall = api.modifyGym(gym.getId(), gymDTO);

        staffCall.enqueue(new Callback<Gym>() {
            @Override
            public void onResponse(Call<Gym> call, Response<Gym> response) {
                listener.onModifyGymSuccess("Ordenador modificado con éxito");
            }

            @Override
            public void onFailure(Call<Gym> call, Throwable t) {
                listener.onModifyGymError("No se ha podido modificar el ordenador");
                t.printStackTrace();
            }
        });
    }







}
