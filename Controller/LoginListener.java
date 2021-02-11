package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Model.Patient;
import Utility.xmlParser;
import View.LoginScreen;
import View.PatientPortalScreen;

public class LoginListener implements ActionListener {

    private LoginScreen screen;

    public LoginListener(LoginScreen screen) {
        this.screen = screen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();
        if (button == screen.getSearchButton()) {

            Patient patientLogin = xmlParser.readPatient(screen.getPatientId().getText());

            if (patientLogin == null) {
                System.out.println("cannot find");
                screen.getPatientId().setText("Please try again ...");
            } else {
                patientLogin.checkPoint();

                JFrame window = screen.getWindow();
                window.getContentPane().removeAll();
                var patientInfoScreen = new PatientPortalScreen(window);
                patientInfoScreen.setPatient(patientLogin);
                patientInfoScreen.init();
                patientInfoScreen.getSaveButton().setEnabled(false);
                patientInfoScreen.getStatusLable().setText(patientLogin.getFirstName() + " "
                        + patientLogin.getLastName() + " - ID: " + patientLogin.getId());
                window.pack();
                window.revalidate();
            }

        } else if (button == screen.getCreateButton()) {
            Patient newPatient = new Patient();

            JFrame window = screen.getWindow();
            window.getContentPane().removeAll();
            var patientInfoScreen = new PatientPortalScreen(window);
            patientInfoScreen.setPatient(newPatient);
            patientInfoScreen.init();
            patientInfoScreen.getSaveButton().setEnabled(true);
            patientInfoScreen.getStatusLable().setText("Enter patient's information");
            window.pack();
            window.revalidate();
        }

    }

}
