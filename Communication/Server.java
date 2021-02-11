package Communication;

import java.net.*;

import Model.Algorithm;
import Model.DummyAlgorithm;
import Model.Patient;
import Utility.xmlParser;

import java.io.*;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    // private BufferedReader in;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("\nCreate a server on port " + port);
            clientSocket = serverSocket.accept();
            System.out.println("\nCatch one connection ...");


            System.out.println("\nReading tempPatient file ...");
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            String k = in.readUTF();

            System.out.println("\nWrite diagnosingTempPatient file ...");
            String dir = System.getProperty("user.dir");
            FileOutputStream fos = new FileOutputStream(dir + "/diagnosingTempPatient.xml");
            byte[] b = k.getBytes();
            fos.write(b);

            System.out.println("\nExtracting diagnosingTempPatient file ...");
            Patient patient = xmlParser.readDiagnosingTempPatient();

            int result = 0;
            if (patient != null) {
                System.out.println("\nStart to diagnose ...");
                Algorithm algorithm = new DummyAlgorithm(patient);
                result = algorithm.generateResult();
                patient.setClassification(result);
            }

            System.out.println("Send the patial result back to client ...");
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(result + "");
            // out.println("good bye");

            // in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // String inputLine;
            // while ((inputLine = in.readLine()) != null) {
            //     if (".".equals(inputLine)) {
            //         out.println("good bye");
            //         break;
            //     }
            //     System.out.println("msg: " + inputLine);
            // }

            // out.println(inputLine);
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // public void stop() {
    //     try {
    //         in.close();
    //         out.close();
    //         clientSocket.close();
    //         serverSocket.close();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }

    // }
}
