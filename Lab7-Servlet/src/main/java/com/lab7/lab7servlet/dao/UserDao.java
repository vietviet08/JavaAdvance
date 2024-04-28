package com.lab7.lab7servlet.dao;

import com.lab7.lab7servlet.db.HibernateUtil;
import com.lab7.lab7servlet.model.User;
import com.lab7.lab7servlet.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao implements DaoInterface<User> {

    public static UserDao getInstance() {
        return new UserDao();
    }

    public static int checkLogin(String username, String password) {
        int check = 0;
        List<User> users = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Query query = session.createQuery("from User where username = :username and password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);

             users = query.getResultList();
            if(users.size() > 0) check = 1;

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            assert transaction != null;
            transaction.rollback();
        }

        return check;
    }

    public static User selectByUsername(String username) {
        List<User> users = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Query query = session.createQuery("from User where username = :username");
            query.setParameter("username", username);

            users = query.getResultList();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            assert transaction != null;
            transaction.rollback();
        }

        return users.get(0);
    }



    @Override
    public int insert(User user) {
        int check = 0;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.save(user);

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
    public int update(User user) {
        int check = 0;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.update(user);

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

            User user = session.get(User.class, id);
            if (user == null) return check;
            session.delete(user);

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
    public List<User> selectAll() {

        List<User> users = null;

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            users = session.createQuery("from User ").getResultList();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            assert transaction != null;
            transaction.rollback();
        }

        return users;

    }

    @Override
    public User selectById(Long id) {
        User user = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            user = session.get(User.class, id);

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            assert transaction != null;
            transaction.rollback();
        }

        return user;
    }
}
