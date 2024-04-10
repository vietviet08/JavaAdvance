public class Student {
    private Long id;
    private String name;
    private String nameClass;
    private int age;
    private double gpa;

    public Student() {
    }

    public Student(Long id, String name, String nameClass, int age, double gpa) {
        this.id = id;
        this.name = name;
        this.nameClass = nameClass;
        this.age = age;
        this.gpa = gpa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", nameClass='" + nameClass + '\'' + ", age=" + age + ", gpa=" + gpa + '}';
    }
}
