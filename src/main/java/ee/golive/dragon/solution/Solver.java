package ee.golive.dragon.solution;

import ee.golive.dragon.domain.Game;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public interface Solver {
    void solve(Game game) throws SolverException;
}
