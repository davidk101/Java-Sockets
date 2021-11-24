import java.io.*; 
import java.net.*; 

class TCPServer extends Thread { 
	Socket connectionSocket;
	int clientNo;
	String clientSentence, capitalizedSentence; 
	int squre;
	
	TCPServer(Socket inSocket,int counter){
	  connectionSocket = inSocket;
	  clientNo=counter;
	}
	
	public void run(){
	  try {
	    
	    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));  
	    DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream()); 
	    
	    
	    //start accepting client requests through socket
	    while(true) { 
	    	  //accept TCP client request until end request
	    	  clientSentence = inFromClient.readLine();
	    	  while((clientSentence != null) && !(clientSentence.toUpperCase().equals("QUIT"))) {
	              System.out.println("Recieved message from client: " + clientSentence + "\n");
	              
	              if(clientSentence.equalsIgnoreCase("CONNECT")){
	            	  //confirm successful connection
	            	  //create new log entry with client details 
	            	  
	              }
	              
	              if(clientSentence.equalsIgnoreCase("MATH")){
	            	  //math function 
	            	  mathFunction(clientSentence);
	            	  
	              }
	             
	              
	              //TCP client message manipulation and return 
	              capitalizedSentence = clientSentence.toUpperCase() + '\n'; 
	              outToClient.writeBytes(capitalizedSentence); 
	              System.out.println("Sending message to client: " + capitalizedSentence + "\n");
	              outToClient.flush();
	              
	              
	        	  clientSentence = inFromClient.readLine();
	    	  }
	    	  
	    	  //handle TCP client's end connection request
	    	  if(clientSentence.equalsIgnoreCase("QUIT")) {
	    		  System.out.println("Recieved end connection request from client\n");              

	              outToClient.writeBytes("Comfirming end connection request\n"); 
	              outToClient.flush();
	    	  }
	    }
	  }catch(Exception ex){
	    System.out.println(ex);
	  }finally{
	    System.out.println("Client -" + clientNo + " exit!! ");
	  }
	}
  
  
  public static int mathFunction(String input) {
	  System.out.println("math\n");
	  
	  return 0;
  }
} 
 

           
