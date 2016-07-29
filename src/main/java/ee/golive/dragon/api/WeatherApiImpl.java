/*
 * The MIT License
 *
 * Copyright (c) 2016 Taavi Ilves (https://github.com/ilves)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ee.golive.dragon.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ee.golive.dragon.helper.RequestHelper;
import ee.golive.dragon.domain.WeatherReport;

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
