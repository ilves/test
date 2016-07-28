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
