package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class USAttachRoomToGridAndListCTRLTest {

    @Test
    @DisplayName("Ensure HouseGrid List is displayed as String")
    void showHouseGridListInString() {

        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45",41, 12.3, 110);
        GeographicalArea ga1 = new GeographicalArea("POR","Porto","City",25,35,15,40,45);
        House house = new House(a1, ga1);
        HouseGridList hgList = new HouseGridList();
        USAttachRoomToGridAndListCTRL ctrl = new USAttachRoomToGridAndListCTRL(house);
        HouseGrid grid1 = new HouseGrid("Grid 1",45);
        HouseGrid grid2 = new HouseGrid("Grid 2", 50);
        house.getHGListInHouse().addHouseGrid(grid1);
        house.getHGListInHouse().addHouseGrid(grid2);

        String expected = "1 - Grid 1 | Maximum Power Output: 45.0\n2 - Grid 2 | Maximum Power Output: 50.0\n";
        String result =  ctrl.showHouseGridListInString();

        assertEquals(expected,result);
    }

    /*@Test
    @DisplayName("Ensure HouseGrid List size is 2")
    void getHouseGridListSize() {
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", 41, 12.3, 110);
        GeographicalArea ga1 = new GeographicalArea("POR","Porto","City",25,35,15,40,45);
        House house = new House(a1, ga1);
        HouseGridList hgList = new HouseGridList();
        USAttachRoomToGridAndListCTRL ctrl = new USAttachRoomToGridAndListCTRL(house,hgList);
        HouseGrid grid1 = new HouseGrid(45);
        HouseGrid grid2 = new HouseGrid(50);
        house.addhgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);

        int expectedResult=2;
        int result=ctrl.;

        assertEquals(expectedResult,result);
    }*/

    @Test
    @DisplayName("Ensure HouseGrid List is listing the correct items")
    void getHouseGridListTest() {
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", 41, 12.3, 110);
        GeographicalArea ga1 = new GeographicalArea("POR","Porto","City",25,35,15,40,45);
        House house = new House(a1, ga1);
        HouseGridList hgList = new HouseGridList();
        USAttachRoomToGridAndListCTRL ctrl = new USAttachRoomToGridAndListCTRL(house);
        HouseGrid grid1 = new HouseGrid(45);
        HouseGrid grid2 = new HouseGrid(50);
        house.getHGListInHouse().addHouseGrid(grid1);
        house.getHGListInHouse().addHouseGrid(grid2);

        List<HouseGrid> expectedResult = Arrays.asList(grid1,grid2);
        List<HouseGrid> result = ctrl.getHouseGridList().getHouseGridList();

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Ensure that list of Rooms without HouseGrid returns 2")
    void getListOfRoomsWithoutHouseGrid() {
        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25", "4000-285", 41, 12.3, 110);
        GeographicalArea ga1 = new GeographicalArea("POR","Porto","City",25,35,15,40,45);
        House house = new House(a1, ga1);
        HouseGridList hgList = new HouseGridList();
        USAttachRoomToGridAndListCTRL ctrl = new USAttachRoomToGridAndListCTRL(house);

        HouseGrid grid= new HouseGrid(1500);
        HouseGrid grid2= new HouseGrid(2000);
        house.getHGListInHouse().addHouseGrid(grid);
        house.getHGListInHouse().addHouseGrid(grid2);

        Room kitchen= new Room("Kitchen",0,5,4, 2);
        Room wc= new Room("Bathroom",0,4,3,2 );
        Room bedroom1 = new Room("Bedroom1",1,4,5, 2);
        Room bedroom2 = new Room("Bedroom2",1,4,5, 2);
        house.getRoomList().addRoom(kitchen);
        house.getRoomList().addRoom(wc);
        house.getRoomList().addRoom(bedroom1);
        house.getRoomList().addRoom(bedroom2);
        bedroom1.setmHouseGrid(grid);
        bedroom2.setmHouseGrid(grid);

        int expectedResult=2;
        int result=ctrl.getListOfRoomsWithoutHouseGrid().getRoomList().size();

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Ensure that get list of rooms returns size 4")
    void getListOfRooms() {
        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25", "4000-285", 41, 12.3, 110);
        GeographicalArea ga1 = new GeographicalArea("POR","Porto","City",25,35,15,40,45);
        House house = new House(a1, ga1);
        HouseGridList hgList = new HouseGridList();
        USAttachRoomToGridAndListCTRL ctrl = new USAttachRoomToGridAndListCTRL(house);

        Room kitchen= new Room("Kitchen",0,5,4, 2);
        Room wc= new Room("Bathroom",0,4,3,2 );
        Room bedroom1 = new Room("Bedroom1",1,4,5, 2);
        Room bedroom2 = new Room("Bedroom2",1,4,5, 2);
        house.getRoomList().addRoom(kitchen);
        house.getRoomList().addRoom(wc);
        house.getRoomList().addRoom(bedroom1);
        house.getRoomList().addRoom(bedroom2);

        int expectedResult=4;
        int result=ctrl.getListOfRooms().getRoomList().size();

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Ensure that list of Rooms without HouseGrid is displayed in string")
    void listOfRoomsWithoutHouseGridInString() {
        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25", "4000-285", 41, 12.3, 110);
        GeographicalArea ga1 = new GeographicalArea("POR","Porto","City",25,35,15,40,45);
        House house = new House(a1, ga1);
        HouseGridList hgList = new HouseGridList();
        USAttachRoomToGridAndListCTRL ctrl = new USAttachRoomToGridAndListCTRL(house);

        HouseGrid grid= new HouseGrid(1500);
        HouseGrid grid2= new HouseGrid(2000);
        house.getHGListInHouse().addHouseGrid(grid);
        house.getHGListInHouse().addHouseGrid(grid2);

        Room kitchen= new Room("Kitchen",0,5,4, 2.5);
        Room wc= new Room("Bathroom",0,4,3, 2.5);
        Room bedroom1 = new Room("Bedroom1",1,4,5, 2.5);
        Room bedroom2 = new Room("Bedroom2",1,4,5, 2.5);
        house.getRoomList().addRoom(kitchen);
        house.getRoomList().addRoom(wc);
        house.getRoomList().addRoom(bedroom1);
        house.getRoomList().addRoom(bedroom2);
        bedroom1.setmHouseGrid(grid);
        bedroom2.setmHouseGrid(grid);
        String expected = "1 - Kitchen\n2 - Bathroom\n";
        String result =  ctrl.showRoomsWithoutHouseGridInStr();
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that grid2 is set as kitchen HouseGrid")
    void attachRoomToHouseGrid() {
        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25", "4000-285", 41, 12.3, 110);
        GeographicalArea ga1 = new GeographicalArea("POR","Porto","City",25,35,15,40,45);
        House house = new House(a1, ga1);
        HouseGridList hgList = new HouseGridList();
        USAttachRoomToGridAndListCTRL ctrl = new USAttachRoomToGridAndListCTRL(house);

        HouseGrid grid= new HouseGrid(1500);
        HouseGrid grid2= new HouseGrid(2000);
        house.getHGListInHouse().addHouseGrid(grid);
        house.getHGListInHouse().addHouseGrid(grid2);

        Room kitchen= new Room("Kitchen",0,5,4, 2);
        Room wc= new Room("Bathroom",0,4,3, 2);
        Room bedroom1 = new Room("Bedroom1",1,4,5, 2);
        Room bedroom2 = new Room("Bedroom2",1,4,5, 2);
        house.getRoomList().addRoom(kitchen);
        house.getRoomList().addRoom(wc);
        house.getRoomList().addRoom(bedroom1);
        house.getRoomList().addRoom(bedroom2);
        bedroom1.setmHouseGrid(grid);
        bedroom2.setmHouseGrid(grid);

        boolean result =  ctrl.attachRoomToHouseGrid(2,1);

        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure that grid2 is not set to any room as the list of rooms without HouseGrid is empty.")
    void attachRoomToHouseGridReturnsFalseAsAllRoomsAlreadyHaveGrid() {
        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25","4000-285", 41, 12.3, 110);
        GeographicalArea ga1 = new GeographicalArea("POR","Porto","City",25,35,15,40,45);
        House house = new House(a1, ga1);
        HouseGridList hgList = new HouseGridList();
        USAttachRoomToGridAndListCTRL ctrl = new USAttachRoomToGridAndListCTRL(house);

        HouseGrid grid= new HouseGrid(1500);
        HouseGrid grid2= new HouseGrid(2000);
        house.getHGListInHouse().addHouseGrid(grid);
        house.getHGListInHouse().addHouseGrid(grid2);

        Room bedroom1 = new Room("Bedroom1",1,4,5, 2);
        Room bedroom2 = new Room("Bedroom2",1,4,5, 2);
        house.getRoomList().addRoom(bedroom1);
        house.getRoomList().addRoom(bedroom2);
        bedroom1.setmHouseGrid(grid);
        bedroom2.setmHouseGrid(grid);
        boolean result =  ctrl.attachRoomToHouseGrid(2,1);
        assertFalse(result);
    }
    @Test
    @DisplayName("Ensure that list of Rooms with HouseGrid is displayed in string")
    void showListOfRoomsWithHouseGridInString() {
        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25", "4000-285", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("POR","Porto","City",25,35,15,40,45);

        House house = new House(a1,g1);
        HouseGridList hgList = new HouseGridList();
        USAttachRoomToGridAndListCTRL ctrl = new USAttachRoomToGridAndListCTRL(house);

        HouseGrid grid= new HouseGrid(1500);
        HouseGrid grid2= new HouseGrid(2000);
        house.getHGListInHouse().addHouseGrid(grid);
        house.getHGListInHouse().addHouseGrid(grid2);

        Room kitchen= new Room("Kitchen",0,5,4, 3);
        Room wc= new Room("Bathroom",0,4,3, 3);
        Room bedroom1 = new Room("Bedroom1",1,4,5, 3);
        Room bedroom2 = new Room("Bedroom2",1,4,5, 3);
        house.getRoomList().addRoom(kitchen);
        house.getRoomList().addRoom(wc);
        house.getRoomList().addRoom(bedroom1);
        house.getRoomList().addRoom(bedroom2);

        bedroom1.setmHouseGrid(grid);
        bedroom2.setmHouseGrid(grid);
        kitchen.setmHouseGrid(grid2);
        wc.setmHouseGrid(grid2);
        String expected = "1 - Bedroom1\n2 - Bedroom2\n";
        String result =  ctrl.showRoomsWithHouseGridInStr(1);
        assertEquals(expected,result);
    }


    @DisplayName("Get list of rooms connected to grid2")
    @Test
    void getListRoomsWithHouseGrid() {

        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25", "4000-285", 41, 12.3, 110);
        GeographicalArea ga1 = new GeographicalArea("POR","Porto","City",25,35,15,40,45);
        House house = new House(a1, ga1);
        USAttachRoomToGridAndListCTRL ctrl = new USAttachRoomToGridAndListCTRL(house);

        HouseGrid grid= new HouseGrid(1500);
        HouseGrid grid2= new HouseGrid(2000);
        house.getHGListInHouse().addHouseGrid(grid);
        house.getHGListInHouse().addHouseGrid(grid2);

        Room kitchen= new Room("Kitchen",0,5,4, 1.5);
        Room wc= new Room("Bathroom",0,4,3, 1.5);
        Room bedroom1 = new Room("Bedroom1",1,4,5, 1.5);
        Room bedroom2 = new Room("Bedroom2",1,4,5, 1.5);
        house.getRoomList().addRoom(kitchen);
        house.getRoomList().addRoom(wc);
        house.getRoomList().addRoom(bedroom1);
        house.getRoomList().addRoom(bedroom2);

        bedroom1.setmHouseGrid(grid);
        bedroom2.setmHouseGrid(grid);
        kitchen.setmHouseGrid(grid2);
        wc.setmHouseGrid(grid2);

        String expected = "1 - Kitchen\n2 - Bathroom\n";
        String result= ctrl.getListOfRoomsWithHouseGrid(2).showRoomListInString();

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that grid2 is detached from kitchen")
    void detachRoomFromHouseGrid() {
        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25","4000-285", 41, 12.3, 110);
        GeographicalArea ga1 = new GeographicalArea("POR","Porto","City",25,35,15,40,45);
        House house = new House(a1, ga1);
        HouseGridList hgList = new HouseGridList();
        USAttachRoomToGridAndListCTRL ctrl = new USAttachRoomToGridAndListCTRL(house);

        HouseGrid grid= new HouseGrid(1500);
        HouseGrid grid2= new HouseGrid(2000);
        house.getHGListInHouse().addHouseGrid(grid);
        house.getHGListInHouse().addHouseGrid(grid2);

        Room kitchen= new Room("Kitchen",0,5,4, 2);
        Room wc= new Room("Bathroom",0,4,3, 2);
        Room bedroom1 = new Room("Bedroom1",1,4,5, 2);
        Room bedroom2 = new Room("Bedroom2",1,4,5, 2);
        house.getRoomList().addRoom(kitchen);
        house.getRoomList().addRoom(wc);
        house.getRoomList().addRoom(bedroom1);
        house.getRoomList().addRoom(bedroom2);

        bedroom1.setmHouseGrid(grid);
        bedroom2.setmHouseGrid(grid);
        kitchen.setmHouseGrid(grid2);
        wc.setmHouseGrid(grid2);

        boolean result= ctrl.detachRoomFromHouseGrid(2,1);

        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure that method detachRoomFromHouseGrid returns false as grid2 does not have any rooms attached.")
    void detachRoomFromHouseGridReturnsFale() {
        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25", "4000-285",41, 12.3, 110);
        GeographicalArea ga1 = new GeographicalArea("POR","Porto","City",25,35,15,40,45);
        House house = new House(a1, ga1);
        HouseGridList hgList = new HouseGridList();
        USAttachRoomToGridAndListCTRL ctrl = new USAttachRoomToGridAndListCTRL(house);

        HouseGrid grid= new HouseGrid(1500);
        HouseGrid grid2= new HouseGrid(2000);
        house.getHGListInHouse().addHouseGrid(grid);
        house.getHGListInHouse().addHouseGrid(grid2);

        Room bedroom1 = new Room("Bedroom1",1,4,5, 2.5);
        Room bedroom2 = new Room("Bedroom2",1,4,5, 2.5);

        house.getRoomList().addRoom(bedroom1);
        house.getRoomList().addRoom(bedroom2);

        bedroom1.setmHouseGrid(grid);
        bedroom2.setmHouseGrid(grid);

        boolean result= ctrl.detachRoomFromHouseGrid(2,1);

        assertFalse(result);
    }

}