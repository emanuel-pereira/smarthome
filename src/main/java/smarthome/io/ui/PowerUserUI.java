package smarthome.io.ui;

import smarthome.model.House;

import java.util.Scanner;

public final class PowerUserUI {

    private PowerUserUI() {
    }

    public static void powerUser(House house) {
        Scanner keyboard = new Scanner(System.in);
        int option = -1;
        //System.out.println("POWER USER");

        while (option != 0) {
            System.out.println("\n******* POWER USER MENU ********\n");
            System.out.println("Click 1. Show all the devices connected to a grid");
            System.out.println("Click 2. Get the total nominal power connected to a grid");
            System.out.println("Click 3. Get the total nominal power of a room");
            System.out.println("Click 4. Estimate the total energy used in heating water in a given day");
            System.out.println("Click 5. Get the total nominal power in a subset of rooms and/or devices");
            System.out.println("Click 6. Deactivate a device");
            System.out.println("Click 7. Get the total energy consumption of a device, a room or a grid in a given time interval");
            System.out.println("Click 8. Get the data series of the metered energy consumption of a device/room/grid in a given time interval");
            System.out.println("\nClick 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    US160GetDeviceListInGridByTypeUI ui160 = new US160GetDeviceListInGridByTypeUI(house);
                    ui160.checkIfHGListIsEmpty();
                    break;
                case 2:
                    GetGridTotalNominalPowerUI uS172 = new GetGridTotalNominalPowerUI(house);
                    uS172.run();
                    break;
                case 3:
                    GetRoomTotalNominalPowerUI ui230 = new GetRoomTotalNominalPowerUI(house);
                    ui230.showTotalNominalPowerRoom();
                    break;
                case 4:
                    GetEnergyConsumptionOfWaterHeatingUI ui752 = new GetEnergyConsumptionOfWaterHeatingUI(house);
                    //ui752.run();
                    break;
                case 5:
                    System.out.println("US705");
                    /*DisplayVariableTotalNominalPowerUI ui705 = new DisplayVariableTotalNominalPowerUI(house);
                    ui705.run();*/
                    break;
                case 6:
                    EditDevicesUI devicesUI = new EditDevicesUI(house);
                    devicesUI.deactivateDevice();
                    break;
                case 7:
                    GetEnergyConsumptionInPeriodUI uiEC = new GetEnergyConsumptionInPeriodUI(house);
                    uiEC.selectOption();
                    break;
                case 8:
                    System.out.println("US730");
                    /*GetEnergyConsumptionInPeriodUI uiEC2 = new GetEnergyConsumptionInPeriodUI(house);
                    uiEC2.selectChartOption();
                    */
                    System.out.println(house.getRoomList().getRoomList().get(2).getDeviceList().get(1).getActivityLogSum());
                    break;
                default:
                    System.out.println("Input not accepted, please insert a number from 1 to 8\n");
                    break;
            }
        }
    }
}
