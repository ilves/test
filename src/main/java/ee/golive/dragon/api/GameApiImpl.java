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
import ee.golive.dragon.helper.RequestHelper;
import ee.golive.dragon.domain.Game;
import ee.golive.dragon.domain.SolutionResponse;
import ee.golive.dragon.domain.SolutionRequest;

import java.io.IOException;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class GameApiImpl implements GameApi {

    private static final String GAME_URL = "http://www.dragonsofmugloar.com/api/game";
    private static final String SOLUTION_URL = "http://www.dragonsofmugloar.com/api/game/{id}/solution";

    private RequestHelper requestHelper;
    private ObjectMapper mapper;

    public GameApiImpl(RequestHelper requestHelper) {
        this.requestHelper = requestHelper;
        this.mapper = new ObjectMapper();
    }

    /**
     * {@inheritDoc}
     */
    public Game getNewGame() throws IOException {
        String json = requestHelper.get(GAME_URL);
        return mapper.readValue(json, Game.class);
    }

    /**
     * {@inheritDoc}
     */
    public SolutionResponse putSolution(Game game) throws IOException {
        SolutionRequest solutionRequest = new SolutionRequest();
        solutionRequest.setDragon(game.getDragon());
        String url = SOLUTION_URL.replaceFirst("\\{id\\}", game.getId().toString());
        String json = requestHelper.put(url, mapper.writeValueAsString(solutionRequest));
        return mapper.readValue(json, SolutionResponse.class);
    }
}
