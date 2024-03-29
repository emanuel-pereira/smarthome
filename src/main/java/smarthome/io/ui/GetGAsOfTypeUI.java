package smarthome.io.ui;

import smarthome.controller.cli.GetGAsOfTypeCTRL;
import smarthome.model.GAList;

import static smarthome.model.TypeGAList.getTypeGAList;

public class GetGAsOfTypeUI {

    private final GAList gaList;
    private final GetGAsOfTypeCTRL ctrlUS4;

    public GetGAsOfTypeUI(GAList inputGAList) {
        gaList = inputGAList;
        ctrlUS4 = new GetGAsOfTypeCTRL(gaList);
    }

    public void checkLists() {
        if (getTypeGAList().isEmpty()) {
            System.out.println("There are no Types of Geographical Area. Please create some first.");
            UtilsUI.backToMenu();
            return;
        }

        if (gaList.size() == 0 && !getTypeGAList().isEmpty()) {
            System.out.println("There are no Geographical Areas Please create some first.");
            UtilsUI.backToMenu();
        }

        else {
            this.run();
        }
    }

    public void run() {
        System.out.println("Choose the Type of Geographical Area you wish to see from the list below.");
        //calls the list of previously inserted types for the user to choose from.
        System.out.println ("Current list of types of geographical area:");
        System.out.println (ctrlUS4.showListOfTypeGA());

        //user chooses the number of type he wishes to see.
        int typeIndex = UtilsUI.requestIntegerInInterval(1,getTypeGAList().size(),"Please choose a valid Type");
        String areaType = getTypeGAList().get(typeIndex - 1).toString();

        //if there are GAs from the chosen type they are returned
        if (!ctrlUS4.getGAListFromType(typeIndex).isEmpty()) {
            System.out.println("Here are the Geographical Areas from " + areaType + " type:\n" + ctrlUS4.showListGAFromType(typeIndex));
            UtilsUI.backToMenu();
        }
        //if there are no GAs from the chosen type the following message is shown
        else{
            System.out.println("No results were found. Please try again.\n\n");
            UtilsUI.backToMenu();
        }
    }

}
