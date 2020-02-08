package ca.uottawa.cmcfa039.liftcare;

public class Request{
    private Patient patient;
    private boolean approved;
    //desired hospital

    public Request(){
        this.patient = new Patient();
        this.approved = false;
    }

    public Request(Patient patient){
        this.patient = patient;
        this.approved = false;
    }

    public Patient getPatient() {
        return patient;
    }
}