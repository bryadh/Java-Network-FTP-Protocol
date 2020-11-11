package java_network_ftp_protocol;

import java.io.*;
import java.net.*;

public class ClientThread extends Thread {

	private Socket socket;
	private BufferedReader reader;
	private BufferedOutputStream out;
	private BufferedInputStream fileReader;
	
	public ClientThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		
		
		try {
			
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedOutputStream(socket.getOutputStream());
			
			// Reading the file name
			String fileName = reader.readLine();
			System.out.println("file "+ fileName +" has been requested by "+ socket.getInetAddress().getHostAddress());
			
			// Specify the root directory and the file
			File file = new File("sroot/"+fileName);
			
			// check if the file exists
			if(!file.exists()) {
				
				// send code 0 to close connection
				out.write((byte)0);
				closeConnection();
			} else {
				
				// send code 1 and send the file
				out.write((byte)1);
				
				fileReader = new BufferedInputStream(new FileInputStream(file));
				
				// buffer size
				byte[] buffer = new byte[1024];
				
				int bytesRead = 0;
				
				while((bytesRead = fileReader.read(buffer)) != -1){
					
					out.write(buffer, 0, bytesRead);
					// debug with System.out.println(bytesRead);
					
					out.flush();
				}
				
				closeConnection();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closeConnection() {
		
		try {
			if(socket != null) {
				socket.close();
			}
			if(reader != null) {
				reader.close();
			}
			if(out != null) {
				out.close();
			}
			if(fileReader != null) {
				fileReader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
