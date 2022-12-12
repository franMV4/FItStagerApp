package com.svalero.fitstagerapplication.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.fitstagerapplication.api.FitStagerApi;
import com.svalero.fitstagerapplication.api.FitStagerApiInterface;
import com.svalero.fitstagerapplication.contract.AddActivityContract;
import com.svalero.fitstagerapplication.contract.AddStaffContract;
import com.svalero.fitstagerapplication.database.AppDatabase;
import com.svalero.fitstagerapplication.domain.Activitie;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.domain.dto.ActivityDTO;
import com.svalero.fitstagerapplication.domain.dto.StaffDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivityModel implements AddActivityContract.Model {

    private AppDatabase db;
    private FitStagerApiInterface api;
    private List<Gym> gyms;
    private List<Staff> staffs;

    @Override
    public void startDb(Context context) {
        gyms = new ArrayList<>();
        staffs = new ArrayList<>();
        api = FitStagerApi.buildInstance();
        db = Room.databaseBuilder(context,
                        AppDatabase.class, "activity").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }


    @Override
    public void addActivity(OnAddActivityListener listener, Activitie activity) {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setName(activity.getName());
        activityDTO.setRoom(activity.getRoom());
        activityDTO.setDifficulty(activity.getDifficulty());
        activityDTO.setStyle(activity.getStyle());
        activityDTO.setDate(activity.getDate());
        activityDTO.setActivityImage(activity.getActivityImage());
        activityDTO.setGym(activity.getGym().getId());
        activityDTO.setStaff(activity.getStaff().getId());

        Call<Activitie> activityCall = api.addActivity(activityDTO);

        activityCall.enqueue(new Callback<Activitie>() {
            @Override
            public void onResponse(Call<Activitie> call, Response<Activitie> response) {
                listener.onAddActivitySuccess("Actividad añadida con éxito");
            }

            @Override
            public void onFailure(Call<Activitie> call, Throwable t) {
                listener.onAddActivityError("No se ha podido añadir la actividad");
                t.printStackTrace();
            }
        });
    }




    @Override
    public void modifyActivity(OnModifyActivityListener listener, Activitie activity) {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setName(activity.getName());
        activityDTO.setRoom(activity.getRoom());
        activityDTO.setDifficulty(activity.getDifficulty());
        activityDTO.setStyle(activity.getStyle());
        activityDTO.setDate(activity.getDate());
        activityDTO.setActivityImage(activity.getActivityImage());
        activityDTO.setGym(activity.getGym().getId());
        activityDTO.setStaff(activity.getStaff().getId());

        Call<Activitie> activityCall = api.modifyActivity(activity.getId(), activityDTO);

        activityCall.enqueue(new Callback<Activitie>() {
            @Override
            public void onResponse(Call<Activitie> call, Response<Activitie> response) {
                listener.onModifyActivitySuccess("Actividad modificada con éxito");
            }

            @Override
            public void onFailure(Call<Activitie> call, Throwable t) {
                listener.onModifyActivityError("No se ha podido modificar la actividad");
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


 @Override
    public void loadAllStaff(OnLoadStaffsListener listener) {
        staffs.clear();

        Call<List<Staff>> staffCall = api.getStaffs();

     staffCall.enqueue(new Callback<List<Staff>>() {
            @Override
            public void onResponse(Call<List<Staff>> call, Response<List<Staff>> response) {
                staffs = response.body();
                listener.onLoadStaffsSuccess(staffs);
            }

            @Override
            public void onFailure(Call<List<Staff>> call, Throwable t) {
                listener.onLoadStaffsError("Se ha producido un error");
            }
        });
    }




}
