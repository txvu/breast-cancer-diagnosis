package Utility;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Model.Patient;

public class xmlParser {

    static public Patient readPatient(String patientId) {
        try {
            // Directory from where the program was launched.
            String dir = System.getProperty("user.dir");

            File fXmlFile = new File(dir + "/data.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Patient");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Staff id : " + eElement.getAttribute("id"));

                    if (eElement.getAttribute("id").equals(patientId)) {
                        // Create new Patient object
                        Patient patient = new Patient();

                        // Set Patient attributes
                        patient.setId(eElement.getAttribute("id"));

                        patient.setFirstName(eElement.getElementsByTagName("first_name").item(0).getTextContent());
                        patient.setLastName(eElement.getElementsByTagName("last_name").item(0).getTextContent());
                        patient.setAge(Integer.parseInt(eElement.getElementsByTagName("age").item(0).getTextContent()));
                        patient.setPhone(eElement.getElementsByTagName("phone").item(0).getTextContent());
                        patient.setAddress(eElement.getElementsByTagName("address").item(0).getTextContent());
                        patient.setEmail(eElement.getElementsByTagName("email").item(0).getTextContent());

                        patient.setClumpThickness(Integer
                                .parseInt(eElement.getElementsByTagName("clump_thickness").item(0).getTextContent()));
                        patient.setUniformityCellSize(Integer.parseInt(
                                eElement.getElementsByTagName("uniformity_cell_size").item(0).getTextContent()));
                        patient.setUniformityCellShape(Integer.parseInt(
                                eElement.getElementsByTagName("uniformity_cell_shape").item(0).getTextContent()));
                        patient.setMarginalAdhesion(Integer
                                .parseInt(eElement.getElementsByTagName("marginal_adhesion").item(0).getTextContent()));
                        patient.setSingleEpithelialCellSize(Integer.parseInt(
                                eElement.getElementsByTagName("single_epithelial_cell_size").item(0).getTextContent()));
                        patient.setBareNuclei(Integer
                                .parseInt(eElement.getElementsByTagName("bare_nuclei").item(0).getTextContent()));
                        patient.setBlandChromatin(Integer
                                .parseInt(eElement.getElementsByTagName("bland_chromatin").item(0).getTextContent()));
                        patient.setNormalNucleoli(Integer
                                .parseInt(eElement.getElementsByTagName("normal_nucleoli").item(0).getTextContent()));
                        patient.setMitoses(
                                Integer.parseInt(eElement.getElementsByTagName("mitoses").item(0).getTextContent()));
                        patient.setClassification(
                                Integer.parseInt(eElement.getElementsByTagName("class").item(0).getTextContent()));

                        return patient;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static public void modifyPatient(String patientId, int classification) {
        try {
            // Directory from where the program was launched.
            String dir = System.getProperty("user.dir");
            String filePath = dir + "/data.xml";
            File fXmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Patient");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Staff id : " + eElement.getAttribute("id"));
                    System.out.println("clump_thickness : "
                            + eElement.getElementsByTagName("clump_thickness").item(0).getTextContent());
                    if (eElement.getAttribute("id").equals(patientId)) {
                        eElement.getElementsByTagName("class").item(0).setTextContent(String.valueOf(classification));
                    }
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void addPatient(Patient patient) {

        try {
            // Directory from where the program was launched.
            String dir = System.getProperty("user.dir");
            String filePath = dir + "/data.xml";
            File fXmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            Node dataset = doc.getFirstChild();

            String id = patient.getId();

            // append a new node to staff
            Element patientNode = doc.createElement("Patient");
            patientNode.setAttribute("id", id);

            Element firstName = doc.createElement("first_name");
            Element lastName = doc.createElement("last_name");
            Element age = doc.createElement("age");
            Element phone = doc.createElement("phone");
            Element address = doc.createElement("address");
            Element email = doc.createElement("email");

            Element clumpThickness = doc.createElement("clump_thickness");
            Element uniformityCellSize = doc.createElement("uniformity_cell_size");
            Element uniformityCellShape = doc.createElement("uniformity_cell_shape");
            Element marginalAdhesion = doc.createElement("marginal_adhesion");
            Element singleEpithelialCellSize = doc.createElement("single_epithelial_cell_size");
            Element bareNuclei = doc.createElement("bare_nuclei");
            Element blandChromatin = doc.createElement("bland_chromatin");
            Element normalNucleoli = doc.createElement("normal_nucleoli");
            Element mitoses = doc.createElement("mitoses");
            Element classification = doc.createElement("class");

            firstName.appendChild(doc.createTextNode(patient.getFirstName()));
            lastName.appendChild(doc.createTextNode(patient.getLastName()));
            age.appendChild(doc.createTextNode(String.valueOf(patient.getAge())));
            phone.appendChild(doc.createTextNode(patient.getPhone()));
            address.appendChild(doc.createTextNode(patient.getAddress()));
            email.appendChild(doc.createTextNode(patient.getEmail()));

            clumpThickness.appendChild(doc.createTextNode(String.valueOf(patient.getClumpThickness())));
            uniformityCellSize.appendChild(doc.createTextNode(String.valueOf(patient.getUniformityCellSize())));
            uniformityCellShape.appendChild(doc.createTextNode(String.valueOf(patient.getUniformityCellShape())));
            marginalAdhesion.appendChild(doc.createTextNode(String.valueOf(patient.getMarginalAdhesion())));
            singleEpithelialCellSize
                    .appendChild(doc.createTextNode(String.valueOf(patient.getSingleEpithelialCellSize())));
            bareNuclei.appendChild(doc.createTextNode(String.valueOf(patient.getBareNuclei())));
            blandChromatin.appendChild(doc.createTextNode(String.valueOf(patient.getBlandChromatin())));
            normalNucleoli.appendChild(doc.createTextNode(String.valueOf(patient.getNormalNucleoli())));
            mitoses.appendChild(doc.createTextNode(String.valueOf(patient.getMitoses())));
            classification.appendChild(doc.createTextNode(String.valueOf(patient.getClassification())));

            patientNode.appendChild(firstName);
            patientNode.appendChild(lastName);
            patientNode.appendChild(age);
            patientNode.appendChild(phone);
            patientNode.appendChild(address);
            patientNode.appendChild(email);

            patientNode.appendChild(clumpThickness);
            patientNode.appendChild(uniformityCellSize);
            patientNode.appendChild(uniformityCellShape);
            patientNode.appendChild(marginalAdhesion);
            patientNode.appendChild(singleEpithelialCellSize);
            patientNode.appendChild(bareNuclei);
            patientNode.appendChild(blandChromatin);
            patientNode.appendChild(normalNucleoli);
            patientNode.appendChild(mitoses);
            patientNode.appendChild(classification);

            dataset.appendChild(patientNode);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public String generatePatientId(Patient patient) {
        try {
            // Directory from where the program was launched.
            String dir = System.getProperty("user.dir");
            String filePath = dir + "/data.xml";
            File fXmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            Node dataset = doc.getFirstChild();

            // Prepare patient id: <init firstName + init lastName + index>
            String id = patient.getFirstName().substring(0, 1).toLowerCase()
                    + patient.getLastName().substring(0, 1).toLowerCase();
            NodeList nList = doc.getElementsByTagName("Patient");
            int index = nList.getLength() + 1;
            id += String.format("%06d", index);

            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    static public void createTempPatient(Patient patient) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            String id = patient.getId();

            // append a new node to staff
            Element patientNode = doc.createElement("Patient");
            patientNode.setAttribute("id", id);
            doc.appendChild(patientNode);

            Element firstName = doc.createElement("first_name");
            Element lastName = doc.createElement("last_name");
            Element age = doc.createElement("age");
            Element phone = doc.createElement("phone");
            Element address = doc.createElement("address");
            Element email = doc.createElement("email");

            Element clumpThickness = doc.createElement("clump_thickness");
            Element uniformityCellSize = doc.createElement("uniformity_cell_size");
            Element uniformityCellShape = doc.createElement("uniformity_cell_shape");
            Element marginalAdhesion = doc.createElement("marginal_adhesion");
            Element singleEpithelialCellSize = doc.createElement("single_epithelial_cell_size");
            Element bareNuclei = doc.createElement("bare_nuclei");
            Element blandChromatin = doc.createElement("bland_chromatin");
            Element normalNucleoli = doc.createElement("normal_nucleoli");
            Element mitoses = doc.createElement("mitoses");
            Element classification = doc.createElement("class");

            firstName.appendChild(doc.createTextNode(patient.getFirstName()));
            lastName.appendChild(doc.createTextNode(patient.getLastName()));
            age.appendChild(doc.createTextNode(String.valueOf(patient.getAge())));
            phone.appendChild(doc.createTextNode(patient.getPhone()));
            address.appendChild(doc.createTextNode(patient.getAddress()));
            email.appendChild(doc.createTextNode(patient.getEmail()));

            clumpThickness.appendChild(doc.createTextNode(String.valueOf(patient.getClumpThickness())));
            uniformityCellSize.appendChild(doc.createTextNode(String.valueOf(patient.getUniformityCellSize())));
            uniformityCellShape.appendChild(doc.createTextNode(String.valueOf(patient.getUniformityCellShape())));
            marginalAdhesion.appendChild(doc.createTextNode(String.valueOf(patient.getMarginalAdhesion())));
            singleEpithelialCellSize
                    .appendChild(doc.createTextNode(String.valueOf(patient.getSingleEpithelialCellSize())));
            bareNuclei.appendChild(doc.createTextNode(String.valueOf(patient.getBareNuclei())));
            blandChromatin.appendChild(doc.createTextNode(String.valueOf(patient.getBlandChromatin())));
            normalNucleoli.appendChild(doc.createTextNode(String.valueOf(patient.getNormalNucleoli())));
            mitoses.appendChild(doc.createTextNode(String.valueOf(patient.getMitoses())));
            classification.appendChild(doc.createTextNode(String.valueOf(patient.getClassification())));

            patientNode.appendChild(firstName);
            patientNode.appendChild(lastName);
            patientNode.appendChild(age);
            patientNode.appendChild(phone);
            patientNode.appendChild(address);
            patientNode.appendChild(email);

            patientNode.appendChild(clumpThickness);
            patientNode.appendChild(uniformityCellSize);
            patientNode.appendChild(uniformityCellShape);
            patientNode.appendChild(marginalAdhesion);
            patientNode.appendChild(singleEpithelialCellSize);
            patientNode.appendChild(bareNuclei);
            patientNode.appendChild(blandChromatin);
            patientNode.appendChild(normalNucleoli);
            patientNode.appendChild(mitoses);
            patientNode.appendChild(classification);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            String dir = System.getProperty("user.dir");
            String filePath = dir + "/tempPatient.xml";
            File newXMLlFile = new File(filePath);

            StreamResult result = new StreamResult(newXMLlFile);

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    static public int readTempPatientResult() {
        try {
            // Directory from where the program was launched.
            String dir = System.getProperty("user.dir");

            File fXmlFile = new File(dir + "/tempPatient.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            Node nNode = doc.getDocumentElement();

            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            Element eElement = (Element) nNode;

            System.out.println("Staff id : " + eElement.getAttribute("id"));

            // Create new Patient object
            Patient patient = new Patient();

            // Set Patient attributes
            patient.setId(eElement.getAttribute("id"));

            patient.setFirstName(eElement.getElementsByTagName("first_name").item(0).getTextContent());
            patient.setLastName(eElement.getElementsByTagName("last_name").item(0).getTextContent());
            patient.setAge(Integer.parseInt(eElement.getElementsByTagName("age").item(0).getTextContent()));
            patient.setPhone(eElement.getElementsByTagName("phone").item(0).getTextContent());
            patient.setAddress(eElement.getElementsByTagName("address").item(0).getTextContent());
            patient.setEmail(eElement.getElementsByTagName("email").item(0).getTextContent());

            patient.setClumpThickness(
                    Integer.parseInt(eElement.getElementsByTagName("clump_thickness").item(0).getTextContent()));
            patient.setUniformityCellSize(
                    Integer.parseInt(eElement.getElementsByTagName("uniformity_cell_size").item(0).getTextContent()));
            patient.setUniformityCellShape(
                    Integer.parseInt(eElement.getElementsByTagName("uniformity_cell_shape").item(0).getTextContent()));
            patient.setMarginalAdhesion(
                    Integer.parseInt(eElement.getElementsByTagName("marginal_adhesion").item(0).getTextContent()));
            patient.setSingleEpithelialCellSize(Integer
                    .parseInt(eElement.getElementsByTagName("single_epithelial_cell_size").item(0).getTextContent()));
            patient.setBareNuclei(
                    Integer.parseInt(eElement.getElementsByTagName("bare_nuclei").item(0).getTextContent()));
            patient.setBlandChromatin(
                    Integer.parseInt(eElement.getElementsByTagName("bland_chromatin").item(0).getTextContent()));
            patient.setNormalNucleoli(
                    Integer.parseInt(eElement.getElementsByTagName("normal_nucleoli").item(0).getTextContent()));
            patient.setMitoses(Integer.parseInt(eElement.getElementsByTagName("mitoses").item(0).getTextContent()));
            patient.setClassification(
                    Integer.parseInt(eElement.getElementsByTagName("class").item(0).getTextContent()));

            
            patient.checkPoint();
            return patient.getClassification();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    static public Patient readDiagnosingTempPatient() {
        try {
            // Directory from where the program was launched.
            String dir = System.getProperty("user.dir");

            File fXmlFile = new File(dir + "/diagnosingTempPatient.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            Node nNode = doc.getDocumentElement();

            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            Element eElement = (Element) nNode;

            System.out.println("Staff id : " + eElement.getAttribute("id"));

            // Create new Patient object
            Patient patient = new Patient();

            // Set Patient attributes
            patient.setId(eElement.getAttribute("id"));

            patient.setFirstName(eElement.getElementsByTagName("first_name").item(0).getTextContent());
            patient.setLastName(eElement.getElementsByTagName("last_name").item(0).getTextContent());
            patient.setAge(Integer.parseInt(eElement.getElementsByTagName("age").item(0).getTextContent()));
            patient.setPhone(eElement.getElementsByTagName("phone").item(0).getTextContent());
            patient.setAddress(eElement.getElementsByTagName("address").item(0).getTextContent());
            patient.setEmail(eElement.getElementsByTagName("email").item(0).getTextContent());

            patient.setClumpThickness(
                    Integer.parseInt(eElement.getElementsByTagName("clump_thickness").item(0).getTextContent()));
            patient.setUniformityCellSize(
                    Integer.parseInt(eElement.getElementsByTagName("uniformity_cell_size").item(0).getTextContent()));
            patient.setUniformityCellShape(
                    Integer.parseInt(eElement.getElementsByTagName("uniformity_cell_shape").item(0).getTextContent()));
            patient.setMarginalAdhesion(
                    Integer.parseInt(eElement.getElementsByTagName("marginal_adhesion").item(0).getTextContent()));
            patient.setSingleEpithelialCellSize(Integer
                    .parseInt(eElement.getElementsByTagName("single_epithelial_cell_size").item(0).getTextContent()));
            patient.setBareNuclei(
                    Integer.parseInt(eElement.getElementsByTagName("bare_nuclei").item(0).getTextContent()));
            patient.setBlandChromatin(
                    Integer.parseInt(eElement.getElementsByTagName("bland_chromatin").item(0).getTextContent()));
            patient.setNormalNucleoli(
                    Integer.parseInt(eElement.getElementsByTagName("normal_nucleoli").item(0).getTextContent()));
            patient.setMitoses(Integer.parseInt(eElement.getElementsByTagName("mitoses").item(0).getTextContent()));
            patient.setClassification(
                    Integer.parseInt(eElement.getElementsByTagName("class").item(0).getTextContent()));

            
            patient.checkPoint();
            return patient;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
