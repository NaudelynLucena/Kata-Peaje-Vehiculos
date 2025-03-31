package dev.nau.peaje_vehiculos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TollStationTest {

    @Test
    public void testCalculateTollFee() {
        TollStation tollStation = new TollStation(1, "Main Toll", "City");

        Vehicle car = new Vehicle(1, VehicleType.CAR, "ABC123", 0);
        Vehicle motorcycle = new Vehicle(2, VehicleType.MOTORCYCLE, "XYZ789", 0);
        Vehicle truck = new Vehicle(3, VehicleType.TRUCK, "LMN456", 3);

        assertEquals(100, tollStation.calculateTollFee(car));
        assertEquals(50, tollStation.calculateTollFee(motorcycle));
        assertEquals(150, tollStation.calculateTollFee(truck));
    }

    @Test
    public void testCalculateTollFeeInvalidVehicle() {
        TollStation tollStation = new TollStation(1, "Main Toll", "City");
        Vehicle invalidVehicle = new Vehicle(4, null, "NOP789", 0);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            tollStation.calculateTollFee(invalidVehicle)
        );
        assertEquals("Tipo de vehículo no válido.", exception.getMessage());
    }

    @Test
    public void testAddVehicleAndCountVehicles() {
        TollStation tollStation = new TollStation(1, "Main Toll", "City");

        Vehicle car = new Vehicle(1, VehicleType.CAR, "ABC123", 0);
        Vehicle truck = new Vehicle(2, VehicleType.TRUCK, "DEF456", 4);

        tollStation.addVehicle(car);
        tollStation.addVehicle(truck);

        assertEquals(2, tollStation.countVehicles());
        assertEquals(100 + 200, tollStation.getTotalCollected());
    }

    @Test
    public void testHasVehiclePassed() {
        TollStation tollStation = new TollStation(1, "Main Toll", "City");

        Vehicle car = new Vehicle(1, VehicleType.CAR, "ABC123", 0);
        tollStation.addVehicle(car);

        assertTrue(tollStation.hasVehiclePassed("ABC123"));
        assertFalse(tollStation.hasVehiclePassed("XYZ789"));
    }

    @Test
    public void testRemoveVehicle() {
        TollStation tollStation = new TollStation(1, "Main Toll", "City");

        Vehicle truck = new Vehicle(2, VehicleType.TRUCK, "LMN456", 4);
        tollStation.addVehicle(truck);
        assertEquals(200, tollStation.getTotalCollected());

        assertTrue(tollStation.removeVehicle("LMN456"));
        assertEquals(0, tollStation.getTotalCollected());
        assertFalse(tollStation.hasVehiclePassed("LMN456"));
    }

    @Test
    public void testResetTollStation() {
        TollStation tollStation = new TollStation(1, "Main Toll", "City");

        Vehicle car = new Vehicle(1, VehicleType.CAR, "ABC123", 0);
        tollStation.addVehicle(car);

        assertEquals(1, tollStation.countVehicles());
        assertEquals(100, tollStation.getTotalCollected());

        tollStation.resetTollStation();

        assertEquals(0, tollStation.countVehicles());
        assertEquals(0, tollStation.getTotalCollected());
    }

    @Test
    public void testInvalidVehicleCreation() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Vehicle(3, VehicleType.TRUCK, "JKL789", 1));
        assertEquals("Un camión debe tener al menos 2 ejes.", exception.getMessage());
    }

    @Test
    public void testInvalidLicensePlate() {
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> 
            new Vehicle(5, VehicleType.CAR, "", 0)
        );
        assertEquals("La matrícula no puede estar vacía.", exception1.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> 
            new Vehicle(6, VehicleType.TRUCK, null, 4)
        );
        assertEquals("La matrícula no puede estar vacía.", exception2.getMessage());
    }
}
