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

import ee.golive.dragon.api.GameApi;
import ee.golive.dragon.api.GameApiImpl;
import ee.golive.dragon.api.WeatherApi;
import ee.golive.dragon.api.WeatherApiImpl;
import ee.golive.dragon.domain.SolutionResponse;
import ee.golive.dragon.domain.SolutionStatus;
import ee.golive.dragon.helper.RequestHelper;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class GameSimulator {

    private static final Logger LOGGER = Logger.getLogger(GameSimulator.class.getName());

    private final BlockingQueue<SimulationTask> workQueue;
    private final ExecutorService service;
    private final ResultCounter resultCounter;
    private final int numWorkers;
    private final int maxConnections;

    public GameSimulator(int numWorkers, int workQueueSize, int maxConnections) {
        workQueue = new LinkedBlockingQueue<>(workQueueSize);
        service = Executors.newFixedThreadPool(numWorkers);
        resultCounter = new ResultCounter();
        this.numWorkers = numWorkers;
        this.maxConnections = maxConnections;
    }

    private void initWorkers() {
        for (int i = 0; i < numWorkers; i++) {
            service.submit(new Worker(workQueue, resultCounter));
        }
    }

    /**
     * Runs game simulator by creating specified number of workers and game tasks.
     * Logs the process and result.
     *
     * @param gamesToSimulate Number of games to run
     * @throws InterruptedException
     */
    public void run(int gamesToSimulate) throws InterruptedException {
        printInfo(gamesToSimulate, numWorkers);
        Date start = new Date();
        RequestHelper requestHelper = createRequestHelper();
        produceGames(gamesToSimulate, createGameApi(requestHelper), createWeatherApi(requestHelper));
        initWorkers();
        service.shutdown();
        if (!service.awaitTermination(1, TimeUnit.MINUTES)) {
            printResults(gamesToSimulate, start);
            System.exit(0);
        }
        printResults(gamesToSimulate, start);
    }

    public void produceGames(int numToProduce, GameApi gameApi, WeatherApi weatherApi) {
        for(int i = 0; i < numToProduce; i++) {
            produceGame(new SimulationTask(gameApi, weatherApi));
        }
    }

    private void printInfo(int numGames, int numWorkers) {
        LOGGER.info("Starting GameSimulator:");
        LOGGER.info(" - running total games: " + numGames);
        LOGGER.info(" - using worker threads: " + numWorkers);
    }

    private void printResults(int numGames, Date start) {
        float winRate = ((float) resultCounter.getVictoryCount().get()/numGames*100f);
        float runTime = ((new Date().getTime() - start.getTime()) / 1000f);
        LOGGER.info("Results:");
        LOGGER.info(" - total games: " + numGames);
        LOGGER.info(" - win rate: " + winRate + "%");
        LOGGER.info(" - time spent: " + runTime + "s");
    }

    public RequestHelper createRequestHelper() {
        HttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        HttpClient httpClient = new HttpClient(connectionManager);
        params.setStaleCheckingEnabled(true);
        params.setMaxConnectionsPerHost(httpClient.getHostConfiguration(), maxConnections);
        params.setMaxTotalConnections(maxConnections);
        connectionManager.setParams(params);
        return new RequestHelper(httpClient);
    }

    public GameApi createGameApi(RequestHelper requestHelper) {
        return new GameApiImpl(requestHelper);
    }

    public WeatherApi createWeatherApi(RequestHelper requestHelper) {
        return new WeatherApiImpl(requestHelper);
    }

    public void produceGame(SimulationTask item) {
        try {
            workQueue.put(item);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Object that represents worker that runs the SimulationTasks
     */
    private static class Worker implements Runnable {

        private final BlockingQueue<SimulationTask> workQueue;
        private final ResultCounter resultCounter;

        public Worker(BlockingQueue<SimulationTask> workQueue, ResultCounter resultCounter) {
            this.workQueue = workQueue;
            this.resultCounter = resultCounter;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    getAndRunTask();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        private void getAndRunTask() throws InterruptedException {
            SimulationTask item = workQueue.poll(1, TimeUnit.MICROSECONDS);
            if (item != null) {
                resultCounter.count(item.run());
            } else {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Object used to count game simulation results
     */
    private static class ResultCounter {

        private AtomicInteger victoryCount = new AtomicInteger(0);
        private AtomicInteger defeatCount = new AtomicInteger(0);

        public AtomicInteger getVictoryCount() {
            return victoryCount;
        }

        public AtomicInteger getDefeatCount() {
            return defeatCount;
        }

        public void count(SolutionResponse solutionResponse) {
            if (solutionResponse.getStatus().equals(SolutionStatus.VICTORY)) {
                victoryCount.incrementAndGet();
            } else {
                defeatCount.incrementAndGet();
            }
        }
    }
}
