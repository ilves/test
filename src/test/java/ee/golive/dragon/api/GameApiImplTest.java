package ee.golive.dragon.api;

import ee.golive.dragon.domain.Game;
import ee.golive.dragon.domain.SolutionResponse;
import ee.golive.dragon.domain.SolutionStatus;
import ee.golive.dragon.helper.RequestHelper;
import org.apache.commons.httpclient.HttpClient;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class GameApiImplTest {

    @Test
    public void testNewGame() throws IOException {
        RequestHelper requestHelper = mock(RequestHelper.class);
        when(requestHelper.get(any())).thenReturn("{\"gameId\":13,\"knight\":{\"name\":\"Sir John\"}}");
        GameApi gameApi = new GameApiImpl(requestHelper);
        Game game = gameApi.getNewGame();
        assertSame(13l, game.getId());
        assertEquals("Sir John", game.getKnight().getName());
    }

    @Test
    public void testPutSolution() throws IOException {
        RequestHelper requestHelper = mock(RequestHelper.class);
        when(requestHelper.put(any(), any())).thenReturn("{\"status\":\"Victory\",\"message\":\"All good!\"}");
        GameApi gameApi = new GameApiImpl(requestHelper);
        Game game = new Game();
        game.setId(18l);
        SolutionResponse solutionResponse = gameApi.putSolution(game);
        assertSame(SolutionStatus.VICTORY, solutionResponse.getStatus());
        assertEquals("All good!", solutionResponse.getMessage());
    }
}
