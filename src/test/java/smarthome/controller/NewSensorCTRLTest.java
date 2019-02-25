
package smarthome.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NewSensorCTRLTest {


    @DisplayName("Test if SensorTypeList is showed as a string to the user")
    @Test
    void showSensorTypeListInString() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        House house = new House();
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        SensorType type1 = new SensorType("Temperature");
        SensorType type2 = new SensorType("Wind");
        sensorTypeList.addSensorType(type1);
        sensorTypeList.addSensorType(type2);
        String expected = "1 - Temperature\n2 - Wind\n";
        String result = ctrl.showSensorTypeListInString();
        assertEquals(expected, result);

    }

    @DisplayName("Test if Geographical Area List is showed as a string to the user")
    @Test
    void showGAListInString() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        House house = new House();
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 25, 15, 12, 32, 41);
        GeographicalArea ga2 = new GeographicalArea("Lis", "Lisboa", "city", 45, 25, 32, 42, 41);
        gaList.addGA(ga1);
        gaList.addGA(ga2);
        String expected = "1 - Porto\n2 - Lisboa\n";
        String result = ctrl.showGAListInString();
        assertEquals(expected, result);
    }

    @Test
    void getGAList() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        House house = new House();
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        OccupationArea occupationArea = new OccupationArea(20, 20);
        Location location = new Location(1, 3, -10);
        GeographicalArea area1 = gaList.newGA("Pt", "Porto", "district", occupationArea, location);
        GeographicalArea area2 = gaList.newGA("Pt", "Braga", "district", occupationArea, location);
        gaList.addGA(area1);
        gaList.addGA(area2);

        List<GeographicalArea> expectedResult = Arrays.asList(area1, area2);
        List<GeographicalArea> result = ctrl.getGAList();
        assertEquals(expectedResult, result);
    }

    @DisplayName("Ensure that two different sensors are added to the respective Geographical Area")
    @Test
    void addNewSensorToGA() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        House house = new House();
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 25, 15, 12, 32, 41);
        GeographicalArea ga2 = new GeographicalArea("Lis", "Lisboa", "city", 45, 25, 32, 42, 41);

        gaList.addGA(ga1);
        gaList.addGA(ga2);

        SensorType type1 = new SensorType("Temperature");
        SensorType type2 = new SensorType("Wind");
        sensorTypeList.addSensorType(type1);
        sensorTypeList.addSensorType(type2);

        Reading r1Porto = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        Reading r2Porto = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 00));
        ReadingList readingsPt = new ReadingList();
        readingsPt.addReading(r1Porto);
        readingsPt.addReading(r2Porto);

        Reading r1Lis = new Reading(27, new GregorianCalendar(2018, 12, 26, 12, 00));
        Reading r2Lis = new Reading(21, new GregorianCalendar(2018, 12, 26, 13, 00));
        ReadingList readingsLis = new ReadingList();
        readingsLis.addReading(r1Lis);
        readingsLis.addReading(r2Lis);
        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 26);
        Location loc = new Location(55, 40, 15);
        boolean result = ctrl.addNewSensorToGA("LisboaTempSensor", startDate, 0, "C", loc, 0, readingsPt);
        assertTrue(result);

        boolean result1 = ctrl.addNewSensorToGA("PortoWindSensor", startDate, 1, "km/h", loc, 1, readingsLis);
        assertTrue(result1);
    }

    @DisplayName("Check if throws exception when the latitude is wrong")
    @Test
    public void throwsIllegalArgumentExceptionForLatitude() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        ReadingList readingsPt = new ReadingList();

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 25, 15, 12, 32, 41);
        gaList.addGA(ga1);

        SensorType type1 = new SensorType("Temperature");
        sensorTypeList.addSensorType(type1);

        Reading r1Porto = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        readingsPt.addReading(r1Porto);
        House house = new House();
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);

        boolean thrown = false;

        try {
            GregorianCalendar startDate = new GregorianCalendar(2018, 12, 26);
            Location loc = new Location(900, 80, 15);
            ctrl.addNewSensorToGA("LisboaTempSensor", startDate, 1, "C", loc, 1, readingsPt);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @DisplayName("Check if throws exception when the longitude is wrong")
    @Test
    public void throwsIllegalArgumentExceptionForLongitude() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        ReadingList readingsPt = new ReadingList();

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 25, 15, 12, 32, 41);
        gaList.addGA(ga1);

        SensorType type1 = new SensorType("Temperature");
        sensorTypeList.addSensorType(type1);

        Reading r1Porto = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        readingsPt.addReading(r1Porto);

        House house = new House();
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);

        boolean thrown = false;

        try {
            GregorianCalendar startDate = new GregorianCalendar(2018, 12, 26);
            Location loc = new Location(90, 190, 1500);
            ctrl.addNewSensorToGA("LisboaTempSensor", startDate, 1, "C", loc, 1, readingsPt);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @DisplayName("Check if throws exception when the altitude is out of bounds defined in ")
    @Test
    public void throwsIllegalArgumentExceptionForAltitude() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        ReadingList readingsPt = new ReadingList();

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", 25, 15, 12, 32, 41);
        gaList.addGA(ga1);

        SensorType type1 = new SensorType("Temperature");
        sensorTypeList.addSensorType(type1);

        Reading r1Porto = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        readingsPt.addReading(r1Porto);

        House house = new House();
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);

        boolean thrown = false;

        try {
            GregorianCalendar startDate = new GregorianCalendar(2018, 12, 26);
            Location loc = new Location(90, 190, 9000);
            ctrl.addNewSensorToGA("LisboaTempSensor", startDate, 1, "C", loc, 1, readingsPt);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }


    @Test
    @DisplayName("Test if GPS coordinates validation methods return true when GPS coordinates are within defined range")
    void testIfGPSCoordinatesAreValid() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature = sensorTypeList.newSensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        OccupationArea occupationArea = new OccupationArea(25, 12);
        Location location = new Location(23, 12, 11);
        GeographicalArea porto = gaList.newGA("PT", "Porto", "City", occupationArea, location);
        gaList.addGA(porto);
        House house = new House();
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        GregorianCalendar startDate = new GregorianCalendar(2020, 11, 1);
        Location loc = new Location(25, 28, 11);
        Sensor sensor = new Sensor("Name", startDate, loc, temperature, "Celsius", new ReadingList());

        boolean expected = true;
        boolean result = ctrl.latitudeIsValid(sensor.getLocation().getLatitude());
        assertEquals(expected, result);

        boolean expected1 = true;
        boolean result1 = ctrl.longitudeIsValid(sensor.getLocation().getLongitude());
        assertEquals(expected1, result1);

        boolean expected2 = true;
        boolean result2 = ctrl.altitudeIsValid(sensor.getLocation().getAltitude());
        assertEquals(expected2, result2);

    }

    @Test
    @DisplayName("Ensure that nameIsValid returns true to valid string")
    void nameIsValid() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature = sensorTypeList.newSensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        OccupationArea occupationArea = new OccupationArea(25, 12);
        Location location = new Location(23, 12, 11);
        GeographicalArea porto = gaList.newGA("PT", "Porto", "City", occupationArea, location);
        gaList.addGA(porto);
        House house = new House();
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        String inputName = "Sensor - ISEP";

        boolean result = ctrl.nameIsValid(inputName);

        assertTrue(result);

    }

    @Test
    @DisplayName("Ensure that the size of the geographical area list is 2")
    void getGAListSize() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        OccupationArea occupationArea = new OccupationArea(25, 12);
        Location location = new Location(23, 12, 11);
        GeographicalArea porto = gaList.newGA("POR", "Porto", "City", occupationArea, location);
        gaList.addGA(porto);
        OccupationArea occupationArea2 = new OccupationArea(12, 11);
        Location location2 = new Location(45, -15, 23);
        GeographicalArea lisboa = gaList.newGA("LIS", "Lisboa", "City", occupationArea2, location2);
        gaList.addGA(lisboa);
        House house = new House();
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        int expected = 2;
        int result = ctrl.getGAListSize();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that the size of the sensor type list is 2")
    void getSensorTypeListSize() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature = sensorTypeList.newSensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        SensorType precipitation = sensorTypeList.newSensorType("precipitation");
        sensorTypeList.addSensorType(precipitation);
        GAList gaList = new GAList();
        House house = new House();
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        int expected = 2;
        int result = ctrl.getSensorTypeListSize();
        assertEquals(expected, result);

    }


    @Test
    @DisplayName("Ensure that the size of the room list is 2")
    void getRoomListSize() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        House house = new House();
        RoomList roomList = house.getRoomList();
        Room kitchen = new Room("Kitchen", 0, 4, 5, 3);
        roomList.addRoom(kitchen);
        Room livingRoom = new Room("Living Room", 0, 6, 5, 3);
        roomList.addRoom(livingRoom);
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        int expected=2;
        int result= ctrl.getRoomListSize();
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that all rooms are displayed in string")

    void showRoomListInStr() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        House house = new House();
        RoomList roomList = house.getRoomList();
        Room kitchen = new Room("Kitchen", 0, 4, 5, 3);
        roomList.addRoom(kitchen);
        Room livingRoom = new Room("Living Room", 0, 6, 5, 3);
        roomList.addRoom(livingRoom);
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        String expected="1 - Kitchen\n" +
                "2 - Living Room\n";
        String result= ctrl.showRoomListInStr();
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure getSensorType method returns temperature as it is the sensor type of the sensor in the last index position of the sensorList of geographical area Lisboa")
    void getSensorType() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature = sensorTypeList.newSensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        OccupationArea occupationArea = new OccupationArea(12, 11);
        Location location = new Location(25, 12, 23);
        GeographicalArea porto = gaList.newGA("POR", "Porto", "City", occupationArea, location);
        gaList.addGA(porto);
        GeographicalArea lisboa = gaList.newGA("LIS", "Lisboa", "City", occupationArea, location);
        gaList.addGA(lisboa);
        ReadingList readingList = new ReadingList();
        GregorianCalendar startDate = new GregorianCalendar(2019, 2, 15);
        Location loc1 = new Location(25, -15, 5);
        Sensor s1 = new Sensor("LisboaTempSensor1", startDate, loc1, temperature, "Cº", readingList);
        Location loc2 = new Location(27, -14, 6);
        Sensor s2 = new Sensor("LisboaTempSensor2", startDate, loc2, temperature, "Cº", readingList);
        lisboa.getSensorListInGA().addSensor(s1);
        lisboa.getSensorListInGA().addSensor(s2);
        House house = new House();
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);

        String expected = "temperature";
        String result = ctrl.getGASensorType(1);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure getGAName() returns name Aveiro as it is the name of the geographical area in the selected index position of the geographical area list.")
    void getGAName() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature = sensorTypeList.newSensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        OccupationArea occupationArea = new OccupationArea(12, 11);
        Location location = new Location(25, 12, 23);
        GeographicalArea porto = gaList.newGA("POR", "Porto", "City", occupationArea, location);
        gaList.addGA(porto);
        Location location2 = new Location(45, -15, 23);
        GeographicalArea lisboa = gaList.newGA("LIS", "Lisboa", "City", occupationArea, location2);
        gaList.addGA(lisboa);
        Location location3 = new Location(29, 7, 2);
        GeographicalArea aveiro = gaList.newGA("AVR", "Aveiro", "City", occupationArea, location3);
        gaList.addGA(aveiro);
        House house = new House();
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);

        String expected = "Aveiro";
        String result = ctrl.getGAName(2);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure getGASensorName() returns the the sensor's name")
    void getSensorName() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature = sensorTypeList.newSensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        OccupationArea occupationArea = new OccupationArea(12, 11);
        Location location = new Location(25, 12, 23);
        GeographicalArea porto = gaList.newGA("POR", "Porto", "City", occupationArea, location);
        gaList.addGA(porto);
        House house = new House();
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        SensorList sensorList = new SensorList();
        GregorianCalendar startDate = new GregorianCalendar(2019, 2, 15);
        Location location2 = new Location(23, -15, 12);
        ReadingList readingList = new ReadingList();
        Sensor s1 = sensorList.newSensor("Temperature sensor 1", startDate, location2, temperature, "Celsius", readingList);
        SensorList portoSensorList = porto.getSensorListInGA();
        portoSensorList.addSensor(s1);
        String expected = "Temperature sensor 1";
        String result = ctrl.getGASensorName(0);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure getGASensorSDate() returns the the sensor's start date")
    void getStartDate() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature = sensorTypeList.newSensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        OccupationArea occupationArea = new OccupationArea(12, 11);
        Location location = new Location(25, 12, 23);
        GeographicalArea porto = gaList.newGA("POR", "Porto", "City", occupationArea, location);
        gaList.addGA(porto);
        House house = new House();
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        SensorList sensorList = new SensorList();
        GregorianCalendar startDate = new GregorianCalendar(2019, 2, 15);
        Location location2 = new Location(23, -15, 12);
        ReadingList readingList = new ReadingList();
        Sensor s1 = sensorList.newSensor("Temperature sensor 1", startDate, location2, temperature, "Celsius", readingList);
        SensorList portoSensorList = porto.getSensorListInGA();
        portoSensorList.addSensor(s1);
        Calendar result = ctrl.getGASensorSDate(0);
        assertEquals(startDate, result);
    }
    @Test
    @DisplayName("Ensure getRoomSensorSDate() returns the the sensor's start date")
    void getRoomSensorSDate() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature = sensorTypeList.newSensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        House house = new House();
        RoomList roomList = house.getRoomList();
        Room kitchen = new Room("Kitchen", 0, 4, 5, 3);
        roomList.addRoom(kitchen);
        Room livingRoom = new Room("Living Room", 0, 6, 5, 3);
        roomList.addRoom(livingRoom);
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        SensorList sensorList = kitchen.getSensorListInRoom();
        GregorianCalendar startDate = new GregorianCalendar(2019, 2, 15);
        Location location2 = new Location(23, -15, 12);
        ReadingList readingList = new ReadingList();
        Sensor s1 = sensorList.newSensor("Temperature sensor 1", startDate, location2, temperature, "Celsius", readingList);
        sensorList.addSensor(s1);
        Calendar result=ctrl.getRoomSensorSDate(0);
        assertEquals(startDate,result);

    }
    @Test
    @DisplayName("Ensure getGASensorUnit() returns the the sensor's unit of measure")
    void getUnit() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature = sensorTypeList.newSensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        OccupationArea occupationArea = new OccupationArea(12, 11);
        Location location = new Location(25, 12, 23);
        GeographicalArea porto = gaList.newGA("POR", "Porto", "City", occupationArea, location);
        gaList.addGA(porto);
        House house = new House();
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        SensorList sensorList = new SensorList();
        GregorianCalendar startDate = new GregorianCalendar(2019, 2, 15);
        Location location2 = new Location(23, -15, 12);
        ReadingList readingList = new ReadingList();
        Sensor s1 = sensorList.newSensor("Temperature sensor 1", startDate, location2, temperature, "Celsius", readingList);
        SensorList portoSensorList = porto.getSensorListInGA();
        portoSensorList.addSensor(s1);
        String expected = "Celsius";
        String result = ctrl.getGASensorUnit(0);
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Ensure getRoomSensorType() returns the the sensor's type")
    void getRoomSensorType() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature= new SensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        House house = new House();
        RoomList roomList = house.getRoomList();
        Room kitchen = new Room("Kitchen", 0, 4, 5, 3);
        roomList.addRoom(kitchen);
        Room livingRoom = new Room("Living Room", 0, 6, 5, 3);
        roomList.addRoom(livingRoom);
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        Calendar startDate= new GregorianCalendar(2018,11,15);
        Location loc= new Location(12,-15,2);
        ReadingList rL= new ReadingList();
        Sensor sensor= new Sensor("Temperature Sensor",startDate,loc,temperature,"celsius",rL);
        SensorList kitSensorLst=kitchen.getSensorListInRoom();
        kitSensorLst.addSensor(sensor);
        String expected="temperature";
        String result=ctrl.getRoomSensorType(0);
        assertEquals(expected,result);
    }


    @Test
    @DisplayName("Ensure getRoomName() returns the room's name where the sensor is installed")
    void getRoomName() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature= new SensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        House house = new House();
        RoomList roomList = house.getRoomList();
        Room kitchen = new Room("Kitchen", 0, 4, 5, 3);
        roomList.addRoom(kitchen);
        Room livingRoom = new Room("Living Room", 0, 6, 5, 3);
        roomList.addRoom(livingRoom);
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        Calendar startDate= new GregorianCalendar(2018,11,15);
        Location loc= new Location(12,-15,2);
        ReadingList rL= new ReadingList();
        Sensor sensor= new Sensor("Temperature Sensor",startDate,loc,temperature,"celsius",rL);
        SensorList kitSensorLst=kitchen.getSensorListInRoom();
        kitSensorLst.addSensor(sensor);
        String expected="Kitchen";
        String result=ctrl.getRoomName(0);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure getInternalSensorName() returns the sensor's name")
    void getInternalSensorName() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature= new SensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        House house = new House();
        RoomList roomList = house.getRoomList();
        Room kitchen = new Room("Kitchen", 0, 4, 5, 3);
        roomList.addRoom(kitchen);
        Room livingRoom = new Room("Living Room", 0, 6, 5, 3);
        roomList.addRoom(livingRoom);
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        Calendar startDate= new GregorianCalendar(2018,11,15);
        Location loc= new Location(12,-15,2);
        ReadingList rL= new ReadingList();
        Sensor sensor= new Sensor("Temperature Sensor",startDate,loc,temperature,"celsius",rL);
        SensorList kitSensorLst=kitchen.getSensorListInRoom();
        kitSensorLst.addSensor(sensor);
        String expected="Temperature Sensor";
        String result=ctrl.getInternalSensorName(0);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure getRoomSensorUnit() returns the sensor's unit of measure")
    void getRoomSensorUnit() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature= new SensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        House house = new House();
        RoomList roomList = house.getRoomList();
        Room kitchen = new Room("Kitchen", 0, 4, 5, 3);
        roomList.addRoom(kitchen);
        Room livingRoom = new Room("Living Room", 0, 6, 5, 3);
        roomList.addRoom(livingRoom);
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        Calendar startDate= new GregorianCalendar(2018,11,15);
        Location loc= new Location(12,-15,2);
        ReadingList rL= new ReadingList();
        Sensor sensor= new Sensor("Temperature Sensor",startDate,loc,temperature,"celsius",rL);
        SensorList kitSensorLst=kitchen.getSensorListInRoom();
        kitSensorLst.addSensor(sensor);
        String expected="celsius";
        String result=ctrl.getRoomSensorUnit(0);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure same sensor is created and added only once to the selected sensor list when trying to add it twice")
    void addNewSensorToRoom() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature= new SensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        GAList gaList = new GAList();
        House house = new House();
        RoomList roomList = house.getRoomList();
        Room kitchen = new Room("Kitchen", 0, 4, 5, 3);
        roomList.addRoom(kitchen);
        Room livingRoom = new Room("Living Room", 0, 6, 5, 3);
        roomList.addRoom(livingRoom);
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        GregorianCalendar startDate= new GregorianCalendar(2018,11,15);
        Location loc= new Location(12,-15,2);
        ReadingList rL= new ReadingList();
        SensorList kitSensorLst=kitchen.getSensorListInRoom();
        boolean result =ctrl.addNewSensorToRoom("Temperature Sensor",startDate,0,0,"celsius",rL);
        assertTrue(result);

        boolean result1 =ctrl.addNewSensorToRoom("Temperature Sensor",startDate,0,0,"celsius",rL);
        assertFalse(result1);
    }

    @Test
    @DisplayName("Ensure sensor list of living room has a size of two elements")
    void sensorListInRoomSize() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature= new SensorType("temperature");
        SensorType humidity= new SensorType("humidity");
        sensorTypeList.addSensorType(temperature);
        sensorTypeList.addSensorType(humidity);
        GAList gaList = new GAList();
        House house = new House();
        RoomList roomList = house.getRoomList();
        Room kitchen = new Room("Kitchen", 0, 4, 5, 3);
        roomList.addRoom(kitchen);
        Room livingRoom = new Room("Living Room", 0, 6, 5, 3);
        roomList.addRoom(livingRoom);
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        Calendar startDate= new GregorianCalendar(2018,11,15);
        Location loc= new Location(12,-15,2);
        ReadingList rL= new ReadingList();
        Sensor tempSensor= new Sensor("Temperature Sensor",startDate,loc,temperature,"celsius",rL);
        Sensor humiditySensor= new Sensor("Humidity Sensor",startDate,loc,temperature,"%",rL);

        SensorList lrSensorList=livingRoom.getSensorListInRoom();
        lrSensorList.addSensor(tempSensor);
        lrSensorList.addSensor(humiditySensor);

        int expected=2;
        int result=ctrl.sensorListInRoomSize(1);

        assertEquals(expected,result);
    }

    @Test
    void showSensorListInRoom() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType temperature= new SensorType("temperature");
        SensorType humidity= new SensorType("humidity");
        sensorTypeList.addSensorType(temperature);
        sensorTypeList.addSensorType(humidity);
        GAList gaList = new GAList();
        House house = new House();
        RoomList roomList = house.getRoomList();
        Room kitchen = new Room("Kitchen", 0, 4, 5, 3);
        roomList.addRoom(kitchen);
        Room livingRoom = new Room("Living Room", 0, 6, 5, 3);
        roomList.addRoom(livingRoom);
        NewSensorCTRL ctrl = new NewSensorCTRL(house, sensorTypeList, gaList);
        Calendar startDate= new GregorianCalendar(2018,11,15);
        Location loc= new Location(12,-15,2);
        ReadingList rL= new ReadingList();
        Sensor tempSensor= new Sensor("Temperature Sensor",startDate,loc,temperature,"celsius",rL);
        Sensor humiditySensor= new Sensor("Humidity Sensor",startDate,loc,temperature,"%",rL);

        SensorList lrSensorList=livingRoom.getSensorListInRoom();
        lrSensorList.addSensor(tempSensor);
        lrSensorList.addSensor(humiditySensor);

        String expected="1 - Temperature Sensor\n" +
                "2 - Humidity Sensor\n";
        String result=ctrl.showSensorListInRoom(1);

        assertEquals(expected,result);
    }
}