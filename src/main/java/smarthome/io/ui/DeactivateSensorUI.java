package smarthome.io.ui;

import smarthome.controller.cli.DeactivateSensorCTRL;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;
import smarthome.model.GAList;

import java.util.Calendar;
import java.util.List;

public class DeactivateSensorUI {


    DeactivateSensorCTRL controller;
    private GeographicalAreaDTO selectedGADto;
    private static final String DESIGNATION = ". Designation: ";
    private List<GeographicalAreaDTO> gaListDTO;
    private String gaDTOId;
    private List<SensorDTO> sensorListDTO;
    private String sensorDTOId;
    private Calendar date;
    private SensorDTO sensorDTO;

    public DeactivateSensorUI(GAList gaList) {
        this.controller = new DeactivateSensorCTRL (gaList);
    }

    /**
     * Start by checking if the GAList is empty.
     */
    public void run() {
        this.gaListDTO = this.controller.getGAList ();
        if (gaListDTO.isEmpty ()) {
            System.out.println ("There are no Geographical Areas. Please ask the Administrator to create/import them.\n");
            return;
        }
        this.selectGA ();
    }

    /**
     * Show the GAList and request to select one
     */
    private void selectGA() {
        System.out.println ("Please choose a Geographical Area from the list below:");
        int counter = 1;
        for (GeographicalAreaDTO gaDTO : gaListDTO) {
            System.out.print(counter++ + ". Id: " + gaDTO.getIdentification() + DESIGNATION + gaDTO.getDesignation() + ".\n");
        }
        int index = UtilsUI.requestIntegerInInterval (1, gaListDTO.size (), "Please choose a valid option.");
        index--;
        this.selectedGADto = gaListDTO.get (index);
        this.checkIfSensorListEmpty ();

    }

    /**
     * Check if the SensorList in the GA is empty
     */
    private void checkIfSensorListEmpty() {
        this.gaDTOId = selectedGADto.getIdentification ();
        if (this.selectedGADto.getSensorListDTO ().isEmpty ()) {
            System.out.println ("The Geographical Area has no sensors. Please ask the Administrator to create/import them.\n");
            return;
        }
        this.checkIfSensorsActive ();
    }

    /**
     * Check if there are active sensor in the SensorList
     */
    private void checkIfSensorsActive() {
        this.sensorListDTO = this.controller.getSensorIfActiveDto (gaDTOId);
        if (sensorListDTO.isEmpty ()) {
            System.out.println ("There are no active sensors.\n");
            return;
        }
        this.selectSensor ();
    }

    /**
     * Show active sensors and ask to select one
     */
    private void selectSensor() {
        System.out.println ("Choose an active Sensors from the list below:");
        int counter = 1;
        for (SensorDTO sensor : sensorListDTO) {
            System.out.print(counter++ + " Id: " + sensor.getId() + DESIGNATION + sensor.getDesignation() + ". Active: " + sensor.isActive() + "\n");
        }
        int index = UtilsUI.requestIntegerInInterval (1, sensorListDTO.size (), "Please choose a valid option");
        index--;
        this.sensorDTO = sensorListDTO.get (index);
        this.sensorDTOId = sensorDTO.getId ();
        this.insertDate ();

    }

    /**
     * Insert the deactivation date of the sensor
     */
    private void insertDate() {
        boolean condition = true;
        while (condition) {
            System.out.println ("Insert the deactivation date for the sensor in yyyy-MM-dd format:");
            this.date = UtilsUI.requestDate ("Please insert a valid date (yyyy-MM-dd format).");
            if (this.sensorDTO.getStartDate().before(date)) {
                condition = false;
                this.deactivateSensor ();
            } else
                System.out.println ("Set the deactivation date after the creation of the sensor ");
        }
    }

    /**
     * Deactivate the sensor and show relevant information
     */
    private void deactivateSensor() {
        if (this.controller.deactivateSensor (gaDTOId, sensorDTOId, date)) {
            System.out.println ("The following sensor was successfully deactivated from the geographical area " + this.selectedGADto.getDesignation () + ":");
            System.out.println(" - Id: " + this.sensorDTO.getId() + DESIGNATION + this.sensorDTO.getDesignation() + ". Active: " + this.controller.sensorStatus(gaDTOId, sensorDTOId) + ".\n");
        }
    }


}
