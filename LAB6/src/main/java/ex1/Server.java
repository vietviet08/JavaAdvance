package ex1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream
                    outputStream = new DataOutputStream(clientSocket.getOutputStream());

            while (true) {
                int num1 = inputStream.readInt();
                int num2 = inputStream.readInt();

                int sum = num1 + num2;

                outputStream.writeInt(sum);
                outputStream.writeInt(num1 - num2);
                outputStream.writeInt(num1 * num2);
                outputStream.writeInt(num1 % num2);
                if (inputStream.readLine().equals("exit")) break;
            }


            outputStream.close();
            inputStream.close();
            clientSocket.close();

        }

    }
}
