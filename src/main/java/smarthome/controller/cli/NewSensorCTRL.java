package smarthome.controller.cli;

import smarthome.model.*;
import smarthome.model.validations.GPSValidations;
import smarthome.model.validations.NameValidations;
import smarthome.repository.Repositories;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static smarthome.model.House.checkIfLocationExists;
import static smarthome.model.House.getHouseRoomList;


public class NewSensorCTRL {

    private final SensorTypeList sensorTypeList;
    private final GAList gaList;
    private final GPSValidations gpsValidations;
    private final NameValidations nameValidations;



    public NewSensorCTRL(SensorTypeList sensorTypeList, GAList listOfGA) {
        this.sensorTypeList = sensorTypeList;
        this.gaList = listOfGA;
        this.gpsValidations = new GPSValidations();
        this.nameValidations = new NameValidations();
    }

    /**
     * Method to check if altitude inputted by the user is valid
     *
     * @param altitude Double inputted by the user to represent altitude
     * @return altitude if it is within [-12.500,8848] range, otherwise returns  to ask again for valid input
     */
    public boolean altitudeIsValid(double altitude) {
        return this.gpsValidations.altitudeIsValid(altitude);
    }

    /**
     * Method to check if longitude inputted by the user is valid
     *
     * @param longitude Double inputted by the user to represent longitude
     * @return longitude if it is within [-180,180] range, otherwise returns  to ask again for valid input
     */
    public boolean longitudeIsValid(double longitude) {
        return this.gpsValidations.longitudeIsValid(longitude);
    }

    /**
     * Method to check if longitude inputted by the user is valid
     *
     * @param latitude Double inputted by the user to represent longitude
     * @return latitude if it is within [-90,90] range, otherwise returns  to ask again for valid input
     */
    public boolean latitudeIsValid(double latitude) {
        return this.gpsValidations.latitudeIsValid(latitude);
    }


    public boolean nameIsValid(String inputName) {
        return this.nameValidations.nameIsValid(inputName);
    }

    /**
     * @return Method that shows the list of dataTypes in a unique string
     */
    public String showSensorTypeListInString() {

        return this.sensorTypeList.showSensorTypeListInString();
    }

    /**
     * @return Method that shows the list of rooms in a unique string
     */
    public String showRoomListInStr() {
        RoomList roomList = getHouseRoomList();
        return roomList.showRoomListInString();
    }

    public List<GeographicalArea> getGAList() {
        return this.gaList.getGAList();
    }

    /**
     * @return Method that shows the list of Geographical Areas in a unique string
     */
    public String showGAListInString() {
        return this.gaList.showGAListInString();
    }

    /**
     * index position of the List of Geographical Areas chosen by the user
     * @param id              String variable to set the sensor id
     * @param inputName       String variable to set the name of the sensor
     * @param startDate       Calendar parameter to set start date of the sensor
     * @param sensorTypeIndex integer declaring the index position of the sensor type in the sensor type list
     * @param indexOfGA       integer declaring the index position of the geographical area in the geographical areas list to which the sensor will be added
     * @param location        GPS coordinates inputted by the user to set the sensor location
     * @param inputUnit       String variable to set the sensor's unit of measure
     * @param readings        list of readings stored by the sensor
     * @return adds the sensor created to the selected Geographical Area
     */
    public boolean addNewSensorToGA(String id, String inputName, GregorianCalendar startDate, int sensorTypeIndex, String inputUnit, Location location, int indexOfGA, ReadingList readings) {
        GeographicalArea geographicalArea = this.gaList.get(indexOfGA);
        SensorType sensorType = this.sensorTypeList.getSensorTypeList().get(sensorTypeIndex);
        Sensor sensor = geographicalArea.getSensorListInGa().newSensor(id, inputName, startDate, location, sensorType, inputUnit, readings);
        return geographicalArea.getSensorListInGa().addSensor(sensor);
    }

    /**
     * Method that creates and adds a new sensor to a room
     * @param id              String variable to set the sensor id
     * @param inputName       String variable to set the name of the sensor
     * @param startDate       Calendar parameter to set start date of the sensor
     * @param sensorTypeIndex integer declaring the index position of the sensor type in the sensor type list
     * @param indexOfRoom     integer declaring the index position of the room in the room list to which the sensor will be added
     * @param unit            String variable to set the sensor's unit of measure
     * @param readingList     list of readings stored by the sensor
     * @return adds the sensor created to the selected room
     */
    public boolean addNewSensorToRoom(String id, String inputName, GregorianCalendar startDate, int sensorTypeIndex, int indexOfRoom, String unit, ReadingList readingList) {
        RoomList roomList = getHouseRoomList();
        SensorType sensorType = this.sensorTypeList.getSensorTypeList().get(sensorTypeIndex);
        Room room = roomList.get(indexOfRoom);
        SensorList rSensorList = room.getSensorListInRoom();
        Sensor sensor = rSensorList.createNewInternalSensor(id, inputName, startDate, sensorType, unit, readingList);
        return rSensorList.addSensor(sensor);

    }


    /**
     * @return the number of elements in the geographical areas list as an integer value
     */
    public int getGAListSize() {
        return this.gaList.size();
    }

    /**
     * @return the number of elements in the sensor type list as an integer value
     */
    public int getSensorTypeListSize() {
        return this.sensorTypeList.size();
    }

    /**
     * @return the number of elements in the room list as an integer value
     */
    public int getRoomListSize() {
        RoomList roomList = getHouseRoomList();
        return roomList.getRoomListSize();
    }

