package smarthome.model;

import smarthome.model.validations.Utils;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Room implements Powered, Metered {

    private String name;
    private Integer floor;
    private OccupationArea area;
    private double height;
    private SensorList sensorListInRoom = new SensorList();
    private DeviceList deviceList;


    /**
     * This constructor sets up the Room
     *
     * @param name   Unique name of the room
     * @param floor  The number of the floor
     * @param length The height of the room to calculate the area
     * @param width  The width of the room to calculate the area
     * @param height The height of the room
     */
    public Room(String name, Integer floor, double length, double width, double height) {
        this.name = name;
        this.floor = floor;
        this.area = new OccupationArea(length, width);
        this.height = height;
        this.deviceList = new DeviceList();
    }

    public Room (){

    }

    /**
     * Method to get the name of the room
     *
     * @return Name of the room
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to set the name of String mName.
     *
     * @param nameRoom name of the room of the house.
     */
    public void setName(String nameRoom) {
        this.name = nameRoom;
    }

    /**
     * Method to get the floor of the room
     *
     * @return Floor number
     */
    public int getFloor() {
        return this.floor;
    }

    /**
     * Method to set the floor of the room on the house.
     *
     * @param floor set the floor room the house.
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * Method to get the area (from the OccupationArea class)
     *
     * @return Area of the room (height*width)
     */
    public OccupationArea getArea() {
        return this.area;
    }

    /**
     * Method to set the dimensions of the room on the house.
     *
     * @param area set the dimensions of the the room on the house.
     */
    public void setArea(OccupationArea area) {
        this.area = area;
    }

    /*public ReadingList getDataSeriesInTimeInterval(Calendar startDate, Calendar endDate) {
        ReadingList roomDataSeries= new ReadingList();
        for (Device device : mDeviceList.getDeviceList()) {
            roomDataSeries = device.getDataSeriesInTimeInterval(startDate,endDate);
            //if(roomDataSeries.
        }
    }*/

    /**
     * Method to get the height
     *
     * @return Height of the room
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Method to set the height of the room on the house.
     *
     * @param height of the room
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Get the nominal power of a room by adding all the devices in the room device list
     *
     * @return nominal power sum
     */
    public double getNominalPower() {
        double sum = 0;
        for (Device device : this.deviceList.getDeviceList()) {
            sum += device.getNominalPower();
        }
        return sum;
    }

    /**
     * Get the total energy consumption of a room by adding all the devices in the room device list
     *
     * @return energy consumption sum
     */
    public double getEnergyConsumptionInTimeInterval(Calendar startHour, Calendar endHour) {
        double sum = 0;
        for (Device device : this.deviceList.getMeteredDevices()) {
            sum += device.getEnergyConsumptionInTimeInterval(startHour, endHour);
        }
        return Utils.round(sum,2);
    }

    /**
     * Method that checks if a text is only spaces
     *
     * @param roomName Name of the floor
     * @return False if only spaces. True if words and numbers
     */
    public boolean validateName(String roomName) {
        if (roomName.trim().isEmpty()) {
            return false;
        }
        this.name = roomName;
        return true;
    }


    public SensorList getSensorListInRoom() {
        return this.sensorListInRoom;
    }

    /**
     * Checks if a sensor type exists in a room
     *
     * @param input Sensor type designation
     * @return True if the sensor type exist in the room or false if not
     */
    public boolean checkIfSensorTypeExistsInRoom(String input) {
        List<Sensor> list = this.getSensorListInRoom().getSensorList();
        for (Sensor s : list) {
            if (s.getSensorType().getType().equals(input)) {
                return true;
            }
        }
        return false;
    }

    public DeviceList getDeviceList() {
        return this.deviceList;
    }

    public int getSizeDeviceListInRoom(){
        return this.deviceList.size ();
    }

    /**
     * When two objects (o1 and o2) with the same data are compare, the result is that they are different objects.
     * If there's the need to check for equality of values inside the objects the inherit equals method need to be override.
     * First: check if the argument is a reference to this object.
     * Second: check if o is an instance of Room or not, it allows for subclasses to be equal.
     * Final: typecast o to Room so that we can compare data member (cast the argument to the correct type so that
     * we can compare data members). Then compare the data members and return accordingly.
     *
     * @param o Any kind of object
     * @return If the object is compared with itself then return true. Check if the argument has the correct type. If not, return false.
     * Check if that field of the argument matches the corresponding field of the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Room)) {
            return false;
        }
        Room room = (Room) o;
        return Objects.equals(this.name, room.name);
    }

    /**
     * Equal objects may get different hash-values, so when equal() is override, the hash value must also be override.
     *
     * @return Equal objects must produce the same hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

}
