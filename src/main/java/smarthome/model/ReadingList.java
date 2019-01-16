package smarthome.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ReadingList {

    private List<Reading> mReadingList;

    public ReadingList(){
        mReadingList = new ArrayList<> ();
    }

    public Reading newReading (double readValue, Calendar timeOfReading) {
        return new Reading (readValue,timeOfReading);
    }

    public boolean addReading(Reading newReading) {
        if (mReadingList.contains(newReading) || (newReading == null))
            return false;
        return mReadingList.add(newReading);
    }

    public List<Reading> getReadingList(){
        return mReadingList;
    }

    /**
     * Method to get total rainfall in a given day
     *
     * @param inputDate
     * @return totalRainfallValue
     */

    public double totalValueInGivenDay(GregorianCalendar inputDate) {
        GregorianCalendar date = inputDate;
        double totalRainfallValue = 0;

        for (Reading reading: mReadingList)
            if(reading.getDateAndTime().equals(inputDate))
            totalRainfallValue = reading.returnValueOfReading() + totalRainfallValue;

        return totalRainfallValue;
    }

}