package Communication;

import java.net.*;
import java.util.stream.Collectors;
import java.io.*;

public class Client {
    private Socket clientSocket;
    // private PrintWriter out;
    private BufferedReader in;
    private int partialResult;

    public void startConnection(String ip, int port) {
        System.out.println("Start connection ...");

        try {

            clientSocket = new Socket(ip, port);

            System.out.println("Read tempPatient... get ready to transfer ...");
            String dir = System.getProperty("user.dir");
            BufferedReader br = new BufferedReader(new FileReader(dir + "/tempPatient.xml"));
            byte []b = new byte[30];
            String k = br.lines().collect(Collectors.joining());
            
            System.out.println("Send patient profile to server to diagnose ...");
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream()); 
            out.writeUTF(k); 
            System.out.println("Transfer Completed"); 

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String response;
            
            // out = new PrintWriter(clientSocket.getOutputStream(), true);
            // out.println("hello there");
            // out.println(".");

            System.out.println("Reading the partial result from server ...");
            response = in.readLine();

            partialResult = Integer.parseInt(response);

            System.out.println(response);

            in.close();
            out.close();
            clientSocket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getPartialResult() {
        return partialResult;
    }
}
