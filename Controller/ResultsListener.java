package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import View.FinalResultScreen;
import View.PatientPortalScreen;

public class ResultsListener implements ActionListener {

    private FinalResultScreen screen;

    public ResultsListener(FinalResultScreen screen) {
        this.screen = screen;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton button = (JButton) e.getSource();
        if (button == screen.getCloselButton()) {
            JFrame window = screen.getWindow();
            window.getContentPane().removeAll();
            var patientPortalScreen = new PatientPortalScreen(window);
            patientPortalScreen.setPatient(screen.getPatient());
            patientPortalScreen.init();
            window.pack();
            window.revalidate();
        }

    }
    
}
