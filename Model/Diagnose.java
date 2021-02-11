package Model;

public class Diagnose {
    private Patient patient;

    public Diagnose(Patient patient) {
        this.patient = patient;
    }

    public int getResult() {
        Algorithm algorithm = new DummyAlgorithm(patient);
        int result = algorithm.generateResult();
        return result;
    }
}
