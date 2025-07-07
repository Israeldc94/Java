package org.example.model;

public class PrivateJet extends Aircraft{
    private boolean hasLuxuryService;
    private int maxSpeed;

    public PrivateJet(String model, int capacity, double fuelCapacity, boolean hasLuxuryService, int maxSpeed) {
        this.model = model;
        this.capacity = capacity;
        this.fuelCapacity = fuelCapacity;
        this.hasLuxuryService = hasLuxuryService;
        this.maxSpeed = maxSpeed;
    }

    public boolean isHasLuxuryService() {
        return hasLuxuryService;
    }

    public void setHasLuxuryService(boolean hasLuxuryService) {
        this.hasLuxuryService = hasLuxuryService;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    @Override
    public String getType() {
        return "Private";
    }
}
