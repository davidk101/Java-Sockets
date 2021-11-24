JFLAGS = -g
JC = javac

default: TCPClient.class TCPServer.class ThreadRunner.class

TCPClient.class: TCPClient.java
	$(JC) $(JFLAGS) TCPClient.java

TCPServer.class: TCPServer.java
	$(JC) $(JFLAGS) TCPServer.java

ThreadRunner.class: ThreadRunner.java
	$(JC) $(JFLAGS) ThreadRunner.java

clean:
	rm -rf *.class