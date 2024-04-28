import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;

public class client {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 8989);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            FileReader fileReader = new FileReader("D:\\JavaAdvance\\testmidex\\src\\main\\java\\num.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (true) {

                int num;

                while (bufferedReader.readLine() != null) {
                    num = bufferedReader.read();

                    out.writeInt(num);
                }
                out.writeUTF("done");

                System.out.println("sum = " + in.read());
            }

//            in.close();
//            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
