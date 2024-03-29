package smarthome.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import smarthome.model.validations.GPSValidations;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 * The @Embeddable annotation allows to specify a class whose instances are stored as intrinsic part of the owning
 * entity. This annotation has no attributes.
 * The behaviour of the persistence of this class, is such as if this class attributes were from the parent class
 * were this Embeddable is Embedded(eg. Sensor.Location).
 */
@Embeddable
@JsonSerialize
public class Location {

    /**
     * Java's transient keyword is used to denote that a field is not to be serialized, whereas JPA's @Transient
     * annotation is used to indicate that a field is not to be persisted in the database
     */
    @Transient
    private final GPSValidations v = new GPSValidations();
    private double latitude;
    private double longitude;
    private double altitude;

    public Location() {
    }

    /**
     * Constructor requiring latitude, longitude and altitude parameters to create a location. Latitude, longitude and altitude
     * must comply with range values defined in the respective validation methods to create an instance of a location.
     * Otherwise it will throw an IllegalArgumentException.
     *
     * @param latitude  latitude coordinates
     * @param longitude longitude coordinates
     * @param altitude  altitude coordinate
     */
    public Location(double latitude, double longitude, double altitude) {
        setLatitude(latitude);
        setLongitude(longitude);
        setAltitude(altitude);
    }

    /**
     * Method to set latitude as the one inputted by the user if it complies with latitudeIsValid criteria
     *
     * Method to set latitude as the one inputted by the user if it complies with latitudeIsValid criteria.
     * @param latitude user input
     */
    public void setLatitude(double latitude) {
        if (this.v.latitudeIsValid(latitude))
            this.latitude = latitude;
    }

    /**
     * Method to set longitude as the one inputted by the user if it complies with longitudeIsValid criteria
     *
     * @param longitude user input
     */
    public void setLongitude(double longitude) {
        if (this.v.longitudeIsValid(longitude))
            this.longitude = longitude;
    }

    /**
     * Method to set altitude as the one inputted by the user if it complies with altitudeIsValid criteria
     *
     * @param altitude user input
     */
    public void setAltitude(double altitude) {
        if (this.v.altitudeIsValid(altitude))
            this.altitude = altitude;
    }


    /**
     * Method to calculate the linear distance between two location, used in both exercise 1 and 2
     * Given two points in the space  and  the distance between them is defined as the module of the vector AB
     * which can be then calculated using the Pitagoric Theorem
     *
     * @param location1 this is the Location object for the first GA
     * @param location2 this is the Location object for the second GA
     * @return distance between location in meters
     */
    public double calcLinearDistanceBetweenTwoPoints(Location location1, Location location2) {
        return Math.sqrt(Math.pow(location2.latitude - location1.latitude, 2)
                + Math.pow(location2.longitude - location1.longitude, 2)
                + Math.pow(location2.altitude - location1.altitude, 2));
    }


    /**
     * Method that simply returns the latitude of a specific location
     *
     * @return the latitude of a specific location
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Method that simply returns the longitude of a specific location
     *
     * @return the longitude of a specific location
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Method that simply returns the altitude of a specific location
     *
     * @return the altitude of a specific location
     */
    public double getAltitude() {
        return this.altitude;
    }

    /**
     * Method to turn the Location object into a string
     *
     * @return location information as a String
     */

    public String locationToString() {
        StringBuilder output = new StringBuilder();

        String separator = " | ";
        String degree = "º";

        output.append(" | Location:");
        output.append("\n");
        output.append("    ");
        output.append("Latitude: ");
        output.append(this.latitude);
        output.append(degree);
        output.append(separator + "Longitude: ");
        output.append(this.longitude);
        output.append(degree);
        output.append(separator + "Altitude: ");
        output.append(this.altitude);
        output.append(" meters");

        return output.toString();
    }

}



