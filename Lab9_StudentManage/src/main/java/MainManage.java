
import com.hiber.dao.StudentDAO;
import com.hiber.model.Student;

import java.util.List;
import java.util.Scanner;

public class MainManage {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. List students");
            System.out.println("2. Add student");
            System.out.println("3. Edit student");
            System.out.println("4. Delete student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<Student> students = studentDAO.listStudents();
                    for (Student student : students) {
                        System.out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Email: " + student.getEmail());
                    }
                    break;
                case 2:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student email: ");
                    String email = scanner.nextLine();
                    studentDAO.addStudent(new Student(name, email));
                    break;
                case 3:
                    System.out.print("Enter student ID to edit: ");
                    Long editId = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Enter new student name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new student email: ");
                    String newEmail = scanner.nextLine();
                    Student editStudent = new Student(newName, newEmail);
                    editStudent.setId(editId);
                    studentDAO.editStudent(editStudent);
                    break;
                case 4:
                    System.out.print("Enter student ID to delete: ");
                    Long deleteId = scanner.nextLong();
                    studentDAO.deleteStudent(deleteId);
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
