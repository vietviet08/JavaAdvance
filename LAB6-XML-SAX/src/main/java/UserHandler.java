import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class UserHandler extends DefaultHandler {
    private String content;
    private Student student;
    private List<Student> students = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("student".equalsIgnoreCase(qName)){
            student = new Student();
            student.setId(Long.valueOf(attributes.getValue("id")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("student".equalsIgnoreCase(qName)) students.add(student);
        else if("name".equalsIgnoreCase(qName)) student.setName(content);
        else if("classname".equalsIgnoreCase(qName)) student.setNameClass(content);
        else if("age".equalsIgnoreCase(qName)) student.setAge(Integer.parseInt(content));
        else if("gpa".equalsIgnoreCase(qName)) student.setGpa(Double.parseDouble(content));
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
       content = String.valueOf(ch, start, length).trim();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
