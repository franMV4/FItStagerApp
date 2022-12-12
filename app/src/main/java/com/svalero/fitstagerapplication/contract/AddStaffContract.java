package com.svalero.fitstagerapplication.contract;

import android.content.Context;

import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;

import java.util.List;

public interface AddStaffContract {




    interface Model {
        // USERS
        interface OnLoadGymsListener {
            void onLoadGymsSuccess(List<Gym> gyms);

            void onLoadGymsError(String message);
        }

        // STAFFS
        interface OnAddStaffListener {
            void onAddStaffSuccess(String message);

            void onAddStaffError(String message);
        }

        interface OnModifyStaffListener {
            void onModifyStaffSuccess(String message);

            void onModifyStaffError(String message);
        }

        void startDb(Context context);

        void addStaff(OnAddStaffListener listener, Staff staff);

        void modifyStaff(OnModifyStaffListener listener, Staff staff);

        void loadAllGym(OnLoadGymsListener listener);
    }

    interface View {
        void loadGymSpinner(List<Gym> gyms);

        void addStaff(android.view.View view);

        void cleanForm();

        void showMessage(String message);
    }

    interface Presenter {
        void loadGymsSpinner();

        void addOrModifyStaff(Staff staff, Boolean modifyStaff);
    }
}
