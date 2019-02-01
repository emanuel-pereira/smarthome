package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class DeviceList {
    private List<Device> mDeviceList;

    /**
     * Constructor initializing an empty device list.
     */
    public DeviceList() {
        mDeviceList = new ArrayList<>();
    }

    /**
     * Method to add a new device instance to a DeviceList
     *
     * @param newDevice instance to be added to the device list
     * @return boolean result of the device addition
     */
    public boolean addDevice(Device newDevice) {
        if (!mDeviceList.contains(newDevice)) {
            mDeviceList.add(newDevice);
            return true;
        } else return false;
    }

    /**
     * Method that creates a local instance of a device a device whose type has its own specific characteristics
     *
     * @param name         of the device
     * @param deviceSpecs  specific characteristics of the device, including, for example, device type
     * @param nominalPower of the device
     * @return a new Device instance
     */
    public Device newDevice(String name, DeviceSpecs deviceSpecs, double nominalPower) {
        return new Device(name, deviceSpecs, nominalPower);
    }

    /**
     * @param index position in the device list
     * @return the device in index position in the device list
     */
    public Device get(int index) {
        return mDeviceList.get(index);
    }

    /**
     * @return the device in the last index position of the device list
     */
    public Device getLastElement() {
        return mDeviceList.get(mDeviceList.size() - 1);
    }


    /**
     * Method to get the list of Powered devices of a room
     *
     * @return the device list
     * public List<Powered> getPoweredDeviceList() {
     List<Powered> poweredDeviceList;
     for Device device: mDeviceList
     {
     poweredDeviceList.add(mDeviceList.get().)
     }
     return ;
     }
     */

    public String showDeviceListInString() {
        List<Device> list = getDeviceList();
        StringBuilder result = new StringBuilder();
        String element = " - Device: ";
        String typeStr = " | Type: ";
        String statusStr = " | Active: ";
        int number = 1;
        for (Device device : list) {
            result.append(number++);
            result.append(element);
            result.append(device.getName());
            result.append(typeStr);
            result.append(device.getDeviceSpecs().getType());
            result.append(statusStr);
            result.append(device.status());
            result.append("\n");
        }
        return result.toString();
    }

    public List<Device> getDeviceList() {
        return mDeviceList;
    }

    public int size() {
        return mDeviceList.size();
    }

    /**
     * Method that call the Java native method to remove an object from the device's list
     *
     * @param device object device that will be removed
     * @return boolean result of the device's removal
     */
    public boolean remove(Device device) {
        return this.mDeviceList.remove(device);
    }

    /**
     * Method that disable the Device by setting it's status to false (false meaning deactivated)
     *
     * @param device Device to be deactivated
     * @return true result if Device successfully deactivated
     */
    public boolean deactivateDevice(Device device) {
        return device.deactivateDevice();
    }

    /**
     * @return a list of all devices that have isMetered parameter set as true
     */

    public List<Device> getMeteredDevices() {
        List<Device> meteredDeviceList = new ArrayList<>();

        for (Device device : mDeviceList) {
            if (device.isMetered()) {
                meteredDeviceList.add(device);
            }
        }
        return meteredDeviceList;
    }

}

