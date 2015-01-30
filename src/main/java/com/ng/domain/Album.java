package com.ng.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

public class Album {

    private int id;
    private String name;
    private Artist artist;
    private ArrayList<Song> songs;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public void addSong(Song song){
        this.songs.add(song);
    }

    public ArrayList<Song> getSong() {
        return songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album(){

    }
    public Album(String name, Artist artist){
        this.name = name;
        this.artist = artist;
    }
}
