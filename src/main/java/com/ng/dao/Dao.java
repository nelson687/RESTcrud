package com.ng.dao;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface Dao<T> {

    List<? extends T> getAll();

    Object getById(int id, HttpSession session);

}