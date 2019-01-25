package com.example.admin.campusonphone;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * This adapter class for the RecyclerView, contains the Place Data
 */
public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {
    // member variables
    private ArrayList<Place> mPlacesData;
    private Context mContext;

    /**
     * Constructor that passes in the Places data and the context
     * @param mContext context of the application
     * @param mPlacesData ArrayList containing the place data/information
     */
    public PlaceAdapter(Context mContext, ArrayList<Place> mPlacesData) {
        this.mPlacesData = mPlacesData;
        this.mContext = mContext;
    }

    /**
     * Required methods for creating the viewholder objects
     * @param parent The ViewGroup into which the new View will be added
     *                after it is bound to an adapter position.
     * @param viewType he view type of the new View.
     * @return The newly created ViewHolder
     */
    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new PlaceViewHolder(LayoutInflater.from(mContext)
       .inflate(R.layout.place_list_item, parent, false));
    }
    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder The AttractionViewHolder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        // get current Place
        Place currentPlace = mPlacesData.get(position);
        // Populate the text views with information/data
        holder.bindTo(currentPlace);
    }

    /**
     * Required method for determining the size of the data set
     * @return Size of the data set
     */
    @Override
    public int getItemCount() {
        return mPlacesData.size();
    }


    // Create the ViewHolder class that represents each row data for the RecyclerView
    // adapter and constructor for it

    /**
     * ViewHolder class that represents each row data for the RecyclerView
     */
     class PlaceViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{

        // member variables for the TextView
        private TextView mPlaceName;
        private  TextView mPlaceInfo;
        private ImageView mPlaceImage;

        /**
         * Constructor for the ViewHolder, used in onCreteViewHolder
         * @param itemView The rootview of the place_list_item
         */
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);

            // initialize the textView
            mPlaceName = itemView.findViewById(R.id.place_name);
            mPlaceInfo = itemView.findViewById(R.id.info_subTitle); // some information
            mPlaceImage = itemView.findViewById(R.id.placesImage);

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }
        void bindTo(Place currentPlace){
            // Populate the textView with data/info
            mPlaceName.setText(currentPlace.getPlaceName());
            mPlaceInfo.setText(currentPlace.getInfo());
            Glide.with(mContext).load(currentPlace.getImageResource()).into(mPlaceImage);
        }

        // implements onClick on entire itemView (set earlier)
        @Override
        public void onClick(View view) {
            // get the sport object for the item that was clicked
            Place currentPlace = mPlacesData.get(getAdapterPosition());

            // create intent to launch new activity for details
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("place_name", currentPlace.getPlaceName());
            detailIntent.putExtra("place_info", currentPlace.getInfo());
            detailIntent.putExtra("image_resource", currentPlace.getImageResource());
            mContext.startActivity(detailIntent);
        }
    }
}
