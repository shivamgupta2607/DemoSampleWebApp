package com.example.demo.service;

import com.example.demo.BaseTest;
import com.example.demo.entity.Employee;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class MyServiceTest extends BaseTest {

  @Autowired
  private MyService myService;

  @Test
  public void empSave() {
    final String empName = "test emp";
    final Employee employee = new Employee();
    employee.setName(empName);
    this.myService.save(employee);
    final List<Employee> employeeList = this.myService.findByName(empName);
    Assert.notNull(employeeList, "List cannot be null");
    Assert.isTrue(employeeList.size() > 0, String
        .format("There should be at least an employee with name {%s}, actual size found is {%d}",
            empName, employeeList.size()));
    Assert.isTrue(employeeList.size() == 1, String
        .format("There should be exactly one employee with name {%s}, actual size found is {%d}",
            empName, employeeList.size()));
  }

}
