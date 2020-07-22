package de.h_da.fbi.demorecyclerview.model;

public class City {
    private String imageUrl;
    private String name;
    public City(String url, String name){
        this.imageUrl = url;
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
