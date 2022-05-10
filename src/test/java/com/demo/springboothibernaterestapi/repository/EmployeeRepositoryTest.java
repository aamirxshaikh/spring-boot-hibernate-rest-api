package com.demo.springboothibernaterestapi.repository;

import com.demo.springboothibernaterestapi.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void existsByEmail() {
        // given

        Employee employee = new Employee();

        employee.setFirstName("Aamir");
        employee.setLastName("Shaikh");
        employee.setEmail("aamir@test.com");

        // when

        employeeRepository.save(employee);

        boolean exists = employeeRepository.existsByEmail("aamir@test.com");

        // then

        assertThat(exists).isTrue();
    }

    @Test
    void notExistsByEmail() {
        // given

        // when

        boolean exists = employeeRepository.existsByEmail("aamir@test.com");

        // then

        assertThat(exists).isFalse();
    }

    @Test
    void findByEmail() {
        // given

        Employee employee = new Employee();

        employee.setFirstName("Aamir");
        employee.setLastName("Shaikh");
        employee.setEmail("aamir@test.com");

        // when

        employeeRepository.save(employee);

        Optional<Employee> exists = employeeRepository.findByEmail("aamir@test.com");

        // then

        assertThat(exists).isPresent();
    }

    @Test
    void notPresentByEmail() {
        Optional<Employee> exists = employeeRepository.findByEmail("aamir@test.com");

        // then

        assertThat(exists).isNotPresent();
    }
}