package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class DishwasherType implements DeviceType {

    private static final String DEVICE_TYPE = "Dishwasher";

    /**
     * Get device type
     * @return Dishwasher Type
     */
    @Override
    public String getDeviceType() {
        return DEVICE_TYPE;
    }

    /**
     * Create a Device
     * @param deviceName Dishwasher name
     * @param nominalPower Dishwasher nominal power
     * @return A new Dishwasher
     */
    @Override
    public Device createDevice(String deviceName, double nominalPower) {
        String[] attributeNames = {"Capacity"};
        String[] attributeUnits = {"Dish Sets"};

        DeviceSpecs devSpecs = new GenericSpecs("Dishwasher",attributeNames,attributeUnits);

        return new Dishwasher(deviceName, devSpecs, nominalPower);
    }
}
