package sprintzero.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GAListTest {

    @Test
    void newGA() {

        GAList ga = new GAList();
        GeographicalArea area1 = ga.newGA("Porto", "district", 20, 20, 1, 3, -10);
        String expectedResult = "Porto";
        String result = area1.getGeographicalAreaDesignation();
        assertEquals(expectedResult, result);
    }

    @Test
    void addGA() {
        GAList ga = new GAList();
        GeographicalArea area1  = ga.newGA("Porto", "district", 20, 20, 1, 3, -10);
        ga.addGA(area1);
        List<GeographicalArea> expectedResult = Arrays.asList(area1);
        List<GeographicalArea> result = ga.getGAList();
        assertEquals(expectedResult, result);
    }

    @DisplayName("Test addition of multiple equal areas")
    @Test
    void addGA2() {
        GAList ga = new GAList();

        GeographicalArea area1  = ga.newGA("Porto", "district", 20, 20, 1, 3, -10);
        ga.addGA(area1);
        assertEquals(Arrays.asList(area1), ga.getGAList());

        GeographicalArea area2  = ga.newGA("Porto", "district", 20, 20, 1, 3, -10);
        ga.addGA(area2);
        assertEquals(Arrays.asList(area1), ga.getGAList());
    }

    @Test
    void getGAList() {
        GAList ga = new GAList();
        GeographicalArea area1 = ga.newGA("Porto", "district", 20, 20, 1, 3, -10);
        GeographicalArea area2 = ga.newGA("Braga", "district", 20, 20, 1, 3, -10);

        ga.addGA(area1);
        ga.addGA(area2);

        List<GeographicalArea> expectedResult = Arrays.asList(area1, area2);
        List<GeographicalArea> result = ga.getGAList();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Ensure GAList doesn't allow to add the same GA twice.")
    void addGAReturnsFalse() {
        GAList ga = new GAList();
        GeographicalArea area1  = ga.newGA("Porto", "district", 20, 20, 1, 3, -10);
        ga.addGA(area1);
        boolean expectedResult = false;
        boolean result = ga.addGA(area1);
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Test if listOfGAsContainingLocation method returns list of GAs with only one GA")
    void checkIfLocationIsInGAList() {
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Gaia","City",20,20,2,2,5);
        gaList.addGA(ga1);
        GeographicalArea ga2= new GeographicalArea("Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga2);
        Location loc = new Location(2.7,5.2,0);
        List<GeographicalArea>  listOfGAsContainingLocation=gaList.listOfGAsContainingLocation(loc.getLatitude(),loc.getLongitude());
        int expectedResult=1;
        int result=listOfGAsContainingLocation.size();
        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Ensure that Location loc is not within any geographical area in gaList")
    void checkIfLocationIsNotInGAList() {
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Gaia","City",20,20,2,2,5);
        gaList.addGA(ga1);
        GeographicalArea ga2= new GeographicalArea("Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga2);
        Location loc = new Location(22.7,5.2,0);
        List<GeographicalArea>  listOfGAsContainingLocation=gaList.listOfGAsContainingLocation(loc.getLatitude(),loc.getLongitude());
        int expectedResult=0;
        int result=listOfGAsContainingLocation.size();
        assertEquals(expectedResult,result);
    }


   /* @Test
    @DisplayName("Ensure sensor is added to the geographical area in the index 0 of a list of Geographical Areas that contain the sensor location")
    void addSensorToGAInListOfGAs() {
        Sensor sensor = new Sensor("GaiaTemperatureSensor", new GregorianCalendar(2018, 8, 4, 11, 0),2.7,5.2,0,new DataType ("Temperature"));
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Gaia","City",20,20,2,2,5);
        GeographicalArea ga2= new GeographicalArea("Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga1);
        gaList.addGA(ga2);
        boolean expectedResult = true;
        boolean result= gaList.addSensorToGAInListOfGAs(sensor,0);
        assertEquals(expectedResult,result);
    }*/
/*
    @Test
    @DisplayName("Ensure sensor is not added to the geographical area in the index 0 of a list of Geographical Areas that contain the sensor location, when the sensor is already in that Geographical Area")
    void addSensorToGAInListOfGAsWhichAlreadyContainsThatSensor() {
        Sensor sensor = new Sensor("GaiaTemperatureSensor", new GregorianCalendar(2018, 8, 4, 11, 0),2.7,5.2,0,new DataType ("Temperature"));
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Gaia","City",20,20,2,2,5);
        GeographicalArea ga2= new GeographicalArea("Matosinhos","City",4,5,2,2,5);
        ga2.addSensor(sensor);
        gaList.addGA(ga1);
        gaList.addGA(ga2);
        boolean expectedResult = false;
        boolean result= gaList.addSensorToGAInListOfGAs(sensor,0);
        assertEquals(expectedResult,result);
    }
    @Test
    @DisplayName("Ensure that when the user selects an index of GA greater than the size of the GAList containing the sensor location returns false")
    void checkIfReturnsFalseWhenIndexOfGAGreaterThanSizeOfGAListContainingSensorLocation() {
        Sensor sensor = new Sensor("GaiaTemperatureSensor", new GregorianCalendar(2018, 8, 4, 11, 0),2.7,5.2,0,new DataType ("Temperature"));
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Gaia","City",20,20,2,2,5);
        GeographicalArea ga2= new GeographicalArea("Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga1);
        gaList.addGA(ga2);
        boolean expectedResult = false;
        boolean result= gaList.addSensorToGAInListOfGAs(sensor,2);
        assertEquals(expectedResult,result);
    }

*/

    @Test
    @DisplayName("Check if method returns a List of GA from the type chosen by the user, when there is only 1 result")
    void GAFromTypeTestOneElement() {
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Gaia","City",20,20,2,2,5);
        gaList.addGA(ga1);
        GeographicalArea ga2= new GeographicalArea("Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga2);
        GeographicalArea ga3 = new GeographicalArea("Cedofeita","Street",41,-8, 83,2,5);
        gaList.addGA(ga3);
        List<GeographicalArea> expectedresult = new ArrayList<>(Arrays.asList(ga3)); //Usar Arrays.asList dentro de um a nova array list caso dê erro null point exception
        List<GeographicalArea> result=gaList.GAFromThisType("Street");
        assertEquals(expectedresult, result);
    }

    @Test
    @DisplayName("Check if method returns a List of GA from the type chosen by the user, when there is 2 or more results")
    void GAFromTypeTestTwoOrMoreElements() {
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Gaia","City",20,20,2,2,5);
        gaList.addGA(ga1);
        GeographicalArea ga2= new GeographicalArea("Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga2);
        GeographicalArea ga3 = new GeographicalArea("Cedofeita","Street",41,-8, 83,2,5);
        gaList.addGA(ga3);
        List<GeographicalArea> expectedresult = new ArrayList<>(Arrays.asList(ga1,ga2));
        List<GeographicalArea> result=gaList.GAFromThisType("City");
        assertEquals(expectedresult, result);
    }

    @Test
    @DisplayName("Check if method returns an empty List of GA, when there is no matches")
    void GAFromTypeTestEmptyNoMatch() {
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Gaia","City",20,20,2,2,5);
        gaList.addGA(ga1);
        GeographicalArea ga2= new GeographicalArea("Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga2);
        GeographicalArea ga3 = new GeographicalArea("Cedofeita","Street",41,-8, 83,2,5);
        gaList.addGA(ga3);
        List<GeographicalArea> expectedresult = Arrays.asList();
        List<GeographicalArea> result=gaList.GAFromThisType("Country");
        assertEquals(expectedresult, result);
    }

    @Test
    @DisplayName("Check if method returns an empty List of GA, when there is no input/empty")
    void GAFromTypeTestEmptyNoInput() {
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Gaia","City",20,20,2,2,5);
        gaList.addGA(ga1);
        GeographicalArea ga2= new GeographicalArea("Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga2);
        GeographicalArea ga3 = new GeographicalArea("Cedofeita","Street",41,-8, 83,2,5);
        gaList.addGA(ga3);
        List<GeographicalArea> expectedresult = Arrays.asList();
        List<GeographicalArea> result=gaList.GAFromThisType("");
        assertEquals(expectedresult, result);
    }

    @Test
    @DisplayName("Test if a sensor is added to the smallest Geographical Area Gaia")
    void addSensorToSmallestGA(){
        GAList gaList= new GAList();
        Sensor sensor = new Sensor("GaiaTemperatureSensor", new GregorianCalendar(2018, 8, 4, 11, 0),2.7,5.2,0,new DataType ("Temperature"));

        GeographicalArea ga1= new GeographicalArea("Gaia","City",4,5,2,2,10);
        GeographicalArea ga2= new GeographicalArea("Porto","District",4,5,2,3,10);
        GeographicalArea ga3= new GeographicalArea("Lisboa","City",85,-127,15,152,85);

        gaList.addGA(ga1);
        gaList.addGA(ga2);
        gaList.addGA(ga3);

        boolean expectedResult=true;
        boolean result = gaList.addSensorToSmallestGA(sensor);
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if a sensor is added to the smallest Geographical Area Setúbal")
    void addSensorToSmallestGA5(){
        GAList gaList= new GAList();
        Sensor sensor = new Sensor("SetubalTemperatureSensor", new GregorianCalendar(2018, 8, 4, 11, 0),2.7,5.2,0,new DataType ("Temperature"));

        GeographicalArea ga1= new GeographicalArea("Gaia","City",54,5,2,2,10);
        GeographicalArea ga2= new GeographicalArea("Porto","District",4,45,2,3,10);
        GeographicalArea ga3= new GeographicalArea("Lisboa","City",4,5,2,3,10);
        GeographicalArea ga4= new GeographicalArea("Setúbal","City",4,5,2,2,10);
        GeographicalArea ga5= new GeographicalArea("Lisboa Distrito","District",4,5,2,2,10);
        gaList.addGA(ga1);
        gaList.addGA(ga2);
        gaList.addGA(ga3);
        gaList.addGA(ga4);
        gaList.addGA(ga5);

        boolean expectedResult=true;
        boolean result = gaList.addSensorToSmallestGA(sensor);
        assertTrue(result);
    }

    @Test
    @DisplayName("Test that sensor is not added any area in a list of Geographical Areas that do not contain the sensor's location")
    void EnsureSensorIsNotAddedToSmallestGA(){
        GAList gaList= new GAList();
        Sensor sensor = new Sensor("GaiaTemperatureSensor", new GregorianCalendar(2018, 8, 4, 11, 0),2.7,5.2,0,new DataType ("Temperature"));

        GeographicalArea ga3= new GeographicalArea("Lisboa","City",85,-127,15,152,85);

        gaList.addGA(ga3);

        boolean expected=false;
        boolean result = gaList.addSensorToSmallestGA(sensor);
        assertEquals(expected,result);
    }
}