package com.demo.springboothibernaterestapi.service;

import com.demo.springboothibernaterestapi.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(long id);
}
