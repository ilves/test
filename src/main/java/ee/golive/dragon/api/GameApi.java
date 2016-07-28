package ee.golive.dragon.api;

import ee.golive.dragon.domain.Game;
import ee.golive.dragon.domain.SolutionResponse;

import java.io.IOException;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public interface GameApi {
    /**
     * Fetches new game from the api and creates Game object
     *
     * @return Game object from the api
     * @throws IOException
     */
    Game getNewGame() throws IOException;

    /**
     * Sends solution of the game to the api and returns the SolutionResponse
     *
     * @param game Game object to be solved
     * @return SolutionResponse of the solution
     * @throws IOException
     */
    SolutionResponse putSolution(Game game) throws IOException;
}
