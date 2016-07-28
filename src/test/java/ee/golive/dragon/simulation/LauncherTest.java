package ee.golive.dragon.simulation;


import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class LauncherTest {

    @Test
    public void argsParsing() throws InterruptedException {
        GameSimulator gameSimulator = mock(GameSimulator.class);
        Launcher launcher = spy(new Launcher());
        doReturn(gameSimulator).when(launcher).createGameSimulator(anyInt(), anyInt(), anyInt());
        launcher.run(new String[]{"5", "4", "3"});
        Mockito.verify(launcher).createGameSimulator(4, 5, 3);
    }
}
