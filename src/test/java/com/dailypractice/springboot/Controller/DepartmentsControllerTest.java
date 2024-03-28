package com.dailypractice.springboot.Controller;

import com.dailypractice.springboot.Entity.Department;
import com.dailypractice.springboot.Exceptions.DepartmentNotFoundException;
import com.dailypractice.springboot.Service.DepartmentService;
import com.dailypractice.springboot.Service.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(DepartmentsController.class)
class DepartmentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentServiceImpl departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department= Department.builder()
                .name("IT")
                .code("IT-001")
                .id(1L)
                .city("Hydrabad")
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment= Department.builder()
                .name("IT")
                .code("IT-001")
                .city("Hydrabad")
                .build();

        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"name\":\"IT\",\n" +
                        "\t\"city\":\"Hydrabad\",\n" +
                        "\t\"code\":\"IT-001\"\n" +
                    "}"))
                .andExpect(status().isOk());

    }

    @Test
    void getDepartment() throws Exception {

        Mockito.when(departmentService.getDepartment(1L))
                .thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value(department.getName()));

    }
}