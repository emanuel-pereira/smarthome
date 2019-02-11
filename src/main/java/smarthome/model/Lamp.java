package smarthome.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Lamp implements DeviceSpecs {
    private int mLuminousFlux;
    private DeviceType mDeviceType;

    public Lamp(){}

    public Lamp(int luminousFlux) {
        this.mLuminousFlux = luminousFlux;
    }

    public void setLuminousFlux(int newLuminousFlux) {
        mLuminousFlux = newLuminousFlux;
    }

    public int getLuminousFlux() {
        return mLuminousFlux;
    }

    public List<String> getAttributesNames() {
        List<String> result = new ArrayList<>();
        String LuminousFlux = "Luminous Flux";
        result.add(LuminousFlux);
        return result;
    }

    public void setAttributeValue(String attribute, String newValue) {
        String LuminousFLux = "Luminous Flux";
        if (attribute.equals(LuminousFLux))
            setLuminousFlux(parseInt(newValue));
    }

    public String showDeviceAttributeNamesAndValues() {
        StringBuilder result = new StringBuilder();
        int number=3;
        for (String s : getAttributesNames()) {
            result.append(number);
            result.append(" - ");
            if (s.contains("Luminous Flux"))
                result.append(s.concat(" : " + this.getLuminousFlux()));
            result.append("\n");
            number++;
        }
        return result.toString();}

    @Override
    public double getEnergyConsumption() {
        return 0;
    }

    public DeviceType getDeviceType() {
        return mDeviceType;
    }
    @Override
    public void setType(DeviceType deviceType) {
        mDeviceType = deviceType;
    }
}
