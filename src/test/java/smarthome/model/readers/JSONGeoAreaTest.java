package smarthome.model.readers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;



class JSONGeoAreaTest {

    TypeGAList typeGAList = TypeGAList.getTypeGAListInstance();

    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance = House.class.getDeclaredField("theHouse");
        instance.setAccessible(true);
        instance.set(null, null);
        Field instance2 = TypeGAList.class.getDeclaredField("typeGaList");
        instance2.setAccessible(true);
        instance2.set(null, null);
    }

    @Test
    void importGAListSize() throws org.json.simple.parser.ParseException, java.text.ParseException, IOException{
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");
        JSONGeoArea reader = new JSONGeoArea();

        int expected = 2;
        int result = reader.loadData(path).size();

        assertEquals(expected,result);
    }

    @Test
    void checkIfImportGANotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.loadData(path);

        GeographicalArea porto = gaListInFile.get(1);
        String expected = "city";
        String result = porto.getName();
        assertEquals(expected, result);
    }

    @Test
    void checkIfImportOccupationAreaNotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.loadData(path);

        GeographicalArea porto = gaListInFile.get(1);
        double expected = 10.09;
        double result = porto.getOccupation().getWidth();
        assertEquals(expected, result);
    }

    @Test
    void checkIfImportLocationNotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.loadData(path);

        GeographicalArea porto = gaListInFile.get(1);

        double expected = 41.149935;
        double result = porto.getLocation().getLatitude();
        assertEquals(expected, result);
    }

    @Test
    void checkIfImportSensorListNotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.loadData(path);
        GeographicalArea porto = gaListInFile.get(1);
        List<Sensor> sensorList = porto.getSensorListInGa().getSensorList();

        int expected = 2;
        int result = sensorList.size();
        assertEquals(expected, result);
    }

    @Test
    void checkIfImportSensorNotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources_tests/DataSet_sprint06_GA.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.loadData(path);
        GeographicalArea porto = gaListInFile.get(1);
        List<Sensor> sensorList = porto.getSensorListInGa().getSensorList();
        Sensor sensor = sensorList.get(1);

        String expected = "TT1AC746";
        String result = sensor.getId();
        assertEquals(expected, result);
    }

    @Test
    void checkIfImportSensorDateNotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources_tests/DataSet_sprint05_GA.json");
        JSONGeoArea reader = new JSONGeoArea();

        List<GeographicalArea> gaListInFile = reader.loadData(path);
        GeographicalArea porto = gaListInFile.get(1);
        List<Sensor> sensorList = porto.getSensorListInGa().getSensorList();
        Sensor sensor = sensorList.get(1);

        GregorianCalendar expected = new GregorianCalendar(2017,Calendar.NOVEMBER,16);
        Calendar result = sensor.getSensorBehavior().getStartDate();
        assertEquals(expected, result);
    }

    @Test
    void checkIfImportSensorTypeNotNull () throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        Path path = Paths.get("resources_tests/DataSet_sprint07_GA.json");
        JSONGeoArea reader = new JSONGeoArea();
        SensorType temperature = new SensorType("temperature");
        SensorType rainfall = new SensorType("rainfall");


        TypeGAList.addTypeGA(TypeGAList.newTypeGA("city"));
        TypeGAList.addTypeGA(TypeGAList.newTypeGA("urban area"));

        List<GeographicalArea> gaListInFile = reader.loadData(path);
        GeographicalArea porto = gaListInFile.get(1);
        List<Sensor> sensorList = porto.getSensorListInGa().getSensorList();
        Sensor sensor = sensorList.get(1);

        String expected = "temperature";
        String result = sensor.getSensorBehavior().getSensorType().getType().getName();
        assertEquals(expected, result);
    }
}
