package ca.uottawa.cmcfa039.liftcare;

public class Patient {
    private String name;
    private int age;
    private int category;
    private double severity;

    public Patient() {
        this.name = "";
        this.age = 0;
        this.category = 0;
        this.severity = 0.0;
    }

    public Patient(String name, int age, int category, double severity) {
        this.name = name;
        this.age = age;
        this.category = category;
        this.severity = severity;
    }

    public double getSeverity() {
        return severity;
    }

    public int getAge() {
        return age;
    }

    public int getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }
}
