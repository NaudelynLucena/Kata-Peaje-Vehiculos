package dev.nau.peaje_vehiculos;

public class Vehicle {
    private long vehicleId;
    private VehicleType vehicleType;
    private String licensePlate;
    private int numberOfAxis;

    public Vehicle(long vehicleId, VehicleType vehicleType, String licensePlate, int numberOfAxis) {
        if (licensePlate == null || licensePlate.trim().isEmpty()) {
            throw new IllegalArgumentException("La matrícula no puede estar vacía.");
        }
        if (vehicleType == VehicleType.TRUCK && numberOfAxis < 2) {
            throw new IllegalArgumentException("Un camión debe tener al menos 2 ejes.");
        }
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.numberOfAxis = (vehicleType == VehicleType.TRUCK) ? numberOfAxis : 0;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getNumberOfAxis() {
        return numberOfAxis;
    }
}
