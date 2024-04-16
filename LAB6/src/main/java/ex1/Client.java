package ex1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 12345);

        DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter first number: ");
            int num1 = scanner.nextInt();
            System.out.print("Enter second number: ");
            int num2 = scanner.nextInt();

            outputStream.writeInt(num1);
            outputStream.writeInt(num2);

            int sum = inputStream.readInt();
            int subtract = inputStream.readInt();
            int multi = inputStream.readInt();
            int mod = inputStream.readInt();

            System.out.println("Sum received from server: " + sum);
            System.out.println("Subtract received from server: " + subtract);
            System.out.println("Multi received from server: " + multi);
            System.out.println("Mod received from server: " + mod);

            if (scanner.nextLine().trim().equals("exit")) break;
        }

        outputStream.close();
        inputStream.close();
        clientSocket.close();

    }

}
