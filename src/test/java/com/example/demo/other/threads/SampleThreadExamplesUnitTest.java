package com.example.demo.other.threads;

import com.example.demo.other.threads.impl.MyBlockingQueue;
import com.example.demo.other.threads.impl.MySynchronizedQueue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

public class SampleThreadExamplesUnitTest {

  @Test
  public void blockingQueueExample() throws Exception {
    final MyBlockingQueue<Integer> bq = new MyBlockingQueue<>();

    final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    final ExecutorService manager = Executors.newFixedThreadPool(5);

    manager.submit(() -> {
      while (true) {
        bq.take();
        Thread.currentThread().sleep(500);
      }
    });

    manager.submit(() -> {
      while (true) {
        bq.push(new Random().nextInt(100));
        Thread.currentThread().sleep(100);
      }
    });
    manager.shutdown();

    scheduledExecutorService.scheduleAtFixedRate(() -> {
      bq.printQueueState();
    }, 0, 5, TimeUnit.SECONDS);
    manager.awaitTermination(30, TimeUnit.SECONDS);

    Thread.currentThread().sleep(1000);
    bq.printQueueState();
  }


  @Test
  public void synchronizedQueueExample() throws Exception {
    final MySynchronizedQueue<Integer> sq = new MySynchronizedQueue<>();

    final ExecutorService manager = Executors.newFixedThreadPool(5);

    manager.submit(() -> {
      while (true) {
        sq.take();
        Thread.currentThread().sleep(3000);
      }
    });

    manager.submit(() -> {
      while (true) {
        sq.push(new Random().nextInt(100));
        Thread.currentThread().sleep(100);
      }
    });
    manager.shutdown();

    manager.awaitTermination(30, TimeUnit.SECONDS);
  }

}


