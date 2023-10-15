package com.oeztuerk.vehiclemanagementfinal;

import java.util.List;

public interface VehicleDataStore {
    List<Vehicle> getAllVehicles();
    void addVehicle(Vehicle vehicle);
}