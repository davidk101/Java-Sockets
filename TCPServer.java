import java.io.*; 
import java.net.*; 

class TCPServer extends Thread { 
	
  public static void main(String argv[]) throws Exception 
	{ 
	  //declare sockets and IO
      Socket connectionSocket = null;
      ServerSocket socket = null;
      
      BufferedReader inFromClient = null;
      DataOutputStream outToClient = null;
      
      String clientSentence; 
      String capitalizedSentence; 
      
      //init server socket on port 6789
      try {
    	  socket = new ServerSocket(6789);
          System.out.println("Starting server on PORT 6789");
      } catch (IOException e) {
          e.printStackTrace();
      }

      //start accepting client requests through socket
      while(true) { 
    	  try {
              connectionSocket = socket.accept();
          } catch (IOException e) {
        	  socket.close();
              System.out.println("I/O error: " + e);
          }
    	  
    	  //init IO streams to communicate with TCP client 
    	  try {
        	  inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));  
        	  outToClient = new DataOutputStream(connectionSocket.getOutputStream()); 
          } catch (IOException e) {
              //socket.close();
              return;
          }
    	  
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
    } 
  
  
  public static int mathFunction(String input) {
	  System.out.println("math\n");
	  
	  return 0;
  }
} 
 

           
