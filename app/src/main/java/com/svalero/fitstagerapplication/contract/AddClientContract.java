package com.svalero.fitstagerapplication.contract;

import android.content.Context;

import com.svalero.fitstagerapplication.domain.Client;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;

import java.util.List;

public interface AddClientContract {




    interface Model {
        // USERS
        interface OnLoadGymsListener {
            void onLoadGymsSuccess(List<Gym> gyms);

            void onLoadGymsError(String message);
        }

        // STAFFS
        interface OnAddClientListener {
            void onAddClientSuccess(String message);

            void onAddClientError(String message);
        }

        interface OnModifyClientListener {
            void onModifyClientSuccess(String message);

            void onModifyClientError(String message);
        }

        void startDb(Context context);

        void addClient(AddClientContract.Model.OnAddClientListener listener, Client client);

        void modifyClient(AddClientContract.Model.OnModifyClientListener listener, Client client);

        void loadAllGym(AddClientContract.Model.OnLoadGymsListener listener);
    }

    interface View {
        void loadGymSpinner(List<Gym> gyms);

        void addClient(android.view.View view);

        void cleanForm();

        void showMessage(String message);
    }

    interface Presenter {
        void loadGymsSpinner();

        void addOrModifyClient(Client client, Boolean modifyClient);
    }
}

