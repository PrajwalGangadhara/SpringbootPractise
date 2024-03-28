package com.dailypractice.springboot.Controller;

import com.dailypractice.springboot.Entity.Department;
import com.dailypractice.springboot.Exceptions.DepartmentNotFoundException;
import com.dailypractice.springboot.Service.DepartmentServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentsController {

    public DepartmentServiceImpl departmentService;
    @Autowired
    public DepartmentsController(DepartmentServiceImpl departmentService)
    {
        this.departmentService=departmentService;
    }

    Logger logger= LoggerFactory.getLogger(DepartmentsController.class);
    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department)
    {
        logger.info("Inside saveDepartment controller");
        return departmentService.saveDepartment(department);
    }

   // @RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping("/departments")
    public List<Department> getDepartments()
    {
        logger.info("Inside getDepartments   controller");
        return departmentService.getDepartments();
    }

    @GetMapping("/departments/{id}")
    public Department getDepartment(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.getDepartment(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public void deleteDepartment(@PathVariable("id") Long departmentId)
    {
         departmentService.deleteDepartment(departmentId);
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department newDepartment)
    {
        return departmentService.updateDepartment(departmentId, newDepartment);
    }

    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentname)
    {
        return departmentService.fetchDepartmentByName(departmentname);
    }

}
