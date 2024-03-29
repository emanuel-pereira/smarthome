package smarthome.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Entity
public class InternalSensor implements Sensor {

    @Id
    private String id;
    @Embedded
    private SensorBehavior sensorBehavior;

    private String idRoom;


    public InternalSensor() {
    }

    /**
     * Constructor used to create internal sensors which require location coordinates.
     *
     * @param id         String parameter to specify sensor's id
     * @param name       String parameter to specify sensor's name
     * @param startDate  specifies the sensor start date as a Calendar dataType
     * @param sensorType specifies the sensor start date as a Calendar variable
     * @param unit       String parameter to specify sensor's unit of measure
     * @param readings   specifies the sensor's readingList
     */
    public InternalSensor(String id, String name, Calendar startDate, SensorType sensorType, String unit, ReadingList readings) {
        this.id = id;
        GenericName sName = new GenericName(name);
        this.sensorBehavior = new SensorBehavior(sName, startDate, sensorType, unit, readings);
    }

    /**
     * @return the sensor's id
     */
    public String getId() {
        return this.id;
    }

    @Override
    public SensorBehavior getSensorBehavior() {
        return this.sensorBehavior;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSensorBehavior(SensorBehavior sensorBehavior) {
        this.sensorBehavior = sensorBehavior;
    }


    public String getRoomId() {
        return this.idRoom;
    }

    public void setRoomId(String idRoom) {
        this.idRoom = idRoom;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InternalSensor)) return false;
        InternalSensor that = (InternalSensor) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}