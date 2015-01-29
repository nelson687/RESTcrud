package com.ng.dao;

import java.util.List;

public interface Dao<T> {

    List<? extends T> getAll();

    Object getById(String id);

}