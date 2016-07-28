package ee.golive.dragon.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public enum SolutionStatus {

    VICTORY("Victory"),
    DEFEAT("Defeat");

    String value;

    SolutionStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static SolutionStatus fromString(String string) {
        if (string.equals(VICTORY.value)) {
            return VICTORY;
        } else {
            return DEFEAT;
        }
    }
}
