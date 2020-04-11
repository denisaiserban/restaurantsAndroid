package com.example.restaurantsss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.restaurantsss.model.Picture;
import com.example.restaurantsss.model.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private Integer restaurantId;
    private Restaurant restaurant;

    private ViewPager viewPager;
    private TextView restaurantNameTextView;
    private TextView descriptionTextView;
    private TextView addressTextView;
    private TextView openingTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                restaurantId = null;
            } else {

                restaurantId = Integer.parseInt(Objects.requireNonNull(extras.getString("id")));
            }
        } else {
            restaurantId = Integer.parseInt(savedInstanceState.getSerializable("id").toString());
        }

        List<Restaurant> restaurants = RestaurantsSingleton.getInstance().getRestaurantsList();
        for (Restaurant rest : restaurants) {
            if (rest.getRestaurantId().equals(restaurantId)) {
                restaurant = rest;
                break;
            }
        }

        List<Picture> pictures = restaurant.getPictures();
        List<String> sources = new ArrayList<>();
        for (Picture pic : pictures) {
            sources.add(pic.getSource());
        }
        System.out.println(pictures);
        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, sources);
        viewPager.setAdapter(adapter);

        restaurantNameTextView = findViewById(R.id.restaurantNameTextView);
        restaurantNameTextView.setText(restaurant.getRestaurantName());

        descriptionTextView  = findViewById(R.id.description);
        descriptionTextView.setText(restaurant.getDescription());

        addressTextView  = findViewById(R.id.addressTextView);
        addressTextView.setText(restaurant.getAddress());

        openingTimeTextView  = findViewById(R.id.openingTimeTextView);
        openingTimeTextView.setText(restaurant.getOpeningTime()+"-"+restaurant.getClosingTime());

    }


    private void initFragment() {
    }
}
