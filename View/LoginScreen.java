package View;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.LoginListener;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

public class LoginScreen {

    private JFrame window;
    public Color color = new Color(0, 100, 200);
    private JTextField patientId = new JTextField("Enter Patient ID");
    private JButton searchButton = new JButton("Look Up");
    private JButton createButton = new JButton("Create New Account");

    public LoginScreen(JFrame window) {
        this.window = window;
        window.setTitle("Login");
        window.setPreferredSize(new Dimension(300, 500));
    }

    public void init() {
        Container cp = window.getContentPane();
        ((JComponent) cp).setBorder(BorderFactory.createEmptyBorder(20, 20, 250, 20));

        JPanel namePanel = new JPanel();
        namePanel.setPreferredSize(new Dimension(300, 100));
        namePanel.setLayout(new GridLayout(4, 1, 10, 10));
        cp.add(BorderLayout.CENTER, namePanel);

        var label = new JLabel("Breast Cancer Diagnosis", JLabel.CENTER);
        namePanel.add(label);

        patientId.setHorizontalAlignment(JTextField.CENTER);
        namePanel.add(patientId);

        searchButton.setBackground(color);
        searchButton.setForeground(Color.WHITE);
        searchButton.setOpaque(true);
        searchButton.setBorderPainted(false);
        namePanel.add(searchButton);
        namePanel.add(createButton);

        LoginListener listener = new LoginListener(this);
        searchButton.addActionListener(listener);
        createButton.addActionListener(listener);

    }

    public JTextField getPatientId() {
        return patientId;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JFrame getWindow() {
        return window;
    }

    public JButton getCreateButton() {
        return createButton;
    }
}