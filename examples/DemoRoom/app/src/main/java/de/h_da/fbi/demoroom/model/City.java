package de.h_da.fbi.demoroom.model;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class City implements Serializable {


    //geht auch mit lokalisierbaren Strings, vgl. https://howtodoinjava.com/java/enum/java-enum-string-example/
    public enum Continent {
        Any,
        Asia,
        Europe,
        America
    }

    @PrimaryKey(autoGenerate = true)
    private long uid;

    private String name;
    private long inhabitants;
    private String attractions;
    private String imageUri;
    private int continent;

    @Ignore
    public City(String name, long inhabitants, String attractions, String imageUri, Continent continent) {
        setName(name);
        setInhabitants(inhabitants);
        setAttractions(attractions);
        setImageUri(imageUri);
        setContinentAsEnumField(continent);
    }

    public City() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(long inhabitants) {
        this.inhabitants = inhabitants;
    }

    public String getAttractions() {
        return attractions;
    }

    public void setAttractions(String attractions) {
        this.attractions = attractions;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Continent getContinentAsEnumField() {
        return Continent.values()[continent];
    }

    public int getContinent(){
        return continent;
    }
    public void setContinent(int continent){
        this.continent = continent;
    }
    public void setContinentAsEnumField(Continent continent) {
        this.continent = continent.ordinal();
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }


}
