package smarthome.io.ui;

import smarthome.controller.US7SetParentOfGACTRL;
import smarthome.model.GAList;

import java.util.Scanner;

public class US7SetParentOfGAUI {

    private GAList mGAList;
    private US7SetParentOfGACTRL mCtrlUS7;

    public US7SetParentOfGAUI(GAList inputList) {
        mGAList = inputList;
        mCtrlUS7 = new US7SetParentOfGACTRL(inputList);
    }

    public void run() {
        Scanner read = new Scanner(System.in);

        while (true) {
            System.out.println("Choose a Geographical Area from the list below. (or insert 0 to return to Main Menu): ");
            System.out.println(mCtrlUS7.showListInString());
            int gaIndex1 = read.nextInt();
            if (gaIndex1 == 0) {
                System.out.println("Return to Main Menu");
                break;
            }
            System.out.println("Success");
            int gaIndex2;
            while (true) {
                System.out.println("Choose a Geographical Area from the list below to set as parent of " + mGAList.getGAList().get(gaIndex1 - 1).getGeographicalAreaDesignation());
                System.out.println(mCtrlUS7.showListInString());
                gaIndex2 = read.nextInt();
                if (gaIndex1 == gaIndex2) {
                    System.out.println("Error. Please choose a different area");
                } else break;
            }
            mCtrlUS7.setParentofGA(gaIndex1, gaIndex2);
            System.out.println("Success");
            break;
        }
    }
}
