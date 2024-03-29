package smarthome.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.dto.SensorTypeDTO;
import smarthome.model.SensorType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SensorTypeMapperTest {

    @Test
    @DisplayName("Ensure that sensorType is converted to sensorType DTO")
    void toDto() {
        SensorType sensorType= new SensorType("City");
        SensorTypeMapper sensorTypeMapper=new SensorTypeMapper();
        SensorTypeDTO sensorTypeDTO=sensorTypeMapper.toDto(sensorType);
        String expected=sensorType.getType().getName();
        String result=sensorTypeDTO.getType();
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that sensorTypeDTO does not return an empty string as sensorType")
    void toDtoDoesNotReturnEmptyStringAsType() {
        SensorType sensorType= new SensorType("City");
        SensorTypeMapper sensorTypeMapper=new SensorTypeMapper();
        SensorTypeDTO sensorTypeDTO=sensorTypeMapper.toDto(sensorType);
        String expected="";
        String result=sensorTypeDTO.getType();
        assertNotEquals(expected,result);

    }
}