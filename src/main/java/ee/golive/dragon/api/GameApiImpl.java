package ee.golive.dragon.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.golive.dragon.helper.RequestHelper;
import ee.golive.dragon.domain.Game;
import ee.golive.dragon.domain.SolutionResponse;
import ee.golive.dragon.domain.SolutionRequest;
import org.apache.commons.httpclient.HttpClient;

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
