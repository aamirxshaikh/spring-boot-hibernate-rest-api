package com.demo.springboothibernaterestapi.service;

import com.demo.springboothibernaterestapi.exception.ResourceNotFoundException;
import com.demo.springboothibernaterestapi.model.Employee;
import com.demo.springboothibernaterestapi.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new ResourceNotFoundException("Employee", "id", id);
        }
    }
}
