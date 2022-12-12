package com.svalero.fitstagerapplication.contract;

import android.content.Context;

import com.svalero.fitstagerapplication.domain.Activitie;
import com.svalero.fitstagerapplication.domain.Activity_Client;
import com.svalero.fitstagerapplication.domain.Client;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;

import java.util.List;

public interface AddActivity_ClientContract {




    interface Model {
        // USERS
        interface OnLoadActivitiesListener {
            void onLoadActivitiesSuccess(List<Activitie> activities);

            void onLoadActivitiesError(String message);
        }
        interface OnLoadClientsListener {
            void onLoadClientsSuccess(List<Client> clients);

            void onLoadClientsError(String message);
        }

        // STAFFS
        interface OnAddActivity_ClientListener {
            void onAddActivity_ClientSuccess(String message);

            void onAddActivity_ClientError(String message);
        }

        interface OnModifyActivity_ClientListener {
            void onModifyActivity_ClientSuccess(String message);

            void onModifyActivity_ClientError(String message);
        }

        void startDb(Context context);

        void addActivity_Client(AddActivity_ClientContract.Model.OnAddActivity_ClientListener listener, Activity_Client activity_client);

        void modifyActivity_Client(AddActivity_ClientContract.Model.OnModifyActivity_ClientListener listener, Activity_Client activity_client);

        void loadAllClient(AddActivity_ClientContract.Model.OnLoadClientsListener listener);

        void loadAllActivity(AddActivity_ClientContract.Model.OnLoadActivitiesListener listener);
    }

    interface View {
        void loadClientSpinner(List<Client> clients);

        void loadActivitySpinner(List<Activitie> activities);

        void addActivity_Client(android.view.View view);

        void cleanForm();

        void showMessage(String message);
    }

    interface Presenter {
        void loadActivitiesSpinner();

        void loadClientsSpinner();

        void addOrModifyActivity_Client(Activity_Client activity_client, Boolean modifyActivity_Client);
    }
}
