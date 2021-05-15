package com.example.demo.other.threads;

import com.example.demo.Util;
import com.example.demo.other.threads.executorFramework.MyExecutorService;
import com.example.demo.other.threads.executorFramework.MyExecutors;
import com.example.demo.other.threads.executorFramework.MyFuture;
import com.example.demo.other.threads.impl.MyBlockingQueue;
import com.example.demo.other.threads.impl.MyReadWriteLock;
import com.example.demo.other.threads.impl.MySynchronizedQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

public class SampleThreadExamplesUnitTest {

  @Test
  public void executorFrameworkExample() throws Exception {
    final MyExecutorService manager = MyExecutors.newFixedThreadPool(3);
    final MyFuture<Integer> myFuture = manager.submit( () -> {
      Thread.currentThread().sleep(2 * 1000);
      return 20;
    });

    Util.printStr(myFuture.get());
  }


  @Test
  public void blockingQueueExample() throws Exception {
    final MyBlockingQueue<Integer> bq = new MyBlockingQueue<>();

    Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
      bq.printQueueState();
    }, 0, 5, TimeUnit.SECONDS);

    final ExecutorService manager = Executors.newFixedThreadPool(5);

    manager.submit(() -> {
      while (true) {
        bq.take();
        Thread.currentThread().sleep(2000);
      }
    });

    manager.submit(() -> {
      while (true) {
        bq.push(new Random().nextInt(100));
        Thread.currentThread().sleep(100);
      }
    });
    manager.shutdown();

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

  /**
   * For testing, there are 1 Writer and multiple Reader Threads. The sleep timings are set in a way to
   * depict the behaviour that when multiple readers are reading, it would print it fast and smooth
   * and shows that while multiple threads reading there is no locking, however when write thread
   * writes and sleeps, it would blocker other threads to read and give suddent jerks while printing
   * the output.
   */
  @Test
  public void readWriteLockExample() throws InterruptedException {

    final List<Integer> list = new ArrayList<>();

    final MyReadWriteLock myReadWriteLock = new MyReadWriteLock();

    final ExecutorService manager = Executors.newCachedThreadPool();

    final Runnable r1_Reader = () -> {
      while(true) {
        try {
          myReadWriteLock.readLock();
          Util.printList(list);
          myReadWriteLock.readUnLock();
          Thread.currentThread().sleep(200);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    final Runnable r2_Writer = () -> {
      while(true) {
        try {
          myReadWriteLock.writeLock();
          Util.printStr("Acquired write lock.....");
          list.add(new Random().nextInt(100));
          Thread.currentThread().sleep(2 * 1000);
          myReadWriteLock.writeUnLock();
          Thread.currentThread().sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };


    manager.submit(r1_Reader);
    manager.submit(r1_Reader);
    manager.submit(r1_Reader);
    manager.submit(r1_Reader);
    manager.submit(r2_Writer);
    manager.shutdown();

    manager.awaitTermination(30, TimeUnit.SECONDS);


  }
}



