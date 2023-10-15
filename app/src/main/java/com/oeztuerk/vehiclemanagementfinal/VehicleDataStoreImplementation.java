package com.oeztuerk.vehiclemanagementfinal;

import java.util.ArrayList;
import java.util.List;

public class VehicleDataStoreImplementation implements VehicleDataStore{
    private List<Vehicle> vehicleList;

    public VehicleDataStoreImplementation() {
        // Initialize the list of vehicles in the constructor
        vehicleList = new ArrayList<>();

        // Add sample vehicles for demonstration
        vehicleList.add(new Car("Toyota", "Camry", "2023-12-31", 5));
        vehicleList.add(new Motorbike("Harley-Davidson", "Sportster", "2024-06-15", false));
    }
    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleList;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }
}
