
package tcpclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        Scanner scanner = null;
        
        try{
            socket = new Socket("localhost", 1234);
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            
            scanner = new Scanner(System.in);
            while (true) {                
                String msgToServer = scanner.nextLine();
                bufferedWriter.write(msgToServer);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                
                System.out.println("Server: " + bufferedReader.readLine());
                
                if(msgToServer.equalsIgnoreCase("stop"))
                    break;
            }
        }catch(IOException e){
            
        }finally{
            try {
                if(socket != null)
                    socket.close();
                if(inputStreamReader != null)
                    inputStreamReader.close();
                if(outputStreamWriter != null)
                    outputStreamWriter.close();
                if(bufferedReader != null)
                    bufferedReader.close();
                if(bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException ex) {
            }
        }
    }
    
}
