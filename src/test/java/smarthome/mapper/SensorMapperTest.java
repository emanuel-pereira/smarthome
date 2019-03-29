package smarthome.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.dto.SensorDTO;
import smarthome.model.ReadingList;
import smarthome.model.Sensor;
import smarthome.model.SensorType;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class SensorMapperTest {

    @Test
    @DisplayName("Ensure that sensor and sensorDTO have the same string value for id attribute")
    void toDtoReturnsSameIdAsInSensor() {
        SensorType temperature = new SensorType("Temperature");
        ReadingList readingList = new ReadingList();
        Sensor sensor = new Sensor("Temperature Sensor", new GregorianCalendar(2019, 1, 5), temperature, "Celsius", readingList);
        SensorMapper sensorMapper= new SensorMapper();
        SensorDTO sensorDTO=sensorMapper.toDto(sensor);
        String expected=sensor.getId();
        String result=sensorDTO.getId();
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that sensor and sensorDTO have the same string value for designation")
    void toDtoReturnsSameDesignationAsInSensor() {
        SensorType temperature = new SensorType("Temperature");
        ReadingList readingList = new ReadingList();
        Sensor sensor = new Sensor("Temperature Sensor", new GregorianCalendar(2019, 1, 5), temperature, "Celsius", readingList);
        SensorMapper sensorMapper= new SensorMapper();
        SensorDTO sensorDTO=sensorMapper.toDto(sensor);
        String expected=sensor.getDesignation();
        String result=sensorDTO.getDesignation();
        assertEquals(expected,result);
        assertNotNull(sensorDTO.getSensorType());


    }


    @Test
    @DisplayName("Ensure that sensor and sensorDTO have the same string value for designation")
    void toDtoReturnsSameTypeAsInSensor() {
        SensorType temperature = new SensorType("Temperature");
        ReadingList readingList = new ReadingList();
        Sensor sensor = new Sensor("Temperature Sensor", new GregorianCalendar(2019, 1, 5), temperature, "Celsius", readingList);
        SensorMapper sensorMapper= new SensorMapper();
        SensorDTO sensorDTO=sensorMapper.toDto(sensor);
        String expected=sensor.getSensorType().getType();
        String result=sensorDTO.getSensorType().getSensorType();
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that sensorDTO does not return an empty designation")
    void toDtoDoesNotReturnEmptyDesignation() {
        SensorType temperature = new SensorType("Temperature");
        ReadingList readingList = new ReadingList();
        Sensor sensor = new Sensor("Temperature Sensor", new GregorianCalendar(2019, 1, 5), temperature, "Celsius", readingList);
        SensorMapper sensorMapper= new SensorMapper();
        SensorDTO sensorDTO=sensorMapper.toDto(sensor);
        String expected="";
        String result=sensorDTO.getDesignation();
        assertNotEquals(expected,result);
    }

}