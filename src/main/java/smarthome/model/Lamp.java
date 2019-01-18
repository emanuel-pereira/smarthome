package smarthome.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Lamp implements DeviceSpecs {
    private int mLuminousFlux;

    public Lamp(int luminousFlux) {
        this.mLuminousFlux = luminousFlux;
    }

    public void setLuminousFlux(int newLuminousFlux) {
        mLuminousFlux = newLuminousFlux;
    }

    public int getLuminousFlux() {
        return mLuminousFlux;
    }
    public List<String> getDeviceAttributesInString() {
        List<String> result = new ArrayList<>();
        String LuminousFlux = "4 - Luminous Flux : " + this.mLuminousFlux;
        result.add(LuminousFlux);
        return result;
    }

    public void setAttributeValue(String attribute, String newValue) {
        String LuminousFLux = "4 - Luminous Flux : " + this.mLuminousFlux;
        if (attribute.equals(LuminousFLux))
            setLuminousFlux(parseInt(newValue));
    }
}

