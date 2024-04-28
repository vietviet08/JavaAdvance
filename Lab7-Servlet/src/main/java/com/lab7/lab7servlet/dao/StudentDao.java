package com.lab7.lab7servlet.dao;

import com.lab7.lab7servlet.db.HibernateUtil;
import com.lab7.lab7servlet.model.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.util.List;

public class StudentDao implements DaoInterface<Student> {

    public static StudentDao getInstance() {
        return new StudentDao();
    }

    @Override
    public int insert(Student student) {
        int check = 0;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.save(student);

            transaction.commit();

            check = 1;
        } catch (Exception e) {
            e.printStackTrace();
            assert transaction != null;
            transaction.rollback();
        }

        return check;
    }

    @Override
    public int update(Student student) {
        int check = 0;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.update(student);

            transaction.commit();

            check = 1;
        } catch (Exception e) {
            e.printStackTrace();
            assert transaction != null;
            transaction.rollback();
        }

        return check;
    }

    @Override
    public int delete(Long id) {
        int check = 0;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Student student = session.get(Student.class, id);
            session.delete(student);

            transaction.commit();

            check = 1;
        } catch (Exception e) {
            e.printStackTrace();
            assert transaction != null;
            transaction.rollback();
        }

        return check;
    }

    @Override
    public List<Student> selectAll() {

        List<Student> students = null;

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            students = session.createQuery("from Student ").getResultList();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            assert transaction != null;
            transaction.rollback();
        }

        return students;

    }

    @Override
    public Student selectById(Long id) {
        Student student = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            student = session.get(Student.class, id);

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            assert transaction != null;
            transaction.rollback();
        }

        return student;
    }
}
