package com.dailypractice.springboot.Service;

import com.dailypractice.springboot.Entity.Department;
import com.dailypractice.springboot.Exceptions.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    public List<Department> getDepartments();

    public Department saveDepartment(Department department);
    public Department getDepartment(Long departmentId) throws DepartmentNotFoundException;

    public void deleteDepartment(Long departmentId);

    Department updateDepartment(Long departmentId, Department newDepartment);

    public Department fetchDepartmentByName(String departmentname);
}
