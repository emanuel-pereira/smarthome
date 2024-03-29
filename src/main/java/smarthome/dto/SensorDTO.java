package smarthome.dto;

import java.util.Calendar;
import java.util.List;

public class SensorDTO {

    private static final boolean ACTIVE = true;
    private String designation;
    private SensorTypeDTO sensorTypeDTO;
    private List<ReadingDTO> readingListDTO;
    private String id;
    private Calendar startDate;


    public SensorDTO() {
    }

    public SensorDTO(String id, String designation, List<ReadingDTO> readingListDTO) {
        this.id = id;
        this.designation = designation;
        this.readingListDTO = readingListDTO;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SensorTypeDTO getSensorType() {
        return sensorTypeDTO;
    }

    public void setSensorType(SensorTypeDTO sensorTypeDTO) {
        this.sensorTypeDTO = sensorTypeDTO;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate= startDate;
    }

    public List<ReadingDTO> getReadingListDTO() {
        return readingListDTO;
    }

    public Calendar getStartDate() {
        return this.startDate;
    }

    public boolean isActive() {
        return ACTIVE;
    }
}
