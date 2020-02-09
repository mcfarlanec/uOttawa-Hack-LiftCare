package ca.uottawa.cmcfa039.liftcare;

import com.google.android.gms.maps.model.LatLng;

public class Route{
    private LatLng startPoint;
    private LatLng endPoint;
    private double distance;
    private double mins;

    public Route(){
        this.startPoint = new LatLng(0.0, 0.0);
        this.endPoint = new LatLng(0.0, 0.0);
        this.distance = 0.0;
        this.mins = 0;
    }

    public Route(LatLng start, LatLng end){
        this.startPoint = start;
        this.endPoint = end;
        this.distance = calculateDistance(start, end);
        this.mins = (distance/(72.22222222))/60;
    }

    public LatLng getEndPoint() {
        return endPoint;
    }

    public LatLng getStartPoint() {
        return startPoint;
    }

    private double calculateDistance(LatLng start, LatLng end){
        final int R = 6371;
        double latDist = Math.toRadians(end.latitude - start.latitude);
        double lngDist = Math.toRadians(end.longitude - start.longitude);
        double a = Math.sin(latDist / 2) * Math.sin(latDist / 2)
                + Math.cos(Math.toRadians(start.latitude)) * Math.cos(Math.toRadians(end.latitude))
                * Math.sin(lngDist / 2) * Math.sin(lngDist / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        distance = Math.pow(distance, 2);
        return Math.sqrt(distance);
    }
}