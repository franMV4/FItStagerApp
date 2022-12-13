package com.svalero.fitstagerapplication.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.fitstagerapplication.api.FitStagerApi;
import com.svalero.fitstagerapplication.api.FitStagerApiInterface;
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

public class AddStaffModel implements AddStaffContract.Model {

    private AppDatabase db;
    private FitStagerApiInterface api;
    private List<Gym> gyms;

    @Override
    public void startDb(Context context) {
        gyms = new ArrayList<>();
        api = FitStagerApi.buildInstance();
        db = Room.databaseBuilder(context,
                        AppDatabase.class, "staff").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }


    @Override
    public void addStaff(OnAddStaffListener listener, Staff staff) {
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setName(staff.getName());
        staffDTO.setSurname(staff.getSurname());
        staffDTO.setDni(staff.getDni());
        staffDTO.setPhone(staff.getPhone());
        staffDTO.setStaffImage(staff.getStaffImage());
        staffDTO.setGym(staff.getGym().getId());

        Call<Staff> staffCall = api.addStaff(staffDTO);

        staffCall.enqueue(new Callback<Staff>() {
            @Override
            public void onResponse(Call<Staff> call, Response<Staff> response) {
                listener.onAddStaffSuccess("Staff añadido con éxito");
            }

            @Override
            public void onFailure(Call<Staff> call, Throwable t) {
                listener.onAddStaffError("No se ha podido añadir el staff");
                t.printStackTrace();
            }
        });
    }




    @Override
    public void modifyStaff(OnModifyStaffListener listener, Staff staff) {
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setName(staff.getName());
        staffDTO.setSurname(staff.getSurname());
        staffDTO.setDni(staff.getDni());
        staffDTO.setPhone(staff.getPhone());
        staffDTO.setStaffImage(staff.getStaffImage());
        staffDTO.setGym(staff.getGym().getId());

        Call<Staff> staffCall = api.modifyStaff(staff.getId(), staffDTO);

        staffCall.enqueue(new Callback<Staff>() {
            @Override
            public void onResponse(Call<Staff> call, Response<Staff> response) {
                listener.onModifyStaffSuccess("Staff modificado con éxito");
            }

            @Override
            public void onFailure(Call<Staff> call, Throwable t) {
                listener.onModifyStaffError("No se ha podido modificar el staff");
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
