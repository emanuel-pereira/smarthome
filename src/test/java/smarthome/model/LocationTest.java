package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.validations.GPSValidations;

import static org.junit.jupiter.api.Assertions.*;


class LocationTest {

    @Test
    void setSensorAttributes(){
      Location location = new Location();

      location.setLatitude(80);
      location.setLongitude(170);
      location.setAltitude(2300);

      double expected1 = 80;
      double expected2 = 170;
      double expected3 = 2300;

      double result1= location.getLatitude();
      double result2 = location.getLongitude();
      double result3 = location.getAltitude();

      assertEquals(expected1,result1);
      assertEquals(expected2,result2);
      assertEquals(expected3,result3);
    }

    @Test
    @DisplayName("Check if all GPS coordinates are valid while creating an instance of a location.")
    public void checkIfGPSCoordinatesAreValid() {
        //Arrange
        Location l = new Location (12, -4, 100);

        //Act
        double expectedLat = 12;
        double resultLat = l.getLatitude ();

        //Assert
        assertEquals (expectedLat, resultLat);

        double expectedLon = -4;
        double resultLon = l.getLongitude ();
        assertEquals (expectedLon, resultLon);

        double expectedAlt = 100;
        double resultAlt = l.getAltitude ();
        assertEquals (expectedAlt, resultAlt);
    }

    @Test
    public void checkIfLatitudeIsInvalid() {

        boolean thrown = false;
        try {
            new Location (100, 180, 8848);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue (thrown);
    }

    @Test
    public void checkIfLongitudeIsInvalid() {

        boolean thrown = false;
        try {
            new Location (90, 200, 8848);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue (thrown);
    }

    @Test
    public void checkIfAltitudeIsInvalid() {

        boolean thrown = false;
        try {
            new Location (90, 180, 9000);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue (thrown);
    }

    @Test
    public void checkIfLatitudeLongitudeAreInvalid() {

        boolean thrown = false;
        try {
            new Location (100, 200, 8848);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue (thrown);
    }

    @Test
    public void checkIfLongitudeAltitudeAreInvalid() {

        boolean thrown = false;
        try {
            new Location (80, 200, 9000);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue (thrown);
    }

    @Test
    public void checkIfLatitudeAltitudeAreInvalid() {

        boolean thrown = false;
        try {
            new Location (-100, 100, 9000);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue (thrown);
    }

    @Test
    @DisplayName("Check that all max limit GPS coordinates are valid while creating an instance of a location.")
    public void ensureAllMaxLimitGPSCoordinatesAreValid() {
        //Arrange
        Location l = new Location (90, 180, 8848);

        //Act
        double expectedLat = 90;
        double resultLat = l.getLatitude ();

        //Assert
        assertEquals (expectedLat, resultLat);

        double expectedLon = 180;
        double resultLon = l.getLongitude ();
        assertEquals (expectedLon, resultLon);

        double expectedAlt = 8848;
        double resultAlt = l.getAltitude ();
        assertEquals (expectedAlt, resultAlt);
    }

    @Test
    @DisplayName("Check that all min limit GPS coordinates are valid while creating an instance of a location.")
    public void ensureAllMinLimitGPSCoordinatesAreValid() {
        //Arrange
        Location l = new Location (-90, -180, -12500);

        //Act
        double expectedLat = -90;
        double resultLat = l.getLatitude ();

        //Assert
        assertEquals (expectedLat, resultLat);

        double expectedLon = -180;
        double resultLon = l.getLongitude ();
        assertEquals (expectedLon, resultLon);

        double expectedAlt = -12500;
        double resultAlt = l.getAltitude ();
        assertEquals (expectedAlt, resultAlt);
    }

    @Test
    @DisplayName("Check if Illegal Argument Exception is thrown with higher permitted altitude")
    void higherAltitudeReturnsIllegalArgumentException() {
        GPSValidations validations = new GPSValidations ();
        boolean thrown = false;
        try {
            validations.altitudeIsValid (9000);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue (thrown);
    }

    @Test
    @DisplayName("Check if Illegal Argument Exception is thrown with lower permitted altitude")
    void lowerAltitudeReturnsIllegalArgumentException() {
        GPSValidations validations = new GPSValidations ();
        boolean thrown = false;
        try {
            validations.altitudeIsValid (-20000);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue (thrown);
    }

    @Test
    @DisplayName("Check if Illegal Argument Exception is thrown with higher permitted latitude")
    void higherLatitudeReturnsIllegalArgumentException() {
        GPSValidations validations = new GPSValidations ();
        boolean thrown = false;
        try {
            validations.latitudeIsValid (100);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue (thrown);
    }

    @Test
    @DisplayName("Check if Illegal Argument Exception is thrown with lower permitted latitude")
    void lowerLatitudeReturnsIllegalArgumentException() {
        GPSValidations validations = new GPSValidations ();
        boolean thrown = false;
        try {
            validations.latitudeIsValid (-100);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue (thrown);
    }

    @Test
    @DisplayName("Check if Illegal Argument Exception is thrown with higher permitted longitude")
    void higherLongitudeReturnsIllegalArgumentException() {
        GPSValidations validations = new GPSValidations ();
        boolean thrown = false;
        try {
            validations.longitudeIsValid (200);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue (thrown);
    }

    @Test
    @DisplayName("Check if Illegal Argument Exception is thrown with lower permitted longitude")
    void lowerLongitudeReturnsIllegalArgumentException() {
        GPSValidations validations = new GPSValidations ();
        boolean thrown = false;
        try {
            validations.longitudeIsValid (-200);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue (thrown);
    }

    @Test
    void validLatitude() {
        GPSValidations validations = new GPSValidations ();
        assertTrue (validations.latitudeIsValid (80));
    }

    @Test
    void validLongitude() {
        GPSValidations validations = new GPSValidations ();
        assertTrue (validations.longitudeIsValid (100));
    }

    @Test
    void validAltitude() {
        GPSValidations validations = new GPSValidations ();
        assertTrue (validations.altitudeIsValid (30));
    }

    @Test
    public void calcLinearDistanceBetweenL1AndL2Test() {
        Location l1 = new Location (41.15, -8.6, 83);
        Location l2 = new Location (38.7, -9.1, 4);
        //Arrange
        double result;
        double expectedresult = 79.0395;
        //Act
        result = l1.calcLinearDistanceBetweenTwoPoints (l1, l2);
        //Assert
        assertEquals (expectedresult, result, 0.0001);
    }

    @Test
    public void calcLinearDistanceBetweenL1AndL2TestNotZero() {
        Location l1 = new Location (41.15, -8.6, 83);
        Location l2 = new Location (38.7, -9.1, 4);
        //Arrange
        double result;
        double expectedresult = 0;
        //Act
        result = l1.calcLinearDistanceBetweenTwoPoints (l1, l2);
        //Assert
        assertNotEquals (expectedresult, result);
    }

    @Test
    public void LocationConstructor() {
        //Act
        Location l1 = new Location();
        l1.setAltitude(2);
        l1.setLongitude(3);
        l1.setLatitude(4);
        //Arrange
        double result;
        double expectedresult = 0;
        //Assert
        assertEquals(2, l1.getAltitude());
        assertEquals(3, l1.getLongitude());
        assertEquals(4, l1.getLatitude());
    }

    @Test
    public void locationToString() {
        String expected = " | Location:\n    Latitude: 70.0º | Longitude: 80.0º | Altitude: 1200.0 meters";

        Location l = new Location(70, 80, 1200);
        String result;
        result = l.locationToString();

        assertEquals(expected, result);
    }
}