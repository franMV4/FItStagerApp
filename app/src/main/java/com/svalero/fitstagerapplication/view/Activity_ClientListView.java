package com.svalero.fitstagerapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.svalero.fitstagerapplication.R;
import com.svalero.fitstagerapplication.adapters.Activity_ClientAdapter;
import com.svalero.fitstagerapplication.adapters.StaffAdapter;
import com.svalero.fitstagerapplication.contract.Activity_ClientListContract;
import com.svalero.fitstagerapplication.contract.StaffListContract;
import com.svalero.fitstagerapplication.domain.Activity_Client;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.presenter.Activity_ClientListPresenter;
import com.svalero.fitstagerapplication.presenter.StaffListPresenter;

import java.util.ArrayList;
import java.util.List;

public class Activity_ClientListView extends AppCompatActivity implements Activity_ClientListContract.View, AdapterView.OnItemClickListener {



    public List<Activity_Client> activities_clients;
    public Activity_ClientAdapter activity_clientArrayAdapter;
    //public Spinner findSpinner;
    private String orderBy;
    private final String[] FIND_SPINNER_OPTIONS = new String[]{"Marca", "Modelo", "Ram"};
    private final String DEFAULT_STRING = "";
    private Activity_ClientListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_client_list_view);
        activities_clients = new ArrayList<>();
        presenter = new Activity_ClientListPresenter(this);
        activity_clientArrayAdapter = new Activity_ClientAdapter(this, activities_clients);

        // ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, FIND_SPINNER_OPTIONS);
        // findSpinner.setAdapter(adapterSpinner);

        //orderBy = DEFAULT_STRING;

        findActivities_ClientsBy(DEFAULT_STRING);
    }

    @Override
    protected void onResume() {
        super.onResume();

        findActivities_ClientsBy(DEFAULT_STRING);

    }


    private void findActivities_ClientsBy(String query) {
        activities_clients.clear();
        presenter.loadAllActivities_Clients();
/*
        if (query.equalsIgnoreCase(DEFAULT_STRING)) {
            presenter.loadAllStaffs();
        } else {
            switch (findSpinner.getSelectedItemPosition()) {
                case 0:
                    presenter.loadGymsByName(query);
                    break;
                case 1:
                    presenter.loadGymsByCity(query);
                    break;
                case 2:
                    Log.i("Street", " presenter.loadComputersByRam(query)");
                    presenter.loadGymsByStreet(query);
                    break;
            }
        }*/
        //orderBy(orderBy);
    }



    @Override
    public void listActivities_Clients(List<Activity_Client> activities_clients) {

        ListView activities_clientsListView = findViewById(R.id.activity_client_listview);
        registerForContextMenu(activities_clientsListView);
        this.activities_clients = activities_clients;

        activity_clientArrayAdapter = new Activity_ClientAdapter(this, this.activities_clients);

        activities_clientsListView.setAdapter(activity_clientArrayAdapter);
        activities_clientsListView.setOnItemClickListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.listview_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(this, AddActivity_ClientView.class);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final int itemSelected = info.position;

        switch (item.getItemId()) {
            case R.id.modify_menu:                      // Modificar moto
                Activity_Client activity_client = activities_clients.get(itemSelected);
                intent.putExtra("modify_activity_client", true);

                intent.putExtra("id", activity_client.getId());
                intent.putExtra("activityId", activity_client.getActivity().getId());
                intent.putExtra("clientId", activity_client.getClient().getId());

                startActivity(intent);


                return true;

            case R.id.detail_menu:                      // Detalles de la moto

                // Todo FALTA usar un fragment para mostrar una ficha con los detalles de la moto

                return true;

            case R.id.add_menu:                         // Añadir moto
                startActivity(intent);
                return true;

            case R.id.delete_menu:                      // Eliminar moto
                deleteActivity_Client(info);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteActivity_Client(AdapterView.AdapterContextMenuInfo info) {
        Activity_Client activity_client = activities_clients.get(info.position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure_delete)
                .setPositiveButton(R.string.yes_capital, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteActivity_Client(activity_client);
                        findActivities_ClientsBy(DEFAULT_STRING);
                    }
                })
                .setNegativeButton(R.string.no_capital, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    public void addActivity_Client(View view) {
        Intent intent = new Intent(this, AddActivity_ClientView.class);
        startActivity(intent);
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


}