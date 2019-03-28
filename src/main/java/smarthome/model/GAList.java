package smarthome.model;

import smarthome.dto.GeographicalAreaDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GAList {

    private List<GeographicalArea> listOfGa;

    /**
     * Constructor method to set the attribute of the GA's List as an ArrayList
     */
    public GAList() {
        this.listOfGa = new ArrayList<>();
    }

    /**
     * Method Constructor to create new instances of Geographical Areas
     *
     * @param inputDesignation GA String designation
     * @param typeArea         GA Type String designation
     * @param occupationArea   GA occupation area
     * @param location         GA central location represented by GPS coordinates
     * @return a new instance of a Geographical Area
     */
    public GeographicalArea newGA(String inputID, String inputDesignation, TypeGA typeArea, OccupationArea occupationArea, Location location) {
        return new GeographicalArea(inputID, inputDesignation, typeArea, occupationArea, location);
    }

    /**
     * Method to add a new GA into the GA's List
     * Before the placement the method will first verify the existence of a similar GA in the list
     * If none is found the method truly adds this new one to the list
     *
     * @param inputGA Geographical Area to be added
     * @return boolean value, true if correctly added, false if not added
     */
    public boolean addGA(GeographicalArea inputGA) {
        if (this.listOfGa.contains(inputGA))
            return false;
        return this.listOfGa.add(inputGA);
    }

    /**
     * Method similar to the get method for Lists but as the List is private in the class it is
     * needed to exist a method that publicly returns the List of GA's to other methods to read
     *
     * @return List of Geographical Areas
     */
    public List<GeographicalArea> getGAList() {
        return this.listOfGa;
    }

    /**
     * Method to get a specific Geographical Area in index position i
     *
     * @param i index position of the GA's List
     * @return the specific requested Geographical Area
     */
    public GeographicalArea get(int i) {
        return this.listOfGa.get(i);
    }


    /**
     * US04
     * Method that returns a list of Geographical Areas of a certain Type.
     * The user inputs the TypeGA he wishes to obtain, for example, "street" and will receive a list of GAs from that Type.
     * A 'for each' was used for simpler and easier list iteration.
     *
     * @param inputTypeGA string inserted by user
     */
    public List<GeographicalArea> gAFromThisType(String inputTypeGA) {
        List<GeographicalArea> gAFromTypeList = new ArrayList<>();
        for (GeographicalArea ga : this.listOfGa) {
            if (ga.getTypeName().equals(inputTypeGA)) {
                gAFromTypeList.add(ga);
            }
        }
        return gAFromTypeList;
    }

    /**
     * @return Method that shows the list of Geographical Areas in a unique string
     */
    public String showGAListInString() {
        List<GeographicalArea> list = this.getGAList();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (GeographicalArea ga : list) {
            result.append(number++);
            result.append(element);
            result.append(ga.getGAName());
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * @return the number of elements in the geographical areas list as an integer value
     */
    public int size() {
        return this.listOfGa.size();
    }

    public GeographicalArea getLastGA() {
        return this.listOfGa.get(this.listOfGa.size() - 1);
    }

    public void importDataFromCSVFileForEachGA(String filePathAndName) throws IOException {
        for (GeographicalArea geographicalArea : this.listOfGa)
            geographicalArea.importReadingsToSensorsFromCSVFile(filePathAndName);
    }

    public List<GeographicalAreaDTO> getGAListDTO() {
        List<GeographicalAreaDTO> gaListDTO=new ArrayList<>();
        for (GeographicalArea ga : listOfGa) {
            GeographicalAreaDTO gaDTO=ga.toDTO();
            gaListDTO.add(gaDTO);
        }
        return gaListDTO;
    }
}
