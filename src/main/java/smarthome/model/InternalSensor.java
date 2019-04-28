package smarthome.model;

import smarthome.model.validations.Utils;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Entity
public class InternalSensor implements Sensor {

    @Id
    private String id;
    private String designation;
    @OneToOne
    @JoinColumn(name = "SENSORTYPE_ID")
    private SensorType sensorType;
    private Calendar startDate;
    private Calendar pauseDate;
    private String unit;
    private boolean active;
    @Transient
    private ReadingList readingList;
    @Transient
    private SensorBehavior sensorBehavior;


    protected InternalSensor() {
    }

    /**
     * Constructor used to create internal sensors which require location coordinates.
     *
     * @param id          String parameter to specify sensor's id
     * @param designation String parameter to specify sensor's designation
     * @param startDate   specifies the sensor start date as a Calendar dataType
     * @param sensorType  specifies the sensor start date as a Calendar variable
     * @param unit        String parameter to specify sensor's unit of measure
     * @param readings    specifies the sensor's readingList
     */
    public InternalSensor(String id, String designation, Calendar startDate, SensorType sensorType, String unit, ReadingList readings) {
        this.sensorBehavior = new SensorBehavior(id, designation, startDate, sensorType, unit, readings);
        this.id = this.sensorBehavior.getId();
        this.designation = this.sensorBehavior.getDesignation();
        this.startDate = this.sensorBehavior.getStartDate();
        this.sensorType = this.sensorBehavior.getSensorType();
        this.unit = this.sensorBehavior.getUnit();
        this.active = this.sensorBehavior.isActive();
        this.readingList = this.sensorBehavior.getReadingList();
    }

    /**
     * Changes the Id of the sensor to the one inputted by the user.
     *
     * @param sensorId sensor's id String
     */
    public boolean setId(String sensorId) {
            return this.sensorBehavior.setId(sensorId);
    }

    /**
     * Changes the type of the sensor to the one inputted by the user.
     *
     * @param sensorType sensor's type
     */
    public void setSensorType(SensorType sensorType) {
        this.sensorBehavior.setSensorType(sensorType);
    }

    /**
     * Changes the start date of the sensor to the one inputted by the user.
     *
     * @param startDate date when the sensor started reading
     */
    public void setStartDate(Calendar startDate) {
        this.sensorBehavior.setStartDate(startDate);
    }

    /**
     * Changes the unit of the sensor to the one inputted by the user.
     *
     * @param unit sensor's unit
     */
    public void setUnit(String unit) {
        this.sensorBehavior.setUnit(unit);
    }

    /**
     * Changes the sensorDesignation of the sensor to the one inputted by the user.
     *
     * @param sensorDesignation sensor's name String
     */
    public boolean setSensorDesignation(String sensorDesignation) {
        return this.sensorBehavior.setSensorDesignation(sensorDesignation);
    }

    /**
     * Returns the designation of the sensor
     *
     * @return is the sensor's name designation
     */
    public String getDesignation() {
        return this.sensorBehavior.getDesignation();
    }

       /**
     * @return the sensor's id
     */
       public String getId() {
        return this.sensorBehavior.getId();
    }

    /**
     * Returns the dataType of the sensor.
     *
     * @return object dataType
     */
    public SensorType getSensorType() {
        return this.sensorBehavior.getSensorType();
    }

    public ReadingList getReadingList() {
        return this.sensorBehavior.getReadingList();
    }


    /**
     * This method looks for the last reading within a list of readings for a sensor.
     *
     * @return the last reading of a list of readings
     */
    public Reading getLastReadingPerSensor() {
        return this.sensorBehavior.getLastReadingPerSensor();
    }

    public double getLastReadingValuePerSensor() {
        return this.sensorBehavior.getLastReadingValuePerSensor();
    }


    /**
     * Gets the start date
     *
     * @return Date
     */
    public Calendar getStartDate() {
        return this.sensorBehavior.getStartDate();
    }

    /**
     * A pause date that marks when a sensor is deactivated
     *
     * @return Date
     */
    public Calendar getPauseDate() {
        return this.sensorBehavior.getPauseDate();
    }

    public String getUnit() {
        return this.sensorBehavior.getUnit();
    }


    /**
     * Deactivate sensor if active
     *
     * @return True if deactivated
     */
    public boolean deactivate(Calendar pauseDate) {
            return this.sensorBehavior.deactivate(pauseDate);

    }

    /**
     * Reactivate sensor if not active
     *
     * @return True if reactivated
     */
    public boolean reactivate() {
        return this.sensorBehavior.reactivate();
    }

    /**
     * Check if sensor is active
     *
     * @return True if active. False if not active
     */
    public boolean isActive() {
        return this.sensorBehavior.isActive();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InternalSensor)) {
            return false;
        }
        Sensor sensor = (InternalSensor) o;
        return id.equals(sensor.getId()) ||
                designation.equals(sensor.getDesignation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, designation);
    }
}
