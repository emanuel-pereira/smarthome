package smarthome.model.readers;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import smarthome.model.*;
import smarthome.model.validations.Name;
import smarthome.repository.Repositories;
import smarthome.repository.SensorTypeRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class JSONGeoArea implements FileReaderGeoArea {
    private Path filePath;
    private final JSONParser parser = new JSONParser();
    static final Logger log = Logger.getLogger(JSONHouse.class);
    private SensorTypeRepository sensorTypeRepository;



    public JSONGeoArea() {
        //this constructor is empty so we can use reflection to choose the correct reader
    }

    private void injectRepository(){
        if(this.sensorTypeRepository==null){
            this.sensorTypeRepository=Repositories.getSensorTypeRepository();
        }
    }

    private JSONObject readFile() throws IOException, ParseException {
        java.io.FileReader fileReader = new java.io.FileReader(filePath.toAbsolutePath().toFile());
        return (JSONObject) this.parser.parse(fileReader);
    }

    public List<GeographicalArea> loadData(Path filePath) throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        this.filePath = filePath;
        List<GeographicalArea> gaList = new ArrayList<>();
        JSONObject jsonGAs = (JSONObject) this.readFile().get("geographical_area_list");
        JSONArray jsonGAList = (JSONArray) jsonGAs.get("geographical_area");

        for (Object ga : jsonGAList) {
            JSONObject jsonGA = (JSONObject) ga;
            GeographicalArea geoArea = importGA(jsonGA);
            List<Sensor> gaSensorList = geoArea.getSensorListInGa().getSensorList();
            importSensorList(jsonGA, gaSensorList, geoArea);
            gaList.add(geoArea);
        }
        return gaList;
    }

    private GeographicalArea importGA(JSONObject jsonGA) {
        String id = (String) jsonGA.get("id");
        String description = (String) jsonGA.get("description");
        String type = jsonGA.get("type").toString();
        TypeGA typeGA = TypeGAList.get(type);
        if (typeGA == null)
            typeGA = new TypeGA(type);
        Location location = importLocation(jsonGA);
        OccupationArea occupationArea = importOccupationArea(jsonGA);
        return new GeographicalArea(id, description, typeGA, occupationArea, location);
    }

    private OccupationArea importOccupationArea(JSONObject jsonObject) {

        String widthString = jsonObject.get("width").toString();
        double width = Double.parseDouble(widthString);
        String lengthString = jsonObject.get("length").toString();
        double length = Double.parseDouble(lengthString);

        return new OccupationArea(length, width);

    }

    private void importSensorList(JSONObject jsonGA, List<Sensor> sensorList, GeographicalArea geoArea) throws java.text.ParseException {
        JSONArray jsonSensorList = (JSONArray) jsonGA.get("area_sensor");
        for (Object areaSensor : jsonSensorList) {
            JSONObject jsonSensor = (JSONObject) areaSensor;
            Sensor sensor = importSensor(jsonSensor, geoArea);
            sensorList.add(sensor);
        }
    }

    private Sensor importSensor(JSONObject jsonSensor, GeographicalArea geoArea) throws java.text.ParseException {
        injectRepository();
        JSONObject sensor = (JSONObject) jsonSensor.get("sensor");
        String id = (String) sensor.get("id");
        String name = (String) sensor.get("name");

        String startDate = (String) sensor.get("start_date");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(startDate);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        String sensorType = (String) sensor.get("type");
        Name typeName = new Name(sensorType);
        SensorType type;
        //Repository call
        try {
            type= sensorTypeRepository.findByType(typeName);
        } catch (NullPointerException e) {
            log.warn("Repository unreachable");
            type= new SensorType(sensorType);
        }
        String unit = (String) sensor.get("units");
        Location location = importLocation(jsonSensor);
        ReadingList readings = new ReadingList();
        ExternalSensor geoSensor = new ExternalSensor(id, name, calendar, location, type, unit, readings);
        //TODO add new unitary tests to this feature
        //TODO add same feature in other readers
        geoSensor.setIdGA(geoArea.getIdentification());
        return geoSensor;

    }

    private Location importLocation(JSONObject jsonObject) {
        JSONObject jsonLocation = (JSONObject) jsonObject.get("location");

        String latitudeString = jsonLocation.get("latitude").toString();
        double latitude = Double.parseDouble(latitudeString);
        String longitudeString = jsonLocation.get("longitude").toString();
        double longitude = Double.parseDouble(longitudeString);
        String altitudeString = jsonLocation.get("altitude").toString();
        double altitude = Double.parseDouble(altitudeString);

        return new Location(latitude, longitude, altitude);
    }
}
