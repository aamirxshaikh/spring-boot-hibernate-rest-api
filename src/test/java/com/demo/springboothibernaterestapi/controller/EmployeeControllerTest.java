package com.demo.springboothibernaterestapi.controller;

import com.demo.springboothibernaterestapi.model.Employee;
import com.demo.springboothibernaterestapi.service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveEmployee() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"1\", \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"Doe@test.com\"}")
                )
                .andExpect(status().isCreated());

        verify(employeeService).saveEmployee(any(Employee.class));
    }

    @Test
    void getAllEmployees() throws Exception {
        Employee employee1 = new Employee(1L, "John","Doe", "Doe@test.com");

        Employee employee2 = new Employee(2L, "Jane", "Doe", "jane@test.com");

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));

        mvc.perform(MockMvcRequestBuilders.get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].email").value("Doe@test.com"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Doe"))
                .andExpect(jsonPath("$[1].email").value("jane@test.com"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getEmployeeById() throws Exception {
        long employeeId = 1L;

        Employee employee = new Employee(employeeId, "John","Doe", "john@test.com");

        when(employeeService.getEmployeeById(employeeId)).thenReturn(employee);

        mvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(4)))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john@test.com"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void updateEmployee() throws Exception {
        long employeeId = 1L;

        mvc.perform(
                MockMvcRequestBuilders
                        .put("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"1\", \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"Doe@test.com\"}")
                )
                .andExpect(status().isOk());

        verify(employeeService).updateEmployee(any(Employee.class), anyLong());
    }

    @Test
    void deleteEmployee() throws Exception {
        long employeeId = 1L;

        mvc.perform(MockMvcRequestBuilders.delete("/api/employees/{id}", employeeId))
                .andExpect(status().isOk());

        verify(employeeService).deleteEmployee(anyLong());
    }
}