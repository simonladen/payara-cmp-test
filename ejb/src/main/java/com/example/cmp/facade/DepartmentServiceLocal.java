package com.example.cmp.facade;

import com.example.cmp.department.DepartmentLocal;
import com.example.cmp.employee.EmployeeLocal;
import jakarta.ejb.EJBLocalObject;
import java.util.Collection;

public interface DepartmentServiceLocal extends EJBLocalObject {
    void createDepartment(Integer id, String name);
    void addEmployee(Integer id, String name, double salary, Integer departmentId);
    Collection<DepartmentLocal> findAllDepartments();
    Collection<EmployeeLocal> getEmployeesForDepartment(Integer departmentId);
}
