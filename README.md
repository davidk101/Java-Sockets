# CS 4390 Network Application

**Run Program** 
- compile all three class files using javac (example: javac TCPClient.java)
- run ThreadRunner.java (java ThreadRunner)
- run TCP clients, open a new terminal for each client you want to run (java TCPClient.java)
- you can start entering requests in the client terminals and you will get reponses from the TCP server
- enter 'quit' when you want to disconnect a client






**ThreadRunner.java**
The ThreadRunner class is in charge of starting individual threads for the incoming client requests to the TCP server. 


**TCPServer.java**
This TCPServer class handles incoming requests from TCP clients by creating independent threads for each client.
Methods: 
- keep log
- math calculation



**TCPClient.java**
The TCPClient class requests a connection to the TCP server and sends formatted requests for opening a connection, closing a connection, and math calculations. 
Client names will be an interger determined by the order in which they request a connection with the TCP server. This simple code is found in the ThreadRunner class. 




**Message requests formats:** [requestType].[args]
- example: math.6/2
- example: quit.0
- example: connect.0
