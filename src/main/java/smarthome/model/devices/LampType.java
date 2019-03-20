package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class LampType implements DeviceType {

    private String deviceType = "Lamp";

    public String getDeviceType() {
        return this.deviceType;
    }

    public Device createDevice(String deviceName, double nominalPower) {
        String devType = getDeviceType();

        DeviceSpecs devSpecs = new LampSpecs(devType);

        return new Lamp(deviceName, devSpecs, nominalPower);
    }
}