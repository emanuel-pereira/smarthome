package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.dto.ReadingDTO;
import smarthome.io.ui.UtilsUI;
import smarthome.model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GetDailySensorDataCTRLTest {

    @Test
    @DisplayName("Tests if ")
    void filterByTypeAndInterval() {
        Address a1 = new Address("Rua de Cedofeita", "4000-678", "Porto", 40, -12, 200);
        OccupationArea oc1 = new OccupationArea(23, 45);
        Location loc1 = new Location(40, -12, 200);
        GeographicalArea ga = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        House house = new House(a1, ga);

        Reading r0 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 0, 0));
        Reading r1 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 1, 6, 0));
        Reading r2 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1, 12, 0));
        Reading r3 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 18, 0));
        Reading r4 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 0, 0));
        Reading r5 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 2, 6, 0));
        Reading r6 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 2, 12, 0));
        Reading r7 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 18, 0));
        Reading r8 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 0, 0));
        Reading r9 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 3, 6, 0));
        Reading r10 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 3, 12, 0));
        Reading r11 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 18, 0));
        Reading r12 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 0, 0));
        Reading r13 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 4, 6, 0));
        Reading r14 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 4, 12, 0));
        Reading r15 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 18, 0));

        ReadingList sensorRL = new ReadingList();
        sensorRL.addReading(r0);
        sensorRL.addReading(r1);
        sensorRL.addReading(r2);
        sensorRL.addReading(r3);
        sensorRL.addReading(r4);
        sensorRL.addReading(r5);
        sensorRL.addReading(r6);
        sensorRL.addReading(r7);
        sensorRL.addReading(r8);
        sensorRL.addReading(r9);
        sensorRL.addReading(r10);
        sensorRL.addReading(r11);
        sensorRL.addReading(r12);
        sensorRL.addReading(r13);
        sensorRL.addReading(r14);
        sensorRL.addReading(r15);

        GregorianCalendar sensorStartDate = new GregorianCalendar(2017, Calendar.APRIL, 28);
        Location sensorLocation = new Location(47, -12, 200);
        SensorType sensorType = new SensorType("temperature");

        Sensor sensor = new Sensor("", "TempSensor", sensorStartDate, sensorLocation, sensorType, "Celsius", sensorRL);

        ga.getSensorListInGA().addSensor(sensor);

        GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(house);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.MAY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JUNE, 30);
        SensorList sensorList = ctrl.filterByTypeAndInterval(sensorType, startDate, endDate);
        List<Sensor> expected = new ArrayList<Sensor>();
        expected.add(sensor);
        assertEquals(expected, sensorList.getSensorList());
    }

    @Test
    void checkIfClosestSensorsHasReadingsInTimePeriod() {
        Address a1 = new Address("Rua de Cedofeita", "4000-678", "Porto", 40, -12, 200);
        OccupationArea oc1 = new OccupationArea(23, 45);
        Location loc1 = new Location(40, -12, 200);
        GeographicalArea ga = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        House house = new House(a1, ga);

        Reading r0 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 0, 0));
        Reading r1 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 1, 6, 0));
        Reading r2 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1, 12, 0));
        Reading r3 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 18, 0));
        Reading r4 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 0, 0));
        Reading r5 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 2, 6, 0));
        Reading r6 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 2, 12, 0));
        Reading r7 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 18, 0));
        Reading r8 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 0, 0));
        Reading r9 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 3, 6, 0));
        Reading r10 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 3, 12, 0));
        Reading r11 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 18, 0));
        Reading r12 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 0, 0));
        Reading r13 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 4, 6, 0));
        Reading r14 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 4, 12, 0));
        Reading r15 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 18, 0));

        ReadingList sensorRL = new ReadingList();
        sensorRL.addReading(r0);
        sensorRL.addReading(r1);
        sensorRL.addReading(r2);
        sensorRL.addReading(r3);
        sensorRL.addReading(r4);
        sensorRL.addReading(r5);
        sensorRL.addReading(r6);
        sensorRL.addReading(r7);
        sensorRL.addReading(r8);
        sensorRL.addReading(r9);
        sensorRL.addReading(r10);
        sensorRL.addReading(r11);
        sensorRL.addReading(r12);
        sensorRL.addReading(r13);
        sensorRL.addReading(r14);
        sensorRL.addReading(r15);

        GregorianCalendar sensorStartDate = new GregorianCalendar(2017, Calendar.APRIL, 28);
        Location sensorLocation = new Location(47, -12, 200);
        SensorType sensorType = new SensorType("temperature");

        Sensor sensor = new Sensor("", "TempSensor", sensorStartDate, sensorLocation, sensorType, "Celsius", sensorRL);

        ga.getSensorListInGA().addSensor(sensor);

        GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(house);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.MAY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JUNE, 30);
        assertTrue(ctrl.checkIfClosestSensorsHasReadingsInTimePeriod(sensorType, startDate, endDate));
    }

    @Test
    void displayMaximum() {
        Address a1 = new Address("Rua de Cedofeita", "4000-678", "Porto", 40, -12, 200);
        OccupationArea oc1 = new OccupationArea(23, 45);
        Location loc1 = new Location(40, -12, 200);
        GeographicalArea ga = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        House house = new House(a1, ga);

        Reading r0 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 0, 0));
        Reading r1 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 1, 6, 0));
        Reading r2 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1, 12, 0));
        Reading r3 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 18, 0));
        Reading r4 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 0, 0));
        Reading r5 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 2, 6, 0));
        Reading r6 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 2, 12, 0));
        Reading r7 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 18, 0));
        Reading r8 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 0, 0));
        Reading r9 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 3, 6, 0));
        Reading r10 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 3, 12, 0));
        Reading r11 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 18, 0));
        Reading r12 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 0, 0));
        Reading r13 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 4, 6, 0));
        Reading r14 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 4, 12, 0));
        Reading r15 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 18, 0));

        ReadingList sensorRL = new ReadingList();
        sensorRL.addReading(r0);
        sensorRL.addReading(r1);
        sensorRL.addReading(r2);
        sensorRL.addReading(r3);
        sensorRL.addReading(r4);
        sensorRL.addReading(r5);
        sensorRL.addReading(r6);
        sensorRL.addReading(r7);
        sensorRL.addReading(r8);
        sensorRL.addReading(r9);
        sensorRL.addReading(r10);
        sensorRL.addReading(r11);
        sensorRL.addReading(r12);
        sensorRL.addReading(r13);
        sensorRL.addReading(r14);
        sensorRL.addReading(r15);

        GregorianCalendar sensorStartDate = new GregorianCalendar(2017, Calendar.APRIL, 28);
        Location sensorLocation = new Location(47, -12, 200);
        SensorType sensorType = new SensorType("temperature");

        Sensor sensor = new Sensor("", "TempSensor", sensorStartDate, sensorLocation, sensorType, "Celsius", sensorRL);

        ga.getSensorListInGA().addSensor(sensor);

        GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(house);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.MAY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JUNE, 30);
        ReadingDTO readingDTO = ctrl.displayMaximum(sensorType, startDate, endDate);
        String expected = "2017-06-01";
        String result = UtilsUI.dateToString(readingDTO.getReadingDateAndTime());
        assertEquals(expected, result);
    }

    @Test
    void displayMinimum() {
        Address a1 = new Address("Rua de Cedofeita", "4000-678", "Porto", 40, -12, 200);
        OccupationArea oc1 = new OccupationArea(23, 45);
        Location loc1 = new Location(40, -12, 200);
        GeographicalArea ga = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        House house = new House(a1, ga);

        Reading r0 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 0, 0));
        Reading r1 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 1, 6, 0));
        Reading r2 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1, 12, 0));
        Reading r3 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 18, 0));
        Reading r4 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 0, 0));
        Reading r5 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 2, 6, 0));
        Reading r6 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 2, 12, 0));
        Reading r7 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 18, 0));
        Reading r8 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 0, 0));
        Reading r9 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 3, 6, 0));
        Reading r10 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 3, 12, 0));
        Reading r11 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 18, 0));
        Reading r12 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 0, 0));
        Reading r13 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 4, 6, 0));
        Reading r14 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 4, 12, 0));
        Reading r15 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 18, 0));

        ReadingList sensorRL = new ReadingList();
        sensorRL.addReading(r0);
        sensorRL.addReading(r1);
        sensorRL.addReading(r2);
        sensorRL.addReading(r3);
        sensorRL.addReading(r4);
        sensorRL.addReading(r5);
        sensorRL.addReading(r6);
        sensorRL.addReading(r7);
        sensorRL.addReading(r8);
        sensorRL.addReading(r9);
        sensorRL.addReading(r10);
        sensorRL.addReading(r11);
        sensorRL.addReading(r12);
        sensorRL.addReading(r13);
        sensorRL.addReading(r14);
        sensorRL.addReading(r15);

        GregorianCalendar sensorStartDate = new GregorianCalendar(2017, Calendar.APRIL, 28);
        Location sensorLocation = new Location(47, -12, 200);
        SensorType sensorType = new SensorType("temperature");

        Sensor sensor = new Sensor("", "TempSensor", sensorStartDate, sensorLocation, sensorType, "Celsius", sensorRL);

        ga.getSensorListInGA().addSensor(sensor);

        GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(house);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.MAY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JUNE, 30);
        ReadingDTO readingDTO = ctrl.displayMinimum(sensorType, startDate, endDate);
        String expected = "2017-06-04";
        String result = UtilsUI.dateToString(readingDTO.getReadingDateAndTime());
        assertEquals(expected, result);
    }

    @Test
    void displayAmplitude() {
        Address a1 = new Address("Rua de Cedofeita", "4000-678", "Porto", 40, -12, 200);
        OccupationArea oc1 = new OccupationArea(23, 45);
        Location loc1 = new Location(40, -12, 200);
        GeographicalArea ga = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        House house = new House(a1, ga);

        Reading r0 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 0, 0));
        Reading r1 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 1, 6, 0));
        Reading r2 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1, 12, 0));
        Reading r3 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 18, 0));
        Reading r4 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 0, 0));
        Reading r5 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 2, 6, 0));
        Reading r6 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 2, 12, 0));
        Reading r7 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 18, 0));
        Reading r8 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 0, 0));
        Reading r9 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 3, 6, 0));
        Reading r10 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 3, 12, 0));
        Reading r11 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 18, 0));
        Reading r12 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 0, 0));
        Reading r13 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 4, 6, 0));
        Reading r14 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 4, 12, 0));
        Reading r15 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 18, 0));

        ReadingList sensorRL = new ReadingList();
        sensorRL.addReading(r0);
        sensorRL.addReading(r1);
        sensorRL.addReading(r2);
        sensorRL.addReading(r3);
        sensorRL.addReading(r4);
        sensorRL.addReading(r5);
        sensorRL.addReading(r6);
        sensorRL.addReading(r7);
        sensorRL.addReading(r8);
        sensorRL.addReading(r9);
        sensorRL.addReading(r10);
        sensorRL.addReading(r11);
        sensorRL.addReading(r12);
        sensorRL.addReading(r13);
        sensorRL.addReading(r14);
        sensorRL.addReading(r15);

        GregorianCalendar sensorStartDate = new GregorianCalendar(2017, Calendar.APRIL, 28);
        Location sensorLocation = new Location(47, -12, 200);
        SensorType sensorType = new SensorType("temperature");

        Sensor sensor = new Sensor("", "TempSensor", sensorStartDate, sensorLocation, sensorType, "Celsius", sensorRL);

        ga.getSensorListInGA().addSensor(sensor);

        GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(house);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.MAY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JUNE, 30);
        ReadingDTO readingDTO = ctrl.displayAmplitude(sensorType, startDate, endDate);
        String expected = "2017-06-01";
        String result = UtilsUI.dateToString(readingDTO.getReadingDateAndTime());
        assertEquals(expected, result);
    }
}