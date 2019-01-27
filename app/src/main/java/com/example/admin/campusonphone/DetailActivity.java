package com.example.admin.campusonphone;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
private static MediaPlayer mMediaPlayer;
int audio;
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
    private static final String TAG = "DetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activty);
        // create views
        TextView placesName =  findViewById(R.id.place_name_detail);
        TextView placeInfo = findViewById(R.id.info_subTitle_detail);
        ImageView placesImage =  findViewById(R.id.placesImage_detail);
        TextView placesAudio = findViewById(R.id.info_audio_detail);
        // set the view values from key pair of the intent
        placesName.setText(getIntent().getStringExtra("place_name"));
        placeInfo.setText(getIntent().getStringExtra("place_info"));
        audio = getIntent().getIntExtra("audio_resource", 0);
        Log.d(TAG, "onCreate: info stored here" + audio);
        // set onclick listener on placesAudio view
        placesAudio.setOnClickListener(this);

        // we are using Glide library to handle images
        Glide.with(this).load(getIntent()
                .getIntExtra("image_resource", 0)).into(placesImage);

    }

    @Override
    public void onClick(View v) {
        // Release the media player just in case it was currently playing different audio
        releaseMediaPlayer();
        // Create and setup the MediaPlayer for the audio associated with the current place
        mMediaPlayer = MediaPlayer.create(this, audio);
        // starts the audio file
        mMediaPlayer.start(); // no need to call prepare

        // Setup a listener on the media player, so that we can stop and release the
        // media player once the sounds has finised playing
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
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

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
