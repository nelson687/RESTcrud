package com.ng.dao;

import com.ng.domain.Album;
import com.ng.domain.Song;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements Dao{
    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Object getById(int id, HttpSession session, String target) {
        ArrayList<Object> object = (ArrayList)session.getAttribute(target);
        return object.get(id);
    }

    @Override
    public ArrayList<Album> getAlbumByName(String name, HttpSession session) {
        ArrayList<Album> results = new ArrayList<Album>();
        for(int i = 0; i < results.size(); i++){
            if(results.get(i).getName().toLowerCase().contains(name)){
                results.add(results.get(i));
            }
        }
        return results;
    }

    @Override
    public ArrayList<Song> getSongsByGenre(String genre, HttpSession session) {
        ArrayList<Song> results = new ArrayList<Song>();
        for(int i = 0; i < results.size(); i++){
            if(results.get(i).getGenre().toLowerCase().contains(genre)){
                results.add(results.get(i));
            }
        }
        return results;
    }

    @Override
    public ArrayList<Song> getSongByName(String name, HttpSession session) {
        ArrayList<Song> results = new ArrayList<Song>();
        for(int i = 0; i < results.size(); i++){
            if(results.get(i).getName().toLowerCase().contains(name)){
                results.add(results.get(i));
            }
        }
        return results;
    }

    @Override
    public void saveSong(Song song, HttpSession session) {
        ArrayList<Song> songs = (ArrayList)session.getAttribute("songs");
        songs.add(song);
        String test = "test";
    }
}
