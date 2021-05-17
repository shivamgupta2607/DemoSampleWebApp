package com.example.demo.others.threads.executorFramework;

import java.util.concurrent.Callable;
import java.util.concurrent.SynchronousQueue;

public class MyFuture<T> {

  private final SynchronousQueue<T> synchronousQueue = new SynchronousQueue<>();
  private Callable<T> callable = null;

  public MyFuture(final Callable<T> callable) {
    this.callable = callable;
  }

  public T get() throws InterruptedException {

    return synchronousQueue.take();
  }

  protected void set(T t) {
    synchronousQueue.add(t);
  }

  public Callable<T> getCallable() {
    return callable;
  }
}
