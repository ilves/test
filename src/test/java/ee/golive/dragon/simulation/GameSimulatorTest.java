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
