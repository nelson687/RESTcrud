package com.ng.dao;

import com.ng.domain.Album;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements Dao{
    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Object getById(int id, HttpSession session) {
        ArrayList<Album> albums = (ArrayList)session.getAttribute("albums");
        return albums.get(id);
    }
}
