package com.lab7.lab7servlet.controller;

import com.lab7.lab7servlet.dao.StudentDao;
import com.lab7.lab7servlet.dao.UserDao;
import com.lab7.lab7servlet.db.HibernateUtil;
import com.lab7.lab7servlet.model.Student;
import com.lab7.lab7servlet.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/")
public class LoginController extends HttpServlet {

    private StudentDao studentDao;

    public void init() {
        studentDao = new StudentDao();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;character=UTF-8");
        

        String action = request.getServletPath();

        try {

            switch (action) {
                case "/students":
                case "/":
                    students(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                case "/login":
                    login(request, response);
                    break;
                case "/new":
                    break;
                case "/delete":
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void checkSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            if (session == null || session.getAttribute("username") == null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {

            String username = request.getParameter("txt-username");
            String password = request.getParameter("txt-password");

            int check = UserDao.checkLogin(username, password);

            if (check != 0) {
                String nameUser = UserDao.selectByUsername(username).getName();
                HttpSession session = request.getSession();

                session.setAttribute("username", nameUser);

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);

            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        session.invalidate();

        try {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void students(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        checkSession(request, response);

        List<Student> students = StudentDao.getInstance().selectAll();
        request.setAttribute("students", students);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);

    }



    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Student existingUser = studentDao.selectById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        request.setAttribute("student", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String birthDate = request.getParameter("birthDay");
        Double gpa = Double.parseDouble(request.getParameter("gpa"));
        Student newStudent = new Student();
        newStudent.setFullName(name);
        newStudent.setBirthDate(birthDate);
        newStudent.setGpa(gpa);
        studentDao.insert(newStudent);
        response.sendRedirect("student");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String birthDate = request.getParameter("birthDay");
        Double gpa = Double.parseDouble(request.getParameter("gpa"));
        Student newStudent = studentDao.selectById(id);
        newStudent.setFullName(name);
        newStudent.setBirthDate(birthDate);
        newStudent.setGpa(gpa);

        studentDao.update(newStudent);
        response.sendRedirect("student");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        studentDao.delete(id);
        response.sendRedirect("student");
    }

}
