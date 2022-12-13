package com.svalero.fitstagerapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.svalero.fitstagerapplication.R;
import com.svalero.fitstagerapplication.contract.AddGymContract;
import com.svalero.fitstagerapplication.contract.AddStaffContract;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.presenter.AddGymPresenter;
import com.svalero.fitstagerapplication.presenter.AddStaffPresenter;
import com.svalero.fitstagerapplication.util.ImageUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AddGymView extends AppCompatActivity implements AddGymContract.View, OnMapReadyCallback, GoogleMap.OnMapClickListener {


    private Gym gym;

    private GoogleMap map;
    private Marker marker;
    private ImageView gymImage;
    private Button addButton;
    private Spinner gymSpinner;
    private EditText etName;
    private EditText etCity;
    private EditText etStreet;
    private EditText etHorary;
    private Intent intent;
    private AddGymPresenter presenter;

    private boolean modifyGym;


    public Button getAddButton() {
        return addButton;
    }

    public void setModifyGym(boolean modifyGym) {
        this.modifyGym = modifyGym;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gym_view);

        gymImage = findViewById(R.id.gym_imageView);
        etName = findViewById(R.id.name_edittext_add_gym);
        etCity = findViewById(R.id.city_edittext_add_gym);
        etStreet = findViewById(R.id.street_edittext_add_gym);
        etHorary = findViewById(R.id.horary_edittext_add_gym);
        addButton = findViewById(R.id.add_gym_button);

        presenter = new AddGymPresenter(this);
        gym = new Gym();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        if (ContextCompat.checkSelfPermission(AddGymView.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(AddGymView.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AddGymView.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000
            );
        }

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        intent();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }



    private void intent() {

        intent = getIntent();
        modifyGym = intent.getBooleanExtra("modify_gym", false);
        // Si se está editando la moto, obtiene los datos de la moto y los pinta en el formulario
        if (modifyGym) {
            gym.setId(intent.getIntExtra("id", 0));
            etName.setText(intent.getStringExtra("name"));
            etCity.setText(intent.getStringExtra("city"));
            etStreet.setText(intent.getStringExtra("street"));
            etHorary.setText(intent.getStringExtra("horary"));
            gym.setLatitude(intent.getFloatExtra("latitude", 0));
            gym.setLongitude(intent.getFloatExtra("longitude", 0));
            if (!intent.getStringExtra("gym_image").equalsIgnoreCase(""))
                gymImage.setImageBitmap(ImageUtils.getBitmap(Base64.getDecoder().decode(intent.getStringExtra("gym_image"))));

            addButton.setText("R.string.modify_capital");
        }
    }

    @Override
    public void addGym(View view) {

        gym.setName(etName.getText().toString().trim());
        gym.setCity(etCity.getText().toString().trim());
        gym.setStreet(etStreet.getText().toString().trim());
        gym.setHorary(etHorary.getText().toString().trim());
        gym.setGymImage(Base64.getEncoder().encodeToString(ImageUtils.fromImageViewToByteArray(gymImage)));

        presenter.addOrModifyGym(gym, modifyGym);




    }


    @Override
    public void cleanForm() {

        gymImage.setImageResource(R.drawable.weight_default);
        etName.setText("");
        etCity.setText("");
        etStreet.setText("");
        etHorary.setText("");
        gym.setLatitude(0);
        gym.setLongitude(0);
        marker.remove();

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
            gymImage.setImageBitmap(imageBitmap);
        }

    }




    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;    // Asignamos el mapa pasado por parámetro a nuestra variable de tipo GoogleMap
        googleMap.setOnMapClickListener(this);  // Establecemos un listener de click sencillo para el mapa

        if (gym.getLatitude() != 0 && gym.getLongitude() != 0) {  // Si el user tiene ubicación
            onMapClick(new LatLng(gym.getLatitude(), gym.getLongitude()));    // Pone un Marker
            map.moveCamera(CameraUpdateFactory.newLatLng    // Centra la camara y asigna un zoom
                    (new LatLng((gym.getLatitude() - 0.06), gym.getLongitude())));
            map.moveCamera(CameraUpdateFactory.zoomTo(11));
        }
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        if (marker != null)     // Si el marker NO está vacio,
            marker.remove();    // lo borramos para asignarle las coordenadas del click
        marker = map.addMarker(new MarkerOptions().position(latLng));
        gym.setLatitude((float) latLng.latitude);    // Asignamos las coordenadas del marker a la
        gym.setLongitude((float) latLng.longitude);   // dirección del user

    }
}