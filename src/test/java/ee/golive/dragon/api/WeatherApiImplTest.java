package ee.golive.dragon.api;

import ee.golive.dragon.domain.Game;
import ee.golive.dragon.domain.WeatherCode;
import ee.golive.dragon.domain.WeatherReport;
import ee.golive.dragon.helper.RequestHelper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class WeatherApiImplTest {

    @Test
    public void testNewGame() throws IOException {
        RequestHelper requestHelper = mock(RequestHelper.class);
        when(requestHelper.get(any())).thenReturn("<report><code>T E</code></report>");
        WeatherApi weatherApi = new WeatherApiImpl(requestHelper);
        WeatherReport weatherReport = weatherApi.getWeather(13l);
        assertSame(WeatherCode.DRY, weatherReport.getCode());
    }
}
