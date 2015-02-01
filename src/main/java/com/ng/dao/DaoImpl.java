package com.ng.dao;

import com.ng.domain.Album;
import com.ng.domain.Artist;
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
        ArrayList<Object> objects = (ArrayList)context.getAttribute(target);
        if(objects.size() > id){
            return objects.get(id);
        }else{
            return null;
        }


    }

    @Override
    public ArrayList<Album> getAlbumByName(String name, ServletContext context) {
        ArrayList<Album> albums = (ArrayList)context.getAttribute("album");
        ArrayList<Album> results = new ArrayList<Album>();
        for(int i = 0; i < albums.size(); i++){
            if(albums.get(i).getName().toLowerCase().contains(name)){
                results.add(albums.get(i));
            }
        }
        return results;
    }

    @Override
    public ArrayList<Song> getSongsByGenre(String genre, ServletContext context) {
        ArrayList<Song> songs = (ArrayList)context.getAttribute("songs");
        ArrayList<Song> results = new ArrayList<Song>();
        for(int i = 0; i < songs.size(); i++){
            if(songs.get(i).getGenre() != null && songs.get(i).getGenre().toLowerCase().contains(genre)){
                results.add(songs.get(i));
            }
        }
        return results;
    }

    @Override
    public ArrayList<Song> getSongByName(String name, ServletContext context) {
        ArrayList<Song> songs = (ArrayList)context.getAttribute("songs");
        ArrayList<Song> results = new ArrayList<Song>();
        for(int i = 0; i < songs.size(); i++){
            if(songs.get(i).getName()!= null && songs.get(i).getName().toLowerCase().contains(name)){
                results.add(songs.get(i));
            }
        }
        return results;
    }

    @Override
    public ArrayList getSongsByArtist(String artist, ServletContext context) {
        ArrayList<Artist> artists = (ArrayList)context.getAttribute("artists");
        ArrayList<Song> songs = (ArrayList)context.getAttribute("songs");
        ArrayList<Artist> resultsArtists = new ArrayList<Artist>();
        ArrayList<Song> resultsSongs = new ArrayList<Song>();

        for(int i = 0; i < artists.size(); i++){
            if(artists.get(i).getName() !=null && artists.get(i).getName().toLowerCase().contains(artist)){
                resultsArtists.add(artists.get(i));
            }
        }

        if(resultsArtists.size() > 0){
            for(int i = 0; i < artists.size(); i++){
                for(int j = 0; j < songs.size(); j++){
                    if(songs.get(j).getArtist().getName() != null && artists.get(i).getName() != null && songs.get(j).getArtist().getName().toLowerCase().
                            contains(artists.get(i).getName().toLowerCase())){
                        resultsSongs.add(songs.get(j));
                    }
                }
            }
        }
        return resultsSongs;
    }

    @Override
    public void saveSong(Song song, ServletContext context) {
        ArrayList<Song> songs = (ArrayList)context.getAttribute("songs");
        song.setId(songs.size());
        songs.add(song);
    }

    @Override
    public String deleteSong(Song songToDelete, ServletContext context) {
        ArrayList<Song> songs = (ArrayList)context.getAttribute("songs");
        Song song = new Song();
        for(int i = 0; i < songs.size(); i++){
            if(songs.get(i).getName() != null && songs.get(i).getName().toLowerCase().equals(songToDelete.getName().toLowerCase())){
                song = songs.get(i);
                break;
            }
        }
        if(song.getName() != null){
            songs.remove(song);
            return "{remove: success}";
        }else{
            return "{remove: doesn't exist}";
        }
    }

    @Override
    public String updateSong(Song song, ServletContext context) {
        ArrayList<Song> songs = (ArrayList)context.getAttribute("songs");
        Song songToUpdate = new Song();
        int index = 0;
        for(int i = 0; i < songs.size(); i++){
            if(songs.get(i).getId() == song.getId()){
                songToUpdate = songs.get(i);
                index = i;
                break;
            }
        }
        if(songToUpdate.getName() != null){
            if(song.getName() != null){
                songToUpdate.setName(song.getName());
            }
            if(song.getArtist() != null){
                songToUpdate.setArtist(song.getArtist());
            }
            if(song.getAlbum() != null){
                songToUpdate.setAlbum(song.getAlbum());
            }
            if(song.getGenre() != null){
                songToUpdate.setGenre(song.getGenre());
            }
            songs.set(index, songToUpdate);
            return "{update: success}";
        }else{
            return "{update: didn't find}";
        }
    }
}
