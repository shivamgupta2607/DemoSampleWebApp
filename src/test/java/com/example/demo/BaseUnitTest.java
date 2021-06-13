package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class BaseUnitTest {
// this is test case from shivam's repo
  @Test
  public void sample() {
    int n = 0;
    Assert.isTrue(n == 0, "n cannot be any other number than zero!");
  }



}
