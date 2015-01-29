package com.ng.domain;

/**
 * Created by root on 29/01/15.
 */
public class Album {

    String name;
    String artist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Album(){

    }
    public Album(String name, String artist){
        this.name = name;
        this.artist = artist;
    }
}
