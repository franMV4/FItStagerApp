package com.svalero.fitstagerapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.svalero.fitstagerapplication.R;
import com.svalero.fitstagerapplication.contract.AddStaffContract;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.presenter.AddStaffPresenter;
import com.svalero.fitstagerapplication.util.ImageUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AddStaffView extends AppCompatActivity implements AddStaffContract.View {

    private Staff staff;
    private Gym gym;

    private Button addButton;
    private Spinner gymSpinner;
    private EditText etName;
    private EditText etSurname;
    private EditText etDni;
    private EditText etPhone;
    private Intent intent;
    private AddStaffPresenter presenter;
    private ImageView staffImage;

    private boolean modifyStaff;
    public List<Gym> gyms;


    public Button getAddButton() {
        return addButton;
    }

    public void setModifyStaff(boolean modifyStaff) {
        this.modifyStaff = modifyStaff;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff_view);

        staffImage = findViewById(R.id.staff_imageView);
        etName = findViewById(R.id.name_edittext_add_staff);
        etSurname = findViewById(R.id.surname_edittext_add_staff);
        etDni = findViewById(R.id.dni_edittext_add_staff);
        etPhone = findViewById(R.id.phone_edittext_add_staff);
        gymSpinner = findViewById(R.id.gym_spinner_add_staff);
        addButton = findViewById(R.id.add_staff_button);

        presenter = new AddStaffPresenter(this);
        staff = new Staff();
        gym = new Gym();
        gyms = new ArrayList<>();

        presenter.loadGymsSpinner(); //MVP
        intent();
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadGymsSpinner(); //MVP
    }

    @Override
    public void loadGymSpinner(List<Gym> gyms) {

        this.gyms.clear();
        this.gyms.addAll(gyms);

        String[] arraySpinner = new String[gyms.size()];

        for (int i = 0; i < gyms.size(); i++) {
            arraySpinner[i] = gyms.get(i).getName() + " " + gyms.get(i).getCity();
        }

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arraySpinner);
        gymSpinner.setAdapter(adapterSpinner);

    }


    private void intent() {

        intent = getIntent();
        modifyStaff = intent.getBooleanExtra("modify_staff", false);
        // Si se está editando la moto, obtiene los datos de la moto y los pinta en el formulario
        if (modifyStaff) {
            staff.setId(intent.getIntExtra("id", 0));
            gym.setId(intent.getIntExtra("gymId", 0));
            Log.i("gymId", "id" + gym.getId());
            staff.setGym(gym);
            etName.setText(intent.getStringExtra("name"));
            etSurname.setText(intent.getStringExtra("surname"));
            etDni.setText(intent.getStringExtra("dni"));
            etPhone.setText(intent.getStringExtra("phone"));
            if (!intent.getStringExtra("staff_image").equalsIgnoreCase(""))
                staffImage.setImageBitmap(ImageUtils.getBitmap(Base64.getDecoder().decode(intent.getStringExtra("staff_image"))));

            addButton.setText("R.string.modify_capital");
        }
    }

    @Override
    public void addStaff(View view) {
/*
        staff.setName(etName.getText().toString().trim());
        staff.setSurname(etSurname.getText().toString().trim());
        staff.setDni(etDni.getText().toString().trim());
        staff.setPhone(etPhone.getText().toString().trim());
        gym.setId(gyms.get(gymSpinner.getSelectedItemPosition()).getId());
        Log.i("userId", "id" + gym.getId());
        staff.setGym (gym);
        staff.setStaffImage(Base64.getEncoder().encodeToString(ImageUtils.fromImageViewToByteArray(staffImage)));
        presenter.addOrModifyStaff(staff, modifyStaff);
*/

        staff.setName(etName.getText().toString().trim());
        staff.setSurname(etSurname.getText().toString().trim());
        staff.setDni(etDni.getText().toString().trim());
        staff.setPhone(etPhone.getText().toString().trim());
        gym.setId(gyms.get(gymSpinner.getSelectedItemPosition()).getId());
        Log.i("userId", "id" + gym.getId());
        staff.setStaffImage(Base64.getEncoder().encodeToString(ImageUtils.fromImageViewToByteArray(staffImage)));
        staff.setGym (gym);
        presenter.addOrModifyStaff(staff, modifyStaff);

    }


    @Override
    public void cleanForm() {

        staffImage.setImageResource(R.drawable.user_default);
        etName.setText("");
        etSurname.setText("");
        etDni.setText("");
        etPhone.setText("");

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }






    public void takePhoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
    }



    static final int REQUEST_IMAGE_CAPTURE = 1;

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            staffImage.setImageBitmap(imageBitmap);
        }
    }









}