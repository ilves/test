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

package ee.golive.dragon.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class Knight {

    private String name;
    private Integer attack;
    private Integer armor;
    private Integer agility;
    private Integer endurance;

    public Integer getAgility() {
        return agility;
    }

    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getEndurance() {
        return endurance;
    }

    public void setEndurance(Integer endurance) {
        this.endurance = endurance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<KnightSkill, Integer> getAllSkills()
    {
        HashMap<KnightSkill, Integer> skills = new HashMap<>();
        skills.put(KnightSkill.ARMOR, getArmor());
        skills.put(KnightSkill.AGILITY, getAgility());
        skills.put(KnightSkill.ATTACK, getAttack());
        skills.put(KnightSkill.ENDURANCE, getEndurance());
        return skills;
    }

    /**
     * Finds and returns Knight's maximum skill
     *
     * @return Maximum skill
     */
    public KnightSkill getMaxSkill() {
        return Collections.max(getAllSkills().entrySet(), (a, b) -> a.getValue() - b.getValue()).getKey();
    }
}
