package smarthome.model;

import java.util.Objects;

public class Room {

    private String mName;
    private Integer mFloor;
    private OccupationArea mArea;
    private double mHeight;
    private HouseGrid mHouseGrid;//each room has only a house grid
    private SensorList mSensorListInRoom = new SensorList();


    /**
     * This constructor sets up the Room
     * @param name Unique name of the room
     * @param floor The number of the floor
     * @param length The height of the room to calculate the area
     * @param width The width of the room to calculate the area
     * @param height The height of the room
     */
    public Room(String name, Integer floor, double length, double width, double height) {
        mName = name;
        mFloor = floor;
        mArea = new OccupationArea ( length, width );
        mHeight = height;
    }

    /**
     * Method to get the name of the room
     * @return Name of the room
     */
    public String getName() {
        return mName;
    }

    /**
     * Method to get the floor of the room
     * @return Floor number
     */
    public int getFloor() {
        return mFloor;
    }

    /**
     * Method to get the area (from the OccupationArea class)
     * @return Area of the room (height*width)
     */
    public OccupationArea getArea() {
        return mArea;
    }

    /**
     * Method to get the height
     * @return Height of the room
     */
    public double getHeight() {
        return mHeight;
    }


    /**
     * Method to set the name of String mName.
     * @param setNameRoom  name of the room of the house.
     */
    public void setName(String setNameRoom) {
        this.mName = setNameRoom;

    }

    /**
     * Method to set the floor of the room on the house.
     * @param mFloor set the floor room the house.
     */
    public void setFloor(int mFloor) {
        this.mFloor = mFloor;
    }

    /**
     * Method to set the dimensions of the room on the house.
     * @param mArea set the dimensions of the the room on the house.
     */
    public void setArea(OccupationArea mArea) {
        this.mArea = mArea;
    }

    /**
     * Method to set the height of the room on the house.
     * @param height of the room
     */
    public void setHeight(double height) {
        this.mHeight = height;
    }

    /**
     * Method that checks if a text is only spaces
     * @param roomName Name of the floor
     * @return False if only spaces. True if words and numbers
     */
    public boolean validateName(String roomName) {
        if (roomName.trim ().isEmpty ()) {
            return false;
        }
        mName = roomName;
        return true;
    }

    public void setmHouseGrid(HouseGrid inputHouseGrid){
        mHouseGrid=inputHouseGrid;
    }

    public HouseGrid getmHouseGrid() {
        return mHouseGrid;
    }

    public SensorList getSensorListInRoom() {
        return mSensorListInRoom;
    }

    public SensorList getSensorListIn(Room room) {
        return mSensorListInRoom;
    }



    /**
     * When two objects (o1 and o2) with the same data are compare, the result is that they are different objects.
     * If there's the need to check for equality of values inside the objects the inherit equals method need to be override.
     * First: check if the argument is a reference to this object.
     * Second: check if o is an instance of Room or not, it allows for subclasses to be equal.
     * Final: typecast o to Room so that we can compare data member (cast the argument to the correct type so that
     * we can compare data members). Then compare the data members and return accordingly.
     * @param o Any kind of object
     * @return If the object is compared with itself then return true. Check if the argument has the correct type. If not, return false.
     * Check if that field of the argument matches the corresponding field of the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals ( mName, room.mName );
    }

    /**
     Equal objects may get different hash-values, so when equal() is override, the hash value must also be override.
     * @return Equal objects must produce the same hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash ( mName );
    }
}
