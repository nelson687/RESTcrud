package com.ng.dao;

import com.ng.domain.Song;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public interface Dao<T> {

    List<? extends T> getAll();

    Object getById(int id, HttpSession session, String target);

    ArrayList getAlbumByName(String name, HttpSession session);

    ArrayList getSongsByGenre(String name, HttpSession session);

    ArrayList getSongByName(String name, HttpSession session);

    void saveSong(Song song, HttpSession session);


    //ArrayList getSongByArtist(String name, HttpSession session);
}