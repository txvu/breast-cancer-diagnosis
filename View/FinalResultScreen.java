package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Controller.ResultsListener;
import Model.Patient;

public class FinalResultScreen {

    private JFrame window;
    private Patient patient;
    public Color color = new Color(0,100,200);
    private JButton closelButton = new JButton("Close");
    private JLabel resultLabel = new JLabel("Result");

    public FinalResultScreen(JFrame window) {
        this.window = window;
        window.setTitle("Final Results");
        window.setPreferredSize(new Dimension(300, 300));
    }

    public void init() {
        Container cp = window.getContentPane();
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 1, 10, 10));
        centerPanel.add(resultLabel);
        resultLabel.setHorizontalAlignment(JLabel.CENTER);

        cp.add(BorderLayout.CENTER, centerPanel);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1, 1, 10, 10));
        southPanel.add(closelButton);

        cp.add(BorderLayout.SOUTH, southPanel);

        ResultsListener listener = new ResultsListener(this);
        closelButton.addActionListener(listener);
    }

    public JFrame getWindow() {
        return window;
    }

    public JButton getCloselButton() {
        return closelButton;
    }

    public JLabel getResultLabel() {
        return resultLabel;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
}
