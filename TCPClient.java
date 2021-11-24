import java.io.*; 
import java.net.*; 

public class TCPClient {

    public static void main(String argv[]) throws Exception 
    { 
        String sentence; 
        String modifiedSentence; 
        
        //declare IO and client socket
        BufferedReader inFromUser = null;
        DataOutputStream outToServer = null;
        BufferedReader inFromServer = null;
        
        Socket clientSocket = new Socket("127.0.0.1", 6789); 
        System.out.println("Starting client on PORT 6789");
        
        //init IO
        try {
        	inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
            outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
        } catch (IOException e) {
            clientSocket.close();
            return;
        }

        //communicate with TCP server
        while (true) {
            try {
            	sentence = inFromUser.readLine();

            	//handle TCP client close connection request
            	if ((sentence == null) || sentence.equalsIgnoreCase("QUIT")) {
            		//System.out.println("Sending close TCP connection request to server\n"); 
                	outToServer.writeBytes(sentence + '\n');                    
                	outToServer.flush();
                	
                	modifiedSentence = inFromServer.readLine(); 
                    System.out.println("FROM SERVER: " + modifiedSentence);
                    clientSocket.close();
                    return;
                } else {//handle any other request 
                	//System.out.println("sending message to server: " + sentence + "\n"); 
                	outToServer.writeBytes(sentence + '\n');                    
                	outToServer.flush();
                	
                	modifiedSentence = inFromServer.readLine(); 
                    System.out.println("FROM SERVER: " + modifiedSentence);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
                         
      } 
} 

        
