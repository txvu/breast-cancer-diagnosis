package Controller;

import View.FinalResultScreen;
import View.LoginScreen;
import View.PatientPortalScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Communication.Client;
import Model.Algorithm;
import Model.DummyAlgorithm;
import Model.Patient;
import Utility.xmlParser;

public class PortalListener implements ActionListener {

    private PatientPortalScreen screen;
    private int benignCount = 0;
    private int malignantCount = 0;

    public PortalListener(PatientPortalScreen screen) {
        this.screen = screen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton button = (JButton) e.getSource();
        if (button == screen.getBackButton()) {
            JFrame window = screen.getWindow();
            window.getContentPane().removeAll();
            var menu = new LoginScreen(window);
            menu.init();
            window.pack();
            window.revalidate();
        } else if (button == screen.getSaveButton()) {
            var patient = screen.getPatient();

            patient.setFirstName(screen.getFirstName().getText());
            patient.setLastName(screen.getLastName().getText());
            patient.setAge(Integer.parseInt(screen.getAge().getText()));
            patient.setPhone(screen.getPhone().getText());
            patient.setAddress(screen.getAddress().getText());
            patient.setEmail(screen.getEmail().getText());
            patient.setClumpThickness(Integer.parseInt(screen.getClumpThickness().getText()));
            patient.setUniformityCellSize(Integer.parseInt(screen.getUniformityCellSize().getText()));
            patient.setUniformityCellShape(Integer.parseInt(screen.getUniformityCellShape().getText()));
            patient.setMarginalAdhesion(Integer.parseInt(screen.getMarginalAdhesion().getText()));
            patient.setSingleEpithelialCellSize(Integer.parseInt(screen.getSingleEpithelialCellSize().getText()));
            patient.setBareNuclei(Integer.parseInt(screen.getBareNuclei().getText()));
            patient.setBlandChromatin(Integer.parseInt(screen.getBlandChromatin().getText()));
            patient.setNormalNucleoli(Integer.parseInt(screen.getNormalNucleoli().getText()));
            patient.setMitoses(Integer.parseInt(screen.getMitoses().getText()));

            patient.setId(xmlParser.generatePatientId(patient));
            
            xmlParser.addPatient(patient);

            screen.getStatusLable().setText("Patient ID is now: " + patient.getId());

        } else if (button == screen.getDiagnoseButton()) {

            var patient = screen.getPatient();
            patient.setClassification(getFinalResult());
            xmlParser.modifyPatient(patient.getId(), patient.getClassification());

            System.out.println(xmlParser.readTempPatientResult());

            JFrame  window = screen.getWindow();
            window.getContentPane().removeAll();
            var panel = new FinalResultScreen(window);
            panel.setPatient(screen.getPatient());
            panel.init();

            if (patient.getClassification() == 2) {
                panel.getResultLabel().setText("Benign - " + benignCount + "B/" + malignantCount + "M");
            } else if (patient.getClassification() == 4) {
                panel.getResultLabel().setText("Malignant - " + benignCount + "B/" + malignantCount + "M");
            }

            window.pack();
            window.setVisible(true);
            window.revalidate();
        }

    }

    public int getFinalResult() {
        // Prepare XML file to transfer
        xmlParser.createTempPatient(screen.getPatient());

        getLocalResult();
        fetchServerResults();
        
        if (benignCount >= malignantCount) {
            return 2;
        } else {
            return 4;
        }
    }

    public void getLocalResult() {
        Algorithm algorithm = new DummyAlgorithm(screen.getPatient());
        int result = algorithm.generateResult();
        if (result == 2) {
            benignCount++;
        } else if (result == 4) {
            malignantCount++;
        }
    }

    public void fetchServerResults() {
        String[] ipAddresses = {"127.0.0.1", "127.0.0.1", "127.0.0.1"};
        int[] ports = {2000, 2001, 2002};
        int[] partialResults = new int[3];
        // String[] ipAddresses = {"127.0.0.1"};
        // int[] ports = {2000};
        
        for (int i = 0; i < ipAddresses.length; i++) {

            Client clientSocket = new Client();
            clientSocket.startConnection(ipAddresses[i], ports[i]);
            partialResults[i] = clientSocket.getPartialResult();

            if (partialResults[i] == 2) {
                benignCount++;
            } else if (partialResults[i] == 4) {
                malignantCount++;
            }
        }
    }
    
}
