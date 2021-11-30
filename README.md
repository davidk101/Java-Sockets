# CS 4390 Network Application

**Run Program** 
- compile all three java files into class files using make command.
- run ThreadRunner.java (java ThreadRunner)
- run TCP clients, open a new terminal for each client you want to run (java TCPClient)
- to connect to the TCP server, enter CONNECT from the client terminal
- you can start entering math requests in the client terminals and you will get reponses from the TCP server
- enter 'quit' when you want to disconnect a client
- note: you can run the command make clean to clear the .class files






**ThreadRunner.java**
The ThreadRunner class is in charge of starting individual threads for the incoming client requests to the TCP server. 


**TCPServer.java**
This TCPServer class handles incoming requests from TCP clients by creating independent threads for each client.
Methods and features: 
- Keeps log - this log is printed to console
- Math calculation - defined in a method which returns solution to simple math requests from client 



**TCPClient.java**
The TCPClient class requests a connection to the TCP server and sends formatted requests for opening a connection, closing a connection, and math calculations. 
Client names will be an integer determined by the order in which they request a connection with the TCP server. This simple code is found in the ThreadRunner class. 




**Message requests formats:** [requestType].[args]  (if any args)
- example: MATH.6/2
- example: QUIT
- example: CONNECT

**Log Format**
- Client [clientNum] >>> CONNECT >>> Open Connection Time: [time] sec
- Client [clientNum] >>> QUIT >>> Connection Duration: [duration time] sec
- Client [clientNum] >>> MATH >>> [mathRequest] >>> Solution: [return value from math function]

**Server Response Format to client**
- Server >>> Successful connection...
- Server >>> Solution: [solution] 
- Server >>> Confirming end connection request



**NOTES**
- Any request not fitting the message format will default to a upper case response from the server 
