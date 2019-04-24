package smarthome.model;

import org.apache.log4j.Logger;
import smarthome.repository.Repositories;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class SensorList {
    private final List<Sensors> listOfSensors;
    static final Logger log = Logger.getLogger(SensorList.class);



    /**
     * Constructor method that creates a new list to save sensor objects
     */
    public SensorList() {
        this.listOfSensors = new ArrayList<>();
    }

    /**
     * Method to add a sensor object to Sensors list, if it is not on the list yet
     *
     * @param newSensor - new Sensors object that will or not be added to the list
     * @return true if the object is added to the list
     */
    public boolean addSensor(Sensors newSensor) {
        if (!this.listOfSensors.contains(newSensor)) {
            this.listOfSensors.add(newSensor);
            //Repository call
            try {
                Repositories.saveSensor(newSensor);
            } catch (NullPointerException e) {
                log.warn("Repository unreachable");
            }
            return true;
        } else return false;
    }

    public boolean checkIfAnySensorHasSameID(Sensors newSensor) {
        for (Sensors sensor : this.listOfSensors)
            if (sensor.getId().equals(newSensor.getId())) {
                return true;}
        return false;
    }

    /**
     * Method to return the sensors included in the list
     *
     * @return list of sensors created
     */
    public List<Sensors> getSensorList() {
        return this.listOfSensors;
    }

    /**
     * @param id          id of the Sensors
     * @param inputName   name of Sensors
     * @param startDate   startDate of Sensors
     * @param geoLocation gps coordinates in which the user wants to place the sensor
     * @return List of sensors
     */
    public Sensors newSensor(String id, String inputName, GregorianCalendar startDate, Location geoLocation, SensorType sensorType, String inputUnit, ReadingList readings) {
        return new Sensors(id, inputName, startDate, geoLocation, sensorType, inputUnit, readings);
    }

    /**
     * @param name       Name of the sensor
     * @param startDate  The first day the sensor starts to work
     * @param sensorType The sensor type
     * @param unit       The measurement unit
     * @return A new interior sensor
     */
    public Sensors createNewInternalSensor(String id, String name, GregorianCalendar startDate, SensorType sensorType, String unit, ReadingList readings) {
        return new Sensors(id, name, startDate, sensorType, unit, readings);
    }

    /**
     * Some SensorTypes are required in some User Stories, so this method checks if a mandatory sensor type exists
     *
     * @param sensorType Sensors type designation
     * @return Sensors type designation
     */
    public boolean checkIfRequiredSensorTypeExists(String sensorType) {
        for (Sensors s : this.listOfSensors) {
            if (s.getSensorType().getType().equals(sensorType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a sensor of one specific type
     *
     * @param type Sensors type designation
     * @return A specific type sensor
     */
    public Sensors getRequiredSensorPerType(String type) {
        Sensors requiredSensor = null;
        for (Sensors sensor : this.listOfSensors)
            if (sensor.getSensorType().getType().equals(type))
                requiredSensor = sensor;
        return requiredSensor;
    }


    /**
     * Transforms a list of sensors as a numbered list of strings with the names of the sensors
     *
     * @return List of sensors as string
     */
    public String showSensorListInString() {
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (Sensors sensor : this.listOfSensors) {
            result.append(number++);
            result.append(element);
            result.append(sensor.getDesignation());
            result.append("\n");
        }
        return result.toString();
    }

    public SensorList getListOfSensorsByType(SensorType sensorType) {
        SensorList listOfSensorsByType = new SensorList();
        for (Sensors sensor : this.listOfSensors) {
            if (sensor.getSensorType().equals(sensorType))
                listOfSensorsByType.addSensor(sensor);
        }
        return listOfSensorsByType;
    }

    public int size() {
        return this.listOfSensors.size();
    }

    public void removeSensor(Sensors sensor) {
        this.listOfSensors.remove(sensor);

    }

    public Sensors getLastSensor() {
        return this.listOfSensors.get(this.listOfSensors.size() - 1);
    }


    /**
     * Get all active sensors
     *
     * @return List of sensors
     */
    public SensorList getActiveSensors() {
        SensorList activeSensors = new SensorList();
        for (Sensors s : this.getSensorList()) {
            if (s.isActive()) {
                activeSensors.addSensor(s);
            }
        }
        return activeSensors;
    }

    /**
     * Deactivate sensor and save new status in the sensorRepository (DB)
     *
     * @param sensorID  id of the sensor
     * @param pauseDate Deactivation date
     */
    public void deactivateSensor(String sensorID, Calendar pauseDate) {
        for (Sensors s : this.getSensorList())
            if (s.getId().matches(sensorID)) {
                s.deactivate(pauseDate);
                //Repository call
                try {
                    Repositories.getSensorRepository().save(s);
                } catch (Exception e) {
                    log.warn("Repository unreachable");
                }
            }


    }
}