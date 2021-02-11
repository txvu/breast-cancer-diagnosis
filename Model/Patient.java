package Model;

public class Patient {
    // Personal info
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String phone;
    private String address;
    private String email;

    // Medical record
    private int clumpThickness;
    private int uniformityCellSize;
    private int uniformityCellShape;
    private int marginalAdhesion;
    private int singleEpithelialCellSize;
    private int bareNuclei;
    private int blandChromatin;
    private int normalNucleoli;
    private int mitoses;
    private int classification;

    public Patient() {
    }

    public Patient(String id, String firstName, String lastName, int age) {
        this.phone = "";
        this.address = "";
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setClumpThickness(int clumpThickness) {
        this.clumpThickness = clumpThickness;
    }

    public int getClumpThickness() {
        return clumpThickness;
    }

    public void setUniformityCellSize(int uniformityCellSize) {
        this.uniformityCellSize = uniformityCellSize;
    }

    public int getUniformityCellSize() {
        return uniformityCellSize;
    }

    public void setUniformityCellShape(int uniformityCellShape) {
        this.uniformityCellShape = uniformityCellShape;
    }

    public int getUniformityCellShape() {
        return uniformityCellShape;
    }

    public int getMarginalAdhesion() {
        return marginalAdhesion;
    }

    public void setMarginalAdhesion(int marginalAdhesion) {
        this.marginalAdhesion = marginalAdhesion;
    }

    public void setSingleEpithelialCellSize(int singleEpithelialCellSize) {
        this.singleEpithelialCellSize = singleEpithelialCellSize;
    }

    public int getSingleEpithelialCellSize() {
        return singleEpithelialCellSize;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBareNuclei(int bareNuclei) {
        this.bareNuclei = bareNuclei;
    }

    public void setBlandChromatin(int blandChromatin) {
        this.blandChromatin = blandChromatin;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMitoses(int mitoses) {
        this.mitoses = mitoses;
    }

    public void setNormalNucleoli(int normalNucleoli) {
        this.normalNucleoli = normalNucleoli;
    }

    public String getAddress() {
        return address;
    }

    public int getBareNuclei() {
        return bareNuclei;
    }

    public int getBlandChromatin() {
        return blandChromatin;
    }

    public int getClassification() {
        return classification;
    }

    public String getLastName() {
        return lastName;
    }

    public int getMitoses() {
        return mitoses;
    }

    public int getNormalNucleoli() {
        return normalNucleoli;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void checkPoint() {
        System.out.println("\nCheck Point ------------------");
        System.out.println("id: " + id);
        System.out.println("firstName: " + firstName);
        System.out.println("lastName: " + lastName);
        System.out.println("age: " + age);
        System.out.println("phone: " + phone);
        System.out.println("address: " + address);
        System.out.println("email: " + email);

        System.out.println("clumpThickness: " + clumpThickness);
        System.out.println("uniformityCellSize: " + uniformityCellSize);
        System.out.println("uniformityCellShape: " + uniformityCellShape);
        System.out.println("marginalAdhesion: " + marginalAdhesion);
        System.out.println("singleEpithelialCellSize: " + singleEpithelialCellSize);
        System.out.println("bareNuclei: " + bareNuclei);
        System.out.println("blandChromatin: " + blandChromatin);
        System.out.println("normalNucleoli: " + normalNucleoli);
        System.out.println("mitoses: " + mitoses);
        System.out.println("classification: " + classification);
    }
}
