package com.svalero.fitstagerapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.svalero.fitstagerapplication.R;
import com.svalero.fitstagerapplication.adapters.GymAdapter;
import com.svalero.fitstagerapplication.contract.GymListContract;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.presenter.GymListPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GymListView extends AppCompatActivity implements GymListContract.View, AdapterView.OnItemClickListener,
        DetailFragment.closeDetails{

    public List<Gym> gyms;
    public GymAdapter gymArrayAdapter;
    public Spinner findSpinner;
    private String orderBy;
    private FrameLayout frameLayout;
    private final String[] FIND_SPINNER_OPTIONS = new String[]{"Name", "City", "Street"};
    private final String DEFAULT_STRING = "";
    private GymListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_list_view);
        frameLayout = findViewById(R.id.frame_layout_gym);
        //findSpinner = findViewById(R.id.find_spinner_view_computer);
        gyms = new ArrayList<>();
        presenter = new GymListPresenter(this);
        gymArrayAdapter = new GymAdapter(this, gyms);

      //  ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, FIND_SPINNER_OPTIONS);
       // findSpinner.setAdapter(adapterSpinner);

        orderBy = DEFAULT_STRING;

        findGymsBy(DEFAULT_STRING);
    }
    @Override
    protected void onResume() {
        super.onResume();

        findGymsBy(DEFAULT_STRING);
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gym_actionbar, menu);
        final MenuItem searchItem = menu.findItem(R.id.app_bar_computer_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                findComputersBy(query.trim());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                findComputersBy(newText.trim());
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    */

    @Override
    public void listGyms(List<Gym> gyms) {

        ListView gymsListView = findViewById(R.id.gym_listview);
        registerForContextMenu(gymsListView);
        this.gyms = gyms;

        gymArrayAdapter = new GymAdapter(this, this.gyms);

        gymsListView.setAdapter(gymArrayAdapter);
        gymsListView.setOnItemClickListener(this);

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



    private void findGymsBy(String query) {
        gyms.clear();

        if (query.equalsIgnoreCase(DEFAULT_STRING)) {
            presenter.loadAllGyms();
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
        }
        orderBy(orderBy);
    }


    private void orderBy(String orderBy) {
        this.orderBy = orderBy;

        Collections.sort(gyms, new Comparator<Gym>() {
            @Override
            public int compare(Gym o1, Gym o2) {
                switch (orderBy) {
                    case "brand":
                        return o1.getName().compareToIgnoreCase(o2.getName());
                    case "model":
                        return o1.getCity().compareToIgnoreCase(o2.getCity());
                    case "ram":
                        return o1.getStreet().compareToIgnoreCase(o2.getStreet());
                    default:
                        return String.valueOf(o1.getId()).compareTo(String.valueOf(o2.getId()));
                }
            }
        });
        gymArrayAdapter.notifyDataSetChanged();
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.listview_menu, menu);

    }



    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(this, AddGymView.class);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final int itemSelected = info.position;

        switch (item.getItemId()) {
            case R.id.modify_menu:                      // Modificar moto
                Gym gym = gyms.get(itemSelected);
                intent.putExtra("modify_gym", true);

                intent.putExtra("id", gym.getId());
                intent.putExtra("name", gym.getName());
                intent.putExtra("city", gym.getCity());
                intent.putExtra("street", gym.getStreet());
                intent.putExtra("horary", gym.getHorary());
                intent.putExtra("gym_image", gym.getGymImage());
                intent.putExtra("latitude", gym.getLatitude());
                intent.putExtra("longitude", gym.getLongitude());
                startActivity(intent);
                return true;

            case R.id.detail_menu:                      // Detalles de la moto

                showDetails(info.position);

                return true;

            case R.id.add_menu:                         // Añadir moto
                startActivity(intent);
                return true;

            case R.id.delete_menu:                      // Eliminar moto
                deleteGym(info);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }


    private void showDetails(int position) {

        Gym gym = gyms.get(position);

        Bundle datos = new Bundle();
        datos.putString("name", gym.getName());
        datos.putString("city", gym.getCity());
        datos.putString("street", gym.getStreet());
        datos.putString("horary", gym.getHorary());
        datos.putString("gym_image", gym.getGymImage());
        datos.putFloat("latitude", gym.getLatitude());
        datos.putFloat("longitude", gym.getLongitude());

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(datos);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.gym_detail, detailFragment)
                .commit();

        frameLayout.setVisibility(View.VISIBLE);
    }




    private void deleteGym(AdapterView.AdapterContextMenuInfo info) {
        Gym gym = gyms.get(info.position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure_delete)
                .setPositiveButton(R.string.yes_capital, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteGym(gym);
                        findGymsBy(DEFAULT_STRING);
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

    public void addGym(View view) {
        Intent intent = new Intent(this, AddGymView.class);
        startActivity(intent);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void hiddeDetails() {
        frameLayout.setVisibility(View.GONE);
    }
}