package com.example.web;

import com.example.cmp.department.DepartmentLocal;
import com.example.cmp.employee.EmployeeLocal;
import com.example.cmp.facade.DepartmentServiceLocal;
import com.example.cmp.facade.DepartmentServiceLocalHome;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.naming.InitialContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    private DepartmentServiceLocal departmentService;

    @Override
    public void init() throws ServletException {
        try {
            DepartmentServiceLocalHome home = (DepartmentServiceLocalHome)
                new InitialContext().lookup("java:comp/env/ejb/DepartmentService");
            departmentService = home.create();
        } catch (Exception e) {
            throw new ServletException("Could not obtain DepartmentService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><body>");

        if ("init".equals(action)) {
            departmentService.createDepartment(1, "Engineering");
            departmentService.createDepartment(2, "Marketing");
            departmentService.addEmployee(1, "Alice", 75000.0, 1);
            departmentService.addEmployee(2, "Bob", 80000.0, 1);
            departmentService.addEmployee(3, "Carol", 65000.0, 2);
            departmentService.addEmployee(4, "Dave", 70000.0, 2);
            out.println("<p>Initialised: 2 departments, 4 employees.</p>");
            out.println("<p><a href='?action=list'>List departments</a></p>");

        } else if ("list".equals(action)) {
            Collection<DepartmentLocal> depts = departmentService.findAllDepartments();
            if (depts.isEmpty()) {
                out.println("<p>No data — run <a href='?action=init'>?action=init</a> first.</p>");
            }
            for (DepartmentLocal dept : depts) {
                out.println("<h2>" + escape(dept.getName())
                        + " (id=" + dept.getDepartmentId() + ")</h2><ul>");
                for (EmployeeLocal emp : dept.getEmployees()) {
                    out.println("<li>" + escape(emp.getName())
                            + " &mdash; salary: " + emp.getSalary() + "</li>");
                }
                out.println("</ul>");
            }

        } else {
            out.println("<p>Actions: "
                    + "<a href='?action=init'>init</a> | "
                    + "<a href='?action=list'>list</a></p>");
        }

        out.println("</body></html>");
    }

    private static String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
