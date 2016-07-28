package ee.golive.dragon.simulation;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class Launcher {

    private static final int DEFAULT_GAMES_TO_RUN = 51;
    private static final int DEFAULT_MAX_HTTP_CONNECTIONS = 20;
    private static final int DEFAULT_WORKER_THREADS = 20;

    public void run(String args[]) throws InterruptedException {
        int gamesToRun = DEFAULT_GAMES_TO_RUN;
        int workerThreads = DEFAULT_WORKER_THREADS;
        int maxConnections = DEFAULT_MAX_HTTP_CONNECTIONS;
        if (args.length > 0) {
            gamesToRun = getIntFromArray(args, 0);
        }
        if (args.length > 1) {
            workerThreads = getIntFromArray(args, 1);
        }
        if (args.length > 2) {
            maxConnections = getIntFromArray(args, 2);
        }
        createGameSimulator(workerThreads, gamesToRun, maxConnections).run(gamesToRun);
    }

    public GameSimulator createGameSimulator(int workerThreads, int gamesToRun, int maxConnections) {
        return new GameSimulator(workerThreads, gamesToRun, maxConnections);
    }

    private static int getIntFromArray(String[] args, int position) {
        return Integer.parseInt(args[position]);
    }
}
