package com.ng.dao;

import com.ng.domain.Album;
import com.ng.domain.Song;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements Dao{
    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Object getById(int id, ServletContext context, String target) {
        ArrayList<Object> object = (ArrayList)context.getAttribute(target);
        return object.get(id);
    }

    @Override
    public ArrayList<Album> getAlbumByName(String name, ServletContext context) {
        ArrayList<Album> albums = (ArrayList)context.getAttribute("album");
        ArrayList<Album> results = new ArrayList<Album>();
        for(int i = 0; i < albums.size(); i++){
            if(albums.get(i).getName().toLowerCase().contains(name)){
                results.add(results.get(i));
            }
        }
        return results;
    }

    @Override
    public ArrayList<Song> getSongsByGenre(String genre, ServletContext context) {
        ArrayList<Song> songs = (ArrayList)context.getAttribute("songs");
        ArrayList<Song> results = new ArrayList<Song>();
        for(int i = 0; i < songs.size(); i++){
            if(songs.get(i).getGenre().toLowerCase().contains(genre)){
                results.add(results.get(i));
            }
        }
        return results;
    }

    @Override
    public ArrayList<Song> getSongByName(String name, ServletContext context) {
        ArrayList<Song> songs = (ArrayList)context.getAttribute("songs");
        ArrayList<Song> results = new ArrayList<Song>();
        for(int i = 0; i < songs.size(); i++){
            if(songs.get(i).getName().toLowerCase().contains(name)){
                results.add(results.get(i));
            }
        }
        return results;
    }

    @Override
    public void saveSong(Song song, ServletContext context) {
        ArrayList<Song> songs = (ArrayList)context.getAttribute("songs");
        songs.add(song);
        String test = "test";
    }
}
