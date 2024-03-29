package smarthome.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarthome.dto.SensorTypeDTO;
import smarthome.mapper.SensorTypeMapper;
import smarthome.model.SensorType;
import smarthome.model.SensorTypeList;
import smarthome.model.validations.Name;
import smarthome.repository.Repositories;
import smarthome.repository.SensorTypeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SensorTypeService {

    private SensorTypeMapper mapper;
    private SensorTypeList sensorTypeList;
    private SensorTypeRepository sensorTypeRepository;

    @Autowired
    public SensorTypeService(SensorTypeRepository sensorTypeRepository) {
        this.mapper = new SensorTypeMapper();
        this.sensorTypeRepository = sensorTypeRepository;
        this.sensorTypeList = new SensorTypeList();
    }

    public SensorTypeService(SensorTypeList sensorTypeList) {
        this.mapper = new SensorTypeMapper();
        this.sensorTypeList = sensorTypeList;
    }

    public SensorTypeService() {
    }

    void injectRepository() {
        if (this.sensorTypeRepository == null) {
            sensorTypeRepository = Repositories.getSensorTypeRepository();
        }
    }

    /**
     * Method to creates and adds a sensor type to the database if the sensor type doesn't already exist.
     *
     * @param type - String that names the type of data
     * @return new data type object with designation
     */
    public boolean createSensorType(SensorTypeDTO type) {
        injectRepository();
        SensorType sensorType = convertToEntity(type);
        if (sensorTypeRepository.existsByType(sensorType.getType())) {
            return false;
        }
        sensorTypeRepository.save(sensorType);
        this.sensorTypeList.addSensorType(sensorType);
        return true;
    }

    private SensorType convertToEntity(SensorTypeDTO dto) {
        return mapper.toEntity(dto);
    }

    /**
     * @return the number of sensor types persisted in the database
     */
    public long size() {
        injectRepository();
        return sensorTypeRepository.count();

    }


    /**
     * Method to return all sensor types included in the repository as dto objects
     *
     * @return list of sensor types as DTO
     */
    public List<SensorTypeDTO> findAll() {
        injectRepository();
        List<SensorTypeDTO> sensorTypeDTOList = new ArrayList<>();
        sensorTypeRepository.findAll().forEach(sensorType -> {
            SensorTypeDTO sensorTypeDTO = mapper.toDto(sensorType);
            sensorTypeDTOList.add(sensorTypeDTO);
        });
        return sensorTypeDTOList;
    }


    /**
     * Some SensorTypes are required in some User Stories, so this method checks if a mandatory sensor type exists
     *
     * @param type sensor type designation
     * @return true if exists and false if not
     */
    public boolean existsByType(String type) {
        injectRepository();
        Name repoType = new Name(type);
        return sensorTypeRepository.existsByType(repoType);
    }

    public boolean existsByID(Long id) {
        injectRepository();
        return sensorTypeRepository.existsById(id);
    }

    public SensorType findByType(String type) {
        injectRepository();
        Name repoType = new Name(type);
        return sensorTypeRepository.findByType(repoType);
    }
}
