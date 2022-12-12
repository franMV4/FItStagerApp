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
import com.svalero.fitstagerapplication.adapters.ActivityAdapter;
import com.svalero.fitstagerapplication.adapters.StaffAdapter;
import com.svalero.fitstagerapplication.contract.ActivityListContract;
import com.svalero.fitstagerapplication.contract.StaffListContract;
import com.svalero.fitstagerapplication.domain.Activitie;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.presenter.ActivityListPresenter;
import com.svalero.fitstagerapplication.presenter.StaffListPresenter;

import java.util.ArrayList;
import java.util.List;

public class ActivityListView extends AppCompatActivity implements ActivityListContract.View, AdapterView.OnItemClickListener,
        DetailFragment.closeDetails{



    public List<Activitie> activities;
    public ActivityAdapter activityArrayAdapter;
    private FrameLayout frameLayout;
    //public Spinner findSpinner;
    private String orderBy;
    private final String[] FIND_SPINNER_OPTIONS = new String[]{"Marca", "Modelo", "Ram"};
    private final String DEFAULT_STRING = "";
    private ActivityListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_list_view);
        activities = new ArrayList<>();
        frameLayout = findViewById(R.id.frame_layout_activity);
        presenter = new ActivityListPresenter(this);
        activityArrayAdapter = new ActivityAdapter(this, activities);

        // ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, FIND_SPINNER_OPTIONS);
        // findSpinner.setAdapter(adapterSpinner);

        //orderBy = DEFAULT_STRING;

        findActivitiesBy(DEFAULT_STRING);
    }

    @Override
    protected void onResume() {
        super.onResume();

        findActivitiesBy(DEFAULT_STRING);

    }


    private void findActivitiesBy(String query) {
        activities.clear();
        presenter.loadAllActivities();
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
    public void listActivities(List<Activitie> activities) {

        ListView activitiesListView = findViewById(R.id.activity_listview);
        registerForContextMenu(activitiesListView);
        this.activities = activities;

        activityArrayAdapter = new ActivityAdapter(this, this.activities);

        activitiesListView.setAdapter(activityArrayAdapter);
        activitiesListView.setOnItemClickListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.listview_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(this, AddActivityView.class);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final int itemSelected = info.position;

        switch (item.getItemId()) {
            case R.id.modify_menu:                      // Modificar moto
                Activitie activity = activities.get(itemSelected);
                intent.putExtra("modify_activity", true);

                intent.putExtra("id", activity.getId());
                intent.putExtra("name", activity.getName());
                intent.putExtra("room", activity.getRoom());
                intent.putExtra("difficulty", activity.getDifficulty());
                intent.putExtra("style", activity.getStyle());
                intent.putExtra("date", String.valueOf(activity.getDate()));
                intent.putExtra("activity_image", activity.getActivityImage());
                intent.putExtra("gymId", activity.getGym().getId());
                intent.putExtra("staffId", activity.getStaff().getId());

                startActivity(intent);


                return true;

            case R.id.detail_menu:                      // Detalles de la moto

                showDetails(info.position);

                return true;

            case R.id.add_menu:                         // Añadir moto
                startActivity(intent);
                return true;

            case R.id.delete_menu:                      // Eliminar moto
                deleteActivity(info);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteActivity(AdapterView.AdapterContextMenuInfo info) {
        Activitie activity = activities.get(info.position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure_delete)
                .setPositiveButton(R.string.yes_capital, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteActivity(activity);
                        findActivitiesBy(DEFAULT_STRING);
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

        Activitie activity = activities.get(position);

        Bundle datos = new Bundle();
        datos.putString("name", activity.getName());
        datos.putString("style", activity.getStyle());
        datos.putString("difficulty", activity.getDifficulty());
        datos.putString("date", activity.getDate());
        datos.putString("room", activity.getRoom());
        datos.putString("activity_image", activity.getActivityImage());

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(datos);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_detail, detailFragment)
                .commit();

        frameLayout.setVisibility(View.VISIBLE);
    }


    public void addActivity(View view) {
        Intent intent = new Intent(this, AddActivityView.class);
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