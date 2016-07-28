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
