package com.dailypractice.springboot.Service;

import com.dailypractice.springboot.Entity.Department;
import com.dailypractice.springboot.Repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DepartmentServiceImplTest {
    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
        Department department= Department.builder()
                .name("IT")
                .city("Banglore")
                .code("IT-001")
                .build();

        Mockito.when(departmentRepository.findByNameIgnoreCase("IT")).thenReturn(department);
    }

    @Test
    public void WhenValidName_ThenshouldReturnDepartment()
    {
        String departmentName="IT";
        Department found=departmentService.fetchDepartmentByName(departmentName);
        assertEquals(found.getName(), departmentName);

    }
}