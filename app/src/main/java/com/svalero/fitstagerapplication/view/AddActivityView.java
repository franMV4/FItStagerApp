package com.svalero.fitstagerapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.svalero.fitstagerapplication.R;
import com.svalero.fitstagerapplication.contract.AddActivityContract;
import com.svalero.fitstagerapplication.contract.AddStaffContract;
import com.svalero.fitstagerapplication.domain.Activitie;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.presenter.AddActivityPresenter;
import com.svalero.fitstagerapplication.presenter.AddStaffPresenter;
import com.svalero.fitstagerapplication.util.DateUtils;
import com.svalero.fitstagerapplication.util.ImageUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AddActivityView extends AppCompatActivity implements AddActivityContract.View {

    private Activitie activity;
    private Gym gym;
    private Staff staff;

    private ImageView activityImage;
    private Button addButton;
    private Spinner gymSpinner;
    private Spinner staffSpinner;
    private EditText etName;
    private EditText etRoom;
    private EditText etStyle;
    private EditText etDifficulty;
    private TextView tvDate;
    private Intent intent;
    private AddActivityPresenter presenter;

    private boolean modifyActivity;
    public List<Gym> gyms;
    public List<Staff> staffs;


    public Button getAddButton() {
        return addButton;
    }

    public void setModifyActivity(boolean modifyActivity) {
        this.modifyActivity = modifyActivity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity_view);

        activityImage = findViewById(R.id.activity_imageView);
        etName = findViewById(R.id.name_edittext_add_activity);
        etRoom = findViewById(R.id.room_edittext_add_activity);
        etStyle = findViewById(R.id.style_edittext_add_activity);
        etDifficulty = findViewById(R.id.difficulty_edittext_add_activity);
        tvDate = findViewById(R.id.date_textlabel_add_activity);
        gymSpinner = findViewById(R.id.gym_spinner_add_activity);
        staffSpinner = findViewById(R.id.staff_spinner_add_activity);
        addButton = findViewById(R.id.add_activity_button);

        presenter = new AddActivityPresenter(this);
        activity = new Activitie();
        gym = new Gym();
        staff = new Staff();
        gyms = new ArrayList<>();
        staffs = new ArrayList<>();

        presenter.loadGymsSpinner(); //MVP
        presenter.loadStaffsSpinner(); //MVP
        intent();
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadGymsSpinner(); //MVP
        presenter.loadStaffsSpinner(); //MVP
    }


    public void selectDate(View view) {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String stringMonth = String.valueOf(month + 1);
                if ((month + 1) < 10) {
                    stringMonth = "0" + stringMonth;
                }
                //tvDate.setText(dayOfMonth + "/" + stringMonth + "/" + year);
                tvDate.setText(  year + "-" + stringMonth + "-" + dayOfMonth);
            }
        }, year, month, day);
        datePickerDialog.show();
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

@Override
    public void loadStaffSpinner(List<Staff> staffs) {

        this.staffs.clear();
        this.staffs.addAll(staffs);

        String[] arraySpinner = new String[staffs.size()];

        for (int i = 0; i < staffs.size(); i++) {
            arraySpinner[i] = staffs.get(i).getName() + " " + staffs.get(i).getSurname();
        }

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arraySpinner);
    staffSpinner.setAdapter(adapterSpinner);

    }


    private void intent() {

        intent = getIntent();
        modifyActivity = intent.getBooleanExtra("modify_activity", false);
        // Si se está editando la moto, obtiene los datos de la moto y los pinta en el formulario
        if (modifyActivity) {
            activity.setId(intent.getIntExtra("id", 0));
            tvDate.setText(DateUtils.fromLocalDateToMyDateFormatString
                    (LocalDate.parse(intent.getStringExtra("date"))));
            gym.setId(intent.getIntExtra("gymId", 0));
            staff.setId(intent.getIntExtra("staffId", 0));
            Log.i("gymId", "id" + gym.getId());
            Log.i("staffId", "id" + staff.getId());
            activity.setGym(gym);
            activity.setStaff(staff);
            etName.setText(intent.getStringExtra("name"));
            etRoom .setText(intent.getStringExtra("room"));
            etStyle .setText(intent.getStringExtra("style"));
            etDifficulty .setText(intent.getStringExtra("difficulty"));
            if (!intent.getStringExtra("activity_image").equalsIgnoreCase(""))
                activityImage.setImageBitmap(ImageUtils.getBitmap(Base64.getDecoder().decode(intent.getStringExtra("activity_image"))));

            addButton.setText("R.string.modify_capital");
        }
    }

    @Override
    public void addActivity(View view) {

        if (!(tvDate.getText().toString().trim()).equalsIgnoreCase(""))
            activity.setDate(tvDate.getText().toString().trim());
        activity.setName(etName.getText().toString().trim());
        activity.setRoom(etRoom.getText().toString().trim());
        activity.setStyle(etStyle.getText().toString().trim());
        activity.setDifficulty(etDifficulty.getText().toString().trim());
        gym.setId(gyms.get(gymSpinner.getSelectedItemPosition()).getId());
        staff.setId(staffs.get(staffSpinner.getSelectedItemPosition()).getId());
        Log.i("gymId", "id" + gym.getId());
        Log.i("staffId", "id" + gym.getId());
        activity.setGym (gym);
        activity.setStaff (staff);
        activity.setActivityImage(Base64.getEncoder().encodeToString(ImageUtils.fromImageViewToByteArray(activityImage)));
        presenter.addOrModifyActivity(activity, modifyActivity);

    }


    @Override
    public void cleanForm() {

        activityImage.setImageResource(R.drawable.activity_default);
        etName.setText("");
        etRoom .setText("");
        etStyle .setText("");
        etDifficulty .setText("");
        tvDate.setText("");

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
            activityImage.setImageBitmap(imageBitmap);
        }
    }






}