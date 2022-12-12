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
import com.svalero.fitstagerapplication.contract.AddClientContract;
import com.svalero.fitstagerapplication.contract.AddStaffContract;
import com.svalero.fitstagerapplication.domain.Client;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.presenter.AddClientPresenter;
import com.svalero.fitstagerapplication.presenter.AddStaffPresenter;
import com.svalero.fitstagerapplication.util.ImageUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AddClientView extends AppCompatActivity implements AddClientContract.View {

    private Client client;
    private Gym gym;

    private ImageView clientImage;
    private Button addButton;
    private Spinner gymSpinner;
    private EditText etName;
    private EditText etSurname;
    private EditText etDni;
    private EditText etPhone;
    private Intent intent;
    private AddClientPresenter presenter;

    private boolean modifyClient;
    public List<Gym> gyms;


    public Button getAddButton() {
        return addButton;
    }

    public void setModifyClient(boolean modifyClient) {
        this.modifyClient = modifyClient;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client_view);

        clientImage = findViewById(R.id.client_imageView);
        etName = findViewById(R.id.name_edittext_add_client);
        etSurname = findViewById(R.id.surname_edittext_add_client);
        etDni = findViewById(R.id.dni_edittext_add_client);
        etPhone = findViewById(R.id.phone_edittext_add_client);
        gymSpinner = findViewById(R.id.gym_spinner_add_client);
        addButton = findViewById(R.id.add_client_button);

        presenter = new AddClientPresenter(this);
        client = new Client();
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
        modifyClient = intent.getBooleanExtra("modify_client", false);
        // Si se está editando la moto, obtiene los datos de la moto y los pinta en el formulario
        if (modifyClient) {
            client.setId(intent.getIntExtra("id", 0));
            gym.setId(intent.getIntExtra("gymId", 0));
            Log.i("gymId", "id" + gym.getId());
            client.setGym(gym);
            etName.setText(intent.getStringExtra("name"));
            etSurname.setText(intent.getStringExtra("surname"));
            etDni.setText(intent.getStringExtra("dni"));
            etPhone.setText(intent.getStringExtra("phone"));
            if (!intent.getStringExtra("client_image").equalsIgnoreCase(""))
                clientImage.setImageBitmap(ImageUtils.getBitmap(Base64.getDecoder().decode(intent.getStringExtra("client_image"))));

            addButton.setText("R.string.modify_capital");
        }
    }

    @Override
    public void addClient(View view) {

        client.setName(etName.getText().toString().trim());
        client.setSurname(etSurname.getText().toString().trim());
        client.setDni(etDni.getText().toString().trim());
        client.setPhone(etPhone.getText().toString().trim());
        gym.setId(gyms.get(gymSpinner.getSelectedItemPosition()).getId());
        Log.i("userId", "id" + gym.getId());
        client.setClientImage(Base64.getEncoder().encodeToString(ImageUtils.fromImageViewToByteArray(clientImage)));
        client.setGym (gym);
        presenter.addOrModifyClient(client, modifyClient);

    }


    @Override
    public void cleanForm() {

        clientImage.setImageResource(R.drawable.user_default);
        etName.setText("");
        etSurname.setText("");
        etDni.setText("");
        etPhone.setText("");

    }

    public void takePhoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    static final int REQUEST_IMAGE_CAPTURE = 1;

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            clientImage.setImageBitmap(imageBitmap);
        }
    }

















}