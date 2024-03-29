package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeviceListTest {

    @Test
    @DisplayName("Create and add new devices and get all correct")
    void newDevice() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DeviceList deviceList = new DeviceList ();
        Device kettle1 = deviceList.newDevice ("Kettle1", "Kettle", 200);
        Device kettle2 = deviceList.newDevice ("Kettle2", "Kettle", 210);
        deviceList.add(kettle1);
        deviceList.add(kettle2);

        List<Device> expected = Arrays.asList (kettle1, kettle2);
        List<Device> result = deviceList.getDeviceList ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Create and fail to add the same device")
    void newDeviceFalse() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DeviceList deviceList = new DeviceList ();
        Device kettle1 = deviceList.newDevice ("Kettle1", "Kettle", 200);
        deviceList.add(kettle1);
        assertFalse(deviceList.add(kettle1));
    }

    @Test
    @DisplayName("Create and add new devices and get one correctly")
    void get() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DeviceList deviceList = new DeviceList ();
        Device lamp = deviceList.newDevice ("Lamp", "Lamp", 20);
        Device kettle = deviceList.newDevice ("Kettle", "Kettle", 120);
        deviceList.add(lamp);
        deviceList.add(kettle);

        Device expected = lamp;
        Device result = deviceList.get (0);

        assertEquals (expected, result);
    }


    @Test
    @DisplayName("Create and add new devices and get a string with the information of the device list")
    void showDeviceListInString() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DeviceList deviceList = new DeviceList ();
        Device lamp = deviceList.newDevice ("Lamp XBZT1000", "Lamp", 20);
        Device kettle = deviceList.newDevice ("Pretty White Kettle", "Kettle", 120);
        deviceList.add(lamp);
        deviceList.add(kettle);

        List<String> expected = new ArrayList<>();
        expected.add("Lamp (Lamp XBZT1000) [Active]");
        expected.add("Kettle (Pretty White Kettle) [Active]");

        List<String> result = deviceList.showDeviceListInString ();

        assertEquals (expected, result);
    }


    @Test
    @DisplayName("Create and add new devices and remove one correctly")
    void removeDevice() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DeviceList deviceList = new DeviceList ();
        Device lamp = deviceList.newDevice ("Lamp", "Lamp", 20);
        Device kettle1 = deviceList.newDevice ("Kettle1", "Kettle", 200);
        Device kettle2 = deviceList.newDevice ("Kettle2", "Kettle", 210);        deviceList.add(lamp);
        deviceList.add(lamp);
        deviceList.add(kettle1);
        deviceList.add(kettle2);

        int expected1 = 3;
        int result1 = deviceList.size ();

        assertEquals (expected1, result1);

        deviceList.removeDevice (lamp);

        int expected2 = 2;
        int result2 = deviceList.size ();

        assertEquals (expected2, result2);
    }


    @Test
    @DisplayName("Create and add new devices and deactivate one correctly")
    void deactivateDevice() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DeviceList deviceList = new DeviceList ();
        Device lamp = deviceList.newDevice ("Lamp", "Lamp", 20);
        Device kettle = deviceList.newDevice ("Kettle", "Kettle", 120);
        deviceList.add(lamp);
        deviceList.add(kettle);
        deviceList.deactivateDevice (kettle);

        boolean result = kettle.isActive ();

        assertFalse (result);

        assertTrue(lamp.isActive ());

        assertEquals (2,deviceList.size ());
    }

    @Test
    @DisplayName("Create and add new metered devices and get list correctly")
    void getMeteredDevices() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DeviceList deviceList = new DeviceList ();
        Device lamp = deviceList.newDevice ("Lamp", "Lamp", 20);
        Device kettle = deviceList.newDevice ("Kettle", "Kettle", 120);
        deviceList.add(lamp);
        deviceList.add(kettle);

        List<Metered> expected = Arrays.asList ((Metered) lamp, (Metered) kettle);
        List<Metered> result = deviceList.getMeteredDevices ();

        assertEquals (expected, result);
    }
}