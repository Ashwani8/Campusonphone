package com.example.admin.campusonphone;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Member variables
    private RecyclerView mRecyclerView;
    private ArrayList<Place> mPlacesData;
    private PlaceAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initiate the RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView);

        // set the Layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ArrayList that will contain the data/information about the places
        mPlacesData = new ArrayList<>();

        // Initialize the Adapter and set it to the RecyclerView
        mAdapter = new PlaceAdapter(this, mPlacesData);
        mRecyclerView.setAdapter(mAdapter);
        // get the place data
        initializeData();
    }

    /**
     * Initialize the place data from resources.
     */
    private void initializeData() {
        String[] placeList = getResources().getStringArray(R.array.places_name);
        String[] placeInfo = getResources().getStringArray(R.array.places_info);
        TypedArray placeImageResources =
                getResources().obtainTypedArray(R.array.places_images);

        // Clear the existing place data/information to avaoid duplication
        mPlacesData.clear();

        // Create the ArrayList of Places Objects with Names and
        // information about each place
        for(int i = 0; i < placeList.length; i++){
            mPlacesData.add(new Place(placeList[i], placeInfo[i],
                    placeImageResources.getResourceId(i,0)));
        }
        placeImageResources.recycle();
    }
}
