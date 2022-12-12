package com.svalero.fitstagerapplication.contract;

import android.content.Context;

import com.svalero.fitstagerapplication.domain.Client;
import com.svalero.fitstagerapplication.domain.Staff;

import java.util.List;

public interface ClientListContract {


    interface Model {
        interface OnLoadClientsListener {
            void onLoadClientsSuccess(List<Client> clients);

            void onLoadClientsError(String message);
        }

        interface OnDeleteClientListener{
            void onDeleteClientSuccess(String message);

            void onDeleteClientError(String message);
        }

        void startDb(Context context);

        void loadAllClients(ClientListContract.Model.OnLoadClientsListener listener);

        //void loadStaffsByBrand(OnLoadStaffsListener listener, String query);

        //void loadStaffsByModel(OnLoadStaffsListener listener, String query);

        //void loadStaffsByRam(OnLoadStaffsListener listener, String query);

        void delete(ClientListContract.Model.OnDeleteClientListener listener, Client client);
    }

    interface View {
        void listClients(List<Client> clients);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllClients();

        //void loadStaffsByBrand(String query);

        //void loadStaffsByModel(String query);

        //void loadStaffsByRam(String query);

        void deleteClient(Client client);
    }
}




