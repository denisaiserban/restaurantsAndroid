package com.example.restaurantsss;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.restaurantsss.adapters.RecyclerViewMainAdapter;
import com.example.restaurantsss.model.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

public class RestaurantsFragment extends Fragment {

    private final static String URL = "https://gist.githubusercontent.com/TeodoraIoanaJipa/975ae5606c72b7e26bb7baaa3821e966/raw/0b6d7ebb24251d142eb75102aa7c42a582810591/restaurant.json";
    private ArrayList<Restaurant> restaurantsList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.restaurants_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getRestaurants();
    }

    private void initializeRecyclerView() {
        RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.restaurants_recycler_view);
//        DividerItemDecoration itemDecor = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(itemDecor);
        RecyclerViewMainAdapter recyclerViewAdapter = new RecyclerViewMainAdapter(getView().getContext(), restaurantsList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
    }

    private void getRestaurants() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                for (int index = 0; index < response.length(); index++) {
                    try {
                        Restaurant restaurant = new Restaurant().fromJSON(response.getJSONObject(index));
                        restaurantsList.add(restaurant);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                RestaurantsSingleton.getInstance().setRestaurantsList(restaurantsList);
                initializeRecyclerView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Volley error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        VolleySingleton.getInstance(Objects.requireNonNull(getView()).getContext()).addToRequestQueue(jsonArrayRequest);
    }
}
