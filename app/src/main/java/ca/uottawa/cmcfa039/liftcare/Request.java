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
        return "The patient," + patient.getName() + " is a severity of " + patient.getSeverity() + " and is " +
                patient.getAge() + " years of age. They are being moved from " + route.getStartHospital().getName() + " to "
                + route.getEndHospital().getName() + ". This trip is " + route.getDistance()/1000 + "kilometers and will take "
                + route.getMins() + " minutes.";
    }
}