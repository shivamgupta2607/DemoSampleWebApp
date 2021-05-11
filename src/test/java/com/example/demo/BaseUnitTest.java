package com.example.demo;

import com.example.demo.vo.EmployeeVO;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class BaseUnitTest {

  @Test
  public void sample() {
    int n = 0;
    Assert.isTrue(n == 0, "n cannot be any other number than zero!");
  }


}
