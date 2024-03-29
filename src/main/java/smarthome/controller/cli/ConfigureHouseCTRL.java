package smarthome.controller.cli;

import org.json.simple.parser.ParseException;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.LocationDTO;
import smarthome.mapper.GeographicalAreaMapper;
import smarthome.model.Address;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.Location;
import smarthome.model.readers.DataImport;

import java.io.IOException;
import java.util.List;

import static smarthome.model.House.*;


public class ConfigureHouseCTRL {

    private final DataImport dataImportHouse;
    private final GeographicalAreaMapper gaMapper = new GeographicalAreaMapper();
    private final GAList gaList;


    public ConfigureHouseCTRL(GAList listOfGA) {
        gaList = listOfGA;
        this.dataImportHouse = new DataImport(this.gaList);
    }

    public List<GeographicalAreaDTO> getGAListDTO() {
        return gaMapper.toDtoList(gaList);
    }

    public String showGAListDTO() {
        List<GeographicalAreaDTO> list = getGAListDTO();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (GeographicalAreaDTO ga : list) {
            result.append(number++);
            result.append(element);
            result.append(ga.getIdentification()+", " +ga.getDesignation()+";");
            result.append("\n");
        }
        return result.toString();
    }

    public String getIdFromIndex (int index){
        GeographicalArea geoArea = this.gaList.get(index-1);
        return geoArea.getIdentification();
    }

    public boolean configureHouseLocation(String idGeoArea, String streetName, String number, String zipCode, String town, String country, LocationDTO locationDTO) {
        GeographicalArea ga = this.gaList.getById(idGeoArea);
        setHouseGA(ga);
        Location location = locationDTO.fromDTO();
        Address houseAddress = new Address(streetName, number, zipCode, town, country,location);
        setHouseAddress(houseAddress);
        return getAddress() != null;

    }

   public void configureHouseFromFileCTRL (String idGeoArea,double latitude,double longitude,double altitude) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException {
        GeographicalArea geoArea = this.gaList.getById(idGeoArea);
        setHouseGA(geoArea);

        this.dataImportHouse.importHouse();

        Address houseAddress = getAddress();
        Location location = new Location(latitude,longitude,altitude);
        houseAddress.setGpsLocation(location);
    }

    public String showAddressInString(){
       return getAddress().addressToString();
    }

    public int getRoomListSizeCTRL(){
       return getHouseRoomList().getRoomListSize();
    }

    public int getGridListSizeCTRL(){
        return getGridListInHouse().getSize();
    }

  public String showGAInString(){
       return getHouseGA().gaInString();
    }


}
