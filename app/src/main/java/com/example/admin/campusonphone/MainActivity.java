package com.example.admin.campusonphone;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    //Member variables
    private RecyclerView mRecyclerView;
    private ArrayList<Place> mPlacesData;
    private PlaceAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the column count mobile in landscape/portrait,
        // runtime will take care of which inter to pick
        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        // Initiate the RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView);

        // set the Layout manager
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        // Initialize the ArrayList that will contain the data/information about the places
        mPlacesData = new ArrayList<>();

        // Initialize the Adapter and set it to the RecyclerView
        mAdapter = new PlaceAdapter(this, mPlacesData);
        mRecyclerView.setAdapter(mAdapter);
        // get the place data
        initializeData();
        // if there are more than 1 column disable swipe to dismiss
        int swipeDirs;
        if(gridColumnCount > 1){
            swipeDirs = 0;
        }else {
            swipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }

        // Helper class for creating swipe to dismiss and drag and drop functionality.
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT
                        |ItemTouchHelper.RIGHT
                        |ItemTouchHelper.DOWN
                        | ItemTouchHelper.UP,
                swipeDirs) {
            /**
             * Defines the drag and drop functionality.
             *
             * @param recyclerView The RecyclerView that contains the list items
             * @param viewHolder The PlaceViewHolder that is being moved
             * @param target The PlaceViewHolder that you are switching the
             *               original one with.
             * @return true if the item was moved, false otherwise
             */

            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                // get the from and to position
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                // swap the items and notify the adapter
                Collections.swap(mPlacesData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }
            /**
             * Defines the swipe to dismiss functionality.
             *
             * @param viewHolder The view holder being swiped.
             * @param direction The direction it is swiped in.
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                mPlacesData.remove(viewHolder.getAdapterPosition());

                // to animate the deletion properly, we must call notification
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        // attach functionality to RecyclerView
        helper.attachToRecyclerView(mRecyclerView);
    }

    /**
     * Initialize the place data from resources.
     */
    private void initializeData() {
        String[] placeList = getResources().getStringArray(R.array.places_name);
        String[] placeInfo = getResources().getStringArray(R.array.places_detail_info);
        TypedArray placeImageResources =
                getResources().obtainTypedArray(R.array.places_images);
        TypedArray placeAudioResources =
                getResources().obtainTypedArray(R.array.places_audio);
        // Clear the existing place data/information to avaoid duplication
        mPlacesData.clear();

        // Create the ArrayList of Places Objects with Names and
        // information about each place
        for (int i = 0; i < placeList.length; i++) {
            mPlacesData.add(new Place(placeList[i], placeInfo[i],
                    placeImageResources.getResourceId(i, 0),
                    placeAudioResources.getResourceId(i,0)));
        }
        placeImageResources.recycle();
        placeAudioResources.recycle();
        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }

    // FAB we are just using onClick method to reset data
    public void resetPlaces(View view) {
        initializeData();
    }
}
