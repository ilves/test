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
