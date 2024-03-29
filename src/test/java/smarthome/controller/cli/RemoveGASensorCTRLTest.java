package smarthome.controller.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;
import smarthome.mapper.SensorMapper;
import smarthome.model.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RemoveGASensorCTRLTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal", loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);
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
    @DisplayName("Ensure that getGAListDTO method returns an empty gaListDTO if the gaList is empty")
    void getGAListDTOEmptyList() {
        GAList gaList = new GAList();
        RemoveGASensorCTRL ctrl = new RemoveGASensorCTRL(gaList);
        List<GeographicalAreaDTO> expected = new ArrayList<>();
        List<GeographicalAreaDTO> result = ctrl.getGAListDTO();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that getGAListDTO method returns a gaListDTO of size the gaList is empty")
    void getGAListDTO() {
        GAList gaList = new GAList();
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);
        RemoveGASensorCTRL ctrl = new RemoveGASensorCTRL(gaList);
        int expected = 2;
        int result = ctrl.getGAListDTO().size();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that getGAListDTO method does not return an empty gaListDTO")
    void getGAListDTONotEmpty() {
        GAList gaList = new GAList();
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);
        RemoveGASensorCTRL ctrl = new RemoveGASensorCTRL(gaList);
        int expected = 0;
        int result = ctrl.getGAListDTO().size();
        assertNotEquals(expected, result);
    }


    @Test
    @DisplayName("Ensure that removeSensor method removes sensor from the sensor list of lisbon")
    void removeSensor() {
        GAList gaList = new GAList();
        //ga Porto created and added to gaList
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);
        //ga Lisbon created and added to gaList
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGa();
        //sensor created and added to lisSensorList
        Location sLoc = new Location(55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar(2019, 2, 2);
        SensorType sensorType = new SensorType("Temperature");
        Sensor sensor = new ExternalSensor("TL1023", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor);

        //created sensorDTO from sensor
        SensorMapper sMapper = new SensorMapper();
        SensorDTO sensorDTO = sMapper.toDto(sensor);
        List<SensorDTO> sensorListDTO = new ArrayList<>();
        sensorListDTO.add(sensorDTO);

        //created geographical area DTO with same id as geographicalArea lisbon
        GeographicalAreaDTO lisbonDTO = new GeographicalAreaDTO("LIS", "Lisbon", sensorListDTO);
        String gaDTOId = lisbonDTO.getIdentification();
        String sensorDTOId = sensorDTO.getId();

        RemoveGASensorCTRL ctrl = new RemoveGASensorCTRL(gaList);
        boolean result = ctrl.removeSensor(gaDTOId, sensorDTOId);
        assertTrue(result);

        int expectedSize = 0;
        int resultingSize = lisbonSensorList.size();
        assertEquals(expectedSize, resultingSize);
    }


    @Test
    @DisplayName("Ensure that removeSensor method does not remove sensor for invalid geographical area id")
    void removeSensorForInvalidGAIdReturnsFalse() {
        GAList gaList = new GAList();
        //ga Porto created and added to gaList
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);
        //ga Lisbon created and added to gaList
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGa();
        //sensor created and added to lisSensorList
        Location sLoc = new Location(55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar(2019, 2, 2);
        SensorType sensorType = new SensorType("Temperature");
        Sensor sensor = new ExternalSensor("TL1023", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor);

        //created sensorDTO from sensor
        SensorMapper sMapper = new SensorMapper();
        SensorDTO sensorDTO = sMapper.toDto(sensor);
        List<SensorDTO> sensorListDTO = new ArrayList<>();
        sensorListDTO.add(sensorDTO);

        //created geographical area DTO with same id as geographicalArea lisbon
        GeographicalAreaDTO lisbonDTO = new GeographicalAreaDTO("LIS", "Lisbon", sensorListDTO);
        String gaDTOId = "Ga";
        String sensorDTOId = sensorDTO.getId();
        RemoveGASensorCTRL ctrl = new RemoveGASensorCTRL(gaList);

        boolean thrown = false;
        try {
            ctrl.removeSensor(gaDTOId, sensorDTOId);
        } catch (NullPointerException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    @DisplayName("Ensure that removeSensor method does not remove sensor for invalid sensor id")
    void removeSensorForInvalidSensorIdReturnsFalse() {
        GAList gaList = new GAList();
        //ga Porto created and added to gaList
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);
        //ga Lisbon created and added to gaList
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGa();
        //sensor created and added to lisSensorList
        Location sLoc = new Location(55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar(2019, 2, 2);
        SensorType sensorType = new SensorType("Temperature");
        Sensor sensor = new ExternalSensor("TL1023", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor);

        //created sensorDTO from sensor
        SensorMapper sMapper = new SensorMapper();
        SensorDTO sensorDTO = sMapper.toDto(sensor);
        List<SensorDTO> sensorListDTO = new ArrayList<>();
        sensorListDTO.add(sensorDTO);

        //created geographical area DTO with same id as geographicalArea lisbon
        GeographicalAreaDTO lisbonDTO = new GeographicalAreaDTO("LIS", "Lisbon", sensorListDTO);
        String gaDTOId = lisbonDTO.getIdentification();
        String sensorDTOId = "invalidID";
        RemoveGASensorCTRL ctrl = new RemoveGASensorCTRL(gaList);
        boolean result = ctrl.removeSensor(gaDTOId, sensorDTOId);
        assertFalse(result);
    }
}