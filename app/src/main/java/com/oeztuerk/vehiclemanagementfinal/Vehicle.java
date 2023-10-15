package com.oeztuerk.vehiclemanagementfinal;

public abstract class Vehicle {

    private String manufacturer;
    private String modelName;
    private String securityCertificateExpirationDate;

    public Vehicle(String manufacturer, String modelName, String securityCertificateExpirationDate) {
        this.manufacturer = manufacturer;
        this.modelName = modelName;
        this.securityCertificateExpirationDate = securityCertificateExpirationDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getSecurityCertificateExpirationDate() {
        return securityCertificateExpirationDate;
    }

    public void setSecurityCertificateExpirationDate(String securityCertificateExpirationDate) {
        this.securityCertificateExpirationDate = securityCertificateExpirationDate;
    }

    public abstract String getAdditionalInfo();

}
