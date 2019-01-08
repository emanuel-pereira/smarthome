package smarthome.io.ui;

import smarthome.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static smarthome.io.ui.HouseAdministrationUI.houseAdministration;
import static smarthome.io.ui.RegularUsageUI.regularUsage;
import static smarthome.io.ui.SystemAdministrationUI.systemAdministration;

public class MainUI {
    public static void main(String[] args) {
        DataTypeList dataTypeList = new DataTypeList();
        GAList gaList = new GAList();
        House house = new House();

        Scanner keyboard = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("Click 1 System Administration");
            System.out.println("Click 2 House Administration");
            System.out.println("Click 3 Regular Usage");
            System.out.println("Click 0 Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    systemAdministration(dataTypeList,gaList);
                    break;
                case 2:
                    houseAdministration(dataTypeList,gaList,house);
                    break;
                case 3:
                    regularUsage();
                    break;
            }
        }
    }
}