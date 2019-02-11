package smarthome.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Fridge implements DeviceSpecs {
    private int mFreezerCapacity;
    private int mRefrigeratorCapacity;
    private int mAnnualEnergyConsumption;
    private DeviceType mDeviceType;


    public Fridge() {
    }

    public Fridge(int freezerCapacity, int refrigeratorCapacity, int annualEnergyConsumption) {
        this.mFreezerCapacity = freezerCapacity;
        this.mRefrigeratorCapacity = refrigeratorCapacity;
        this.mAnnualEnergyConsumption = annualEnergyConsumption;
    }

    @Override
    public DeviceType getDeviceType() {
        return mDeviceType;
    }

    @Override
    public void setType(DeviceType deviceType) {
        mDeviceType = deviceType;
    }

    public List<String> getAttributesNames() {
        List<String> result = new ArrayList<>();
        String freezerCapacity = "Freezer Capacity";
        String refrigeratorCapacity = "Refrigerator Capacity";
        result.add(freezerCapacity);
        result.add(refrigeratorCapacity);
        return result;
    }

    @Override
    public void setAttributeValue(String attribute, String newValue) {
        String freezerCapacity = "Freezer Capacity";
        String refrigeratorCapacity = "Refrigerator Capacity";
        if (attribute.contains(freezerCapacity))
            setFreezerCapacity(parseInt(newValue));
        if (attribute.contains(refrigeratorCapacity))
            setRefrigeratorCapacity(parseInt(newValue));
    }

    public String showDeviceAttributeNamesAndValues() {
        StringBuilder result = new StringBuilder();
        int number = 3;
        for (String s : getAttributesNames()) {
            result.append(number);
            result.append(" - ");
            if (s.contains("Freezer Capacity"))
                result.append(s.concat(" : " + this.getFreezerCapacity()));
            if (s.contains("Refrigerator Capacity"))
                result.append(s.concat(" : " + this.getRefrigeratorCapacity()));
            result.append("\n");
            number++;

        }
        return result.toString();
    }

    @Override
    public double getEnergyConsumption() {
        return mAnnualEnergyConsumption / 365;
    }

    public void setFreezerCapacity(int freezerCapacity) {
        mFreezerCapacity = freezerCapacity;
    }

    public int getFreezerCapacity() {
        return mFreezerCapacity;
    }

    public void setRefrigeratorCapacity(int refrigeratorCapacity) {
        mRefrigeratorCapacity = refrigeratorCapacity;
    }

    public int getRefrigeratorCapacity() {
        return mRefrigeratorCapacity;
    }
}

