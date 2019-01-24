package com.example.admin.campusonphone;

/**
 * Data model for each row of the Recycler View
 */
public class Place {
    // Member variables representing the Name and information about the Place
    private String placeName;
    private  String info;

    /**
     * Constructor for the Place data model
     * @param placeName
     * @param info
     */
    public Place(String placeName, String info) {
        this.placeName = placeName;
        this.info = info;
    }

    /**
     * Gets the Name of the place
     * @return the name of the place
     */
    public String getPlaceName() {
        return placeName;
    }

    /**
     * gets the information about the place
     * @return information about the place
     */
    public String getInfo() {
        return info;
    }
}
