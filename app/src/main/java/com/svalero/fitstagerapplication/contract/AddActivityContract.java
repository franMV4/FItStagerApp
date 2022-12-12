package com.svalero.fitstagerapplication.contract;

import android.content.Context;

import com.svalero.fitstagerapplication.domain.Activitie;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;

import java.util.List;

public interface AddActivityContract {




    interface Model {
        // USERS
        interface OnLoadGymsListener {
            void onLoadGymsSuccess(List<Gym> gyms);

            void onLoadGymsError(String message);
        }

        interface OnLoadStaffsListener {
            void onLoadStaffsSuccess(List<Staff> staffs);

            void onLoadStaffsError(String message);
        }

        // STAFFS
        interface OnAddActivityListener {
            void onAddActivitySuccess(String message);

            void onAddActivityError(String message);
        }

        interface OnModifyActivityListener {
            void onModifyActivitySuccess(String message);

            void onModifyActivityError(String message);
        }

        void startDb(Context context);

        void addActivity(AddActivityContract.Model.OnAddActivityListener listener, Activitie activity);

        void modifyActivity(AddActivityContract.Model.OnModifyActivityListener listener, Activitie activity);

        void loadAllGym(AddActivityContract.Model.OnLoadGymsListener listener);
        void loadAllStaff(AddActivityContract.Model.OnLoadStaffsListener listener);
    }

    interface View {
        void loadGymSpinner(List<Gym> gyms);

        void loadStaffSpinner(List<Staff> staffs);

        void addActivity(android.view.View view);

        void cleanForm();

        void showMessage(String message);
    }

    interface Presenter {
        void loadGymsSpinner();

        void loadStaffsSpinner();

        void addOrModifyActivity(Activitie activity, Boolean modifyActivity);
    }
}
