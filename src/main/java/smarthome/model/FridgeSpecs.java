package smarthome.model;

import java.util.*;

import static java.lang.Double.NaN;


/**
 * This is a "template" class for classes that implement DeviceSpecs and should be appropriate for generic devices, i.e.,
 * those that don't require a very specific interface implementation. Most devices should fall in this category, with
 * specific energy consumption calculations the only thing setting them apart.
 */

public class FridgeSpecs implements DeviceSpecs {


    /* ------- Private fields ------- */

    private String deviceType;


    // Changing the following string arrays allows one to quickly develop device specs just by using different parameters.
    // Improvement (?) Create a distinct class for this bit.

    private String[] attributeNames = {"Freezer Capacity", "Refrigerator Capacity", "Annual Energy Consumption"};
    private String[] attributeUnits = {"liters", "liters", "kWh"};

    private HashMap<String, Double> attributeValuesMap = new HashMap<>();
    private HashMap<String, String> attributeUnitsMap = new HashMap<>();

    private List<String> attributeNamesList = new ArrayList<>();


    /* ------- Public fields ------- */


    /* ------ Constructors ------- */

    public FridgeSpecs(String deviceType) {
        this.deviceType = deviceType;
        initializeClass();
    }

    /**
     * Private initialization method of the constructor(s). Copies the string arrays containing the attributes names and units
     * to the internal Map representation of them. This is a design choice, as Maps are more easily managed than
     * arrays of strings and because this allows one to create an instance of the class using the device type only and
     * deferring the setting of the attributes to later. The isInitialized field is used to quickly assert if the
     * instance is valid or not.
     */
    private void initializeClass() {

        int items = this.attributeNames.length;

        for (int i = 0; i < items; i++) {
            this.attributeNamesList.add(this.attributeNames[i]);
        }

        this.attributeValuesMap.clear();
        this.attributeUnitsMap.clear();

        for (int j = 0; j < items; j++) {


            this.attributeUnitsMap.put(this.attributeNames[j], this.attributeUnits[j]);
           this.attributeValuesMap.put(this.attributeNames[j], NaN); // values are not part of the constructor
        }
    }

    /* ------ Interface methods ------- */

    public String getDeviceType() {
        return this.deviceType;
    }

    public Double getAttributeValue(String attribute) {
        return this.attributeValuesMap.get(attribute);
    }

    public String getAttributeUnit(String attribute) {
        return this.attributeUnitsMap.get(attribute);
    }

    public List<String> getAttributesNames() {
        return this.attributeNamesList;
    }

    public List<Double> getAttributeValues() {
        List<Double> attributeValues = new ArrayList<>();

        for (String key : this.attributeNamesList
        ) {
            attributeValues.add(this.attributeValuesMap.get(key));
        }
        return attributeValues;
    }

    public List<String> getAttributeUnits() {

        List<String> unitsList = new ArrayList<>();

        for (String key : this.attributeNamesList
        ) {
            unitsList.add(this.attributeUnitsMap.get(key));
        }
        return unitsList;
    }

    public void setAttributeUnit(String attribute, String unit) {
        if (this.attributeValuesMap.containsKey(attribute)) {
            this.attributeUnitsMap.put(attribute, unit);
        }
    }

    public void setAttributeValue(String attribute, double newValue) {
        if (this.attributeValuesMap.containsKey(attribute)) {
            this.attributeValuesMap.replace(attribute, newValue);
        }
    }
}