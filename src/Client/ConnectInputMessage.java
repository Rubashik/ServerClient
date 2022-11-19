package Client;

import java.io.*;
import java.net.Socket;

public class ConnectInputMessage implements Runnable{
    private Socket serverConnect;
    private InputStream inputStreamServer;

    public ConnectInputMessage() throws IOException {
        serverConnect = new Socket("localhost",8887);
        inputStreamServer = serverConnect.getInputStream();
    }

    public Socket getServerConnect() {
        return serverConnect;
    }

    public InputStream getInputStreamServer() {
        return inputStreamServer;
    }

    @Override
    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStreamServer));
        String serverMessage;

        while (true){
            try {
                serverMessage = in.readLine();
                if(serverMessage!=null){
                    System.out.print(serverMessage+"\n");
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PrintWriter out = null;
        BufferedReader inputUser = new BufferedReader((new InputStreamReader(System.in)));

        String userMessage = null;
        while (true){
            System.out.print("Enter message: ");
            try {
                userMessage=inputUser.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out = new PrintWriter(serverConnect.getOutputStream(),true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println(userMessage);

            if("exit".equals(userMessage)){
                break;
            }
        }
    }
}
