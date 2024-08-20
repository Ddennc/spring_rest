package com.denysdudka.spring.rest.dao;


import com.denysdudka.spring.rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class,id);
       
        session.getTransaction().commit();
        session.close();
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Employee> query = session.createQuery("delete from Employee "+"where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(employee);
        session.getTransaction().commit();
        session.close();
    }

    @Override
//   @Transactional
    public List<Employee> getAllEmployees() {
        
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Employee> allEmployees = session.createQuery("from Employee", Employee.class).getResultList();
        session.getTransaction().commit();
        session.close();
//        Query<Employee> query = session.createQuery("from Employee", Employee.class);
//        List<Employee> allEmployees = query.getResultList();
        return allEmployees;
    }
}
