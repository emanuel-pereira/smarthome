package smarthome.io.ui;

import smarthome.controller.DataImportCTRL;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DataImportUI {
    private DataImportCTRL ctrl;
    private Path filePath;
    private List<GeographicalArea> gaListInFile = new ArrayList<>();

    public DataImportUI(GAList gaList) {
        this.ctrl = new DataImportCTRL(gaList);
    }


    public void loadGeoAreaFile() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, org.json.simple.parser.ParseException, java.text.ParseException  {
        boolean loop = true;
        while (loop) {
            System.out.println("Please enter the json file path to import geographical areas and sensors (eg: resources/JsonFile.json):");
            String filepath = UtilsUI.requestText("Invalid filepath.", ".*");

            try {
                this.filePath = Paths.get(filepath);
                gaListInFile = ctrl.readGeoAreasFromFile(this.filePath);
                loop = false;
                this.showGAsNumberInFile();
            } catch (FileNotFoundException e) {
                UtilsUI.showError("File not found.", "Json file not found in the specified file path: " + filepath);
            }
        }
    }

    public void showGAsNumberInFile() throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException   {
        System.out.println("In the file there are\n");
        System.out.println(" - " + gaListInFile.size() + " geographical area(s).");
        int nrOfSensors = 0;
        for (GeographicalArea geographicalArea : gaListInFile) {
            nrOfSensors += geographicalArea.getSensorListInGA().size();
        }
        System.out.println(" - " + nrOfSensors + " sensor(s).");
        this.importGAs();
    }

    public void importGAs()throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException, org.json.simple.parser.ParseException, java.text.ParseException  {
        System.out.println("\n------\n");
        if(UtilsUI.confirmOption("Do you wish to import this data?(y/n)","Please type y for Yes or n for No.")){
            ctrl.importGeoAreasFromFile(this.filePath);
            int notImported = ctrl.failedToAdd();
            int imported = gaListInFile.size() - notImported;
            System.out.println("Success!" + imported+ "geographical areas and respective sensors were imported.");
            if(notImported > 0){
                System.out.println("Warning: " + notImported + "geographical areas were not imported");
                UtilsUI.backToMenu();
            }
            else {
                UtilsUI.backToMenu();
            }
        }
    }

    public void importDataFromCSVFile() throws org.json.simple.parser.ParseException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        boolean loop = true;
        while (loop) {
            System.out.println("Please insert the directory and the name of the file (eg: resources/DataSet_sp04_SensorData.csv):");
            String filepath = UtilsUI.requestText("Invalid filepath.", "[A-Za-z0-9/._]*");
            Path path = Paths.get(filepath);
            try {
                ctrl.importReadingsFromFile(path);
                loop = false;
                System.out.println("Success!");
                this.showReadings();

            } catch (FileNotFoundException e) {
                System.out.println("CSV file not found in the specified file path: " + filepath);
            }
        }
    }

    public void showReadings() {
        System.out.println("The following geographical areas and respective sensors were imported from the selected JsonFile:");
        for (GeographicalAreaDTO geographicalAreaDTO : ctrl.getGAListDTO()) {
            System.out.println("GEOGRAPHICAL AREA");
            System.out.println(" Id: " + geographicalAreaDTO.getIdentification());
            System.out.println(" Name: " + geographicalAreaDTO.getDesignation());
            System.out.println(" SensorList: ");
            int counter = 1;
            for (SensorDTO sensorDTO : geographicalAreaDTO.getSensorListDTO()) {
                System.out.print("  " + counter + " - Sensor Id: " + sensorDTO.getId());
                System.out.println(" | Name " + sensorDTO.getDesignation());
                System.out.println("Number of readings imported: " + sensorDTO.getReadingList().size());
                counter++;
            }
        }
    }
}
