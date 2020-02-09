package ca.uottawa.cmcfa039.liftcare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private GoogleMap mMap;

    Double lat1;
    Double long1;
    Double lat2;
    Double long2;
    String name1;
    String name2;
    CameraUpdate point;


    private Boolean permissionGranted = false;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(lat1, long1));
        markerOptions.title(name1);
        mMap.addMarker(markerOptions);

        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(new LatLng(lat2, long2));
        markerOptions2.title(name2);
        mMap.addMarker(markerOptions2);

        point = CameraUpdateFactory.newLatLngZoom(new LatLng(lat1, long1), 8.0f);
        mMap.moveCamera(point);
        mMap.animateCamera(point);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initMap();

        Intent i = getIntent();
        lat1 = i.getDoubleExtra("latitude1", -1);
        long1 = i.getDoubleExtra("longitude1", -1);
        lat2 = i.getDoubleExtra("latitude2", -1);
        long2 = i.getDoubleExtra("longitude2", -1);
        name1 = i.getStringExtra("hospital1");
        name2 = i.getStringExtra("hospital2");

    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(MapActivity.this);
    }

}
