package com.dailypractice.springboot.Service;


import com.dailypractice.springboot.Entity.Department;
import com.dailypractice.springboot.Exceptions.DepartmentNotFoundException;
import com.dailypractice.springboot.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    public DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public  Department getDepartment(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> departmentById=departmentRepository.findById(departmentId);
        if(!departmentById.isPresent())
        {
            throw new DepartmentNotFoundException("Department is not available");
        }
        return departmentById.get();
    }

    @Override
    public void deleteDepartment(Long departmentId) {
         departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department newDepartment) {
        Department tobeUpdated= departmentRepository.findById(departmentId).get();
        if(Objects.nonNull(newDepartment.getName()) && !"".equalsIgnoreCase(newDepartment.getName())) {
            tobeUpdated.setName(newDepartment.getName());
        }
        if(Objects.nonNull(newDepartment.getCity()) && !"".equalsIgnoreCase(newDepartment.getCity())) {
            tobeUpdated.setCity(newDepartment.getCity());
        }

        if(Objects.nonNull(newDepartment.getCode()) && !"".equalsIgnoreCase(newDepartment.getCode())) {
            tobeUpdated.setCode(newDepartment.getCode());
        }
        return departmentRepository.save(tobeUpdated);

    }

    @Override
    public Department fetchDepartmentByName(String departmentname) {
        //return departmentRepository.findByName(departmentname);
        return departmentRepository.findByNameIgnoreCase(departmentname);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }
}
