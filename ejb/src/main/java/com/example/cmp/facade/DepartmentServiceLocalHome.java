package com.example.cmp.facade;

import jakarta.ejb.CreateException;
import jakarta.ejb.EJBLocalHome;

public interface DepartmentServiceLocalHome extends EJBLocalHome {
    DepartmentServiceLocal create() throws CreateException;
}
