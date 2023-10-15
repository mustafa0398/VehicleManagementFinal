package com.oeztuerk.vehiclemanagementfinal;

import static com.oeztuerk.vehiclemanagementfinal.AddVehicleActivity.ACTION_ADD_VEHICLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VehicleAdapter vehicleAdapter;
    private VehicleDataStore dataStore;

    private static final int CAMERA_LOCATION_PERMISSION_REQUEST = 123;

    private void checkPermissions() {
        int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (cameraPermission == PackageManager.PERMISSION_GRANTED && locationPermission == PackageManager.PERMISSION_GRANTED) {

        } else {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION}, CAMERA_LOCATION_PERMISSION_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_LOCATION_PERMISSION_REQUEST) {
            if (permissions.length == 2 &&
                    permissions[0].equals(Manifest.permission.CAMERA) &&
                    permissions[1].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {

            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) ||
                        ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                    Toast.makeText(this, "You need to grant camera and location permissions to use this feature.", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(this, "Permissions have been denied multiple times. Go to app settings to grant permissions.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_check_permissions) {
            checkPermissions();
            return true;
        } else if (itemId == R.id.menu_add_vehicle) {
            startActivity(new Intent(this, AddVehicleActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String type = intent.getStringExtra("vehicleType");
            String manufacturer = intent.getStringExtra("manufacturer");
            String modelName = intent.getStringExtra("modelName");
            String securityCertificateExpirationDate = intent.getStringExtra("securityCertificateExpirationDate");

            Vehicle newVehicle;
            if (type.equals("Car")) {
                int numberOfSeats = intent.getIntExtra("numberOfSeats", 0);
                newVehicle = new Car(manufacturer, modelName, securityCertificateExpirationDate, numberOfSeats);
            } else {
                boolean isElectric = intent.getBooleanExtra("isElectric", false);
                newVehicle = new Motorbike(manufacturer, modelName, securityCertificateExpirationDate, isElectric);
            }

            vehicleAdapter.addVehicle(newVehicle);
            vehicleAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_ADD_VEHICLE);
        registerReceiver(broadcastReceiver, intentFilter);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataStore = new VehicleDataStoreImplementation();
        List<Vehicle> vehicleList = dataStore.getAllVehicles();

        vehicleAdapter = new VehicleAdapter(vehicleList);
        recyclerView.setAdapter(vehicleAdapter);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}

