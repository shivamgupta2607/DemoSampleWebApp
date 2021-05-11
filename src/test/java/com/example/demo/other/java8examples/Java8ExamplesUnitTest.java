package com.example.demo.other.java8examples;

import com.example.demo.vo.EmployeeVO;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 * Some common Java-8 examples using,
 *  - Functional Interface/Lambda Expression
 *  - Streams (map, filter, reduce, collect)
 *  - Optional
 *  - Local Date Time
 */
public class Java8ExamplesUnitTest {


  @Test
  public void sortByValuesInHashMap() {
    final Map<String, Integer> map = new HashMap<>();
    map.put("red", 3);
    map.put("green", 5);
    map.put("yellow", 4);
    map.put("pink", 3);

    final List<Entry<String, Integer>> ll = map.entrySet().stream()
        .sorted((o1, o2) -> o1.getValue() - o2.getValue()).collect(Collectors.toList());
    System.out.println(ll);

    IntStream.range(1, ll.size()).forEach(i -> {
          Assert.isTrue(ll.get(i).getValue() >= ll.get(i - 1).getValue(),
              String.format("{%d} Current value {%d} cannot be smaller than the previous one {%d}", i, ll.get(i).getValue(), ll.get(i-1).getValue()));
        }
    );
  }

  @Test
  public void listToMapWithNaturalIndexing() {
    final List<String> list = Arrays.asList("zero", "one", "two", "three", "four");

    final HashMap<Integer, String> collect = list
        .stream()
        .collect(HashMap<Integer, String>::new,
            (map, streamValue) -> map.put(map.size(), streamValue),
            (map, map2) -> {
            });

    collect.forEach((k, v) -> System.out.println(k + ":" + v));
  }

  @Test
  public void iterateListWithIndexVariableAvailable() {
    final List<String> list = Arrays.asList("zero", "one", "two", "three", "four");
    IntStream.range(0, list.size()).forEach(i -> {
      System.out.println(String.format("list(%d) : {%s}", i, list.get(i)));
    });
  }


  @Test
  public void basicStreamMapFilterCollect() {
    final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    final int sum = numbers.stream().map(e -> e + 1).filter(e -> e % 2 == 0)
        .reduce(0, (e1, e2) -> e1 + e2);
    Assert.isTrue(sum == 30, "Sum should match");

  }


  @Test
  public void listForEach() {
    final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    numbers.forEach(new Consumer<Integer>() {
                      @Override
                      public void accept(Integer integer) {
                        System.out.println(integer);
                      }
                    }
    );
    System.out.println();
    numbers.forEach(e -> {
      System.out.println(e);
    });

  }

  @Test
  public void parallelStream() {
    final List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      list.add(i);
    }
    final Set<Long> threadNames = new HashSet<>();

    final int result = list.parallelStream().filter(e -> e % 2 == 0).reduce(0, (e1, e2) -> e1 + e2);

    System.out.println(result);

  }

  @Test
  public void sampleComparatorUsingLambdaInJava8Style() {
    final List<EmployeeVO> employeeVOList = Arrays
        .asList(new EmployeeVO(1, "one"), new EmployeeVO(2, "two"),
            new EmployeeVO(3, "three"), new EmployeeVO(4, "four"), new EmployeeVO(5, "five"));

    Collections.sort(employeeVOList, (o1, o2) -> o1.getName().compareTo(o2.getName()));
    employeeVOList.stream().forEach(e -> {
      System.out.println(e);
    });
  }

  @Test
  public void lambdaExampleUsingRunnable() throws Exception {
    new Thread(() -> {
      System.out.println("Thread Name : " + Thread.currentThread().getName());
    }).start();

    Thread.currentThread().sleep(1000);
  }


  @Test
  public void functionaInterfaceExampleUsingCustomPredicates() {
    final MyPredicate<Integer> evenPredicate = (x) -> x % 2 == 0;
    final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    numbers.stream().forEach(e -> {
      final boolean isEven = e % 2 == 0;
      final boolean result = isEven ? evenPredicate.apply(e) : !evenPredicate.apply(e);
      Assert.isTrue(result, String.format("{%d} is an {%s} number", e, (isEven ? "even" : "odd")));
    });

    Assert.isTrue(12 == MyPredicate.staticTriple(4), "Value should match");
    Assert.isTrue(8 == evenPredicate.defaultDouble(4), "Value should match");

  }

  @Test
  public void printAvailableZones() {
    ZoneId.getAvailableZoneIds().stream().forEach(e -> {
      System.out.println(e);
    });
  }

  @Test
  public void timeAPIs() {
    final LocalDateTime dateTimeInCST = LocalDateTime.now(ZoneId.of("US/Central"));
    System.out.println(dateTimeInCST);

    final LocalDateTime dateTimeInSG = dateTimeInCST.atZone(ZoneId.of("US/Eastern"))
        .withZoneSameInstant(ZoneId.of("Asia/Singapore")).toLocalDateTime();
    System.out.println(dateTimeInSG);

  }
}
