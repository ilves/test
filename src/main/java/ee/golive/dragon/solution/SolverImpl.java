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

import ee.golive.dragon.domain.Dragon;
import ee.golive.dragon.domain.DragonSkill;
import ee.golive.dragon.domain.Game;
import ee.golive.dragon.domain.Knight;

import static ee.golive.dragon.solution.DragonTrainer.balanceDown;
import static ee.golive.dragon.solution.DragonTrainer.balanceUp;
import static ee.golive.dragon.solution.DragonTrainer.increasableBy;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class SolverImpl implements Solver {

    private static int MAX_POINTS_PER_SKILL = 10;
    private Game game;

    /**
     * Solves the game by assigning skill points to dragon to defeat the knight.
     *
     * @param game Game to be solved
     * @throws SolverException When solution can not be calculated
     */
    public void solve(Game game) throws SolverException {
        this.game = game;
        validateInput();
        setDefaultSkills();
        applyCounterStrategy();
        applyWeatherStrategy();
    }

    private void validateInput() throws SolverException {
        KnightValidator knightValidator = new KnightValidator(game.getKnight());
        WeatherValidator weatherValidator = new WeatherValidator(game.getWeatherReport());
        if (!knightValidator.isSkillsSet()) {
            throw new SolverException("Some knight skills are not set!");
        }
        if (!weatherValidator.isCodeSet()) {
            throw new SolverException("Weather report is not valid!");
        }
    }

    private void applyCounterStrategy() throws SolverException {
        DragonSkill[] priority = new DragonSkill[]{};
        DragonSkill counterSkill = null;
        Dragon dragon = game.getDragon();
        switch (game.getKnight().getMaxSkill()) {
            case AGILITY:
                priority = new DragonSkill[] {
                        DragonSkill.SCALE_THICKNESS,
                        DragonSkill.CLAW_SHARPNESS,
                        DragonSkill.FIRE_BREATH
                };
                counterSkill = DragonSkill.WING_STRENGTH;
                break;
            case ARMOR:
                priority = new DragonSkill[] {
                        DragonSkill.SCALE_THICKNESS,
                        DragonSkill.FIRE_BREATH,
                        DragonSkill.WING_STRENGTH
                };
                counterSkill = DragonSkill.CLAW_SHARPNESS;
                break;
            case ATTACK:
                priority = new DragonSkill[] {
                        DragonSkill.FIRE_BREATH,
                        DragonSkill.CLAW_SHARPNESS,
                        DragonSkill.WING_STRENGTH
                };
                counterSkill = DragonSkill.SCALE_THICKNESS;
                break;
            case ENDURANCE:
                priority = new DragonSkill[]{
                        DragonSkill.SCALE_THICKNESS,
                        DragonSkill.CLAW_SHARPNESS,
                        DragonSkill.WING_STRENGTH
                };
                counterSkill = DragonSkill.FIRE_BREATH;
                break;
        }
        dragon.increaseSkill(counterSkill, 2);
        balanceDown(dragon, priority, 2);
    }

    private void applyWeatherStrategy() throws SolverException {
        Dragon dragon = game.getDragon();
        DragonSkill[] priority;
        switch (game.getWeatherReport().getCode()) {
            case DRY:
                dragon.setScaleThickness(5);
                dragon.setFireBreath(5);
                dragon.setClawSharpness(5);
                dragon.setWingStrength(5);
                break;
            case RAIN:
                int points = increasableBy(dragon, DragonSkill.CLAW_SHARPNESS, dragon.getFireBreath(),
                        MAX_POINTS_PER_SKILL);
                dragon.setFireBreath(dragon.getFireBreath() - points);
                dragon.increaseSkill(DragonSkill.CLAW_SHARPNESS, points);
                priority = new DragonSkill[]{
                        DragonSkill.FIRE_BREATH,
                        DragonSkill.SCALE_THICKNESS,
                        DragonSkill.WING_STRENGTH
                };
                int claw = increasableBy(dragon, DragonSkill.CLAW_SHARPNESS, 10, MAX_POINTS_PER_SKILL);
                balanceDown(dragon, priority, claw);
                dragon.increaseSkill(DragonSkill.CLAW_SHARPNESS, claw);
                priority = new DragonSkill[]{
                        DragonSkill.WING_STRENGTH,
                        DragonSkill.SCALE_THICKNESS
                };
                balanceUp(dragon, priority, dragon.getFireBreath(), MAX_POINTS_PER_SKILL);
                dragon.setFireBreath(0);
                break;
        }
    }

    private void setDefaultSkills() {
        Dragon dragon = game.getDragon();
        Knight knight = game.getKnight();
        dragon.setFireBreath(knight.getEndurance());
        dragon.setScaleThickness(knight.getAttack());
        dragon.setWingStrength(knight.getAgility());
        dragon.setClawSharpness(knight.getArmor());
    }
}
