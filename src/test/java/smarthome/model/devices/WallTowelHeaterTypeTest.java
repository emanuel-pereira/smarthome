package smarthome.model.devices;

import org.junit.jupiter.api.Test;
import smarthome.model.Device;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WallTowelHeaterTypeTest {

    @Test
    void getDeviceType() {
        WallTowelHeaterType type = new WallTowelHeaterType();

        String expected = "WallTowelHeater";
        String result = type.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    void createDevice() {
        WallTowelHeaterType type = new WallTowelHeaterType();
        type.createDevice("warm towels", 555);
        String result = type.getDeviceType();

        assertEquals("WallTowelHeater", result);
    }

    @Test
    void createDeviceNotValid() {
        WallTowelHeaterType type = new WallTowelHeaterType();

        Device device = type.createDevice("132.5",555);
        String result = device.getDeviceType();

        assertEquals("WallTowelHeater",result);
    }
}