package com.example.restaurantsss;

import com.example.restaurantsss.model.Restaurant;

import java.util.ArrayList;

public class RestaurantsSingleton {

    private ArrayList<Restaurant> restaurantsList = new ArrayList<>();

    private static RestaurantsSingleton ourInstance = new RestaurantsSingleton();

    public static RestaurantsSingleton getInstance() {
        if(ourInstance == null) {
            ourInstance = new RestaurantsSingleton();
        }
        return ourInstance;
    }

    private RestaurantsSingleton() {
    }

    public void setRestaurantsList(ArrayList<Restaurant> restaurantsList) {
        this.restaurantsList = restaurantsList;
    }

    public ArrayList<Restaurant> getRestaurantsList() {
        return restaurantsList;
    }
}
