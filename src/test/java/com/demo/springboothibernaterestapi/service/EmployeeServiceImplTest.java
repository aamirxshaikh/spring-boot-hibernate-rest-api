package com.demo.springboothibernaterestapi.service;

import com.demo.springboothibernaterestapi.model.Employee;
import com.demo.springboothibernaterestapi.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void saveEmployee() throws Exception {
        Employee employee = new Employee();

        employee.setFirstName("Aamir");
        employee.setLastName("Shaikh");
        employee.setEmail("aamir@test.com");

        employeeService.saveEmployee(employee);

        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

        verify(employeeRepository)
                .save(employeeArgumentCaptor.capture());

        Employee captured = employeeArgumentCaptor.getValue();

        assertThat(captured).isEqualTo(employee);
    }

    @Test
    void throwWhenEmailIsTaken() throws Exception {
        Employee employee = new Employee();

        employee.setFirstName("Aamir");
        employee.setLastName("Shaikh");
        employee.setEmail("aamir@test.com");

        given(employeeRepository.existsByEmail(employee.getEmail()))
                .willReturn(true);

        // expecting exception to be thrown
        assertThatThrownBy(() -> employeeService.saveEmployee(employee))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Email " + employee.getEmail() + " is taken");

        verify(employeeRepository, never()).save(any());
    }

    @Test
    void getAllEmployees() {
        employeeService.getAllEmployees();

        verify(employeeRepository).findAll();
    }

    @Test
    @Disabled
    void getEmployeeById() {
    }

    @Test
    @Disabled
    void updateEmployee() {
    }

    @Test
    @Disabled
    void deleteEmployee() {
    }
}