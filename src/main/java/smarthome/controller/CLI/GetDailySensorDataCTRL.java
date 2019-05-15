package smarthome.controller.CLI;

import smarthome.dto.ReadingDTO;
import smarthome.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static smarthome.model.House.*;


public class GetDailySensorDataCTRL {
    private final SensorTypeList sensorTypeList;

    public GetDailySensorDataCTRL(SensorTypeList sensorTypeList) {
        this.sensorTypeList = sensorTypeList;
    }

    public boolean checkIfSensorTypeExists(String sensorType) {
        return this.sensorTypeList.checkIfSensorTypeExists(sensorType);
    }

    public boolean isHouseGAConfigured() {
        return getHouseGA() != null;
    }

    public int filterByType(SensorType sensorType) {
        GeographicalArea houseGA = getHouseGA();
        SensorList gaSensorList = houseGA.getSensorListInGA();
        SensorList listOfType = gaSensorList.getListOfSensorsByType(sensorType);
        return listOfType.size();
    }

    public SensorList filterByTypeAndInterval(SensorType sensorType, Calendar startDate, Calendar endDate) {
        return filterListByTypeByIntervalAndDistance(sensorType, startDate, endDate);
    }

    public boolean checkIfClosestSensorsHasReadingsInTimePeriodCTRL(SensorType sensorType, Calendar startDate, Calendar endDate) {
        return checkIfClosestSensorsHasReadingsInTimePeriod(sensorType, startDate, endDate);
    }

    public ReadingDTO displayMaximum(SensorType sensorType, Calendar startDate, Calendar endDate) {
        Sensor sensor = filterByTypeByIntervalAndDistance(sensorType, startDate, endDate);
        endDate.add(Calendar.DATE, 1);
        ReadingList sensorReadings = sensor.getSensorBehavior().getReadingList().filterByDate(startDate, endDate);
        endDate.add(Calendar.DATE, -1);
        sensorReadings = sensorReadings.dailyMaximumReadings();
        Reading reading = sensorReadings.maxValueInInterval();
        return reading.toDTO();
    }

    public ReadingDTO displayMinimum(SensorType sensorType, GregorianCalendar startDate, GregorianCalendar endDate) {
        Sensor sensor = filterByTypeByIntervalAndDistance(sensorType, startDate, endDate);
        endDate.add(Calendar.DATE, 1);
        ReadingList sensorReadings = sensor.getSensorBehavior().getReadingList().filterByDate(startDate, endDate);
        endDate.add(Calendar.DATE, -1);
        sensorReadings = sensorReadings.dailyMaximumReadings();
        Reading reading = sensorReadings.minValueInInterval();
        return reading.toDTO();
    }

    public ReadingDTO displayAmplitude(SensorType sensorType, GregorianCalendar startDate, GregorianCalendar endDate) {
        Sensor sensor = filterByTypeByIntervalAndDistance(sensorType, startDate, endDate);
        endDate.add(Calendar.DATE, 1);
        ReadingList sensorReadings = sensor.getSensorBehavior().getReadingList().filterByDate(startDate, endDate);
        endDate.add(Calendar.DATE, -1);
        ReadingList sensorXPTO = sensorReadings.dailyAmplitude();
        Reading reading = sensorXPTO.maxValueInInterval();
        return reading.toDTO();
    }
}