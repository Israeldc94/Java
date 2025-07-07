package org.example.model;

public class CommercialAircraft extends Aircraft{
    private String airlineName;

    public CommercialAircraft(String model, int capacity, double fuelCapacity, String airlineName){
        this.model = model;
        this.capacity = capacity;
        this.fuelCapacity = fuelCapacity;
        this.airlineName = airlineName;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
    @Override
    public String getType() {
        return "Commercial";
    }
}
