package ee.golive.dragon.solution;

import ee.golive.dragon.domain.Dragon;
import ee.golive.dragon.domain.DragonSkill;

import java.util.Map;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class DragonTrainer {

    /**
     * Balances Dragon's skills down by provided skill priority list.
     * Decreases points one by one from the skills provided in the list.
     * Does not allow negative skills.
     *
     * @param dragon Dragon object
     * @param skillList Skill priority list
     * @param points Number of skill points
     * @throws SolverException when there is skill points underflow
     */
    public static void balanceDown(Dragon dragon, DragonSkill[] skillList, int points) throws SolverException {
        while (points > 0) {
            Map<DragonSkill, Integer> skills = dragon.getAllSkills();
            boolean decreased = false;
            for (DragonSkill skill : skillList) {
                if (skills.get(skill) > 0 && points > 0) {
                    dragon.increaseSkill(skill, -1);
                    points--;
                    decreased = true;
                }
            }
            if (!decreased) {
                break;
            }
        }
        if (points > 0) {
            throw new SolverException("Skill points underflow by: " + points);
        }
    }

    /**
     * Balances Dragon's skills up by provided skill priority list.
     * Increases points one by one from the skills provided in the list.
     * Does not allow higher skill level than provided by maxPointsPerSkill
     * argument.
     *
     * @param dragon Dragon object
     * @param skillList Skill priority list
     * @param points Number of skill points
     * @param maxPointsPerSkill Maximum number of skill points per skill
     * @throws SolverException When there is skill points overflow
     */
    public static void balanceUp(Dragon dragon, DragonSkill[] skillList, int points, int maxPointsPerSkill)
            throws SolverException {
        while (points > 0) {
            Map<DragonSkill, Integer> skills = dragon.getAllSkills();
            boolean increased = false;
            for (DragonSkill skill : skillList) {
                if (skills.get(skill) < maxPointsPerSkill && points > 0) {
                    dragon.increaseSkill(skill, 1);
                    points--;
                    increased = true;
                }
            }
            if (!increased) {
                break;
            }
        }
        if (points > 0) {
            throw new SolverException("Skill points overflow by: " + points);
        }
    }

    /**
     * Returns how many skill can be added without exceeding max skill points per skill
     * while using skill points amount provided by the argument.
     *
     * @param dragon Dragon object
     * @param skill Skill to be increased
     * @param skillPoints Skill points to be added
     * @param maxPointsPerSkill Maximum number of skill points per skill
     * @return Number of skill points can be added
     */
    public static int increasableBy(Dragon dragon, DragonSkill skill, int skillPoints, int maxPointsPerSkill) {
        int currentPoints = dragon.getAllSkills().get(skill);
        int pointsAvailable = maxPointsPerSkill - currentPoints;
        return pointsAvailable <= 0 ? 0 : (pointsAvailable >= skillPoints ? skillPoints : pointsAvailable);
    }
}
