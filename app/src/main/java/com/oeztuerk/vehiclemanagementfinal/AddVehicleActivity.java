package com.oeztuerk.vehiclemanagementfinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class AddVehicleActivity extends AppCompatActivity {

    public static final String ACTION_ADD_VEHICLE = "com.oeztuerk.vehiclemanagementfinal.ACTION_ADD_VEHICLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        // Find the views
        final EditText manufacturerEditText = findViewById(R.id.manufacturerEditText);
        final EditText modelNameEditText = findViewById(R.id.modelNameEditText);
        final EditText certificateDateEditText = findViewById(R.id.certificateDateEditText);
        final EditText seatsEditText = findViewById(R.id.seatsEditText);
        final CheckBox electricCheckBox = findViewById(R.id.electricCheckBox);
        Button btnAddVehicle = findViewById(R.id.btnAddVehicle);
        final Spinner spinner = findViewById(R.id.spinnerVehicleType);

        btnAddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get selected vehicle type from Spinner
                String selectedType = spinner.getSelectedItem().toString();

                // Get the manufacturer and model name from EditText fields
                String manufacturer = manufacturerEditText.getText().toString();
                String modelName = modelNameEditText.getText().toString();
                String securityCertificateExpirationDate = certificateDateEditText.getText().toString();

                // Get the number of seats and isElectric values
                int numberOfSeats = 0;
                boolean isElectric = false;

                Intent broadcastIntent = new Intent(AddVehicleActivity.ACTION_ADD_VEHICLE);

                if (selectedType.equals("Car")) {
                    numberOfSeats = Integer.parseInt(seatsEditText.getText().toString());
                    broadcastIntent.putExtra("numberOfSeats", numberOfSeats);
                } else if (selectedType.equals("Motorbike")) {
                    isElectric = electricCheckBox.isChecked();
                    broadcastIntent.putExtra("isElectric", isElectric);
                }


                broadcastIntent.putExtra("vehicleType", selectedType);
                broadcastIntent.putExtra("manufacturer", manufacturer);
                broadcastIntent.putExtra("modelName", modelName);
                broadcastIntent.putExtra("securityCertificateExpirationDate", securityCertificateExpirationDate);
                broadcastIntent.putExtra("numberOfSeats", numberOfSeats);
                broadcastIntent.putExtra("isElectric", isElectric);

                sendBroadcast(broadcastIntent);
                finish();
            }
        });


        // Set a listener for the spinner to show/hide specific input fields
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedType = spinner.getSelectedItem().toString();

                // Show/hide specific input fields based on the selected type
                switch (selectedType) {
                    case "Car":
                        seatsEditText.setVisibility(View.VISIBLE);
                        electricCheckBox.setVisibility(View.GONE);
                        break;
                    case "Motorbike":
                        seatsEditText.setVisibility(View.GONE);
                        electricCheckBox.setVisibility(View.VISIBLE);
                        break;
                    default:
                        seatsEditText.setVisibility(View.GONE);
                        electricCheckBox.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
