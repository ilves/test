package ee.golive.dragon.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
@JacksonXmlRootElement(localName = "report")
public class WeatherReport {

    private WeatherCode code;

    @JacksonXmlProperty(isAttribute = true)
    private String time;

    @JacksonXmlProperty(isAttribute = true)
    private Cord coords;

    @JacksonXmlProperty(isAttribute = true)
    private String message;

    @JacksonXmlProperty(localName = "varX-Rating", isAttribute = true)
    private String varX;

    public WeatherCode getCode() {
        return code;
    }

    public void setCode(WeatherCode code) {
        this.code = code;
    }

    public class Cord {

        private float x;
        private float y;
        private float z;

        public Cord() {}

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public float getZ() {
            return z;
        }

        public void setZ(float z) {
            this.z = z;
        }
    }
}
