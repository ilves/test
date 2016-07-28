package ee.golive.dragon.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ee.golive.dragon.helper.RequestHelper;
import ee.golive.dragon.domain.WeatherReport;
import org.apache.commons.httpclient.HttpClient;

import java.io.IOException;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class WeatherApiImpl implements WeatherApi {

    private static final String WEATHER_URL = "http://www.dragonsofmugloar.com/weather/api/report/{id}";

    private RequestHelper requestHelper;
    private ObjectMapper xmlMapper;

    public WeatherApiImpl(RequestHelper requestHelper) {
        this.requestHelper = requestHelper;
        xmlMapper = new XmlMapper();
    }

    /**
     * {@inheritDoc}
     */
    public WeatherReport getWeather(Long gameId) throws IOException {
        String url = WEATHER_URL.replaceFirst("\\{id\\}", gameId.toString());
        String xml = requestHelper.get(url);
        return xmlMapper.readValue(xml, WeatherReport.class);
    }
}
