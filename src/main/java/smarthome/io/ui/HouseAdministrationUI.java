package smarthome.io.ui;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import smarthome.model.GAList;
import smarthome.model.SensorTypeList;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

import static smarthome.model.House.getHouseRoomList;

public class HouseAdministrationUI {

    public HouseAdministrationUI() {
    }

    public void menu(SensorTypeList sensorTypeList, GAList gaList) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SAXException, ParserConfigurationException, ParseException, IOException, NoSuchFieldException {

        int option = -1;
        while (option != 0) {
            ArrayList<String> options = new ArrayList<>();

            options.add("[1] Configure the location of the house");
            options.add("[2] Configure the house from a file");
            options.add("[3] Add a new room to the house");
            options.add("[4] Show the list of existing rooms");
            options.add("[5] Edit a room");
            options.add("[6] Create a house grid");
            options.add("[7] Add a new power source to a house grid");
            options.add("[8] List (Attach/detach) a room to/from a house grid");
            options.add("[9] Show the Total Nominal Power from a Room.");
            options.add("[10] Add a new sensor to a room");
            options.add("[11] List all sensors in a room");
            options.add("[12] List (edit/add/remove) devices in a room");
            options.add("[13] Show all the devices connected to a grid");
            options.add("[14] Show the total nominal power connected to a grid");
            options.add("[15] Import sensors to the house from a file (JSON)");
            options.add("[16] Import house sensors' readings from a file");
            options.add("[17] Check temperature comfort level in a room");
            options.add("[0] Exit");

            UtilsUI.showList("House administration", options, false, 5);

            option = UtilsUI.requestIntegerInInterval(0, 16, "Please choose an action between 1 and 15, or 0 to exit the program");

            switch (option) {
                case 1:
                    ConfigureHouseUI ui101 = new ConfigureHouseUI(gaList);
                    ui101.configHouseLocationManually();
                    break;
                case 2:
                    ConfigureHouseUI ui100 = new ConfigureHouseUI(gaList);
                    ui100.configHouseFromFile() ;
                    break;
                case 3:
                    AddRoomToHouseUI ui105 = new AddRoomToHouseUI();
                    ui105.addRoomToHouse();
                    break;
                case 4:
                    ListRoomsOfHouseUI ui108 = new ListRoomsOfHouseUI();
                    ui108.run();
                    break;
                case 5:
                    EditRoomUI ui109 = new EditRoomUI();
                    ui109.run();
                    break;
                case 6:
                    NewHouseGridUI ui130 = new NewHouseGridUI();
                    ui130.run();
                    break;
                case 7:
                    AddPowerSourceToGridUI ui135 = new AddPowerSourceToGridUI();
                    ui135.checkIfHGListIsEmpty();
                    break;
                case 8:
                    AttachDetachAndListRoomsInGridUI ui145 = new AttachDetachAndListRoomsInGridUI();
                    ui145.checkIfHGListIsEmpty();
                    break;
                case 9:
                    GetTotalNominalPowerUI ui230 = new GetTotalNominalPowerUI();
                    ui230.getRoomTotalNominalPower();
                    break;
                case 10:
                    NewSensorUI newSensorUI = new NewSensorUI(sensorTypeList, gaList);
                    newSensorUI.checkIfRoomListIsEmpty();
                    break;
                case 11:
                    NewSensorUI listSensorsUI = new NewSensorUI(sensorTypeList, gaList);
                    listSensorsUI.selectRoomAndList();
                    break;
                case 12:
                    EditDevicesUI ui210 = new EditDevicesUI();
                    ui210.selectOption();
                    break;
                case 13:
                    GetDeviceListInGridByTypeUI ui160 = new GetDeviceListInGridByTypeUI();
                    ui160.checkIfHGListIsEmpty();
                    break;
                case 14:
                    GetTotalNominalPowerUI uS172 = new GetTotalNominalPowerUI();
                    uS172.getGridTotalNominalPower();
                    break;
                case 15:
                    DataImportUI ui14 = new DataImportUI(getHouseRoomList(),sensorTypeList);
                    ui14.loadHouseSensorsFile();
                    break;
                case 16:
                    DataImportUI ui13 = new DataImportUI(getHouseRoomList());
                    ui13.checkIfRoomListIsEmpty(getHouseRoomList());
                    break;
                case 17:
                    ComfortLevelUI ui = new ComfortLevelUI();
                    ui.run();
                    break;
                default:
                    //no action needed
            }
        }
    }
}
