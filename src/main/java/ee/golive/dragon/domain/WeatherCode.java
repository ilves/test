package ee.golive.dragon.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public enum WeatherCode {

    STORM("SRO"),
    FOG("FUNDEFINEDG"),
    DRY("T E"),
    RAIN("HVA"),
    NORMAL("NMR");

    String value;

    WeatherCode(String value) {
        this.value = value;
    }

    @JsonCreator
    public static WeatherCode fromString(String string) throws Exception {
        for(WeatherCode code : WeatherCode.values()) {
            if (code.value.equals(string)) {
                return code;
            }
        }
        throw new Exception("WeatherCode: " + string + " not implemented!");
    }
}
