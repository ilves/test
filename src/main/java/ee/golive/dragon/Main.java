package ee.golive.dragon;

import ee.golive.dragon.simulation.Launcher;

/**
 * Launches the game solver.
 * Accepts two arguments. First one is the number of games to run
 * and the second one is the number of worker threads to use,
 * third one is the number of maximum connections. This should be
 * at least the same as number of workers, because worker threads will
 * wait for a connection object from the pool and will sleep if all are used.
 * If argument(s) are not provided, default values are used.
 *
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class Main {

    private static Launcher launcher = new Launcher();

    public static void main(String[] args) throws InterruptedException {
        launcher.run(args);
    }

    public static void setLauncher(Launcher launcher) {
        Main.launcher = launcher;
    }
}
