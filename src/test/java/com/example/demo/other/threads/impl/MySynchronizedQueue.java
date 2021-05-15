package com.example.demo.other.threads.impl;

import com.example.demo.Util;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySynchronizedQueue<T> {


  private  T data = null;
  private final Lock lock = new ReentrantLock();
  private final Condition cNoData = lock.newCondition();
  private final Condition cHasData = lock.newCondition();



  public void push(T t) {
    lock.lock();

    try {
      while (this.data != null) {
        cNoData.await();
      }
      this.data = t;
      Util.printStr(String.format("Pushed Element {%s}", t));
      cHasData.signalAll();

      while (this.data != null) {
        cNoData.await();
      }
      Util.printStr(String.format("Consumer has read the element {%s} and hence exiting!\n", t));
    } catch (final InterruptedException ex) {

    } finally {
      lock.unlock();
    }

  }

  public T take() {
    lock.lock();
    try {
      while (this.data == null) {
        cHasData.await();
      }
      T e = this.data;
      this.data = null;
      cNoData.signalAll();
      Util.printStr(String.format("Took element {%s}", e));
      return e;
    } catch (InterruptedException ex) {

    } finally {
      lock.unlock();
    }
    return null;
  }

}