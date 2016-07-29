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
