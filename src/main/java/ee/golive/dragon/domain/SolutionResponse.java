package ee.golive.dragon.domain;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class SolutionResponse {

    private SolutionStatus status;
    private String message;

    public SolutionStatus getStatus() {
        return status;
    }

    public void setStatus(SolutionStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
