import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class test {

    public static void main(String[] args) {
        try {

            File file = new File("D:\\JavaAdvance\\LAB6-XML-SAX\\src\\main\\java\\students.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler();


            saxParser.parse(file, userhandler);

            for (Student student : userhandler.getStudents()) {
                System.out.println(student.toString());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
