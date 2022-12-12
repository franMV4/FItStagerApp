package com.svalero.fitstagerapplication.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.fitstagerapplication.api.FitStagerApi;
import com.svalero.fitstagerapplication.api.FitStagerApiInterface;
import com.svalero.fitstagerapplication.contract.GymListContract;
import com.svalero.fitstagerapplication.contract.StaffListContract;
import com.svalero.fitstagerapplication.database.AppDatabase;
import com.svalero.fitstagerapplication.domain.Staff;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffListModel implements StaffListContract.Model {


    private AppDatabase db;
    private Context context;
    private FitStagerApiInterface api;
    private List<Staff> staffs;

    @Override
    public void startDb(Context context) {
        this.context = context;
        staffs = new ArrayList<>();
        api = FitStagerApi.buildInstance();
        db = Room.databaseBuilder(context,
                        AppDatabase.class, "staff").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }

    @Override
    public void loadAllStaffs(OnLoadStaffsListener listener) {
        staffs.clear();

        Call<List<Staff>> staffCall = api.getStaffs();

        loadStaffsCallEnqueue(listener, staffCall);
    }


    private void loadStaffsCallEnqueue(OnLoadStaffsListener listener, Call<List<Staff>> call) {
        call.enqueue(new Callback<List<Staff>>() {
            @Override
            public void onResponse(Call<List<Staff>> call, Response<List<Staff>> response) {
                staffs = response.body();
                listener.onLoadStaffsSuccess(staffs);
            }

            @Override
            public void onFailure(Call<List<Staff>> call, Throwable t) {
                listener.onLoadStaffsError("Se ha producido un error");
                t.printStackTrace();
            }
        });
    }


    @Override
    public void delete(OnDeleteStaffListener listener, Staff staff) {
        Call<Void> staffCall = api.deleteStaff(staff.getId());

        staffCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                listener.onDeleteStaffSuccess("Ordenador eliminado correctamente");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onDeleteStaffError("No se ha podido eliminar el ordenador");
                t.printStackTrace();
            }
        });
    }



}
