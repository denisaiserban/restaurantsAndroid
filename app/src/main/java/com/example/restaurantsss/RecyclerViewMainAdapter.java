package com.example.restaurantsss;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantsss.model.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewMainAdapter extends RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolder> {

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
//    private ToDoListFragment fragment1;

    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private Context mContext;

    public RecyclerViewMainAdapter(Context context, ArrayList<Restaurant> restaurants) {
        this.restaurants.addAll(restaurants);
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Restaurant restaurant = restaurants.get(position);
        Picasso.get().load(restaurant.getPictures().get(0).getSource()).into(holder.restaurantImageView);
        holder.restaurantNameTextView.setText(restaurant.getRestaurantName());
        holder.addressTextView.setText(restaurant.getAddress());
        holder.openingTimeTextView.setText(restaurant.getOpeningTime() + " - " + restaurant.getClosingTime());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fragment1 = new ToDoListFragment(user.getId());
//                fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.to_do_list_fragment, fragment1);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
                startDetailsActivity(restaurant.getRestaurantId());
            }
        });
    }

    public void startDetailsActivity(Integer id) {
        Intent intent = new Intent(mContext, RestaurantDetailsActivity.class);
        intent.putExtra("id", id.toString());
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return this.restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //        TextView idTextView;
        TextView restaurantNameTextView;
        ImageView restaurantImageView;
        //        TextView closingTimeTextView;
        TextView openingTimeTextView;
        TextView addressTextView;
        //        TextView websiteTextView;
        CardView parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantImageView = itemView.findViewById(R.id.rImageView);
            restaurantNameTextView = itemView.findViewById(R.id.nameTextView);
//            closingTimeTextView = itemView.findViewById(R.id.closingTimeTextView);
            openingTimeTextView = itemView.findViewById(R.id.openingTimeTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
//            websiteTextView = itemView.findViewById(R.id.websiteTextView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
