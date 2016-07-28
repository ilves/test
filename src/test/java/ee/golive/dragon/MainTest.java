package ee.golive.dragon;

import ee.golive.dragon.simulation.Launcher;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class MainTest {

    @Test
    public void mainMethod() throws InterruptedException {
        Launcher launcher = Mockito.mock(Launcher.class);
        Main.setLauncher(launcher);
        Main.main(new String[]{"1", "2", "3"});
        Mockito.verify(launcher).run(new String[] { "1", "2", "3" });
    }
}
