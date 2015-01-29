package com.ng.dao;

import com.ng.domain.Album;

import java.util.List;

public class DaoImpl implements Dao{
    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Object getById(String id) {
        Album album = new Album("NelsonAlbum", "Gallardo");
        return album;
    }
}
