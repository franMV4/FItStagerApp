package com.svalero.fitstagerapplication.view;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.svalero.fitstagerapplication.R;
import com.svalero.fitstagerapplication.contract.AddActivity_ClientContract;
import com.svalero.fitstagerapplication.contract.AddStaffContract;
import com.svalero.fitstagerapplication.domain.Activitie;
import com.svalero.fitstagerapplication.domain.Activity_Client;
import com.svalero.fitstagerapplication.domain.Client;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.presenter.AddActivity_ClientPresenter;
import com.svalero.fitstagerapplication.presenter.AddStaffPresenter;

import java.util.ArrayList;
import java.util.List;

public class AddActivity_ClientView extends AppCompatActivity implements AddActivity_ClientContract.View {

    private Activity_Client activity_client;
    private Activitie activity;
    private Client client;


    private Button addButton;
    private Spinner activitySpinner;
    private Spinner clientSpinner;

    private Intent intent;
    private AddActivity_ClientPresenter presenter;

    private boolean modifyActivity_Client;
    public List<Activitie> activities;
    public List<Client> clients;


    public Button getAddButton() {
        return addButton;
    }

    public void setModifyActivity_Client(boolean modifyActivity_Client) {
        this.modifyActivity_Client = modifyActivity_Client;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity_client_view);


        activitySpinner = findViewById(R.id.activity_spinner_add_activity_client);
        clientSpinner = findViewById(R.id.client_spinner_add_activity_client);
        addButton = findViewById(R.id.add_activity_client_button);

        presenter = new AddActivity_ClientPresenter(this);
        activity_client = new Activity_Client();
        activity = new Activitie();
        client = new Client();
        activities = new ArrayList<>();
        clients = new ArrayList<>();

        presenter.loadActivitiesSpinner(); //MVP
        presenter.loadClientsSpinner(); //MVP
        intent();
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadActivitiesSpinner(); //MVP
        presenter.loadClientsSpinner(); //MVP
    }

    @Override
    public void loadActivitySpinner(List<Activitie> activities) {

        this.activities.clear();
        this.activities.addAll(activities);

        String[] arraySpinner = new String[activities.size()];

        for (int i = 0; i < activities.size(); i++) {
            arraySpinner[i] = activities.get(i).getName() + " " + activities.get(i).getName();
        }

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arraySpinner);
        activitySpinner.setAdapter(adapterSpinner);

    }

    @Override
    public void loadClientSpinner(List<Client> clients) {

        this.clients.clear();
        this.clients.addAll(clients);

        String[] arraySpinner = new String[clients.size()];

        for (int i = 0; i < clients.size(); i++) {
            arraySpinner[i] = clients.get(i).getName() + " " + clients.get(i).getName();
        }

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arraySpinner);
        clientSpinner.setAdapter(adapterSpinner);

    }


    private void intent() {

        intent = getIntent();
        modifyActivity_Client = intent.getBooleanExtra("modify_activity_client", false);
        // Si se está editando la moto, obtiene los datos de la moto y los pinta en el formulario
        if (modifyActivity_Client) {
            activity_client.setId(intent.getIntExtra("id", 0));
            activity.setId(intent.getIntExtra("activityId", 0));
            client.setId(intent.getIntExtra("clientId", 0));
            Log.i("activityId", "id" + activity.getId());
            Log.i("clientId", "id" + client.getId());
            activity_client.setActivity(activity);
            activity_client.setClient(client);


            addButton.setText("R.string.modify_capital");
        }
    }

    @Override
    public void addActivity_Client(View view) {


        activity.setId(activities.get(activitySpinner.getSelectedItemPosition()).getId());
        client.setId(clients.get(clientSpinner.getSelectedItemPosition()).getId());
        Log.i("activityId", "id" + activity.getId());
        Log.i("clientId", "id" + client.getId());
        activity_client.setActivity (activity);
        activity_client.setClient (client);
        presenter.addOrModifyActivity_Client(activity_client, modifyActivity_Client);

    }


    @Override
    public void cleanForm() {


    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }




















}