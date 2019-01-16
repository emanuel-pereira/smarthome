package smarthome.controller;

import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US230TotalNominalPowerRoomCTRLTest {

    @Test
    void getRoomListCtrlTest() {
        House house = new House();
        US230TotalNominalPowerRoomCTRL ctrl230 = new US230TotalNominalPowerRoomCTRL(house);
        Room r1 = new Room("cozinha",1,10,20,3);
        Room r2 = new Room("sala",1,10,20,3);
        house.getRoomListFromHouse().addRoom(r1);
        house.getRoomListFromHouse().addRoom(r2);

        List<Room> expectedResult = Arrays.asList(r1,r2);
        List<Room> result = ctrl230.getRoomListCtrl();

        assertEquals(expectedResult,result);
    }

    @Test
    void showListRoomInStringTest() {
        House house = new House();
        US230TotalNominalPowerRoomCTRL ctrl230 = new US230TotalNominalPowerRoomCTRL(house);
        Room r1 = new Room("cozinha",1,10,20,3);
        Room r2 = new Room("sala",1,10,20,3);
        house.getRoomListFromHouse().addRoom(r1);
        house.getRoomListFromHouse().addRoom(r2);

        String expectedResult = "1 - cozinha\n2 - sala\n";
        String result = ctrl230.showListRoomInString();

        assertEquals(expectedResult,result);
    }

    @Test
    void getNominalPowerRoomCtrlTest () {
        House house = new House();
        US230TotalNominalPowerRoomCTRL ctrl230 = new US230TotalNominalPowerRoomCTRL(house);
        Room r1 = new Room("cozinha",1,10,20,3);
        Room r2 = new Room("sala",1,10,20,3);
        house.getRoomListFromHouse().addRoom(r1);
        house.getRoomListFromHouse().addRoom(r2);

        Device d1 = new Device("tv",r2,"Television",200);
        Device d2 = new Device("fridge",r1,"Fridge",300);
        Device d3 = new Device("stove",r1,"Stove",500);
        Device d4 = new Device("lamp",r2,"Lamp",100);
        house.getRoomListFromHouse().get(0).getDeviceList().addDevice(d2);
        house.getRoomListFromHouse().get(0).getDeviceList().addDevice(d3);
        house.getRoomListFromHouse().get(1).getDeviceList().addDevice(d1);
        house.getRoomListFromHouse().get(1).getDeviceList().addDevice(d4);

        double expectedResult = 300;
        double result = ctrl230.getNominalPowerRoomCtrl(2);

        assertEquals(expectedResult,result);
    }
}