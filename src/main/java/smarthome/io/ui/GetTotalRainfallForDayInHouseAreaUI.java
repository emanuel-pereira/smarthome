package smarthome.io.ui;

import smarthome.controller.GetTotalRainfallForDayInHouseAreaCTRL;
import smarthome.model.House;
import smarthome.model.SensorType;
import smarthome.model.SensorTypeList;
import smarthome.model.Validations.DateValidations;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class GetTotalRainfallForDayInHouseAreaUI {

    private GetTotalRainfallForDayInHouseAreaCTRL mCtrl;
    private DateValidations mValidations;
    Scanner read = new Scanner(System.in);
    private String sensorTypeRainfall = "rainfall";
    private GregorianCalendar mDate;
    private int mYear;
    private int mMonth;
    private int mDay;

    public GetTotalRainfallForDayInHouseAreaUI(House house, SensorTypeList sensorType) {

        mCtrl = new GetTotalRainfallForDayInHouseAreaCTRL(house, sensorType);
        mValidations = new DateValidations();

    }

    public void run() {

        if (mCtrl.checkIfRequiredSensorTypeExists(sensorTypeRainfall)) {
            this.checkRainfallSensorReadingList();
        } else System.out.println("Please ask the Administrator to create the rainfall sensor type");
    }

    public void checkRainfallSensorReadingList() {
        while (true) {
            if (mCtrl.getHouseGA() != null) {
                this.selectDate();
            }
            System.out.println("No location. Add a location to a house.");
            return;
        }
    }

    public void selectDate() {
        System.out.println("The Sensor rainfall is on your current options." +
                " Get total rainfall in a given day? Insert date you want to check. Insert year.");
        System.out.println("Insert the year:");
        mYear = getYear();
        System.out.println("Insert the month:");
        mMonth = getMonth();
        System.out.println("Insert the day:");
        mDay = getDay(mYear, mMonth);
        mDate = new GregorianCalendar(mYear, mMonth, mDay);
        int year = mDate.get(Calendar.YEAR);
        int month = mDate.get(Calendar.MONTH)+1;
        int day = mDate.get(Calendar.DAY_OF_MONTH);

        System.out.println("Input Date success! The total rainfall in: "
                + year + "/" + month + "/" + day + " is " + mCtrl.showTotalRainfallInDay(mDate, new SensorType(sensorTypeRainfall)));
    }

    private int getYear() {
        int year;
        while (true) {
            String inputYearOfReading = read.nextLine();
            if (mCtrl.yearIsValid(inputYearOfReading)) {
                year = Integer.parseInt(inputYearOfReading);
                break;
            }
        }
        return year;
    }

    private int getMonth() {
        int month;
        while (true) {
            String inputMonthOfReading = read.nextLine();
            if (mCtrl.monthIsValid(inputMonthOfReading)) {
                month = Integer.parseInt(inputMonthOfReading) - 1;
                break;
            }
        }
        return month;
    }

    private int getDay(int yearOfReading, int monthOfReading) {
        int day;
        while (true) {
            String inputDayOfReading = read.nextLine();
            if (mCtrl.dayIsValid(inputDayOfReading, monthOfReading + 1, yearOfReading)) {
                day = Integer.parseInt(inputDayOfReading);
                break;
            }
        }
        return day;
    }

}


