import java.io.*;
import java.net.*;

public class Server1 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8989);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

                String numbers = in.readUTF();

                int sum = calculateSum(numbers);

                out.writeInt(sum);

                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static int calculateSum(String numbers) {
        String[] numArray = numbers.split(" ");
        int sum = 0;
        for (String num : numArray) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }
}
