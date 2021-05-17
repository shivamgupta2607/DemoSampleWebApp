package com.example.demo.others;

import com.example.demo.util.GenUtil;
import org.junit.jupiter.api.Test;

public class MyHeapTest {

  @Test
  public void heapExample() {
    final MyHeap<Integer> myHeap = new MyHeap<>(10, false);
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
    GenUtil.printArr(myHeap.getData());
  }
}
