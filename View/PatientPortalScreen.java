package View;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Controller.PortalListener;
import Model.Patient;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;

public class PatientPortalScreen {

    private JFrame window;
    private Patient patient;
    // private JTextField patientTextField = new JTextField();
    public Color colorRed = new Color(230, 57, 70);
    public Color colorBlue = new Color(34, 106, 246);

    private JButton diagnoseButton = new JButton("Diagnose");
    private JButton saveButton = new JButton("Save");
    private JButton deleteButton = new JButton("Delete");
    private JButton backButton = new JButton("Back");

    private JLabel statusLable = new JLabel("");

    private JLabel firstNameLable = new JLabel("First Name");
    private JLabel lastNameLable = new JLabel("Last Name");
    private JLabel ageLable = new JLabel("Age");
    private JLabel phoneLable = new JLabel("Phone Number");
    private JLabel addressLable = new JLabel("Address");
    private JLabel emailLable = new JLabel("Email");

    private JLabel clumpThicknessLable = new JLabel("clumpThickness");
    private JLabel uniformityCellSizeLable = new JLabel("uniformityCellSize");
    private JLabel uniformityCellShapeLable = new JLabel("uniformityCellShape");
    private JLabel marginalAdhesionLable = new JLabel("marginalAdhesion");
    private JLabel singleEpithelialCellSizeLable = new JLabel("singleEpithelialCellSize");
    private JLabel bareNucleiLable = new JLabel("bareNuclei");
    private JLabel blandChromatinLable = new JLabel("blandChromatin");
    private JLabel normalNucleoliLable = new JLabel("normalNucleoli");
    private JLabel mitosesLable = new JLabel("mitoses");

    private JTextField firstName = new JTextField("");
    private JTextField lastName = new JTextField("");
    private JTextField age = new JTextField("");
    private JTextField address = new JTextField("");
    private JTextField phone = new JTextField("");
    private JTextField email = new JTextField("");

    private JTextField clumpThickness = new JTextField("");
    private JTextField uniformityCellSize = new JTextField("");
    private JTextField uniformityCellShape = new JTextField("");
    private JTextField marginalAdhesion = new JTextField("");
    private JTextField singleEpithelialCellSize = new JTextField("");
    private JTextField bareNuclei = new JTextField("");
    private JTextField blandChromatin = new JTextField("");
    private JTextField normalNucleoli = new JTextField("");
    private JTextField mitoses = new JTextField("");

    public PatientPortalScreen(JFrame window) {
        this.window = window;
        window.setTitle("Patient Portal");
        window.setPreferredSize(new Dimension(800, 600));
    }

