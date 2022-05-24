package com.demo.springboothibernaterestapi.service;

import com.demo.springboothibernaterestapi.exception.ResourceNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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
    void getEmployeeById() {
        Employee employee = new Employee();

        long employeeId = 1L;

        employee.setId(employeeId);
        employee.setFirstName("Aamir");
        employee.setLastName("Shaikh");
        employee.setEmail("aamir@test.com");

        doReturn(Optional.of(employee)).when(employeeRepository).findById(employeeId);

        assertThat(employeeService.getEmployeeById(employeeId)).isEqualTo(employee);
    }

    @Test
    void throwWhenResourceNotFoundForGet() {
        long employeeId = 1L;

        given(employeeRepository.findById(employeeId))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.getEmployeeById(employeeId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("not found with : id and : " + employeeId);
    }

    @Test
    void updateEmployee() {
        Employee employee = new Employee();

        long employeeId = 1L;

        employee.setId(employeeId);
        employee.setFirstName("Aamir");
        employee.setLastName("Shaikh");
        employee.setEmail("aamir@test.com");

        doReturn(Optional.of(employee)).when(employeeRepository).findById(employeeId);

        assertThat(employeeService.updateEmployee(employee, employeeId)).isEqualTo(employee);

        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

        verify(employeeRepository)
                .save(employeeArgumentCaptor.capture());

        Employee captured = employeeArgumentCaptor.getValue();

        assertThat(captured).isEqualTo(employee);
    }

    @Test
    void throwWhenResourceNotFoundForUpdate() {
        long employeeId = 1L;

        given(employeeRepository.findById(employeeId))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.updateEmployee(new Employee(), employeeId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("not found with : id and : " + employeeId);

        verify(employeeRepository, never()).save(any());
    }

    @Test
    void deleteEmployee() {
        Employee employee = new Employee();

        long employeeId = 1L;

        employee.setId(employeeId);
        employee.setFirstName("Aamir");
        employee.setLastName("Shaikh");
        employee.setEmail("aamir@test.com");

        given(employeeRepository.findById(employeeId))
                .willReturn(Optional.of(employee));

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository).deleteById(employeeId);
    }

    @Test
    void throwWhenResourceNotFoundForDelete() {
        long employeeId = 1L;

        given(employeeRepository.findById(employeeId))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.deleteEmployee(employeeId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("not found with : id and : " + employeeId);

        verify(employeeRepository, never()).deleteById(any());
    }
}