package com.example.cmp.employee;

import com.example.cmp.department.DepartmentLocal;
import jakarta.ejb.EJBLocalObject;

public interface EmployeeLocal extends EJBLocalObject {
    Integer getEmployeeId();
    String getName();
    void setName(String name);
    double getSalary();
    void setSalary(double salary);
    DepartmentLocal getDepartment();
    void setDepartment(DepartmentLocal department);
}
