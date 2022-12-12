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
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.svalero.fitstagerapplication.R;
import com.svalero.fitstagerapplication.adapters.ClientAdapter;
import com.svalero.fitstagerapplication.adapters.StaffAdapter;
import com.svalero.fitstagerapplication.contract.ClientListContract;
import com.svalero.fitstagerapplication.contract.StaffListContract;
import com.svalero.fitstagerapplication.domain.Client;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.presenter.ClientListPresenter;
import com.svalero.fitstagerapplication.presenter.StaffListPresenter;

import java.util.ArrayList;
import java.util.List;

public class ClientListView extends AppCompatActivity implements ClientListContract.View, AdapterView.OnItemClickListener,
        DetailFragment.closeDetails{



    public List<Client> clients;
    public ClientAdapter clientArrayAdapter;
    //public Spinner findSpinner;
    private String orderBy;
    private FrameLayout frameLayout;
    private final String[] FIND_SPINNER_OPTIONS = new String[]{"Marca", "Modelo", "Ram"};
    private final String DEFAULT_STRING = "";
    private ClientListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list_view);
        clients = new ArrayList<>();
        presenter = new ClientListPresenter(this);
        clientArrayAdapter = new ClientAdapter(this, clients);
        frameLayout = findViewById(R.id.frame_layout_client);

        // ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, FIND_SPINNER_OPTIONS);
        // findSpinner.setAdapter(adapterSpinner);

        //orderBy = DEFAULT_STRING;

        findClientsBy(DEFAULT_STRING);
    }

    @Override
    protected void onResume() {
        super.onResume();

        findClientsBy(DEFAULT_STRING);

    }


    private void findClientsBy(String query) {
        clients.clear();
        presenter.loadAllClients();
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
    public void listClients(List<Client> clients) {

        ListView clientsListView = findViewById(R.id.client_listview);
        registerForContextMenu(clientsListView);
        this.clients = clients;

        clientArrayAdapter = new ClientAdapter(this, this.clients);

        clientsListView.setAdapter(clientArrayAdapter);
        clientsListView.setOnItemClickListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.listview_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(this, AddClientView.class);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final int itemSelected = info.position;

        switch (item.getItemId()) {
            case R.id.modify_menu:                      // Modificar moto
                Client client = clients.get(itemSelected);
                intent.putExtra("modify_client", true);

                intent.putExtra("id", client.getId());
                intent.putExtra("name", client.getName());
                intent.putExtra("surname", client.getSurname());
                intent.putExtra("dni", client.getDni());
                intent.putExtra("phone", client.getPhone());
                intent.putExtra("gymId", client.getGym().getId());
                intent.putExtra("client_image", client.getClientImage());

                startActivity(intent);


                return true;

            case R.id.detail_menu:                      // Detalles de la moto

                showDetails(info.position);

                return true;

            case R.id.add_menu:                         // Añadir moto
                startActivity(intent);
                return true;

            case R.id.delete_menu:                      // Eliminar moto
                deleteClient(info);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteClient(AdapterView.AdapterContextMenuInfo info) {
        Client client = clients.get(info.position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure_delete)
                .setPositiveButton(R.string.yes_capital, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteClient(client);
                        findClientsBy(DEFAULT_STRING);
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

    private void showDetails(int position) {

        Client client = clients.get(position);

        Bundle datos = new Bundle();
        datos.putString("name", client.getName());
        datos.putString("surname", client.getSurname());
        datos.putString("dni", client.getDni());
        datos.putString("phone", client.getPhone());
        datos.putString("client_image", client.getClientImage());

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(datos);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.client_detail, detailFragment)
                .commit();

        frameLayout.setVisibility(View.VISIBLE);
    }


    public void addClient(View view) {
        Intent intent = new Intent(this, AddClientView.class);
        startActivity(intent);
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void hiddeDetails() {
        frameLayout.setVisibility(View.GONE);
    }

}