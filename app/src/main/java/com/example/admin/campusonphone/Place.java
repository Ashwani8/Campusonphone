package com.example.admin.campusonphone;

/**
 * Data model for each row of the Recycler View
 */
public class Place {
    // Member variables representing the Name and information about the Place
    private String placeName;
    private  String info;
    private final int imageResource; // use int as we are accessing images from drawable
    /**
     * Constructor for the Place data model
     * @param placeName the name of the place
     * @param info information about the place
     * @param imageResource image of the place
     */
     Place(String placeName, String info, int imageResource) {
        this.placeName = placeName;
        this.info = info;
        this.imageResource = imageResource;
    }

    /**
     * Gets the Name of the place
     * @return the name of the place
     */
     String getPlaceName() {
        return placeName;
    }

    /**
     * gets the information about the place
     * @return information about the place
     */
     String getInfo() {
        return info;
    }

    /**
     * gets the image of the place
     * @return the image of the place
     */
    int getImageResource(){return imageResource;}

}
