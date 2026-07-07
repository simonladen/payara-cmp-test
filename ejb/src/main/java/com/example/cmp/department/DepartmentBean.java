package com.example.cmp.department;

import com.example.cmp.employee.EmployeeLocal;
import jakarta.ejb.CreateException;
import jakarta.ejb.EntityBean;
import jakarta.ejb.EntityContext;
import jakarta.ejb.RemoveException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DepartmentBean implements EntityBean {

    private static final Logger logger = Logger.getLogger(DepartmentBean.class.getName());

    private EntityContext context;

    public abstract Integer getDepartmentId();
    public abstract void setDepartmentId(Integer id);
    public abstract String getName();
    public abstract void setName(String name);

    public abstract Collection<EmployeeLocal> getEmployees();
    public abstract void setEmployees(Collection<EmployeeLocal> employees);

    public Integer ejbCreate(Integer id, String name) throws CreateException {
        logger.info("DepartmentBean.ejbCreate: id=" + id + ", name=" + name);
        setDepartmentId(id);
        setName(name);
        return null;
    }

    public void ejbPostCreate(Integer id, String name) throws CreateException {
        logger.info("DepartmentBean.ejbPostCreate: pk=" + context.getPrimaryKey());
    }

    @Override public void setEntityContext(EntityContext ctx) { logger.fine("DepartmentBean.setEntityContext"); this.context = ctx; }
    @Override public void unsetEntityContext() { logger.fine("DepartmentBean.unsetEntityContext"); this.context = null; }
    @Override public void ejbActivate() { logger.fine("DepartmentBean.ejbActivate: pk=" + context.getPrimaryKey()); }
    @Override public void ejbPassivate() { logger.fine("DepartmentBean.ejbPassivate: pk=" + context.getPrimaryKey()); }
    @Override public void ejbLoad() { logger.fine("DepartmentBean.ejbLoad: pk=" + context.getPrimaryKey()); }
    @Override public void ejbStore() { logger.fine("DepartmentBean.ejbStore: pk=" + context.getPrimaryKey()); }
    @Override public void ejbRemove() throws RemoveException { logger.info("DepartmentBean.ejbRemove: pk=" + context.getPrimaryKey()); }
}
