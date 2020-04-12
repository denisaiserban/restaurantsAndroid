package com.example.restaurantsss.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Restaurant {
    private Integer restaurantId;
    private String restaurantName;
    private String description;
    private String openingTime;
    private String closingTime;
    private Address address;
    private ArrayList<Picture> pictures = new ArrayList<>();


    public Restaurant(Integer restaurantId, String restaurantName, String description, String openingTime, String closingTime, Address address, ArrayList<Picture> pictures) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.description = description;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.address = address;
        this.pictures = pictures;
    }

    public Restaurant() {
    }

    public Restaurant fromJSON(JSONObject userJSON) throws JSONException {
        Integer id = userJSON.getInt("restaurantId");
        String restaurantName = userJSON.getString("restaurantName");
        String description = userJSON.getString("description");
        String openingTime = userJSON.getString("openingTime");
        String closingTime = userJSON.getString("closingTime");

        Address address = new Address().fromJSON(userJSON.getJSONObject("address"));

        ArrayList<Picture> picturesList = new ArrayList<>();

        JSONArray picturesArray = userJSON.getJSONArray("pictures");
        for (int index = 0; index < picturesArray.length(); index++) {
            Picture picture = new Picture().fromJSON(picturesArray.getJSONObject(index));
            picturesList.add(picture);
        }

        Restaurant restaurant = new Restaurant(id, restaurantName, description, openingTime, closingTime, address, picturesList);

        return restaurant;
    }

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
