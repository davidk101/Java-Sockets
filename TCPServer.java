import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

class TCPServer extends Thread {
	Socket connectionSocket;
	int clientNum;
	String clientSentence, capitalizedSentence;
	LocalTime connectTime, disconnectTime;
	String[] request;

	TCPServer(Socket inSocket, int counter, LocalTime startTime) {
		connectionSocket = inSocket;
		clientNum = counter;
		connectTime = startTime;
	}

	public void run() {
		int solution;
		String serverResponse;
		try {

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			// start accepting client requests through socket
			while (true) {
				// accept TCP client request until end request
				clientSentence = inFromClient.readLine();

				while ((clientSentence != null) && !(clientSentence.toUpperCase().equals("QUIT"))) {

					if (clientSentence.equals("CONNECT")) {
						System.out.println("Client " + clientNum + " >>> CONNECT >>> Open Connection Time: "
								+ connectTime + " sec");
						serverResponse = "Server >>> Successful connection...\n";

					} else if (clientSentence.contains("math")) {
						request = clientSentence.split("\\.");
						solution = mathFunction(request[1]);
						System.out.println(
								"Client " + clientNum + " >>> MATH >>> " + request[1] + " >>> Solution: " + solution);
						serverResponse = "Server >>> Solution: " + solution + "\n";

					}
					else {
						serverResponse = clientSentence.toUpperCase() + '\n';
					}

					// TODO: any other client message will just get capitalized and returned, we can
					// change this..
					outToClient.writeBytes(serverResponse);
					outToClient.flush();

					clientSentence = inFromClient.readLine();
				}

				// handles null string exception
				if (clientSentence == null) {
					continue;
				}

				// handle TCP client's end connection request
				if (clientSentence.equalsIgnoreCase("QUIT")) {
					// log client connection duration
					disconnectTime = LocalTime.now();
					System.out.println("Client " + clientNum + " >>> QUIT >>> Connection Duration: "
							+ connectTime.until(disconnectTime, ChronoUnit.SECONDS) + " sec");

					outToClient.writeBytes("Server >>> Confirming end connection request\n");
					outToClient.flush();
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			System.out.println("Client " + clientNum + " has disconnected. ");
		}
	}

	public static int mathFunction(String mathRequest) {
		try {
            int a, b;

            if (mathRequest.contains("+")) {
                String[] splitExpr = mathRequest.split("\\+");
                a = Integer.valueOf(splitExpr[0]);
                b = Integer.valueOf(splitExpr[1]);
                return a + b;
            } else if (mathRequest.contains("-")) {
                String[] splitExpr = mathRequest.split("-");
                a = Integer.valueOf(splitExpr[0]);
                b = Integer.valueOf(splitExpr[1]);
                return a - b;
            } else if (mathRequest.contains("*")) {
                String[] splitExpr = mathRequest.split("\\*");
                a = Integer.valueOf(splitExpr[0]);
                b = Integer.valueOf(splitExpr[1]);
                return a * b;
            } else if (mathRequest.contains("/")) {
                String[] splitExpr = mathRequest.split("/");
                a = Integer.valueOf(splitExpr[0]);
                b = Integer.valueOf(splitExpr[1]);
                return a / b;
            } else if (mathRequest.contains("^")) {
                String[] splitExpr = mathRequest.split("\\^");
                a = Integer.valueOf(splitExpr[0]);
                b = Integer.valueOf(splitExpr[1]);
                return (int) Math.pow(a, b);
            }

        }
        catch(Exception e) {
            System.out.println(e);
            return -1;
        }

		return 0;
	}
}
