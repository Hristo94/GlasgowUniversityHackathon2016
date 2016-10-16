/**
 * Created by Nelly on 15/10/2016.
 */
import java.net.*;
import java.io.*;
import java.net.*;
import java.io.*;
public class Server {
    public static void main (String args[]) {
        try{
            int serverPort = 8080;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        } catch(IOException e) {System.out.println("Listen :"+e.getMessage());}
    }
}