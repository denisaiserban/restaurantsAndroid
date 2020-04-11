package com.example.restaurantsss.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Picture {
    private Integer id;
    private String source;

    public Picture fromJSON(JSONObject pictureJSON) throws JSONException {
        Integer id = pictureJSON.getInt("id");
        String source = pictureJSON.getString("link");

        return new Picture(id, source);
    }

    public Picture(Integer id, String source) {
        this.id = id;
        this.source = source;
    }

    public Picture() {
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
