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
import ee.golive.dragon.domain.Dragon;
import ee.golive.dragon.domain.Game;
import ee.golive.dragon.domain.SolutionResponse;
import ee.golive.dragon.solution.Solver;
import ee.golive.dragon.solution.SolverImpl;

import java.util.logging.Logger;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
class SimulationTask {

    private static final Logger LOGGER = Logger.getLogger(SimulationTask.class.getName());

    private GameApi gameApi;
    private WeatherApi weatherApi;
    private Solver solver;

    public SimulationTask(GameApi gameApi, WeatherApi weatherApi, Solver solver) {
        this.gameApi = gameApi;
        this.weatherApi = weatherApi;
        this.solver = solver;
    }

    public SimulationTask(GameApi gameApi, WeatherApi weatherApi) {
        this(gameApi, weatherApi, new SolverImpl());
    }

    /**
     * Runs game simulation task by querying apis and passing data to game solver.
     *
     * @return Solution response from the api.
     */
    public SolutionResponse run() {
        try {
            Game game = gameApi.getNewGame();
            game.setWeatherReport(weatherApi.getWeather(game.getId()));
            game.setDragon(new Dragon());
            solver.solve(game);
            SolutionResponse solutionResponse = gameApi.putSolution(game);
            LOGGER.info("Game: " + game.getId()
                    + " Result: " + solutionResponse.getStatus()
                    + " Message: " + solutionResponse.getMessage());
            return solutionResponse;
        } catch (Exception e) {
            LOGGER.severe("Solution failed: " + e.getMessage());
        }
        return null;
    }
}
