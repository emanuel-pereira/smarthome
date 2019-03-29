package smarthome.controller;

import smarthome.model.House;
import smarthome.model.Sensor;
import smarthome.model.SensorType;
import smarthome.model.SensorTypeList;

import java.util.GregorianCalendar;

public class GetTotalRainfallForDayInHouseAreaCTRL {


    private SensorTypeList mSensorTypeList;
    private House mHouse;

    public GetTotalRainfallForDayInHouseAreaCTRL(House house, SensorTypeList sensorTypeList) {

        mSensorTypeList = sensorTypeList;
        mHouse = house;
    }

    /**
     * Method to check if sensorType set as parameter exists
     * @param sensorType String parameter that checks if SensorType with the same String name exists
     * @return true if sensorType exists, otherwise returns false
     */
    public boolean checkIfSensorTypeExists(String sensorType) {
        return mSensorTypeList.checkIfSensorTypeExists(sensorType);
    }

    /**
     * Boolean method to check if there is any closest sensors to the house of a specific type that has readings in the date inputted as parameter
     * @param inputDate in GregorianCalendar format for which this method checks if exists any closest sensors to the house with readings in the specified date
     * @param sensorType selected to check sensors of that type
     * @return true if at least exists one of the possible closest sensors with readings in the inputDate, otherwise returns false
     */
    public boolean closestSensorsWithLatestReadingsInDate(GregorianCalendar inputDate, SensorType sensorType){
        return mHouse.closestSensorsWithReadingsInDate(inputDate,sensorType);
    }
    /**
     * Method that sums up the values of readings in the date inputted as parameter for the selected sensorType
     *
     * @param inputDate  for which the values of readings will be summed up
     * @param sensorType for which the total value of readings in the inputDate will be shown
     * @return a double value which resulted from summing up the values of readings in the date inputted as parameter
     */
    public double showTotalValueInADay(GregorianCalendar inputDate, SensorType sensorType) {
        Sensor sensor = mHouse.getSensorOfTypeWithLatestReadingsInDate(inputDate, sensorType);

        return sensor.getReadingList().totalValueInGivenDay(inputDate);
    }

    /**
     * Method that checks if the house geographical area is already configured
     * @return true if house geographical area is already configured, otherwise returns false
     */
    public boolean isHouseGAConfigured() {
        return mHouse.getHouseGA() != null;
    }
}
