package Client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    ConnectInputMessage connectWithServer = new ConnectInputMessage();
    Thread tConnectInputMessage = new Thread(connectWithServer);
    tConnectInputMessage.start();

    ReceiveMessageFromServer receiveMessage = new ReceiveMessageFromServer(connectWithServer.getInputStreamServer());
    Thread tReceiveMessage = new Thread(receiveMessage);
    tReceiveMessage.start();
    }
}
