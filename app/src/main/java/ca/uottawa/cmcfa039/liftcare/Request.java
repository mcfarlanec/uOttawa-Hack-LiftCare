package ca.uottawa.cmcfa039.liftcare;

import androidx.annotation.NonNull;

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

    public String toString(){
        return "Patient Name: " + patient.getName() + " Start Hospital: " + route.getStartPoint().getName() + " Destination: "
                + route.getEndPoint().getName() + " Distance: " + route.getDistance()/1000 + " Flight Time: "
                + route.getMins();
    }
}