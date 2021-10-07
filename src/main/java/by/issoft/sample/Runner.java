package by.issoft.sample;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Runner {
    static Statistics statistics = new Statistics();

    public static void main(String[] args) throws InterruptedException {

        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1024);

        final List<Producer> producers = IntStream.range(0, 2)
                .mapToObj(i -> new Producer(queue))
                .collect(Collectors.toList());
        producers.forEach(Thread::start);

        final List<Consumer> consumers = IntStream.range(0, 20)
                .mapToObj(i -> new Consumer(queue))
                .collect(Collectors.toList());
        consumers.forEach(Thread::start);

    }

    public static void methodC() {
        statistics.incrementNumberOfMethodCExecutions();
    }
}

class Statistics {
    @Getter
    private int numberOfMethodCExecutions;

    public synchronized void incrementNumberOfMethodCExecutions() {
        int k = numberOfMethodCExecutions + 1;
        numberOfMethodCExecutions = k;
    }
}

@Slf4j
@RequiredArgsConstructor
class StatisticsOutputWriter extends Thread {
    private final Statistics statistics;
    private final int rateInSeconds;
    private boolean needRun = true;
    private boolean paused = false;

    @SneakyThrows
    @Override
    public void run() {
        while (needRun) {
            checkIfPausedAndWait();
            log.info("C executed {} time", statistics.getNumberOfMethodCExecutions());
            TimeUnit.SECONDS.sleep(rateInSeconds);
        }
    }

    public void finish() {
        log.info("finished");
        needRun = false;
    }

    private synchronized void waitUntilResume() throws InterruptedException {
        this.wait();
    }

    private void checkIfPausedAndWait() throws InterruptedException {
        while (paused) {
            waitUntilResume();
        }
    }

    public void pause() {
        log.info("paused");
        paused = true;
    }

    public synchronized void unpause() {
        log.info("unpaused");
        paused = false;
        this.notify();
    }
}

@Slf4j
class Producer extends Thread {
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                log.info("producing");
                IntStream.range(0, 30).forEach(i -> {
                    queue.offer(new Random().nextInt());
                    queue.notifyAll();
                });
            }
            Thread.sleep(10_000);
        }
    }
}

@Slf4j
class Consumer extends Thread {
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Integer element = queue.take();
            log.info("polled = {}", element);
        }
    }
}
