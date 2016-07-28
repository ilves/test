package ee.golive.dragon.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class Game {

    @JsonProperty("gameId")
    private Long id;

    private Knight knight;

    private Dragon dragon;

    private WeatherReport weatherReport;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }

    public Knight getKnight() {
        return knight;
    }

    public void setKnight(Knight knight) {
        this.knight = knight;
    }

    public WeatherReport getWeatherReport() {
        return weatherReport;
    }

    public void setWeatherReport(WeatherReport weatherReport) {
        this.weatherReport = weatherReport;
    }
}
