package udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

    public static void main(String[] args) {

            DatagramSocket socket = null;
        try {

            socket = new DatagramSocket(1234);
            System.out.println("UDP Server started. Listening on port 1234...");
            
            byte[] receiveBuffer = new byte[1024];
            
            while (true) {
                // Receive packet
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                
                String msgFromClient = new String(receivePacket.getData(), 0, receivePacket.getLength());
                        
                System.out.println("UDP Client: " + msgFromClient);
                
                // Prepare response (convert to uppercase)
                String msgToClient = msgFromClient.toUpperCase();
                byte[] sendBuffer = msgToClient.getBytes();
                
                DatagramPacket sendPacket = new DatagramPacket(
                        sendBuffer,
                        sendBuffer.length,
                        receivePacket.getAddress(),
                        receivePacket.getPort()
                );
                socket.send(sendPacket);
                
                if (msgFromClient.equalsIgnoreCase("stop")) {
                    System.out.println("Stopping server...");
                    break;
                }
            }
            
        } catch (IOException e) {
        } finally {
            if (socket != null)
                socket.close();
        }
    }
}
