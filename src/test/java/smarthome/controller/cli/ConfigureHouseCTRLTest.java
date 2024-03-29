/*
package smarthome.controller.cli;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.LocationDTO;
import smarthome.mapper.GeographicalAreaMapper;
import smarthome.model.*;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static smarthome.model.House.getAddress;
import static smarthome.model.House.getHouseGA;
import static smarthome.model.TypeGAList.getTypeGAListInstance;

class ConfigureHouseCTRLTest {

    private Location loc = new Location(20, 20, 2);
    private Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
    private OccupationArea oc = new OccupationArea(2, 5);
    private GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);
    TypeGAList typeGAList = getTypeGAListInstance();



    @DisplayName("Tests if Geographical Area List is showed as a string to the user")
    @Test
    void showGAListInString() {
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);
        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        TypeGAList.addTypeGA(new TypeGA("city"));

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);

        String expected = "1 - Pt, Porto;\n" +
                          "2 - Ls, Lisboa;\n";
        String result = ctrl101.showGAListDTO();
        assertEquals(expected, result);
    }

    @DisplayName("Tests if Geographical Area List is returned")
    @Test
    void getGAList() {
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);
        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);

        GeographicalAreaMapper mapper = new GeographicalAreaMapper();

        List<GeographicalAreaDTO> expectedResult = mapper.toDtoList(gl1);
        List<GeographicalAreaDTO> result = ctrl101.getGAListDTO();
        assertEquals(expectedResult, result);
    }


    @DisplayName("Tests if the house GA is configured correctly")
    @Test
    void configureHouseLocationGetGa() {
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);
        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        TypeGAList.addTypeGA(new TypeGA("city"));

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);

        String id = ctrl101.getIdFromIndex(1);

        LocationDTO locationDto = new LocationDTO (41, 12.3, 110);

        boolean result = ctrl101.configureHouseLocation(id , "Rua Júlio Dinis", "345", "3380-45", "Porto","Portugal",locationDto);

        GeographicalArea result2 = getHouseGA();

        assertTrue(result);
        assertEquals(ga1, result2);
    }

    @DisplayName("Tests if the house Address is configured correctly")
    @Test
    void configureHouseLocationGetAddress() {
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);
        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        TypeGAList.addTypeGA(new TypeGA("city"));

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);

        String id = ctrl101.getIdFromIndex(1);

        LocationDTO locationDto = new LocationDTO (41, 12.3, 110);
        boolean result = ctrl101.configureHouseLocation(id , "Rua Júlio Dinis", "345", "3380-45", "Porto","Portugal",locationDto);

        String result2 = getAddress().getStreet();
        String expected = "Rua Júlio Dinis";

        assertTrue(result);
        assertEquals(expected, result2);
    }

    @DisplayName("Check if throws exception when the latitude is wrong")
    @Test
    void throwsIllegalArgumentExceptionForLatitude() {

        GAList gl1 = new GAList();

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);
        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        TypeGAList.addTypeGA(new TypeGA("city"));

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);


        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);

        String id = ctrl101.getIdFromIndex(1);
        LocationDTO locationDto = new LocationDTO (400, 12.3, 110);

        boolean thrown = false;

        try {

            ctrl101.configureHouseLocation(id, "Rua Júlio Dinis", "345", "3380-45", "Porto","Portugal",locationDto);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @DisplayName("Check if throws exception when the longitude is wrong")
    @Test
    void throwsIllegalArgumentExceptionForLongitude() {

        GAList gl1 = new GAList();


        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);
        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        TypeGAList.addTypeGA(new TypeGA("city"));

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);


        gl1.addGA(ga1);
        gl1.addGA(ga2);

        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);
        String id = ctrl101.getIdFromIndex(1);
        LocationDTO locationDto = new LocationDTO (80, 181, 110);

        boolean thrown = false;

        try {
            ctrl101.configureHouseLocation(id, "Rua Júlio Dinis", "345", "3380-45", "Porto","Portugal",locationDto);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @DisplayName("Check if throws exception when the altitude is wrong")
    @Test
    void throwsIllegalArgumentExceptionForAltitude() {

        GAList gl1 = new GAList();

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);
        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        TypeGAList.addTypeGA(new TypeGA("city"));

        GeographicalArea ga1 = new GeographicalArea("Pt","Porto", "city", oc1,loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls","Lisboa", "city", oc2,loc2);

        assertTrue(gl1.addGA(ga1));
        assertTrue(gl1.addGA(ga2));

        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);
        String id = ctrl101.getIdFromIndex(1);
        LocationDTO locationDto = new LocationDTO (80, -170, -13000);

        boolean thrown = false;

        try {

            ctrl101.configureHouseLocation(id, "Rua Júlio Dinis", "345", "3380-45", "Porto","Portugal",locationDto);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    @DisplayName("Check if GeoArea was properly set")
    void checkGeoAreaTestThrowsNullPointerException () throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException {
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl = new ConfigureHouseCTRL(gl1);

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);

        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        TypeGAList.addTypeGA(new TypeGA("city"));

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);
        gl1.addGA(ga1);
        gl1.addGA(ga2);
        String id = ctrl.getIdFromIndex(1);
        Assertions.assertThrows(NullPointerException.class, () ->ctrl.configureHouseFromFileCTRL(id,25,14,12));
    }


    @Test
    @DisplayName("Check if the Grids in the File were properly added")
    void checkGridListSizeTest () throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException {
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl = new ConfigureHouseCTRL(gl1);

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);

        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        TypeGAList.addTypeGA(new TypeGA("city"));

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);

        String id = ctrl.getIdFromIndex(1);

        Assertions.assertThrows(NullPointerException.class, () -> ctrl.configureHouseFromFileCTRL(id,25,14,12));
    }


    @Test
    @DisplayName("Show GA In String")
    void showGAinStringTest()  throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException {
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl = new ConfigureHouseCTRL(gl1);
        String expected = "    Porto, Pt";
        String result = ctrl.showGAInString();

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Show Address In String")
    void showAddressInStringTest()  throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException {
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl = new ConfigureHouseCTRL(gl1);

        String expected = "    R. Dr. Antonio Bernardino de Almeida, 431, 4200-072\n" +
                "    Porto, Portugal\n" +
                " | Location:\n" +
                "    Latitude: 0.0º | Longitude: 0.0º | Altitude: 0.0 meters";
        String result = ctrl.showAddressInString();

        assertEquals(expected,result);
    }
    @Test
    void getRoomListSizeCTRLReturnsZero(){
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl = new ConfigureHouseCTRL(gl1);
        int expected=-1;
        int result=ctrl.getRoomListSizeCTRL();
        assertNotEquals(expected,result);
    }

    @Test
    void getGridListSizeCTRLReturnsZero(){
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl = new ConfigureHouseCTRL(gl1);
        int expected=-1;
        int result=ctrl.getGridListSizeCTRL();
        assertNotEquals(expected,result);
    }


}*/
