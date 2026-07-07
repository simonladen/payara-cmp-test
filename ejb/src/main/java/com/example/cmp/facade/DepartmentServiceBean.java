package com.example.cmp.facade;

import com.example.cmp.department.DepartmentLocal;
import com.example.cmp.department.DepartmentLocalHome;
import com.example.cmp.employee.EmployeeLocal;
import com.example.cmp.employee.EmployeeLocalHome;
import jakarta.ejb.EJBException;
import jakarta.ejb.FinderException;
import jakarta.ejb.SessionBean;
import jakarta.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Collection;
import java.util.logging.Logger;

public class DepartmentServiceBean implements SessionBean {

    private static final Logger logger = Logger.getLogger(DepartmentServiceBean.class.getName());

    private SessionContext sessionContext;

    private DepartmentLocalHome getDepartmentHome() {
        try {
            return (DepartmentLocalHome) new InitialContext().lookup("java:comp/env/ejb/DepartmentHome");
        } catch (NamingException e) {
            throw new EJBException(e);
        }
    }

    private EmployeeLocalHome getEmployeeHome() {
        try {
            return (EmployeeLocalHome) new InitialContext().lookup("java:comp/env/ejb/EmployeeHome");
        } catch (NamingException e) {
            throw new EJBException(e);
        }
    }

    public void createDepartment(Integer id, String name) {
        try {
            getDepartmentHome().create(id, name);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public void addEmployee(Integer id, String name, double salary, Integer departmentId) {
        try {
            DepartmentLocal dept = getDepartmentHome().findByPrimaryKey(departmentId);
            EmployeeLocal emp = getEmployeeHome().create(id, name, salary);
            emp.setDepartment(dept);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public Collection<DepartmentLocal> findAllDepartments() {
        try {
            return getDepartmentHome().findAll();
        } catch (FinderException e) {
            throw new EJBException(e);
        }
    }

    public Collection<EmployeeLocal> getEmployeesForDepartment(Integer departmentId) {
        try {
            DepartmentLocal dept = getDepartmentHome().findByPrimaryKey(departmentId);
            return dept.getEmployees();
        } catch (FinderException e) {
            throw new EJBException(e);
        }
    }

    @Override public void setSessionContext(SessionContext ctx) { logger.fine("DepartmentServiceBean.setSessionContext"); this.sessionContext = ctx; }
    public void ejbCreate() { logger.info("DepartmentServiceBean.ejbCreate: stateless session instance created"); }
    @Override public void ejbRemove() { logger.info("DepartmentServiceBean.ejbRemove: stateless session instance removed"); }
    @Override public void ejbActivate() {}
    @Override public void ejbPassivate() {}
}
