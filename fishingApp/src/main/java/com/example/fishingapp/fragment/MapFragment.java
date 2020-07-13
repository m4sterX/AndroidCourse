package com.example.fishingapp.fragment;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.fishingapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DirectionsResult;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static androidx.constraintlayout.widget.Constraints.TAG;
import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

public class MapFragment extends Fragment {
    private static final int LOCATION_REQUEST_CODE = 10000;
    private GoogleMap googleMap;
    private MarkerOptions markerOptions;
    private GeoApiContext mGeoApiContext = null;
    private double lat;
    private double lon;
    private LatLng destLatLng;
    private LatLng mLatLng;
    private MarkerOptions mMarkerOptions;
    private FusedLocationProviderClient client;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.map_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lat = getArguments().getDouble("lat");
        lon = getArguments().getDouble("lon");
        destLatLng = new LatLng(lat,lon);
        mMarkerOptions = new MarkerOptions().position(destLatLng).title("here");
        client = LocationServices.getFusedLocationProviderClient(view.getContext());
        initMap();

    }
    private void initMap() {
        FragmentManager childFragmentManager = getChildFragmentManager();
        SupportMapFragment supportMapFragment = (SupportMapFragment) childFragmentManager.findFragmentById(R.id.mapFragment_support);
        if(supportMapFragment != null) {
            supportMapFragment.getMapAsync(this::onMapReady);
            if(mGeoApiContext == null) {
                mGeoApiContext = new GeoApiContext.Builder()
                        .apiKey("AIzaSyCqSzlZIvOubur2-vlQkuoAK0Gn7KEMykA")
                        .build();
            }
        }
    }

    private void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.addMarker(mMarkerOptions);
        checkSelfPermission();
    }

    private void checkSelfPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), ACCESS_FINE_LOCATION) == PERMISSION_GRANTED) {
            enableLocation();
        } else {
            requestPermissions(new String[]{ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        }
    }
    private void enableLocation() {
        if(googleMap != null) {
            googleMap.setMyLocationEnabled(true);
            getCurrentLocation();
        }
    }
    private void getCurrentLocation() {
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                    calculateDirections(mMarkerOptions);
                } else {
                    Toast.makeText(getContext(), "NULL" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == LOCATION_REQUEST_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                enableLocation();
                getCurrentLocation();
            }
        }
    }

    private void calculateDirections(MarkerOptions marker){
        Log.d(TAG, "calculateDirections: calculating directions.");

        com.google.maps.model.LatLng destination = new com.google.maps.model.LatLng(
                marker.getPosition().latitude,
                marker.getPosition().longitude
        );
        DirectionsApiRequest directions = new DirectionsApiRequest(mGeoApiContext);

        directions.alternatives(true);
        directions.origin(
                new com.google.maps.model.LatLng(mLatLng.latitude, mLatLng.longitude)
        );
        Log.d(TAG, "calculateDirections: destination: " + destination.toString());
        directions.destination(destination).setCallback(new PendingResult.Callback<DirectionsResult>() {
            @Override
            public void onResult(DirectionsResult result) {
                Log.d(TAG, "calculateDirections: routes: " + result.routes[0].toString());
                Log.d(TAG, "calculateDirections: duration: " + result.routes[0].legs[0].duration);
                Log.d(TAG, "calculateDirections: distance: " + result.routes[0].legs[0].distance);
                Log.d(TAG, "calculateDirections: geocodedWayPoints: " + result.geocodedWaypoints[0].toString());
            }

            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG, "calculateDirections: Failed to get directions: " + e.getMessage() );
            }
        });
    }
//    private GeoApiContext getGeoContext() {
//        GeoApiContext geoApiContext = new GeoApiContext();
//        return geoApiContext.setQueryRateLimit(3)
//                .setApiKey("AIzaSyBdksrNTpZCvSBvIhFFMogQZOOEcukxUBg")
//                .setConnectTimeout(1, TimeUnit.SECONDS)
//                .setReadTimeout(1, TimeUnit.SECONDS)
//                .setWriteTimeout(1, TimeUnit.SECONDS);
//    }
//    private void addMarkersToMap(DirectionsResult results, GoogleMap mMap) {
//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(results.routes[0].legs[0].startLocation.lat,results.routes[0].legs[0].startLocation.lng))
//                .title(results.routes[0].legs[0].startAddress));
//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(results.routes[0].legs[0].endLocation.lat,results.routes[0].legs[0].endLocation.lng))
//                .title(results.routes[0].legs[0].startAddress).snippet(getEndLocationTitle(results)));
//    }
//    private String getEndLocationTitle(DirectionsResult results){
//        return  "Time :"+
//                results.routes[0].legs[0].duration.humanReadable + " Distance :" +
//                results.routes[0].legs[0].distance.humanReadable;
//    }
//    private void addPolyline(DirectionsResult results, GoogleMap mMap) {
//        String decodedPath = results.routes[0].overviewPolyline.getEncodedPath();
//        mMap.addPolyline(new PolylineOptions().add(decodedPath));
//    }
}
