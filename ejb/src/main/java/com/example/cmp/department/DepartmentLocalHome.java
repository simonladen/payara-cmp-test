package com.example.cmp.department;

import jakarta.ejb.CreateException;
import jakarta.ejb.EJBLocalHome;
import jakarta.ejb.FinderException;
import java.util.Collection;

public interface DepartmentLocalHome extends EJBLocalHome {
    DepartmentLocal create(Integer id, String name) throws CreateException;
    DepartmentLocal findByPrimaryKey(Integer id) throws FinderException;
    Collection<DepartmentLocal> findAll() throws FinderException;
}
