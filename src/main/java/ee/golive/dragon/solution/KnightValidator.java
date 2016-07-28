package ee.golive.dragon.solution;

import ee.golive.dragon.domain.Knight;
import ee.golive.dragon.domain.KnightSkill;

import java.util.Map;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class KnightValidator {

    private Knight knight;

    KnightValidator(Knight knight) {
        this.knight = knight;
    }

    public boolean isSkillsSet() {
        Map<KnightSkill, Integer> skills = knight.getAllSkills();
        for (Integer points : skills.values()) {
            if (points == null) return false;
        }
        return true;
    }
}
