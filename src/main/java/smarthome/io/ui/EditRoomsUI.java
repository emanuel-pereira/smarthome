package smarthome.io.ui;

import smarthome.controller.cli.EditRoomCTRL;
import smarthome.controller.cli.ListRoomsOfHouseCTRL;
import smarthome.dto.RoomDTO;
import smarthome.dto.RoomDetailDTO;

import java.util.List;

public class EditRoomsUI {


    private final ListRoomsOfHouseCTRL ctrlUS108;
    private  EditRoomCTRL ctrlUS109;
    private String roomId;

    /**
     * User interface constructor
     *
     */
    public EditRoomsUI() {
        this.ctrlUS108 = new ListRoomsOfHouseCTRL();
        this.ctrlUS109 = new EditRoomCTRL();

    }

    /**
     * Checks if the repository is empty (size equal 0). If not advanced to room selection. Otherwise a message is shown
     * informing that there are no rooms
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */


    public void run() throws NoSuchFieldException {
        if (this.ctrlUS108.roomListSize() == 0) {
            System.out.println ("There are no rooms. Please add a room to the house first.\n");
            UtilsUI.backToMenu();
            return;
        }
        this.selectRoom ();
    }

    /**
     * A list of rooms is shown and the user selects one
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    private void selectRoom() throws NoSuchFieldException {
        System.out.println ("Choose a Room from the list below:");
        int counter = 1;
        for (RoomDTO room : this.ctrlUS108.findAll()) {
            System.out.print(counter++ + " Id: " + room.getID() + " | Description: " + room.getDescription() + "\n");
        }
        int index = UtilsUI.requestIntegerInInterval (1, (int) this.ctrlUS108.roomListSize (), "Please choose a valid option");
        index--;

        RoomDTO room1 = this.ctrlUS108.findAll().get(index);
        this.roomId = room1.getID ();
        this.editFloor();

    }

    /**
     * It isn't possible to change Id or Description, so first the the user is asked if he wants to edit the floor of the room.
     * If so, a Integer is validated and the data is sent
     * in order to edit the floor.
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    private void editFloor() throws NoSuchFieldException {
        if (UtilsUI.confirmOption("\"Do you want to edit the floor?(y/n)", "Please type y/Y for Yes or n/N for No.")) {
            System.out.println("Insert the floor where the room is:");
            Integer floor = UtilsUI.requestInteger("Please insert a number\nInsert the floor where the room is:");
            this.ctrlUS109.setFloor(this.roomId, floor);
            this.editLength();
        } else {

            this.editLength();
        }
    }

    /**
     * The user is asked if he wants to edit the length of the room. If so, a double is validated and the data is sent
     * in order to edit the length.
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    private void editLength() throws NoSuchFieldException {
        if (UtilsUI.confirmOption("\"Do you want to edit the length?(y/n)", "Please type y/Y for Yes or n/N for No.")) {
            System.out.println("Insert the length of the room (in meters):");
            double length = UtilsUI.requestDoubleInInterval(1, 1000, "Please insert a number (higher than zero)\nInsert the length of the room (in meters):");
            this.ctrlUS109.setLength(this.roomId, length);
            this.editWidth();
        } else {
            this.editWidth();
        }
    }

    /**
     * The user is asked if he wants to edit the width of the room. If so, a double is validated and the data is sent
     * in order to edit the width.
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    private void editWidth() throws NoSuchFieldException {
        if (UtilsUI.confirmOption("\"Do you want to edit the width?(y/n)", "Please type y/Y for Yes or n/N for No.")) {
            System.out.println("Insert the width of the room (in meters):");
            double width = UtilsUI.requestDoubleInInterval(1, 1000, "Please insert a number (higher than zero)\nInsert the width of the room (in meters):");
            this.ctrlUS109.setWidth(this.roomId, width);
            this.editHeight();
        } else {
            this.editHeight();
        }
    }

    /**
     * The user is asked if he wants to edit the height of the room. If so, a double is validated and the data is sent
     * in order to edit the height.
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    private void editHeight() throws NoSuchFieldException {
        if (UtilsUI.confirmOption("\"Do you want to edit the height?(y/n)", "Please type y/Y for Yes or n/N for No.")) {
            System.out.println("Insert the height of the room (in meters):");
            double height = UtilsUI.requestDoubleInInterval(0, 1000, "Please insert a number (higher than zero)\nInsert the height of the room (in meters):");
            this.ctrlUS109.setHeight(this.roomId, height);
            UtilsUI.backToMenu();
        } else {
            UtilsUI.backToMenu();
        }
    }

}