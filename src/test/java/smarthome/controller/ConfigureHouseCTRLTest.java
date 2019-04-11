package smarthome.controller;

import org.junit.jupiter.api.BeforeEach;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.mapper.GeographicalAreaMapper;
import smarthome.model.*;

import java.lang.reflect.Field;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static smarthome.model.House.*;

class ConfigureHouseCTRLTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);

    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance = House.class.getDeclaredField("theHouse");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @DisplayName("Tests if Geographical Area List is showed as a string to the user")
    @Test
    void showGAListInString() {
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


    @DisplayName("Tests if the house location is configured correctly")
    @Test
    void configureHouseLocation() {
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

        String id = ctrl101.getIdFromIndex(1);

        boolean result = ctrl101.configureHouseLocation(id , "Rua Júlio Dinis", "345", "3380-45", "Porto","Portugal",41, 12.3, 110);

        GeographicalArea result2 = getHouseGA();

        assertTrue(result);
        assertEquals(ga1, result2);


    }

    @DisplayName("Check if throws exception when the latitude is wrong")
    @Test
    void throwsIllegalArgumentExceptionForLatitude() {

        GAList gl1 = new GAList();

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);
        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);


        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);

        String id = ctrl101.getIdFromIndex(1);

        boolean thrown = false;

        try {

            ctrl101.configureHouseLocation(id, "Rua Júlio Dinis", "345", "3380-45", "Porto","Portugal",400, 12.3, 110);
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

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);


        gl1.addGA(ga1);
        gl1.addGA(ga2);

        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);
        String id = ctrl101.getIdFromIndex(1);

        boolean thrown = false;

        try {
            ctrl101.configureHouseLocation(id, "Rua Júlio Dinis", "345", "3380-45", "Porto","Portugal",80, 181, 110);
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

        GeographicalArea ga1 = new GeographicalArea("Pt","Porto", "city", oc1,loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls","Lisboa", "city", oc2,loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);

        ConfigureHouseCTRL ctrl101 = new ConfigureHouseCTRL(gl1);
        String id = ctrl101.getIdFromIndex(1);

        boolean thrown = false;

        try {

            ctrl101.configureHouseLocation(id, "Rua Júlio Dinis", "345", "3380-45", "Porto","Portugal",80, -170,-13000);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    void getGAListSize() {
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


        int expected=2;
        int result = ctrl101.getGAListSize();

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Check if Address in file was properly set")
    void checkAddressTest () throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException {
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl = new ConfigureHouseCTRL(gl1);

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);

        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);

        String id = ctrl.getIdFromIndex(1);

        ctrl.configureHouseFromFileCTRL(id,25,14,12);

        String expected = "R. Dr. António Bernardino de Almeida";
        String result = getAddress().getName();

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Check if Address's new Location was properly set")
    void checkAddressNewLocationTest () throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException {
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl = new ConfigureHouseCTRL(gl1);

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);

        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);
        String id = ctrl.getIdFromIndex(1);

        ctrl.configureHouseFromFileCTRL(id,25,14,12);

        double expected = 14;
        double result = getAddress().getGPSLocation().getLongitude();

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Check if the Rooms in the File were properly added")
    void checkRoomListSizeTest () throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException {
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl = new ConfigureHouseCTRL(gl1);

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);

        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);

        String id = ctrl.getIdFromIndex(1);

        ctrl.configureHouseFromFileCTRL(id,25,14,12);

        int expected = 7;
        int result = ctrl.getRoomListSizeCTRL();

        assertEquals(expected,result);
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

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);

        String id = ctrl.getIdFromIndex(1);

        ctrl.configureHouseFromFileCTRL(id,25,14,12);

        int expected = 2;
        int result = ctrl.getGridListSizeCTRL();

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Check if the Rooms,in the Grids in the File were properly added")
    void checkRoomListInGridSizeTest () throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException {
        GAList gl1 = new GAList();
        ConfigureHouseCTRL ctrl = new ConfigureHouseCTRL(gl1);

        Location loc1 = new Location(25, 15, 12);
        OccupationArea oc1 = new OccupationArea(32, 41);

        Location loc2 = new Location(45, 25, 32);
        OccupationArea oc2 = new OccupationArea(42, 41);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", oc1, loc1);
        GeographicalArea ga2 = new GeographicalArea("Ls", "Lisboa", "city", oc2, loc2);

        gl1.addGA(ga1);
        gl1.addGA(ga2);

        String id = ctrl.getIdFromIndex(1);

        ctrl.configureHouseFromFileCTRL(id,25,14,12);

        int expected = 5;
        int result = getGridListInHouse().get(0).getRoomListInAGridSize();

        assertEquals(expected,result);
    }
}
