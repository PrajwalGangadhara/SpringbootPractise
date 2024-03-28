package com.dailypractice.springboot.Repository;

import com.dailypractice.springboot.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    public Department findByName(String departmentName);

    public Department findByNameIgnoreCase(String departmentName);
}
