import java.io.*;
import java.net.*;

public class Client1 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8989);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            FileReader fileReader = new FileReader("D:\\JavaAdvance\\testmidex\\src\\main\\java\\num.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String numbers = bufferedReader.readLine();

            out.writeUTF(numbers);

            int sum = in.readInt();
            System.out.println("Tổng của dãy số: " + sum);

            socket.close();
        } catch (IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}
