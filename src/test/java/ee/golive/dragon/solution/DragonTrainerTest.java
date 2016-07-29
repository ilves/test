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
import org.junit.Before;
import org.junit.Test;

import static ee.golive.dragon.solution.DragonTrainer.balanceDown;
import static ee.golive.dragon.solution.DragonTrainer.balanceUp;
import static ee.golive.dragon.solution.DragonTrainer.increasableBy;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.spy;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class DragonTrainerTest {

    private Dragon dragon;

    @Before
    public void setup() {
        dragon = spy(Dragon.class);
        dragon.setClawSharpness(5);
        dragon.setFireBreath(3);
        dragon.setWingStrength(2);
        dragon.setScaleThickness(3);
    }

    @Test
    public void balanceDownSingleSkill() throws SolverException {
        balanceDown(dragon, new DragonSkill[]{DragonSkill.CLAW_SHARPNESS}, 4);
        assertSame(1, dragon.getClawSharpness());
        assertSame(3, dragon.getFireBreath());
        assertSame(2, dragon.getWingStrength());
        assertSame(3, dragon.getScaleThickness());
    }

    @Test
    public void balanceDownMultipleSkills() throws SolverException {
        balanceDown(dragon, new DragonSkill[]{
                DragonSkill.CLAW_SHARPNESS,
                DragonSkill.FIRE_BREATH,
        }, 3);
        assertSame(3, dragon.getClawSharpness());
        assertSame(2, dragon.getFireBreath());
        assertSame(2, dragon.getWingStrength());
        assertSame(3, dragon.getScaleThickness());
    }

    @Test(expected = Exception.class)
    public void balanceDownNoPointsAvailable() throws SolverException {
        balanceDown(dragon, new DragonSkill[]{DragonSkill.FIRE_BREATH}, 4);
        assertSame(0, dragon.getFireBreath());
    }

    @Test
    public void balanceDownZeroSkillPoints() throws SolverException {
        balanceDown(dragon, new DragonSkill[]{DragonSkill.FIRE_BREATH}, 0);
        assertSame(3, dragon.getFireBreath());
    }

    @Test
    public void balanceUpSingleSkill() throws SolverException {
        balanceUp(dragon, new DragonSkill[]{DragonSkill.WING_STRENGTH}, 3, 10);
        assertSame(5, dragon.getClawSharpness());
        assertSame(3, dragon.getFireBreath());
        assertSame(5, dragon.getWingStrength());
        assertSame(3, dragon.getScaleThickness());
    }

    @Test
    public void balanceUpMultipleSkills() throws SolverException {
        balanceUp(dragon, new DragonSkill[]{
                DragonSkill.CLAW_SHARPNESS,
                DragonSkill.FIRE_BREATH,
        }, 3, 10);
        assertSame(7, dragon.getClawSharpness());
        assertSame(4, dragon.getFireBreath());
    }

    @Test(expected = Exception.class)
    public void balanceUpNoOverMaximum() throws SolverException {
        balanceUp(dragon, new DragonSkill[]{DragonSkill.FIRE_BREATH}, 8, 10);
        assertSame(10, dragon.getFireBreath());
    }

    @Test
    public void increasableByTest() {
        assertSame(1, increasableBy(dragon, DragonSkill.FIRE_BREATH, 1, 10));
        assertSame(7, increasableBy(dragon, DragonSkill.FIRE_BREATH, 8, 10));
    }
}
