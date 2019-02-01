package smarthome.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class GeographicalArea {
    private String mID;
    private String mDesignation;
    private TypeGA mTypeOfGA;
    private Location mLocation;
    private SensorList mSensorListInGA;
    private OccupationArea mOccupation;
    private GeographicalArea mParentGA;


    /**
     * This constructor method defines a Geographical Area with a designation, type, location as well as length and width
     * to calculate its occupation area
     *
     * @param designation GA name
     * @param typeGA      GA type
     * @param longitude   GA longitude
     * @param latitude    GA latitude
     * @param altitude    GA altitude
     * @param length      GA length
     * @param width       GA width
     */
    public GeographicalArea(String id, String designation, String typeGA, double latitude, double longitude, double altitude, double length, double width) {

        mID = id;
        mDesignation = designation;
        mTypeOfGA = new TypeGAList().newTypeGA(typeGA)/*new TypeGA(typeGA)*/;
        mLocation = new Location(latitude, longitude, altitude);
        mOccupation = new OccupationArea(length, width);
        mSensorListInGA = new SensorList();
    }

    /**
     * method to get this Geographical Area designation
     *
     * @return return this geographical area designation
     */
    public String getGeographicalAreaDesignation() {
        return this.mDesignation;
    }

    /**
     * method to get this Geographical Area Type designation
     *
     * @return return this geographical Area Type designation
     */
    public String getGeographicalAreaType() {
        return this.mTypeOfGA.toString();
    }

    /**
     * method to get this Geographical Area Parent Geographical Area
     *
     * @return return this geographical Area Parent
     */
    public GeographicalArea getGeographicalParentGA() {
        return mParentGA;
    }


    /**
     * Method to get list of Sensors in GA attribute
     *
     * @return the list of sensors in a Geographical Area
     */
    public SensorList getSensorListInGA() {
        return mSensorListInGA;
    }

    /**
     * Method to calculate the distance between to Geographical Areas
     *
     * @param anotherGA second Geographical Area
     * @return returns the linear distance already calculated
     */
    public double calculateDistanceTo(GeographicalArea anotherGA) {
        Location anotherLocation = anotherGA.getLocation();
        double distance;
        distance = this.calculateDistance(anotherLocation);
        //return this.mLocation.calcLinearDistanceBetweenTwoPoints(anotherLocation); advanced method
        return distance;
    }

    /**
     * method to get this Geographical Area location
     *
     * @return return this geographical area location
     */
    public Location getLocation() {
        return this.mLocation;
    }

    /**
     * Method to call the method in Location which executes the calculation of the linear distance between two locations
     *
     * @param aLocation second geographical area location
     * @return returns the linear distance already calculated
     */
    private double calculateDistance(Location aLocation) {
        return mLocation.calcLinearDistanceBetweenTwoPoints(this.mLocation, aLocation);
    }

    public SensorList getTheClosestSensorsByType(SensorType sensorType) {

        SensorList sensorListOfType = mSensorListInGA.getListOfSensorsByType(sensorType);
        double distance;
        double minDistance = calculateDistance((sensorListOfType.getSensorList().get(0)).getLocation());
        SensorList closestSensors = new SensorList();
        for (Sensor sensor : sensorListOfType.getSensorList()) {
            distance = calculateDistance(sensor.getLocation());
            /*Floating point math is imprecise because of the challenges of storing such values in a binary representation.
            Even worse, floating point math is not associative; push a float or a double through a series of simple mathematical
            operations and the answer will be different based on the order of those operation because of the rounding that takes
            place at each step.
            Even simple floating point assignments are not simple:
            float f = 0.1; // 0.100000001490116119384765625
            double d = 0.1; // 0.1000000000000000055511151231257827021181583404541015625
            (Results will vary based on compiler and compiler settings);
            Therefore, the use of the equality (==) and inequality (!=) operators on float or double values is almost always an
            error. Instead the best course is to avoid floating point comparisons altogether. When that is not possible, you
            should consider using one of Java's float-handling Numbers such as BigDecimal which can properly handle floating
            point comparisons. A third option is to look not for equality but for whether the value is close enough. I.e. compare
            the absolute value of the difference between the stored value and the expected value against a margin of acceptable
            error. Note that this does not cover all cases (NaN and Infinity for instance).*/

            if (BigDecimal.valueOf(distance) == BigDecimal.valueOf(minDistance)) {
                closestSensors.addSensor(sensor);
            }
            if (distance < minDistance) {
                closestSensors.getSensorList().clear();
                closestSensors.addSensor(sensor);
            }
        }
        return closestSensors;
    }

    public SensorList getSensorsWithReadingsInDate(GregorianCalendar inputDate, SensorType sensorType) {
        SensorList closestSensorsByType = this.getTheClosestSensorsByType(sensorType);
        SensorList sensorsWithReadingsInDate = new SensorList();
        for (Sensor sensor : closestSensorsByType.getSensorList()) {
            if (BigDecimal.valueOf(sensor.getReadingList().totalValueInGivenDay(inputDate)) != BigDecimal.valueOf(0)) {
                sensorsWithReadingsInDate.addSensor(sensor);
            }
        }
        return sensorsWithReadingsInDate;
    }

    public Sensor getSensorWithLatestReadingsByType(SensorType sensorType) {
        SensorList closestSensors = this.getTheClosestSensorsByType(sensorType);
        Sensor closestSensorWithLatestReading = closestSensors.getSensorList().get(0);
        Reading lastReading = closestSensorWithLatestReading.getLastReadingPerSensor();
        Calendar lastDate = lastReading.getDateAndTime();
        for (Sensor sensor : closestSensors.getSensorList()) {
            Reading sensorLastReading = sensor.getLastReadingPerSensor();
            if (sensorLastReading.getDateAndTime().after(lastDate)) {
                lastDate = sensor.getLastReadingPerSensor().getDateAndTime();
                closestSensorWithLatestReading = sensor;
            }
        }
        return closestSensorWithLatestReading;
    }

    public Sensor getSensorOfTypeWithReadingsInDate(GregorianCalendar inputDate, SensorType sensorType) {
        SensorList closestSensors = this.getSensorsWithReadingsInDate(inputDate, sensorType);
        Sensor closestSensorWithLatestReading = closestSensors.getSensorList().get(0);
        Reading lastReading = closestSensorWithLatestReading.getLastReadingPerSensor();
        Calendar lastDate = lastReading.getDateAndTime();
        for (Sensor sensor : closestSensors.getSensorList()) {
            Reading sensorLastReading = sensor.getLastReadingPerSensor();
            if (sensorLastReading.getDateAndTime().after(lastDate)) {
                lastDate = sensor.getLastReadingPerSensor().getDateAndTime();
                closestSensorWithLatestReading = sensor;
            }
        }
        return closestSensorWithLatestReading;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeographicalArea)) {
            return false;
        }
        GeographicalArea that = (GeographicalArea) o;
        return Objects.equals(mID, that.mID) &&
                Objects.equals(mDesignation, that.mDesignation) &&
                Objects.equals(mTypeOfGA, that.mTypeOfGA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mID, mDesignation, mTypeOfGA);
    }

    /**
     * US07
     * Method that tell´s if a Geographical Area is parented or contained on other.
     *
     * @param ga1 is defined as an geographical area parented with other.
     */


    public void setmParentGA(GeographicalArea ga1) {

        this.mParentGA = ga1;

    }

    public SensorList getGASensorsByType(String type) {
        SensorList currentGASensors = this.mSensorListInGA;

        return currentGASensors.getSensorListOfRequiredSensorPerType(type);
    }


}







