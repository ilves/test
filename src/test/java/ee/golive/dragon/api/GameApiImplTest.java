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

import ee.golive.dragon.domain.Game;
import ee.golive.dragon.domain.SolutionResponse;
import ee.golive.dragon.domain.SolutionStatus;
import ee.golive.dragon.helper.RequestHelper;
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
