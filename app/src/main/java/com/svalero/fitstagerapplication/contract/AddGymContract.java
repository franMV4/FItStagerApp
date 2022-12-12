package com.svalero.fitstagerapplication.contract;

import android.content.Context;

import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;

import java.util.List;

public interface AddGymContract {


    interface Model {
        // USERS


        // STAFFS
        interface OnAddGymListener {
            void onAddGymSuccess(String message);

            void onAddGymError(String message);
        }

        interface OnModifyGymListener {
            void onModifyGymSuccess(String message);

            void onModifyGymError(String message);
        }

        void startDb(Context context);

        void addGym(AddGymContract.Model.OnAddGymListener listener, Gym gym);

        void modifyGym(AddGymContract.Model.OnModifyGymListener listener, Gym gym);

    }

    interface View {

        void addGym(android.view.View view);

        void cleanForm();

        void showMessage(String message);
    }

    interface Presenter {

        void addOrModifyGym(Gym gym, Boolean modifyGym);
    }

}
