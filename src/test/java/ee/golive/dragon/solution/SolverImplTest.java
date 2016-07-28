package ee.golive.dragon.solution;

import ee.golive.dragon.domain.*;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class SolverImplTest {

    @Test()
    public void testNormalSolution() throws SolverException {
        Game game = createGame();
        Solver solver = new SolverImpl();
        solver.solve(game);
        assertNotNull(game.getKnight());
    }

    @Test(expected = SolverException.class)
    public void testWeatherCodeMissing() throws SolverException {
        Game game = createGame();
        game.setKnight(new Knight());
        Solver solver = new SolverImpl();
        solver.solve(game);
    }

    @Test(expected = SolverException.class)
    public void testKnightSkillsMissing() throws SolverException {
        Game game = createGame();
        game.setWeatherReport(new WeatherReport());
        Solver solver = new SolverImpl();
        solver.solve(game);
    }

    private Game createGame() {
        Game game = new Game();
        WeatherReport weatherReport = new WeatherReport();
        weatherReport.setCode(WeatherCode.DRY);
        game.setDragon(new Dragon());
        game.setKnight(createKnight(5, 5, 5, 5));
        game.setWeatherReport(weatherReport);
        return game;
    }

    private Knight createKnight(int agility, int armor, int attack, int endurance) {
        Knight knight = new Knight();
        knight.setAgility(agility);
        knight.setArmor(armor);
        knight.setAttack(attack);
        knight.setEndurance(endurance);
        return knight;
    }

    private Dragon createDragon(int clawSharpness, int fireBreath, int scaleThickness, int wingStrength) {
        Dragon dragon = new Dragon();
        dragon.setClawSharpness(clawSharpness);
        dragon.setFireBreath(fireBreath);
        dragon.setScaleThickness(scaleThickness);
        dragon.setWingStrength(wingStrength);
        return dragon;
    }
}
