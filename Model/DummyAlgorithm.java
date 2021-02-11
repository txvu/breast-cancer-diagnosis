package Model;

import java.util.Random;

public class DummyAlgorithm implements Algorithm {

    private Patient patient;

    public DummyAlgorithm(Patient patient) {
        this.patient = patient;
    }

    @Override
    public int generateResult() {
        Random r = new Random();
        int x = r.nextInt(2);
        int num;
        if (x == 0) {
            num = 2;
        } else {
            num = 4;
        }
        System.out.println(num);
        return num;
    }

}
