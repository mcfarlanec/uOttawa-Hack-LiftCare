package ca.uottawa.cmcfa039.liftcare;

//hypothetically we would track helicopter's current location, but for now just using destinations
//ideally helicopter would confirm completed route, right now we'll use time

public class Helicopter{
    private final int SPEED = 260;
    //current location is last destination
    //maybe current patient
    private Route route;
    private boolean onRoute;

    public Helicopter(){
        //default location
        this.onRoute = false;
    }

    public Helicopter(boolean onRoute){
        //current location
        this.onRoute = onRoute;
    }

    public void setRoute(Route route){

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