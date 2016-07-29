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

package ee.golive.dragon.simulation;

import ee.golive.dragon.api.GameApi;
import ee.golive.dragon.api.WeatherApi;
import ee.golive.dragon.domain.Game;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class GameSimulatorTest {

    @Mock
    GameApi gameApi;

    @Mock
    WeatherApi weatherApi;

    @Before
    public void setup() throws IOException {
        MockitoAnnotations.initMocks(this);
        Game game = new Game();
        doReturn(game).when(gameApi).getNewGame();
    }

    @Test
    public void run() throws InterruptedException {
        GameSimulator gameSimulator = spy(new GameSimulator(5, 5, 5));
        doNothing().when(gameSimulator).produceGames(anyInt(), any(), any());
        doReturn(gameApi).when(gameSimulator).createGameApi(any());
        doReturn(weatherApi).when(gameSimulator).createWeatherApi(any());
        gameSimulator.run(5);
        Mockito.verify(gameSimulator).produceGames(anyInt(), any(), any());
    }
}
