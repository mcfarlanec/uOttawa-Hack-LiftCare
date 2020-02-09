package ca.uottawa.cmcfa039.liftcare;

import com.google.android.gms.maps.model.LatLng;

public class Route{
    private Hospital startPoint;
    private Hospital endPoint;
    private double distance;
    private double mins;

    public Route(){
        this.startPoint = new Hospital();
        this.endPoint = new Hospital();
        this.distance = 0.0;
        this.mins = 0;
    }

    public Route(Hospital start, Hospital end){
        this.startPoint = start;
        this.endPoint = end;
        this.distance = calculateDistance(start.getLocation(), end.getLocation());
        this.mins = (distance/(72.22222222))/60;
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

    public Hospital getEndPoint() {
        return endPoint;
    }

    public Hospital getStartPoint() {
        return startPoint;
    }

    public double getDistance() {
        return distance;
    }

    public double getMins() {
        return mins;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setEndPoint(Hospital endPoint) {
        this.endPoint = endPoint;
    }

    public void setMins(double mins) {
        this.mins = mins;
    }

    public void setStartPoint(Hospital startPoint) {
        this.startPoint = startPoint;
    }
}