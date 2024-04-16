package ex2;

import java.io.IOException;
import java.net. DatagramPacket;
import java.net. DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        InetAddress serverAddress = InetAddress.getLocalHost();
        int serverPort = 12345;

        while (true) {
            System.out.print("Enter a number between 1 and 10 (or 'q' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                break;
            }

            int number;
            try {
                number = Integer.parseInt(input);
                if (number < 1 || number > 10) {
                    System.out.println("Invalid number. Please try again.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            DatagramSocket clientSocket = new DatagramSocket();

            byte[] sendBuffer = String.valueOf(number).getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);

            clientSocket.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            clientSocket.receive(receivePacket);

            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Server response: " + message);

            clientSocket.close();
        }

        scanner.close();
    }
}

