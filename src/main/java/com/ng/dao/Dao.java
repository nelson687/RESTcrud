package com.ng.dao;

import com.ng.domain.Song;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public interface Dao<T> {

    List<? extends T> getAll();

    Object getById(int id, ServletContext context, String target);

    ArrayList getAlbumByName(String name, ServletContext context);

    ArrayList getSongsByGenre(String name, ServletContext context);

    ArrayList getSongByName(String name, ServletContext context);

    ArrayList getSongsByArtist(String name, ServletContext context);

    void saveSong(Song song, ServletContext context);

    String deleteSong(Song song, ServletContext context);

    String updateSong(Song song, ServletContext context);

}