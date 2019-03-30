package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;
import smarthome.mapper.GeographicalAreaMapper;
import smarthome.mapper.SensorMapper;
import smarthome.model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class DeactivateSensorCTRLTest {

    @Test
    @DisplayName("Check the correct size of the gaList")
    void getGAList() {
        GAList gaList = new GAList ();
        //ga Porto created and added to gaList
        OccupationArea portoOA = new OccupationArea (25, 20);
        Location portoLoc = new Location (25, 12, 29);
        GeographicalArea porto = new GeographicalArea ("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA (porto);
        //ga Lisbon created and added to gaList
        OccupationArea lisOA = new OccupationArea (35, 20);
        Location lisLoc = new Location (55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea ("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA (lisbon);

        GeographicalAreaMapper areaMapper = new GeographicalAreaMapper ();
        areaMapper.toDtoList (gaList);

        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL (gaList);

        int result = ctrl.getGAList ().size ();

        assertEquals (2, result);
    }

    @Test
    @DisplayName("Check the correct size of the gaList")
    void getGAListSize() {
        GAList gaList = new GAList ();
        OccupationArea portoOA = new OccupationArea (25, 20);
        Location portoLoc = new Location (25, 12, 29);
        GeographicalArea porto = new GeographicalArea ("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA (porto);
        OccupationArea lisOA = new OccupationArea (35, 20);
        Location lisLoc = new Location (55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea ("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA (lisbon);
        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL (gaList);

        int expected = 2;
        int result = ctrl.getGAListSize ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Deactivate a active sensor")
    void deactivateSensorCorrectly() {
        GAList gaList = new GAList ();
        OccupationArea portoOA = new OccupationArea (25, 20);
        Location portoLoc = new Location (25, 12, 29);
        GeographicalArea porto = new GeographicalArea ("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA (porto);

        OccupationArea lisOA = new OccupationArea (35, 20);
        Location lisLoc = new Location (55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea ("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA (lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGA ();
        Location sLoc = new Location (55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar (2019, 2, 2);
        SensorType sensorType = new SensorType ("Temperature");
        Sensor sensor = new Sensor ("TL1023", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList ());
        lisbonSensorList.addSensor (sensor);

        SensorMapper sMapper = new SensorMapper ();
        SensorDTO sensorDTO = sMapper.toDto (sensor);
        List<SensorDTO> sensorListDTO = new ArrayList<> ();
        sensorListDTO.add (sensorDTO);

        GeographicalAreaDTO lisbonDTO = new GeographicalAreaDTO ("LIS", "Lisbon", sensorListDTO);
        String gaDTOId = lisbonDTO.getIdentification ();
        String sensorDTOId = sensorDTO.getId ();
        GregorianCalendar pDate = new GregorianCalendar (2019, 2, 2);

        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL (gaList);
        boolean result = ctrl.deactivateSensor (gaDTOId, sensorDTOId, pDate);
        assertTrue (result);
    }

    @Test
    void getSensorIfActiveToDto() {
        GAList gaList= new GAList();
        OccupationArea lisOA= new OccupationArea(35,20);
        Location lisLoc= new Location(55,22,29);
        GeographicalArea lisbon= new GeographicalArea("LIS","Lisbon","City",lisOA,lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList=lisbon.getSensorListInGA();

        Location sLoc= new Location(55,21,26);
        GregorianCalendar sDate=new GregorianCalendar(2019,2,2);
        SensorType sensorType=new SensorType("Temperature");
        Sensor sensor= new Sensor("TL1023","TemperatureSensor",sDate,sLoc,sensorType,"Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor);
        GregorianCalendar pDate=new GregorianCalendar(2019,8,2);
        sensor.deactivate (pDate);

        SensorMapper sMapper= new SensorMapper();
        SensorDTO sensorDTO=sMapper.toDto(sensor);
        List<SensorDTO> sensorListDTO= new ArrayList<>();
        sensorListDTO.add(sensorDTO);

        GeographicalAreaDTO lisbonDTO= new GeographicalAreaDTO("LIS","Lisbon",sensorListDTO);
        String gaDTOId=lisbonDTO.getIdentification();

        DeactivateSensorCTRL ctrl= new DeactivateSensorCTRL (gaList);

        int result=ctrl.getSensorIfActiveDto (gaDTOId).size ();

        assertEquals(0,result);
    }



    @Test
    @DisplayName("Returns geographical area Lisbon when searching for a geographical area with Id=LIS")
    void getGAById() {
        GAList gaList= new GAList();
        OccupationArea portoOA= new OccupationArea(25,20);
        Location portoLoc= new Location(25,12,29);
        GeographicalArea porto= new GeographicalArea("POR","Porto","City",portoOA,portoLoc);
        gaList.addGA(porto);
        OccupationArea lisOA= new OccupationArea(35,20);
        Location lisLoc= new Location(55,22,29);
        GeographicalArea lisbon= new GeographicalArea("LIS","Lisbon","City",lisOA,lisLoc);
        gaList.addGA(lisbon);
        DeactivateSensorCTRL ctrl= new DeactivateSensorCTRL (gaList);
        GeographicalArea result=ctrl.getGAById("LIS");
        assertEquals(lisbon,result);
    }

    @Test
    @DisplayName("Returns Null Pointer Exception when searching for an nonexistent geographical area Id")
    void getGAByIdReturnsNullPointerException() {
        GAList gaList= new GAList();
        OccupationArea portoOA= new OccupationArea(25,20);
        Location portoLoc= new Location(25,12,29);
        GeographicalArea porto= new GeographicalArea("POR","Porto","City",portoOA,portoLoc);
        gaList.addGA(porto);
        OccupationArea lisOA= new OccupationArea(35,20);
        Location lisLoc= new Location(55,22,29);
        GeographicalArea lisbon= new GeographicalArea("LIS","Lisbon","City",lisOA,lisLoc);
        gaList.addGA(lisbon);
        DeactivateSensorCTRL ctrl= new DeactivateSensorCTRL (gaList);
        boolean thrown = false;
        try {
            GeographicalArea ga=ctrl.getGAById("coim");
        } catch (NullPointerException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    @DisplayName("Don´t return geographical area Lisbon when searching for a geographical area with Id=POR")
    void getGAByIdNotEquals() {
        GAList gaList= new GAList();
        OccupationArea portoOA= new OccupationArea(25,20);
        Location portoLoc= new Location(25,12,29);
        GeographicalArea porto= new GeographicalArea("POR","Porto","City",portoOA,portoLoc);
        gaList.addGA(porto);
        OccupationArea lisOA= new OccupationArea(35,20);
        Location lisLoc= new Location(55,22,29);
        GeographicalArea lisbon= new GeographicalArea("LIS","Lisbon","City",lisOA,lisLoc);
        gaList.addGA(lisbon);
        DeactivateSensorCTRL ctrl= new DeactivateSensorCTRL (gaList);
        GeographicalArea result=ctrl.getGAById("POR");
        assertNotEquals(lisbon,result);
    }







    @Test
    @DisplayName("Display true because sensor is active")
    void sensorStatusTrue() {
        GAList gaList = new GAList ();
        OccupationArea lisOA= new OccupationArea(35,20);
        Location lisLoc= new Location(55,22,29);
        GeographicalArea lisbon= new GeographicalArea("LIS","Lisbon","City",lisOA,lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList=lisbon.getSensorListInGA();

        Location sLoc= new Location(55,21,26);
        GregorianCalendar sDate=new GregorianCalendar(2019,2,2);
        SensorType sensorType=new SensorType("Temperature");

        Sensor sensor1= new Sensor("TL1023","TemperatureSensor",sDate,sLoc,sensorType,"Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor1);
        Sensor sensor2= new Sensor("TL1024","TemperatureSensor",sDate,sLoc,sensorType,"Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor2);

        SensorMapper sMapper= new SensorMapper();
        SensorDTO sensor1DTO=sMapper.toDto(sensor1);
        SensorDTO sensor2DTO=sMapper.toDto(sensor2);
        List<SensorDTO> sensorListDTO= new ArrayList<>();
        sensorListDTO.add(sensor1DTO);
        sensorListDTO.add(sensor2DTO);

        GeographicalAreaDTO lisbonDTO= new GeographicalAreaDTO("LIS","Lisbon",sensorListDTO);
        String gaDTOId=lisbonDTO.getIdentification();
        String sensorDTOId=sensor1DTO.getId();

        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL (gaList);

        boolean result = ctrl.sensorStatus (gaDTOId,sensorDTOId);

        assertTrue (result);
    }

    @Test
    @DisplayName("Display false because sensor is deactivated")
    void sensorStatusFalse() {
        GAList gaList = new GAList ();
        OccupationArea lisOA= new OccupationArea(35,20);
        Location lisLoc= new Location(55,22,29);
        GeographicalArea lisbon= new GeographicalArea("LIS","Lisbon","City",lisOA,lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList=lisbon.getSensorListInGA();

        Location sLoc= new Location(55,21,26);
        GregorianCalendar sDate=new GregorianCalendar(2019,2,2);
        SensorType sensorType=new SensorType("Temperature");

        Sensor sensor1= new Sensor("TL1023","TemperatureSensor",sDate,sLoc,sensorType,"Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor1);
        Sensor sensor2= new Sensor("TL1024","TemperatureSensor",sDate,sLoc,sensorType,"Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor2);

        GregorianCalendar pDate=new GregorianCalendar(2019,2,2);
        sensor2.deactivate (pDate);

        SensorMapper sMapper= new SensorMapper();
        SensorDTO sensor1DTO=sMapper.toDto(sensor1);
        SensorDTO sensor2DTO=sMapper.toDto(sensor2);
        List<SensorDTO> sensorListDTO= new ArrayList<>();
        sensorListDTO.add(sensor1DTO);
        sensorListDTO.add(sensor2DTO);

        GeographicalAreaDTO lisbonDTO= new GeographicalAreaDTO("LIS","Lisbon",sensorListDTO);
        String gaDTOId=lisbonDTO.getIdentification();
        String sensorDTOId=sensor2DTO.getId();

        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL (gaList);

        boolean result = ctrl.sensorStatus (gaDTOId,sensorDTOId);

        assertFalse(result);
    }
}