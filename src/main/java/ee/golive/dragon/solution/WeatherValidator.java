package ee.golive.dragon.solution;

import ee.golive.dragon.domain.Knight;
import ee.golive.dragon.domain.KnightSkill;
import ee.golive.dragon.domain.WeatherReport;

import java.util.Map;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class WeatherValidator {

    private WeatherReport weatherReport;

    WeatherValidator(WeatherReport weatherReport) {
        this.weatherReport = weatherReport;
    }

    public boolean isCodeSet() {
        return weatherReport.getCode() != null;
    }
}
