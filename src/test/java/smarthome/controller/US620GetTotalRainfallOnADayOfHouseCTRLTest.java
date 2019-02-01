package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class US620GetTotalRainfallOnADayOfHouseCTRLTest {


    @Test
    @DisplayName("Verify if rainfall sensor exists.")
    public void checkIfRequiredSensorTypeExists() {

        House h1 = new House();

        SensorTypeList sL = new SensorTypeList();
        SensorType temprature = sL.newSensorType("temperature");
        SensorType rainfall = sL.newSensorType("rainfall");

        assertEquals(0, sL.getSensorTypeList().size());
        assertTrue(sL.addSensorType(temprature));
        assertEquals(1, sL.getSensorTypeList().size());
        assertTrue(sL.addSensorType(rainfall));
        assertEquals(2, sL.getSensorTypeList().size());

        US620GetTotalRainfallOnADayOfHouseCTRL ctrl620 = new US620GetTotalRainfallOnADayOfHouseCTRL(h1, sL);

        boolean result = ctrl620.checkIfRequiredSensorTypeExists("rainfall");

        assertTrue(result);

    }

    @Test
    @DisplayName("verify if rainfall sensor not exists.")
    public void checkIfRequiredSensorTypeNotExists() {

        House h1 = new House();
        SensorTypeList sL = new SensorTypeList();
        SensorType temprature = sL.newSensorType("temperature");
        SensorType humidity = sL.newSensorType("humidity");

        assertEquals(0, sL.getSensorTypeList().size());
        assertTrue(sL.addSensorType(temprature));
        assertEquals(1, sL.getSensorTypeList().size());
        assertTrue(sL.addSensorType(humidity));
        assertEquals(2, sL.getSensorTypeList().size());

        US620GetTotalRainfallOnADayOfHouseCTRL ctrl620 = new US620GetTotalRainfallOnADayOfHouseCTRL(h1, sL);

        boolean result = ctrl620.checkIfRequiredSensorTypeExists("rainfall");

        assertFalse(result);

    }


    @Test
    void dateIsValid() {
        House house = new House();
        SensorTypeList sensorTypeList = new SensorTypeList();
        US620GetTotalRainfallOnADayOfHouseCTRL ctrl = new US620GetTotalRainfallOnADayOfHouseCTRL(house, sensorTypeList);
        String year = "2018";
        boolean result = ctrl.yearIsValid(year);
        assertTrue(result);

        String month = "11";
        boolean result1 = ctrl.monthIsValid(month);
        assertTrue(result1);

        int yearAsInteger = Integer.parseInt(year);
        int monthAsInteger = Integer.parseInt(month);
        String day = "30";
        boolean result2 = ctrl.dayIsValid(day, monthAsInteger, yearAsInteger);
        assertTrue(result2);
    }

    @Test
    void monthAndDayAreInvalid() {
        House house = new House();
        SensorTypeList sensorTypeList = new SensorTypeList();
        US620GetTotalRainfallOnADayOfHouseCTRL ctrl = new US620GetTotalRainfallOnADayOfHouseCTRL(house, sensorTypeList);
        String year = "2018";
        boolean result = ctrl.yearIsValid(year);
        assertTrue(result);

        String month = "15";
        boolean result1 = ctrl.monthIsValid(month);
        assertFalse(result1);

        int yearAsInteger = Integer.parseInt(year);
        int monthAsInteger = Integer.parseInt(month);
        String day = "35";
        boolean result2 = ctrl.dayIsValid(day, monthAsInteger, yearAsInteger);
        assertFalse(result2);
    }

    @Test
    void requestReadingRainfall() {
        GeographicalArea ga = new GeographicalArea("PORTO", "Porto", "City", 1, 1, 1, 1, 1);
        House house = new House();
        house.setHouseGA(ga);
        SensorList houseGASensorList = house.getHouseGA().getSensorListInGA();


        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType wind = new SensorType("wind");
        SensorType temperature = new SensorType("temperature");

        sensorTypeList.addSensorType(wind);
        sensorTypeList.addSensorType(temperature);

        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 31, 11, 0);
        ReadingList readingList1 = new ReadingList();
        Reading r1 = readingList1.newReading(17, new GregorianCalendar(2018, 12, 31, 11, 0));
        Reading r2 = readingList1.newReading(15, new GregorianCalendar(2018, 12, 31, 12, 0));
        Reading r3 = readingList1.newReading(14, new GregorianCalendar(2019, 2, 3, 15, 0));
        readingList1.addReading(r1);
        readingList1.addReading(r2);
        readingList1.addReading(r3);

        ReadingList readingList2 = new ReadingList();
        Reading r4 = readingList2.newReading(27, new GregorianCalendar(2019, 2, 3, 11, 0));
        Reading r5 = readingList2.newReading(35, new GregorianCalendar(2019, 2, 3, 12, 0));
        Reading r6 = readingList2.newReading(42, new GregorianCalendar(2019, 2, 3, 17, 0));
        readingList2.addReading(r4);
        readingList2.addReading(r5);
        readingList2.addReading(r6);

        Sensor s0 = new Sensor("WindSensor2", startDate, 85, 65, 10, wind, "c", readingList2);
        Sensor s1 = new Sensor("WindSensor1", startDate, 80, 50, 10, wind, "c", readingList1);
        Sensor s2 = new Sensor("WindSensor3", startDate, 80, 50, 10, wind, "c", readingList2);
        Sensor s3 = new Sensor("TemperatureSensor", startDate, 1.5, 1.5, 1.5, temperature, "c", readingList2);

        houseGASensorList.addSensor(s0);
        houseGASensorList.addSensor(s1);
        houseGASensorList.addSensor(s2);
        houseGASensorList.addSensor(s3);


        US620GetTotalRainfallOnADayOfHouseCTRL ctr = new US620GetTotalRainfallOnADayOfHouseCTRL(house, sensorTypeList);

        GregorianCalendar date = new GregorianCalendar(2019, 2, 3);


        double result = ctr.requestReadingRainfall(date, wind);
        double expected = 104;

        assertEquals(expected, result);
    }


    @Test
    void getHouseGA() {
        House house = new House();
        GeographicalArea ga = new GeographicalArea("PORTO", "Porto", "City", 1, 1, 1, 1, 1);
        house.setHouseGA(ga);
        SensorTypeList sensorTypeList = new SensorTypeList();
        US620GetTotalRainfallOnADayOfHouseCTRL ctrl= new US620GetTotalRainfallOnADayOfHouseCTRL(house,sensorTypeList);

        String expected="Porto";
        String result=ctrl.getHouseGA().getGeographicalAreaDesignation();
        assertEquals(expected,result);


    }


}






