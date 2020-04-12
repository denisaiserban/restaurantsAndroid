package com.example.restaurantsss;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.restaurantsss.model.Restaurant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Objects;

public class RestaurantDetailsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Integer restaurantId;
    private Restaurant restaurant;

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int PERMISSION_REQUEST_CODE = 123;

    private boolean mPermisionGranted = false;
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        getExtrasFromIntent(savedInstanceState);
        openFragment();
    }

    private void openFragment() {
        DetailsFragment fragment = new DetailsFragment(restaurant);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.details_fragment, fragment);
        fragmentTransaction.commit();
    }

    private void getExtrasFromIntent(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                restaurantId = null;
            } else {

                restaurantId = Integer.parseInt(Objects.requireNonNull(extras.getString("id")));
            }
        } else {
            restaurantId = Integer.parseInt(Objects.requireNonNull(savedInstanceState.getSerializable("id")).toString());
        }

        filterRestaurantsById();

    }

    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mPermisionGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermisionGranted = false;
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++)
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mPermisionGranted = false;
                            return;
                        }
                    mPermisionGranted = true;
                    initMap();
                }


            }
        }
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(RestaurantDetailsActivity.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(restaurant!=null){
            Double longitute = restaurant.getAddress().getLongitude();
            Double latitude = restaurant.getAddress().getLatitude();
            LatLng coord = new LatLng(longitute, latitude);

            mMap.addMarker(new MarkerOptions().position(coord).title(restaurant.getRestaurantName()));
            // Add a marker in Sydney, Australia, and move the camera.
            mMap.moveCamera(CameraUpdateFactory.newLatLng(coord));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(coord)
                    .zoom(17).build();
            //Zoom in and animate the camera.
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

    }

    private void filterRestaurantsById() {
        List<Restaurant> restaurants = RestaurantsSingleton.getInstance().getRestaurantsList();

        for (Restaurant rest : restaurants) {
            if (rest.getRestaurantId().equals(restaurantId)) {
                restaurant = rest;
                break;
            }
        }

        getLocationPermission();
    }
}
