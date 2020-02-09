package ca.uottawa.cmcfa039.liftcare;

import com.google.android.gms.maps.model.LatLng;

public class Hospital{
	private String name;
	LatLng location;

    public Hospital(){
        this.name = "";
        this.location = new LatLng(0.0, 0.0);
    }

    public Hospital(String name, double lat, double lng){
        this.name = name;
        this.location = new LatLng(lat, lng);
    }

    public Hospital(String name, LatLng latLng){
        this.name = name;
        this.location = latLng;
    }
}