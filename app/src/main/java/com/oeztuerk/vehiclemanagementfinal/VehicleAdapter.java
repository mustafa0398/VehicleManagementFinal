package com.oeztuerk.vehiclemanagementfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {
    private List<Vehicle> vehicleList;

    public VehicleAdapter(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        Vehicle vehicle = vehicleList.get(position);
        holder.manufacturerTextView.setText(vehicle.getManufacturer());
        holder.modelNameTextView.setText(vehicle.getModelName());
        holder.expirationDateTextView.setText("Expiry Date: " + vehicle.getSecurityCertificateExpirationDate());
        holder.additionalInfoTextView.setText(vehicle.getAdditionalInfo());
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    static class VehicleViewHolder extends RecyclerView.ViewHolder {
        TextView manufacturerTextView;
        TextView modelNameTextView;
        TextView expirationDateTextView;
        TextView additionalInfoTextView;

        public VehicleViewHolder(@NonNull View itemView) {
            super(itemView);
            manufacturerTextView = itemView.findViewById(R.id.manufacturerTextView);
            modelNameTextView = itemView.findViewById(R.id.modelNameTextView);
            expirationDateTextView = itemView.findViewById(R.id.expirationDateTextView);
            additionalInfoTextView = itemView.findViewById(R.id.additionalInfoTextView);
        }
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }
}

