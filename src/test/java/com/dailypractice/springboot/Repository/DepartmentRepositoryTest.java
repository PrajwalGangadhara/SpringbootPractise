package com.dailypractice.springboot.Repository;

import com.dailypractice.springboot.Entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department= Department.builder()
                .name("Mechanical Engineering")
                .city("Bnaglore")
                .code("Mech")
                .build();

        entityManager.persist(department);
    }

    @Test
    @DisplayName("Test FindByID")
    public void WhenFindById_ThenReturnDepartment()
    {
        Department department=departmentRepository.findById(1L).get();
        assertEquals(department.getName(), "Mechanical Engineering");
    }
}