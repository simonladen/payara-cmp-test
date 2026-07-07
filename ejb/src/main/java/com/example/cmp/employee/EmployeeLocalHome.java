package com.example.cmp.employee;

import jakarta.ejb.CreateException;
import jakarta.ejb.EJBLocalHome;
import jakarta.ejb.FinderException;
import java.util.Collection;

public interface EmployeeLocalHome extends EJBLocalHome {
    EmployeeLocal create(Integer id, String name, double salary) throws CreateException;
    EmployeeLocal findByPrimaryKey(Integer id) throws FinderException;
    Collection<EmployeeLocal> findAll() throws FinderException;
}
