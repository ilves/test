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
import ee.golive.dragon.domain.SolutionResponse;
import ee.golive.dragon.domain.SolutionStatus;
import ee.golive.dragon.solution.Solver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class SimulationTaskTest {

    @Mock
    WeatherApi weatherApi;

    @Mock
    GameApi gameApi;

    @Mock
    Solver solver;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void runTask() throws IOException {
        Game game = new Game();
        game.setId(5l);
        SolutionResponse solutionResponse = new SolutionResponse();
        solutionResponse.setStatus(SolutionStatus.VICTORY);
        solutionResponse.setMessage("Superb!");
        doReturn(game).when(gameApi).getNewGame();
        doReturn(solutionResponse).when(gameApi).putSolution(any());
        SimulationTask task = new SimulationTask(gameApi, weatherApi, solver);
        SolutionResponse response = task.run();
    }
}
