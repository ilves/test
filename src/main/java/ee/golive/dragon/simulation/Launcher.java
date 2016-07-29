/*
 * The MIT License
 *
 * Copyright (c) 2016 Taavi Ilves (https://github.com/ilves)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
