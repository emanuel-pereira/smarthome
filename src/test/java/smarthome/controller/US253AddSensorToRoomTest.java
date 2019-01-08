package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class US253AddSensorToRoomTest {
    @DisplayName("Test if SensorType List is showed as a string to the user")
    @Test
    void showSensorTypeListInString() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        House h1 = new House();
        US253AddSensorToRoomCTRL ctr1 = new US253AddSensorToRoomCTRL(sensorTypeList, h1);
        SensorType type1 = new SensorType("Temperature");
        SensorType type2 = new SensorType("Wind");
        sensorTypeList.addSensorType(type1);
        sensorTypeList.addSensorType(type2);
        String expected = "1 - Temperature\n2 - Wind\n";
        String result = ctr1.showDataTypeListInString();
        assertEquals(expected, result);
    }

    @DisplayName("Test if Room List is showed as a string to the user")
    @Test
    void showRoomListInString() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        House h1 = new House();
        List<Room> roomList = h1.getRoomList();
        US253AddSensorToRoomCTRL ctrl = new US253AddSensorToRoomCTRL(sensorTypeList, h1);
        Room r1 = new Room("Living Room", 1, 2, 3, 2);
        Room r2 = new Room("Bed Room", 1, 2, 3, 2);
        roomList.add(r1);
        roomList.add(r2);
        String expected = "1 - Living Room\n2 - Bed Room\n";
        String result = ctrl.showRoomListInString();
        assertEquals(expected, result);
    }

    @DisplayName("Ensure that two different sensors are added to the respective Rooms")
    @Test
    void addNewSensorToRoom() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        House h1 = new House();
        List<Room> roomList = h1.getRoomList();
        US253AddSensorToRoomCTRL ctrl = new US253AddSensorToRoomCTRL(sensorTypeList, h1);
        Room r1 = new Room("Living Room", 1, 2, 3, 2);
        Room r2 = new Room("Bed Room", 1, 2, 3, 2);
        roomList.add(r1);
        roomList.add(r2);
        SensorType type1 = new SensorType("Temperature");
        SensorType type2 = new SensorType("Wind");
        sensorTypeList.addSensorType(type1);
        sensorTypeList.addSensorType(type2);
        Sensor s1 = new Sensor();
        Sensor s2 = new Sensor();
        s1.setRoom(r1);
        s2.setRoom(r2);

        ctrl.addNewSensorToRoom("LivingRoomTempSensor", new GregorianCalendar(2018, 12, 26), 1, 1);
        assertEquals(r1, s1.getRoom());

        ctrl.addNewSensorToRoom("BedRoomWindSensor", new GregorianCalendar(2018, 12, 26), 2, 2);
        assertEquals(r2, s2.getRoom());
    }
}
