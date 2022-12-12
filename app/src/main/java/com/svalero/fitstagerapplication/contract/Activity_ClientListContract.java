package com.svalero.fitstagerapplication.contract;

import android.content.Context;

import com.svalero.fitstagerapplication.domain.Activity_Client;
import com.svalero.fitstagerapplication.domain.Staff;

import java.util.List;

public interface Activity_ClientListContract {



    interface Model {
        interface OnLoadActivities_ClientsListener {
            void onLoadActivities_ClientsSuccess(List<Activity_Client> activities_clients);

            void onLoadActivities_ClientsError(String message);
        }

        interface OnDeleteActivity_ClientListener{
            void onDeleteActivity_ClientSuccess(String message);

            void onDeleteActivity_ClientError(String message);
        }

        void startDb(Context context);

        void loadAllActivities_Clients(Activity_ClientListContract.Model.OnLoadActivities_ClientsListener listener);

        //void loadStaffsByBrand(OnLoadStaffsListener listener, String query);

        //void loadStaffsByModel(OnLoadStaffsListener listener, String query);

        //void loadStaffsByRam(OnLoadStaffsListener listener, String query);

        void delete(Activity_ClientListContract.Model.OnDeleteActivity_ClientListener listener, Activity_Client activity_client);
    }

    interface View {
        void listActivities_Clients(List<Activity_Client> activities_clients);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllActivities_Clients();

        //void loadStaffsByBrand(String query);

        //void loadStaffsByModel(String query);

        //void loadStaffsByRam(String query);

        void deleteActivity_Client(Activity_Client activity_client);
    }
}

