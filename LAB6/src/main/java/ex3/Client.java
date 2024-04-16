package ex3;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import static java.net.NetworkInterface.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        InetAddress multicastAddress = InetAddress.getByName("224.0.0.1");
        int port = 12345;

        DatagramSocket clientSocket = new DatagramSocket();

        while (true) {
            System.out.print("Enter a message (or 'q' to quit): ");
            String message = scanner.nextLine();

            if (message.equalsIgnoreCase("q")) {
                break;
            }

            byte[] sendBuffer = message.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, InetAddress.getLocalHost(), port); // Localhost for initial send

            clientSocket.send(sendPacket);



            sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, multicastAddress, port);

            clientSocket.send(sendPacket);
        }

        scanner.close();
        clientSocket.close();
    }
}
