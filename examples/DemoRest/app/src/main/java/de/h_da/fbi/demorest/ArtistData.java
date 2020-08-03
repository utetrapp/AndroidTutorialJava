package de.h_da.fbi.demorest;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArtistData implements Serializable {
    private String name;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("facebook_page_url")
    private String facebookUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }
}
