package dev.nau.peaje_vehiculos;

import java.util.ArrayList;
import java.util.List;

public class TollStation {
    private long tollStationId;
    private String name;
    private String city;
    private double totalCollected;
    private List<Vehicle> vehicleList;

    public TollStation(long tollStationId, String name, String city) {
        this.tollStationId = tollStationId;
        this.name = name;
        this.city = city;
        this.totalCollected = 0;
        this.vehicleList = new ArrayList<>();
    }

    public long getTollStationId() {
        return tollStationId;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public double getTotalCollected() {
        return totalCollected;
    }

    public List<Vehicle> getVehicleList() {
        return new ArrayList<>(vehicleList);
    }

    public int calculateTollFee(Vehicle vehicle) {
        if (vehicle.getVehicleType() == null) {
            throw new IllegalArgumentException("Tipo de vehículo no válido.");
        }
    
        switch (vehicle.getVehicleType()) {
            case CAR:
                return 100;
            case MOTORCYCLE:
                return 50;
            case TRUCK:
                return vehicle.getNumberOfAxis() * 50;
            default:
                throw new IllegalArgumentException("Tipo de vehículo no válido.");
        }
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);
        totalCollected += calculateTollFee(vehicle);
    }

    public boolean removeVehicle(String licensePlate) {
        for (Vehicle v : vehicleList) {
            if (v.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                totalCollected -= calculateTollFee(v);
                vehicleList.remove(v);
                return true;
            }
        }
        return false;
    }

    public int countVehicles() {
        return vehicleList.size();
    }

    public boolean hasVehiclePassed(String licensePlate) {
        return vehicleList.stream().anyMatch(v -> v.getLicensePlate().equalsIgnoreCase(licensePlate));
    }

    public void resetTollStation() {
        vehicleList.clear();
        totalCollected = 0;
    }

    public void summary() {
        System.out.println("==================================");
        System.out.printf("Toll Station: %s (%s)%n", name, city);
        System.out.println("----------------------------------");
        System.out.printf("Total collected: %.2f€%n", totalCollected);
        System.out.printf("Total vehicles: %d%n", countVehicles());
        System.out.println("Vehicles:");
        vehicleList.forEach(v -> System.out.printf("- %s (%s)%n", v.getLicensePlate(), v.getVehicleType()));
        System.out.println("==================================");
    }
}