    public void init() {
        Container cp = window.getContentPane();
        ((JComponent) cp).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2, 1, 10, 10));
        northPanel.add(statusLable);

        cp.add(BorderLayout.NORTH, northPanel);

        JPanel patientInfoPanel = new JPanel();
        patientInfoPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
        patientInfoPanel.setBorder(BorderFactory.createTitledBorder("Patient Information"));
        patientInfoPanel.setLayout(new GridLayout(9, 2, 10, 10));
        patientInfoPanel.add(firstNameLable);
        patientInfoPanel.add(firstName);
        patientInfoPanel.add(lastNameLable);
        patientInfoPanel.add(lastName);
        patientInfoPanel.add(ageLable);
        patientInfoPanel.add(age);
        patientInfoPanel.add(addressLable);
        patientInfoPanel.add(address);
        patientInfoPanel.add(phoneLable);
        patientInfoPanel.add(phone);
        patientInfoPanel.add(emailLable);
        patientInfoPanel.add(email);

        JPanel checkInfoPanel = new JPanel();
        checkInfoPanel.setLayout(new GridLayout(9, 2, 10, 10));
        checkInfoPanel.setBorder(BorderFactory.createTitledBorder("Medical Record"));

        clumpThickness.setColumns(3);

        checkInfoPanel.add(clumpThicknessLable);
        checkInfoPanel.add(clumpThickness);
        checkInfoPanel.add(uniformityCellSizeLable);
        checkInfoPanel.add(uniformityCellSize);
        checkInfoPanel.add(uniformityCellShapeLable);
        checkInfoPanel.add(uniformityCellShape);
        checkInfoPanel.add(marginalAdhesionLable);
        checkInfoPanel.add(marginalAdhesion);
        checkInfoPanel.add(singleEpithelialCellSizeLable);
        checkInfoPanel.add(singleEpithelialCellSize);
        checkInfoPanel.add(bareNucleiLable);
        checkInfoPanel.add(bareNuclei);
        checkInfoPanel.add(blandChromatinLable);
        checkInfoPanel.add(blandChromatin);
        checkInfoPanel.add(normalNucleoliLable);
        checkInfoPanel.add(normalNucleoli);
        checkInfoPanel.add(mitosesLable);
        checkInfoPanel.add(mitoses);

        firstName.setText(patient.getFirstName());
        lastName.setText(patient.getLastName());
        age.setText(String.valueOf(patient.getAge()));
        phone.setText(patient.getPhone());
        address.setText(patient.getAddress());
        email.setText(patient.getEmail());

        clumpThickness.setText(String.valueOf(patient.getClumpThickness()));
        uniformityCellSize.setText(String.valueOf(patient.getUniformityCellSize()));
        uniformityCellShape.setText(String.valueOf(patient.getUniformityCellShape()));
        marginalAdhesion.setText(String.valueOf(patient.getMarginalAdhesion()));
        singleEpithelialCellSize.setText(String.valueOf(patient.getSingleEpithelialCellSize()));
        bareNuclei.setText(String.valueOf(patient.getBareNuclei()));
        blandChromatin.setText(String.valueOf(patient.getBlandChromatin()));
        normalNucleoli.setText(String.valueOf(patient.getNormalNucleoli()));
        mitoses.setText(String.valueOf(patient.getMitoses()));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 1));
        centerPanel.add(patientInfoPanel);
        centerPanel.add(checkInfoPanel);
        cp.add(BorderLayout.CENTER, centerPanel);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1, 4, 10, 10));
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        // deleteButton.setBackground(colorRed);
        deleteButton.setForeground(Color.red);
        // deleteButton.setOpaque(true);
        // deleteButton.setBorderPainted(false);
        // southPanel.add(deleteButton);
        saveButton.setEnabled(false);

        southPanel.add(backButton);
        southPanel.add(saveButton);

        diagnoseButton.setBackground(colorBlue);
        diagnoseButton.setForeground(Color.white);
        diagnoseButton.setOpaque(true);
        diagnoseButton.setBorderPainted(false);
        southPanel.add(diagnoseButton);

        cp.add(BorderLayout.SOUTH, southPanel);

        // observer = new PatientSubscriber(this);
        // patient.addSubscriber(observer);

        PortalListener listener = new PortalListener(this);
        deleteButton.addActionListener(listener);
        backButton.addActionListener(listener);
        diagnoseButton.addActionListener(listener);
        saveButton.addActionListener(listener);

    }

    public Patient getPatient() {
        return patient;
    }

    public JButton getDiagnoseButton() {
        return diagnoseButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JFrame getWindow() {
        return window;
    }

    public JTextField getFirstName() {
        return firstName;
    }

    public JTextField getLastName() {
        return lastName;
    }

    public JTextField getAge() {
        return age;
    }

    public JTextField getPhone() {
        return phone;
    }

    public JTextField getAddress() {
        return address;
    }

    public JTextField getClumpThickness() {
        return clumpThickness;
    }

    public JTextField getUniformityCellSize() {
        return uniformityCellSize;
    }

    public JTextField getUniformityCellShape() {
        return uniformityCellShape;
    }

    public JTextField getMarginalAdhesion() {
        return marginalAdhesion;
    }

    public JTextField getSingleEpithelialCellSize() {
        return singleEpithelialCellSize;
    }

    public JTextField getBareNuclei() {
        return bareNuclei;
    }

    public JTextField getBlandChromatin() {
        return blandChromatin;
    }

    public JTextField getNormalNucleoli() {
        return normalNucleoli;
    }

    public JTextField getMitoses() {
        return mitoses;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public JLabel getStatusLable() {
        return statusLable;
    }
    
    public JTextField getEmail() {
        return email;
    }
}