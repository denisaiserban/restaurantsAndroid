package com.example.restaurantsss;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class RestaurantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(RestaurantsActivity.this);
        if(available == ConnectionResult.SUCCESS){
            Log.d("RestaurantsActivity","Google Play Services working");
        } else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(RestaurantsActivity.this,
                    available, 9001);
            dialog.show();
        } else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
    }
}
