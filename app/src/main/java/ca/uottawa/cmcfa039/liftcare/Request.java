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
        return "Patient : " + patient.getName() + " Start : " + route.getStartPoint().getName() + " Destination: "
                + route.getEndPoint().getName() + " Distance: " + Math.round(route.getDistance()/1000) + "km Flight Time: "
                + Math.round(route.getMins()+2) + " minutes";
    }
}