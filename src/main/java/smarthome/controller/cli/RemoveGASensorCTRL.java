package smarthome.controller.cli;

import org.apache.log4j.Logger;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.mapper.GeographicalAreaMapper;
import smarthome.model.*;
import smarthome.repository.Repositories;

import java.util.List;

public class RemoveGASensorCTRL {
    private final GAList gaList;
    private final GeographicalAreaMapper gaMapper = new GeographicalAreaMapper();
    static final Logger log = Logger.getLogger(RemoveGASensorCTRL.class);


    public RemoveGASensorCTRL(GAList gaList) {
        this.gaList = gaList;
    }


    /**
     * Method that iterates the geographical area list and converts each geographical area in a geographical area DTO which has only the necessary attributes
     * to display to the user.
     *
     * @return a list of geographical area DTOs with attributes id, designation, typeDTO and sensorListDTO
     */
    public List<GeographicalAreaDTO> getGAListDTO() {
        return gaMapper.toDtoList(gaList);
    }

    /**
     * This method iterates the geographical area comparing for each geographical area its id with the id passed as parameter.
     *
     * @param gaDTOId String value correspondent to the id of the geographical area passed as parameter
     * @return geographical area object if its id matches the parameter gaDTOid, else throws a null pointer exception.
     */
    private GeographicalArea getGAById(String gaDTOId) {
        for (GeographicalArea geographicalArea : gaList.getGAList()) {
            if (geographicalArea.getIdentification().matches(gaDTOId)) {
                return geographicalArea;
            }
        }
        throw new NullPointerException();
    }


    /**
     * This method looks for the geographical area with the same Id as gaDTOId parameter and then looks
     * within its sensorList for the sensor which has the same id as the sensorDTOid parameter
     *
     * @param gaDTOId     String value correspondent to the geographical area DTO id
     * @param sensorDTOId String value correspondent to the geographical area DTO id
     * @return true if sensor is removed from the geographical area sensor list, otherwise returns false.
     */
    public boolean removeSensor(String gaDTOId, String sensorDTOId) {
        GeographicalArea ga = getGAById(gaDTOId);
        SensorList sensorList = ga.getSensorListInGa();
        for (Sensor sensor : sensorList.getSensorList()) {
            if (sensor.getId().matches(sensorDTOId)) {
                sensorList.removeSensor(sensor);
                try {
                    //Repository call
                    ExternalSensor externalSensor = (ExternalSensor) sensor;
                    Repositories.getExternalSensorRepository().delete(externalSensor);
                } catch (NullPointerException e) {
                    log.warn("Repository unreachable");
                }
                return true;
            }
        }
        return false;
    }

}
