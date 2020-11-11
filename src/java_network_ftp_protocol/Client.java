package java_network_ftp_protocol;

import java.io.*;
import java.net.*;

public class Client {

	public static void main(String[] args) {
		
		try {
			
			InputStreamReader in = new InputStreamReader(System.in);
			
			BufferedReader reader = new BufferedReader(in);
			
			String ipAddress ="";
			String fileName = "";
			
			boolean isValid = false;
			
			while(!isValid) {
				
				System.out.println("Enter a valid ip address: ");
				ipAddress = reader.readLine();
				
				// use some sort of validation here
				// then set isValid to true
				isValid = true;
			}
			
			System.out.println("Enter a file name: ");
			fileName = reader.readLine();
			
			Socket socket = new Socket(ipAddress, 9090);
			
			InputStream inputByte = socket.getInputStream();
			BufferedInputStream input = new BufferedInputStream(inputByte);
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
			
			// send the file name
			out.println(fileName);
			
			int code = input.read();
			
			if(code == 1) {
				
				// download process
				
				// Root to the download folder
				FileOutputStream fOut = new FileOutputStream("C:\\Users\\belgh\\Downloads\\"+fileName);
				BufferedOutputStream outputFile = new BufferedOutputStream(fOut);
				
				byte[] buffer = new byte[1024];
				int bytesRead = 0;
				
				while((bytesRead = input.read(buffer)) != -1) {
					
					System.out.println(".");
					outputFile.write(buffer, 0, bytesRead);
					outputFile.flush();
				}
				
				System.out.println(fileName+" downloaded");
				
				outputFile.close();
				socket.close();
				
			} else {
				
				System.out.println("No such file in the server");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
