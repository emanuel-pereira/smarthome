package smarthome.model.devices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.Device;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WashingMachineTypeTest {

    @Test
    @DisplayName("Get correct device type")
    void getCorrectDeviceType() {
        WashingMachineType type = new WashingMachineType ();
        type.createDevice ("Singer", 200);

        String expected = "Washing Machine";
        String result = type.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get incorrect device type")
    void getIncorrectDeviceType() {
        WashingMachineType type = new WashingMachineType ();
        type.createDevice ("Singer", 200);

        String expected = "WashingMachine";
        String result = type.getDeviceType();

        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Create device with success")
    void createCorrectDevice() {
        WashingMachineType type = new WashingMachineType ();
        Device wM = type.createDevice ("Singer", 200);

        String expected = "Singer";
        String result = wM.getDeviceName();

        assertEquals(expected, result);
    }

}