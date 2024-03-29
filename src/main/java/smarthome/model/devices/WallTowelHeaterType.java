package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class WallTowelHeaterType implements DeviceType {

    private static final String DEVICE_TYPE = "WallTowelHeater";

    @Override
    public String getDeviceType() {
        return DEVICE_TYPE;
    }

    @Override
    public Device createDevice(String devName, double nominalPower) {
        String devType = getDeviceType();
        DeviceSpecs devSpecs = new GenericNoSpecs(devType);
        return new WallTowelHeater(devName,devSpecs,nominalPower);
    }
}
