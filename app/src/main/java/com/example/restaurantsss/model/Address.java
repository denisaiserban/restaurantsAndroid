package com.example.restaurantsss.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Address {
    private Double longitude;
    private Double latitude;
    private String address;

    public Address fromJSON(JSONObject addressJSON) throws JSONException {
        Double longitude = addressJSON.getDouble("long");
        Double lat = addressJSON.getDouble("lat");
        String address1 = addressJSON.getString("address");

        Address address = new Address(longitude,lat,address1);
        return address;
    }

    public Address() {
    }

    public Address(Double longitude, Double latitude, String address) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }
}