    /**
     * Method to get the name of the last element in the list of sensors of the geographical area selected as parameter
     *
     * @param indexOfGA geographical area in the index position of the list of geographical areas
     * @return the name of sensor in the last position of the list of sensors of the geographical area selected as parameter
     */
    public String getGASensorType(int indexOfGA) {
        GeographicalArea ga = this.gaList.get(indexOfGA);
        Sensor createdSensor = ga.getSensorListInGa().getLastSensor();
        SensorType type = createdSensor.getSensorBehavior().getSensorType();
        return type.getType().getName();
    }

    public String getRoomSensorType(int indexOfRoom) {
        RoomList roomList = getHouseRoomList();
        Room room = roomList.get(indexOfRoom);
        Sensor createdSensor = room.getSensorListInRoom().getLastSensor();
        SensorType type = createdSensor.getSensorBehavior().getSensorType();
        return type.getType().getName();
    }


    /**
     * Method to get the name of the geographical area in the index position of the list of geographical areas
     *
     * @param indexOfGA correspondent to the geographical area in the index position of the list of geographical areas
     * @return the name of the geographical area in the index position of the list of geographical areas
     */
    public String getGAName(int indexOfGA) {
        GeographicalArea ga = this.gaList.get(indexOfGA);
        return ga.getDesignation();
    }

    /**
     * Method to get the name of the roomin the index position of the list of rooms
     *
     * @param indexOfRoom correspondent to the room in the index position of the list of rooms
     * @return the name of the room in the index position of the list of rooms
     */
    public String getRoomName(int indexOfRoom) {
        RoomList roomList = getHouseRoomList();
        Room room = roomList.get(indexOfRoom);
        return room.getDesignation();
    }

    /**
     * Method to get the name of the sensor created
     *
     * @param indexOfGA correspondent to the geographical area in the index position of the list of geographical areas
     * @return the name of the sensor created in the selected geographical area
     */
    public String getGASensorName(int indexOfGA) {
        GeographicalArea ga = this.gaList.get(indexOfGA);
        Sensor createdSensor = ga.getSensorListInGa().getLastSensor();
        return createdSensor.getSensorBehavior().getDesignation();
    }

    /**
     * Method to get the id of the sensor created
     *
     * @param indexOfGA correspondent to the geographical area in the index position of the list of geographical areas
     * @return the id of the sensor created in the selected geographical area
     */
    public String getGASensorId(int indexOfGA) {
        GeographicalArea ga = this.gaList.get(indexOfGA);
        Sensor createdSensor = ga.getSensorListInGa().getLastSensor();
        return createdSensor.getId();
    }

    /**
     * Method to get the name of the internal sensor created
     *
     * @param indexOfRoom correspondent to the room in the index position of the list of rooms
     * @return the name of the sensor created in the selected room
     */
    public String getInternalSensorName(int indexOfRoom) {
        RoomList roomList = getHouseRoomList();
        Room room = roomList.get(indexOfRoom);
        Sensor createdSensor = room.getSensorListInRoom().getLastSensor();
        return createdSensor.getSensorBehavior().getDesignation();
    }

    /**
     * Method to get the start date of the sensor created
     *
     * @param indexOfGA correspondent to the geographical area in the index position of the list of geographical areas
     * @return the start date of the sensor created in the selected geographical area
     */
    public Calendar getGASensorSDate(int indexOfGA) {
        GeographicalArea ga = this.gaList.get(indexOfGA);
        Sensor createdSensor = ga.getSensorListInGa().getLastSensor();
        return createdSensor.getSensorBehavior().getStartDate();
    }

    /**
     * Method to get the start date of the internal sensor created
     *
     * @param indexOfRoom correspondent to the room in the index position of the room list where the sensor is located
     * @return the start date of the sensor created in the selected room
     */
    public Calendar getRoomSensorSDate(int indexOfRoom) {
        RoomList roomList = getHouseRoomList();
        Room room = roomList.get(indexOfRoom);
        Sensor createdSensor = room.getSensorListInRoom().getLastSensor();
        return createdSensor.getSensorBehavior().getStartDate();
    }

    /**
     * Method to get the unit of the sensor created
     *
     * @param indexOfRoom correspondent to the roomin the index position of the room list
     * @return the unit of the sensor created in the selected room
     */
    public String getRoomSensorUnit(int indexOfRoom) {
        RoomList roomList = getHouseRoomList();
        Room room = roomList.get(indexOfRoom);
        Sensor createdSensor = room.getSensorListInRoom().getLastSensor();
        return createdSensor.getSensorBehavior().getUnit();
    }

    /**
     * Method to get the unit of the sensor created
     *
     * @param indexOfGA correspondent to the geographical area in the index position of the list of geographical areas
     * @return the unit of the sensor created in the selected geographical area
     */
    public String getGASensorUnit(int indexOfGA) {
        GeographicalArea ga = this.gaList.get(indexOfGA);
        Sensor createdSensor = ga.getSensorListInGa().getLastSensor();
        return createdSensor.getSensorBehavior().getUnit();
    }

    public String showSensorListInRoom (int indexOfRoom){
        RoomList roomList = getHouseRoomList();
        Room room = roomList.get(indexOfRoom);
        SensorList sensorList = room.getSensorListInRoom();
        return sensorList.showSensorListInString();
    }

    public int sensorListInRoomSize (int indexOfRoom){
        RoomList roomList = getHouseRoomList();
        Room room = roomList.get(indexOfRoom);
        SensorList sensorList = room.getSensorListInRoom();
        return sensorList.size();
    }

    public boolean checkIfHouseAsLocation() {
        return checkIfLocationExists();
    }

}