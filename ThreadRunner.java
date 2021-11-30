import java.net.*;
import java.time.LocalTime;
import java.io.*;

public class ThreadRunner {

	public static void main(String[] args) throws Exception {
		LocalTime connectTime;
		int PORT = Integer.parseInt(args[0]); 		


		try {
			ServerSocket server = new ServerSocket(PORT);
			int counter = 0;
			System.out.println("Server Started on PORT " + PORT);
			
			while (true) {
				counter++;
				// server accept the client connection request
				Socket serverClient = server.accept();
				connectTime = LocalTime.now();
				
				
				// send the request to a separate thread and run()
				TCPServer sct = new TCPServer(serverClient, counter, connectTime); 
				sct.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
