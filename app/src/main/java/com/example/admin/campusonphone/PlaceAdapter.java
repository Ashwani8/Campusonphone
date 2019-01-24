package com.example.admin.campusonphone;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * This adapter class for the RecyclerView, contains the Place Data
 */
public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {
    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder placeViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    // Create the ViewHolder class that represents each row data for the RecyclerView
    // adapter and constructor for it

    /**
     * ViewHolder class that represents each row data for the RecyclerView
     */
     class PlaceViewHolder extends RecyclerView.ViewHolder{

        // member variables for the TextView
        private TextView mPlaceName;
        private  TextView mPlaceInfo;

        /**
         * Constructor for the ViewHolder, used in onCreteViewHolder
         * @param itemView The rootview of the place_list_item
         */
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);

            // initialize the textView
            mPlaceName = itemView.findViewById(R.id.place_name);
            mPlaceInfo = itemView.findViewById(R.id.info_subTitle); // some information
        }

    }
}
