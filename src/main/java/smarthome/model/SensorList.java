package smarthome.model;

import org.apache.log4j.Logger;
import smarthome.repository.Repositories;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class SensorList {
    private final List<Sensor> listOfSensors;
    static final Logger log = Logger.getLogger(SensorList.class);


    /**
     * Constructor method that creates a new list to save sensor objects
     */
    public SensorList() {
        this.listOfSensors = new ArrayList<>();
    }


    public boolean addSensor(Sensor sensor) {
        if (sensor.getClass().equals(ExternalSensor.class)) {
            return this.addExternalSensor((ExternalSensor) sensor);
        } else if (sensor.getClass().equals(InternalSensor.class)) {
            return this.addInternalSensor((InternalSensor) sensor);
        } else {
            return false;
        }
    }

    /**
     * Method to add a external sensor object to Sensors list, if it is not on the list yet
     *
     * @param newSensor - new Sensors object that will or not be added to the list
     */
    private boolean addExternalSensor(ExternalSensor newSensor) {
        if (!this.listOfSensors.contains(newSensor)) {
            this.listOfSensors.add(newSensor);

            //Repository call
            try {
                Repositories.saveExternalSensor(newSensor);
            } catch (NullPointerException e) {
                log.warn("Repository unreachable");
            }
            return true;
        } else return false;
    }

    /**
     * Method to add a internal sensor object to Sensors list, if it is not on the list yet
     *
     * @param newSensor - new Sensors object that will or not be added to the list
     * @return true if sensor is added, otherwise returns false
     */
    private boolean addInternalSensor(InternalSensor newSensor) {
        if (!this.listOfSensors.contains(newSensor)) {
            this.listOfSensors.add(newSensor);

            //Repository call
            try {
                Repositories.saveInternalSensor(newSensor);
            } catch (NullPointerException e) {
                log.warn("Repository unreachable");
            }
            return true;
        } else return false;
    }


    /**
     * Method to return the sensors included in the list
     *
     * @return list of sensors created
     */
    public List<Sensor> getSensorList() {
        return this.listOfSensors;
    }

    /**
     * @param id          id of the Sensors
     * @param inputName   name of Sensors
     * @param startDate   startDate of Sensors
     * @param geoLocation gps coordinates in which the user wants to place the sensor
     * @param sensorType  represents the type of sensor
     * @param inputUnit   sensor's unit of measure
     * @param readings    sensor's readingList
     * @return an instance of the created external sensor
     */
    public Sensor newSensor(String id, String inputName, GregorianCalendar startDate, Location geoLocation, SensorType sensorType, String inputUnit, ReadingList readings) {
        return new ExternalSensor(id, inputName, startDate, geoLocation, sensorType, inputUnit, readings);
    }

    /**
     * @param id         Id of the sensor
     * @param name       Name of the sensor
     * @param startDate  The first day the sensor starts to work
     * @param sensorType The sensor type
     * @param unit       The measurement unit
     * @param readings   Sensor's readinglist
     * @return A new interior sensor
     */
    public Sensor createNewInternalSensor(String id, String name, GregorianCalendar startDate, SensorType sensorType, String unit, ReadingList readings) {
        return new InternalSensor(id, name, startDate, sensorType, unit, readings);
    }

    /**
     * Some SensorTypes are required in some User Stories, so this method checks if a mandatory sensor type exists
     *
     * @param sensorType Sensors type designation
     * @return Sensors type designation
     */
    public boolean checkIfRequiredSensorTypeExists(String sensorType) {
        for (Sensor s : this.listOfSensors) {
            SensorType type = s.getSensorBehavior().getSensorType();
            if (type.getType().getName().equals(sensorType)) {
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
    public Sensor getRequiredSensorPerType(String type) {
        Sensor requiredSensor = null;
        for (Sensor sensor : this.listOfSensors) {
            SensorType sensorType = sensor.getSensorBehavior().getSensorType();

            if (sensorType.getType().getName().equals(type))
                requiredSensor = sensor;
        }
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
        for (Sensor sensor : this.listOfSensors) {
            String name = sensor.getSensorBehavior().getDesignation();
            result.append(number++);
            result.append(element);
            result.append(name);
            result.append("\n");
        }
        return result.toString();
    }

    public SensorList getListOfSensorsByType(SensorType sensorType) {
        SensorList listOfSensorsByType = new SensorList();
        for (Sensor sensor : this.listOfSensors) {
            SensorType type = sensor.getSensorBehavior().getSensorType();

            if (type.equals(sensorType))
                listOfSensorsByType.getSensorList().add(sensor);
        }
        return listOfSensorsByType;
    }

    public int size() {
        return this.listOfSensors.size();
    }

    public void removeSensor(Sensor sensor) {
        this.listOfSensors.remove(sensor);
    }

    public Sensor getLastSensor() {
        return this.listOfSensors.get(this.listOfSensors.size() - 1);
    }


    /**
     * Get all active sensors
     *
     * @return List of sensors
     */
    public SensorList getActiveSensors() {
        SensorList activeSensors = new SensorList();
        for (Sensor s : this.getSensorList()) {

            if (s.getSensorBehavior().isActive()) {
                activeSensors.getSensorList().add(s);
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
        for (Sensor s : this.getSensorList())
            if (s.getId().matches(sensorID)) {
                s.getSensorBehavior().deactivate(pauseDate);

                //Repository call
                try {
                    ExternalSensor externalSensor = (ExternalSensor) s;
                    Repositories.getExternalSensorRepository().save(externalSensor);
                } catch (Exception e) {
                    log.warn("Repository unreachable");
                }
            }


    }

    public Sensor getInternalSensorByTypeWithLatestReadings(SensorType type) {
        SensorList listByType = getListOfSensorsByType(type);
        Calendar latest = new GregorianCalendar(0000, 00, 00);
        Sensor result = new InternalSensor();

        for (Sensor s : listByType.getSensorList()) {
            if (s.getSensorBehavior().getReadingList().getLastReading() != null) {
                if (latest.before(s.getSensorBehavior().getLastReading().getReadingDateAndTime())) {
                    latest = s.getSensorBehavior().getReadingList().getLastReading().getReadingDateAndTime();
                    result = s;
                }
            }
            else {
                result = null;
            }
        }
        return result;
    }


}