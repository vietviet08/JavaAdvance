package ex2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net. DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) throws IOException {
        String[] elements = {"Apple", "Banana", "Cherry", "Orange", "Pineapple",
                "Grape", "Mango", "Watermelon", "Kiwi", "Strawberry"};

        DatagramSocket serverSocket = new DatagramSocket(12345);

        while (true) {
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            serverSocket.receive(receivePacket);

            int number = Integer.parseInt(new String(receivePacket.getData(), 0, receivePacket.getLength()));

            if (number < 1 || number > 10) {
                String errorMessage = "Invalid number. Please enter a number between 1 and 10.";
                sendResponse(serverSocket, receivePacket.getAddress(), receivePacket.getPort(), errorMessage);
                continue;
            }

            String element = elements[number - 1];

            sendResponse(serverSocket, receivePacket.getAddress(), receivePacket.getPort(), element);
        }
    }

    private static void sendResponse(DatagramSocket serverSocket, InetAddress address, int port, String message) throws IOException {
        byte[] sendBuffer = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
        serverSocket.send(sendPacket);
    }
}

