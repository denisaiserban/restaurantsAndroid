package com.example.restaurantsss;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.restaurantsss.adapters.ViewPagerAdapter;
import com.example.restaurantsss.model.Picture;
import com.example.restaurantsss.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DetailsFragment extends Fragment {

    private Integer restaurantId;
    private Restaurant restaurant;

    private ViewPager viewPager;
    private TextView restaurantNameTextView;
    private TextView descriptionTextView;
    private TextView addressTextView;
    private TextView openingTimeTextView;
    private Button reserveButton;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_fragment, container, false);

    }

    public DetailsFragment(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeTextViews();
        List<String> sources = getRestaurantsPictures();

        viewPager = getView().findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getContext(), sources);
        viewPager.setAdapter(adapter);

        setTextViewsContent();
    }

    private void setTextViewsContent() {
        restaurantNameTextView.setText(restaurant.getRestaurantName());
        descriptionTextView.setText(restaurant.getDescription());
        addressTextView.setText(restaurant.getAddress().getAddress());
        openingTimeTextView.setText(String.format("%s-%s", restaurant.getOpeningTime(),
                restaurant.getClosingTime()));
        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityIntent = new Intent(getContext(), ReservationActivity.class);
                activityIntent.putExtra("name",restaurant.getRestaurantName());
                startActivity(activityIntent);
            }
        });
    }

    private List<String> getRestaurantsPictures() {
        List<Picture> pictures = restaurant.getPictures();
        List<String> sources = new ArrayList<>();
        for (Picture pic : pictures) {
            sources.add(pic.getSource());
        }
        return sources;
    }


    private void initializeTextViews() {
        restaurantNameTextView = getView().findViewById(R.id.restaurantNameTextView);
        descriptionTextView = getView().findViewById(R.id.description);
        addressTextView = getView().findViewById(R.id.addressTextView);
        openingTimeTextView = getView().findViewById(R.id.openingTimeTextView);
        reserveButton = getView().findViewById(R.id.reserveButton);

    }


}
