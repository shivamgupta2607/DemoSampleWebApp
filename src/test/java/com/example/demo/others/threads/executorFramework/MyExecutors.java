package com.example.demo.others.threads.executorFramework;

public class MyExecutors {

  public static MyExecutorService newFixedThreadPool(int nThreads) {
    return new MyExecutorService(nThreads);
  }
}
