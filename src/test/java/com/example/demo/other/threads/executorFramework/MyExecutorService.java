package com.example.demo.other.threads.executorFramework;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class MyExecutorService<T> {

  final BlockingQueue<MyFuture<T>> mbq = new LinkedBlockingQueue<>();

  public MyExecutorService(int nThreads) {

    IntStream.range(0, nThreads).forEach(i -> {
      initNewThread();
    });
  }

  private void initNewThread() {
    new Thread(() -> {

      while(true) {
        try {
          final MyFuture<T> myFuture = mbq.take();
          final T t = myFuture.getCallable().call();
          myFuture.set(t);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

    }).start();
  }

  public MyFuture<T> submit(final Callable<T> callable) {
    final MyFuture<T> myFuture = new MyFuture<>(callable);
    mbq.add(myFuture);
    return myFuture;
  }
}
