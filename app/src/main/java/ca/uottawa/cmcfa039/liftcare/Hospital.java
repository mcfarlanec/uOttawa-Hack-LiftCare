package ca.uottawa.cmcfa039.liftcare;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

public class Hospital{
	private String name;
	private Double latitude;
    private Double longitude;

    public Hospital(){
        this.name = "";
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    public Hospital(String name, double lat, double lng){
        this.name = name;
        this.latitude = lat;
        this.longitude = lng;
    }


    public LatLng getLocation() {
        return new LatLng(latitude, longitude);
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return (name + " Latitude: " + latitude + " Longitude: " + longitude);
    }
}