package ca.uottawa.cmcfa039.liftcare;

public class Request{
    private Patient patient;
    private Route route;

    public Request(){
        this.patient = new Patient();
        this.route = new Route();
    }

    public Request(Patient patient, Route route){
        this.patient = patient;
        this.route = route;
    }

    public Patient getPatient() {
        return patient;
    }

    public Route getRoute() {
        return route;
    }
}