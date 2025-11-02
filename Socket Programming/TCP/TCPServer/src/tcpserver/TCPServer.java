
package tcpserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) {
        
        ServerSocket serverSocket = null;
        Socket socket = null;
        
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        while (true) {            
            try{
                serverSocket =  new ServerSocket(1234);
                socket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while (true) {
                    String msgFromClient = bufferedReader.readLine();
                    System.out.println("TCP Client: "+ msgFromClient);
                    String msgToClient = msgFromClient.toUpperCase();

                    bufferedWriter.write(msgToClient);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    
                    if(msgFromClient.equalsIgnoreCase("stop"))
                        break;
                }
                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
                
            }catch(IOException e){

            }
        }

        
    }
    
}
