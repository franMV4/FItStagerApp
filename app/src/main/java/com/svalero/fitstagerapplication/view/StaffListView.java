package com.svalero.fitstagerapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.svalero.fitstagerapplication.R;
import com.svalero.fitstagerapplication.adapters.StaffAdapter;
import com.svalero.fitstagerapplication.contract.StaffListContract;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.domain.dto.StaffDTO;
import com.svalero.fitstagerapplication.presenter.StaffListPresenter;

import java.util.ArrayList;
import java.util.List;

public class StaffListView extends AppCompatActivity implements StaffListContract.View, AdapterView.OnItemClickListener,
        DetailFragment.closeDetails{


    public ArrayList<StaffDTO> staffsDTOArrayList;
    public List<Staff> staffs;
    public StaffAdapter staffArrayAdapter;
    //public Spinner findSpinner;
    private FrameLayout frameLayout;
    private String orderBy;
    private final String[] FIND_SPINNER_OPTIONS = new String[]{"Marca", "Modelo", "Ram"};
    private final String DEFAULT_STRING = "";
    private StaffListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_list_view);
        frameLayout = findViewById(R.id.frame_layout_staff);
        staffs = new ArrayList<>();
        presenter = new StaffListPresenter(this);
        staffArrayAdapter = new StaffAdapter(this, staffs);
        staffsDTOArrayList = new ArrayList<>();

       // ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, FIND_SPINNER_OPTIONS);
       // findSpinner.setAdapter(adapterSpinner);

        //orderBy = DEFAULT_STRING;

        findStaffsBy(DEFAULT_STRING);
    }

    @Override
    protected void onResume() {
        super.onResume();

        findStaffsBy(DEFAULT_STRING);

    }


    private void findStaffsBy(String query) {
        staffs.clear();
        presenter.loadAllStaffs();
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
    public void listStaffs(List<Staff> staffs) {

        ListView staffsListView = findViewById(R.id.staff_listview);
        registerForContextMenu(staffsListView);
        this.staffs = staffs;

        staffArrayAdapter = new StaffAdapter(this, this.staffs);

        staffsListView.setAdapter(staffArrayAdapter);
        staffsListView.setOnItemClickListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.listview_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(this, AddStaffView.class);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final int itemSelected = info.position;

        switch (item.getItemId()) {
            case R.id.modify_menu:                      // Modificar moto
                Staff staff = staffs.get(itemSelected);
                intent.putExtra("modify_staff", true);

                intent.putExtra("id", staff.getId());
                intent.putExtra("name", staff.getName());
                intent.putExtra("surname", staff.getSurname());
                intent.putExtra("dni", staff.getDni());
                intent.putExtra("phone", staff.getPhone());
                intent.putExtra("gymId", staff.getGym().getId());
                intent.putExtra("staff_image", staff.getStaffImage());

                startActivity(intent);


                return true;

            case R.id.detail_menu:                      // Detalles de la moto

                showDetails(info.position);

                return true;

            case R.id.add_menu:                         // Añadir moto
                startActivity(intent);
                return true;

            case R.id.delete_menu:                      // Eliminar moto
                deleteStaff(info);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteStaff(AdapterView.AdapterContextMenuInfo info) {
        Staff staff = staffs.get(info.position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure_delete)
                .setPositiveButton(R.string.yes_capital, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteStaff(staff);
                        findStaffsBy(DEFAULT_STRING);
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

        Staff staff = staffs.get(position);

        Bundle datos = new Bundle();
        datos.putString("name", staff.getName());
        datos.putString("surname", staff.getSurname());
        datos.putString("dni", staff.getDni());
        datos.putString("phone", staff.getPhone());
        datos.putString("staff_image", staff.getStaffImage());

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(datos);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.staff_detail, detailFragment)
                .commit();

        frameLayout.setVisibility(View.VISIBLE);
    }




    public void addStaff(View view) {
        Intent intent = new Intent(this, AddStaffView.class);
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
