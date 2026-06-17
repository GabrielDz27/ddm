package com.example.geolocalizacao;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity {


    LocationManager locationManager;

    MapView mapView;

    Marker markerPosicaoAtual;

    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView);

        mapInit();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (checkLocationPermission()) {
            locationManager.requestLocationUpdates(
                    locationManager.GPS_PROVIDER,
                    0,0,
                    location -> {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        tv.setText("Latitude: "+ latitude + "\nLongitude: " + longitude);
                        showLocationOnMap(location);
                    });
        }


    }

    public void mapInit() {
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(16.0);
    }

    public void showLocationOnMap(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        GeoPoint myLocation = new GeoPoint(latitude, longitude);

        mapView.getController().setCenter(myLocation);
        if (markerPosicaoAtual == null) {
            markerPosicaoAtual = new Marker(mapView);
        }
        markerPosicaoAtual.setPosition(myLocation);
        markerPosicaoAtual.setTitle("Minha localização");

        mapView.getOverlays().clear();
        mapView.getOverlays().add(markerPosicaoAtual);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            } else {
                checkLocationPermission();
            }
        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},1);
            return false;
        }
        return true;
    }
}