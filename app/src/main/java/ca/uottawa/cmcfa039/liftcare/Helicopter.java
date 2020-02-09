package ca.uottawa.cmcfa039.liftcare;

//hypothetically we would track helicopter's current location, but for now just using destinations
//ideally helicopter would confirm completed route, right now we'll use time

import com.google.android.gms.maps.model.LatLng;

public class Helicopter{
    private final int SPEED = 260;
    private Patient patient;
    private LatLng lastLocation;
    private Route route;
    private boolean onRoute;

    public Helicopter(){
        this.patient = new Patient();
        this.lastLocation = new LatLng(45.399311,-75.648460);
        this.route = new Route();
        this.onRoute = false;
    }

    public Helicopter(Patient patient, boolean onRoute){
        this.patient = new Patient();
        this.lastLocation = new LatLng(45.399311,-75.648460);
        this.route = new Route();
        this.onRoute = false;
    }

    public void setRoute(Route route){
        lastLocation = this.route.getEndPoint();
        this.route = route;
    }
    
    public boolean getOnRoute() {
        return onRoute;
    }

    //send location

	//request next patient

	//delivery complete

	//available

	//accept patient

	//public get location function

	//has a route
}