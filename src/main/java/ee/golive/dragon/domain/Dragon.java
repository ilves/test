package ee.golive.dragon.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class Dragon {

    private Integer scaleThickness;
    private Integer clawSharpness;
    private Integer wingStrength;
    private Integer fireBreath;

    public Integer getClawSharpness() {
        return clawSharpness;
    }

    public void setClawSharpness(Integer clawSharpness) {
        this.clawSharpness = clawSharpness;
    }

    public Integer getFireBreath() {
        return fireBreath;
    }

    public void setFireBreath(Integer fireBreath) {
        this.fireBreath = fireBreath;
    }

    public Integer getWingStrength() {
        return wingStrength;
    }

    public void setWingStrength(Integer wingStrength) {
        this.wingStrength = wingStrength;
    }

    public Integer getScaleThickness() {
        return scaleThickness;
    }

    public void setScaleThickness(int scaleThickness) {
        this.scaleThickness = scaleThickness;
    }

    /**
     * Increases skill by provided amount
     *
     * @param skill to increase
     * @param increaseAmount number of skill points to increase
     */
    public void increaseSkill(DragonSkill skill, int increaseAmount) {
        switch (skill) {
            case WING_STRENGTH:
                setWingStrength(getWingStrength()+increaseAmount);
                break;
            case CLAW_SHARPNESS:
                setClawSharpness(getClawSharpness()+increaseAmount);
                break;
            case FIRE_BREATH:
                setFireBreath(getFireBreath()+increaseAmount);
                break;
            case SCALE_THICKNESS:
                setScaleThickness(getScaleThickness()+increaseAmount);
                break;
        }
    }

    public Map<DragonSkill, Integer> getAllSkills()
    {
        HashMap<DragonSkill, Integer> skills = new HashMap<>();
        skills.put(DragonSkill.CLAW_SHARPNESS, getClawSharpness());
        skills.put(DragonSkill.FIRE_BREATH, getFireBreath());
        skills.put(DragonSkill.SCALE_THICKNESS, getScaleThickness());
        skills.put(DragonSkill.WING_STRENGTH, getWingStrength());
        return skills;
    }
}
