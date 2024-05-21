package com.hiber.dao;

import com.hiber.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class StudentDAO {
    private SessionFactory factory;

    public StudentDAO() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public List<Student> listStudents() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Student", Student.class).list();
        }
    }

    public void addStudent(Student student) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        }
    }

    public void editStudent(Student student) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        }
    }

    public void deleteStudent(Long studentId) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            if (student != null) {
                session.delete(student);
            }
            transaction.commit();
        }
    }
}
