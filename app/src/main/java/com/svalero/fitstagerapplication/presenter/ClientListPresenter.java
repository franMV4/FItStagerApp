package com.svalero.fitstagerapplication.presenter;

import com.svalero.fitstagerapplication.contract.ClientListContract;
import com.svalero.fitstagerapplication.contract.StaffListContract;
import com.svalero.fitstagerapplication.domain.Client;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.model.ClientListModel;
import com.svalero.fitstagerapplication.model.StaffListModel;
import com.svalero.fitstagerapplication.view.ClientListView;
import com.svalero.fitstagerapplication.view.StaffListView;

import java.util.List;

public class ClientListPresenter implements ClientListContract.Presenter, ClientListContract.Model.OnLoadClientsListener, ClientListContract.Model.OnDeleteClientListener {


    private ClientListModel model;
    private ClientListView view;

    public ClientListPresenter(ClientListView view) {
        model = new ClientListModel();
        model.startDb(view.getApplicationContext());

        this.view = view;
    }

    @Override
    public void loadAllClients() {
        model.loadAllClients(this);
    }

    @Override   // OnLoadComputersListener SUCCESS
    public void onLoadClientsSuccess(List<Client> clients) {
        view.listClients(clients);
    }

    @Override   // OnLoadComputersListener ERROR
    public void onLoadClientsError(String message) {
        view.showMessage(message);
    }

    @Override
    public void deleteClient(Client client) {
        model.delete(this, client);
    }

    @Override   // OnDeleteComputersListener SUCCESS
    public void onDeleteClientSuccess(String message) {
        view.showMessage(message);
    }

    @Override   // OnDeleteComputersListener ERROR
    public void onDeleteClientError(String message) {
        view.showMessage(message);
    }




}
