package ex3;

import java.io.IOException;
import java.net. DatagramPacket;
import java.net. DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) throws IOException {
        // Multicast group address (use a well-known multicast address like 224.0.0.1)
        InetAddress multicastAddress = InetAddress.getByName("224.0.0.1");
        int port = 12345;  // Port for communication

        // Create a DatagramSocket
        DatagramSocket serverSocket = new DatagramSocket();

        while (true) {
            // Prepare a DatagramPacket to receive data
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            // Wait for a client message
            serverSocket.receive(receivePacket);

            // Extract the message
            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received message: " + message);

            // Create a DatagramPacket to send the message to all clients (multicast)
            byte[] sendBuffer = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, multicastAddress, port);

            // Send the message to the multicast group
            serverSocket.send(sendPacket);
        }
    }
}
