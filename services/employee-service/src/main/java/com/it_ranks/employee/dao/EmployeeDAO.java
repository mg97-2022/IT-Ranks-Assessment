package com.it_ranks.employee.dao;

import com.it_ranks.employee.entity.Employee;
import com.it_ranks.employee.exception.NotFoundException;
import com.it_ranks.employee.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDAO {
    public Employee saveEmployee(Employee employee) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(employee);
            transaction.commit();
            return employee;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Employee> getAllEmployees() {
        Session session = HibernateUtil.getSession();
        List<Employee> employees = null;

        try {
            employees = session.createQuery("from Employee", Employee.class).list();
        } finally {
            session.close();
        }
        return employees;
    }

    public Employee getEmployeeById(Long id) {
        Session session = HibernateUtil.getSession();
        Employee employee = null;

        try {
            employee = session.get(Employee.class, id);
        } finally {
            session.close();
        }

        if (employee == null) {
            throw new NotFoundException("Employee", id);
        }

        return employee;
    }

    public long getEmployeesCountByNationalId(String nationalId) {
        Session session = HibernateUtil.getSession();
        long count;
        try {
            Query query = session.createQuery("select count(e) from Employee e where e.nationalId = :nationalId");
            query.setParameter("nationalId", nationalId);
            count = (long) query.uniqueResult();
        } finally {
            session.close();
        }
        return count;
    }

    public Employee updateEmployee(Employee employee) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(employee);
            transaction.commit();
            return employee;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    public void deleteEmployee(Long id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Employee employee = this.getEmployeeById(id);
            session.delete(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
