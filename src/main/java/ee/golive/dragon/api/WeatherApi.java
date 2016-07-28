package ee.golive.dragon.api;

import ee.golive.dragon.domain.WeatherReport;

import java.io.IOException;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public interface WeatherApi {
    /**
     * Gets weather report by game id
     *
     * @param gameId Id of the game
     * @return WeatherReport report
     * @throws IOException
     */
    WeatherReport getWeather(Long gameId) throws IOException;
}
