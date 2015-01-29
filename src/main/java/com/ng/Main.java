package com.ng;

import com.google.inject.AbstractModule;
import com.ng.dao.Dao;
import com.ng.dao.DaoImpl;

public class Main extends AbstractModule {

    @Override
    protected void configure() {

        bind(Dao.class).to(DaoImpl.class);
    }

}