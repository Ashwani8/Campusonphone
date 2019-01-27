package com.example.admin.campusonphone;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * This adapter class for the RecyclerView, contains the Place Data
 */
public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {
    // member variables
    private ArrayList<Place> mPlacesData;
    private Context mContext;
    private static final String TAG = "PlaceAdapter";
    /** handles playback of all the sound files */
    private static MediaPlayer mMediaPlayer;
    /**
     * This listener gets triggered when the Media Player has completed playing audio file
     */
    MediaPlayer.OnCompletionListener mCompletionListener =
            new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    releaseMediaPlayer();
                }
            };
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
        private TextView mPlaceAudio;
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
            mPlaceAudio = itemView.findViewById(R.id.info_audio);
            // Set the OnClickListener to the entire view.
            // itemView.setOnClickListener(this);

            // set the onClickListener to only text line.
            mPlaceInfo.setOnClickListener(this);
            mPlaceAudio.setOnClickListener(this);
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
            if(view.getId() == mPlaceInfo.getId()) {
                // create intent to launch new activity for details
                Intent detailIntent = new Intent(mContext, DetailActivity.class);
                detailIntent.putExtra("place_name", currentPlace.getPlaceName());
                detailIntent.putExtra("place_info", currentPlace.getInfo());
                detailIntent.putExtra("image_resource", currentPlace.getImageResource());
                detailIntent.putExtra("audio_resource", currentPlace.getAudioResource());
                mContext.startActivity(detailIntent);
            }else if(view.getId() == mPlaceAudio.getId()){
               //Log.d(TAG, "onClick: Place: " + currentPlace + " was clicked ");
                //Toast.makeText(mContext, "Place name clicked",Toast.LENGTH_SHORT ).show();

                // Release the media player just in case it was currently playing different audio
                releaseMediaPlayer();
                // Create and setup the MediaPlayer for the audio associated with the current place
                mMediaPlayer = MediaPlayer.create(mContext, currentPlace.getAudioResource());
                // starts the audio file
                mMediaPlayer.start(); // no need to call prepare

                // Setup a listener on the media player, so that we can stop and release the
                // media player once the sounds has finised playing
                mMediaPlayer.setOnCompletionListener(mCompletionListener);

            }
        }
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
    // TODO stop media player when activity is stopped/paused

}
