package ee.golive.dragon.simulation;

import ee.golive.dragon.api.GameApi;
import ee.golive.dragon.api.WeatherApi;
import ee.golive.dragon.domain.Dragon;
import ee.golive.dragon.domain.Game;
import ee.golive.dragon.domain.SolutionResponse;
import ee.golive.dragon.domain.WeatherReport;
import ee.golive.dragon.solution.Solver;
import ee.golive.dragon.solution.SolverImpl;

import java.util.Date;
import java.util.logging.Level;
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
            LOGGER.log(Level.SEVERE, "", e);
        }
        return null;
    }
}
