package udpclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        Scanner scanner = null;
        
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            scanner = new Scanner(System.in);
            
            while (true) {
                System.out.print("Enter message: ");
                String msgToServer = scanner.nextLine();
                
                // Send message
                byte[] sendBuffer = msgToServer.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 1234);
                socket.send(sendPacket);
                
                // Receive response
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                
                String msgFromServer = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server: " + msgFromServer);
                
                if (msgToServer.equalsIgnoreCase("stop"))
                    break;
            }
            
        } catch (IOException e) {
        } finally {
            if (socket != null)
                socket.close();
            if (scanner != null)
                scanner.close();
        }
    }
}
