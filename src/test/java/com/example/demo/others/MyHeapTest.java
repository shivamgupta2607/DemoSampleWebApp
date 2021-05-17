package com.example.demo.others;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class MyHeapTest {

  @Test
  public void heapExample() {
    final MyHeap<Integer> myHeap = new MyHeap<>(10, true);
    myHeap.add(10);
    myHeap.add(9);
    myHeap.add(15);
    myHeap.add(4);
    myHeap.add(8);
    myHeap.add(3);
    myHeap.add(7);
    myHeap.add(13);
    myHeap.add(20);
    myHeap.add(1);
    Assert.isTrue(myHeap.remove() == 1, "Remove logic broken");
    Assert.isTrue(myHeap.remove() == 3, "Remove logic broken");
    Assert.isTrue(myHeap.remove() == 4, "Remove logic broken");
    Assert.isTrue(myHeap.remove() == 7, "Remove logic broken");
  }
}
