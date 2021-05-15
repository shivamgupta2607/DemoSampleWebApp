package com.example.demo.other.threads.executorFramework;

public class MyExecutors {

  public static MyExecutorService newFixedThreadPool(int nThreads) {
    return new MyExecutorService(nThreads);
  }
}
