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
        return this.getAlbumMatches((ArrayList)session.getAttribute("albums"), name.toLowerCase());
    }

    @Override
    public ArrayList<Album> getAlbumByGenre(String genre, HttpSession session) {
        return this.getAlbumMatches((ArrayList) session.getAttribute("albums"), genre.toLowerCase());
    }

    @Override
    public ArrayList<Song> getSongByName(String name, HttpSession session) {
        return this.getSongMatches((ArrayList) session.getAttribute("songs"), name.toLowerCase());
    }

    private ArrayList<Album> getAlbumMatches(ArrayList<Album> albums, String genre){
        ArrayList<Album> results = new ArrayList<Album>();
        for(int i = 0; i < albums.size(); i++){
            if(albums.get(i).getGenre().toLowerCase().contains(genre)){
                results.add(albums.get(i));
            }
        }
        return results;
    }

    private ArrayList<Song> getSongMatches(ArrayList<Song> songs, String val){
        ArrayList<Song> results = new ArrayList<Song>();
        for(int i = 0; i < songs.size(); i++){
            if(songs.get(i).getName().toLowerCase().contains(val)){
                results.add(songs.get(i));
            }
        }
        return results;
    }
}
