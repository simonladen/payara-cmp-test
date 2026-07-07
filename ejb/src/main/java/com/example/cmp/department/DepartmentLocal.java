package com.example.cmp.department;

import com.example.cmp.employee.EmployeeLocal;
import jakarta.ejb.EJBLocalObject;
import java.util.Collection;

public interface DepartmentLocal extends EJBLocalObject {
    Integer getDepartmentId();
    String getName();
    void setName(String name);
    Collection<EmployeeLocal> getEmployees();
}
