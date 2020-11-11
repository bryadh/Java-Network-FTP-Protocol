package java_network_ftp_protocol;

import java.io.*;
import java.net.*;

public class Server {

	public static void main(String[] args) {
		
		try {
			
			ServerSocket serverSocket = new ServerSocket(9090);
			
			boolean isStopped = false;
			while (!isStopped) {
				
				Socket clientSocket = serverSocket.accept();
				ClientThread clientThread = new ClientThread(clientSocket);
				clientThread.start();
			}
			
		} catch (IOException e) {
			System.out.println("Port 9090 is already used !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
