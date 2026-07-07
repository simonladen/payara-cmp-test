package com.example.cmp.employee;

import com.example.cmp.department.DepartmentLocal;
import jakarta.ejb.CreateException;
import jakarta.ejb.EntityBean;
import jakarta.ejb.EntityContext;
import jakarta.ejb.RemoveException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class EmployeeBean implements EntityBean {

    private static final Logger logger = Logger.getLogger(EmployeeBean.class.getName());

    private EntityContext context;

    public abstract Integer getEmployeeId();
    public abstract void setEmployeeId(Integer id);
    public abstract String getName();
    public abstract void setName(String name);
    public abstract double getSalary();
    public abstract void setSalary(double salary);

    public abstract DepartmentLocal getDepartment();
    public abstract void setDepartment(DepartmentLocal department);

    public Integer ejbCreate(Integer id, String name, double salary) throws CreateException {
        logger.info("EmployeeBean.ejbCreate: id=" + id + ", name=" + name + ", salary=" + salary);
        setEmployeeId(id);
        setName(name);
        setSalary(salary);
        return null;
    }

    public void ejbPostCreate(Integer id, String name, double salary) throws CreateException {
        logger.info("EmployeeBean.ejbPostCreate: pk=" + context.getPrimaryKey());
    }

    @Override public void setEntityContext(EntityContext ctx) { logger.fine("EmployeeBean.setEntityContext"); this.context = ctx; }
    @Override public void unsetEntityContext() { logger.fine("EmployeeBean.unsetEntityContext"); this.context = null; }
    @Override public void ejbActivate() { logger.fine("EmployeeBean.ejbActivate: pk=" + context.getPrimaryKey()); }
    @Override public void ejbPassivate() { logger.fine("EmployeeBean.ejbPassivate: pk=" + context.getPrimaryKey()); }
    @Override public void ejbLoad() { logger.fine("EmployeeBean.ejbLoad: pk=" + context.getPrimaryKey()); }
    @Override public void ejbStore() { logger.fine("EmployeeBean.ejbStore: pk=" + context.getPrimaryKey()); }
    @Override public void ejbRemove() throws RemoveException { logger.info("EmployeeBean.ejbRemove: pk=" + context.getPrimaryKey()); }
}
