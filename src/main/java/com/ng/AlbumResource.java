package com.ng;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ng.dao.Dao;
import com.ng.domain.Album;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.ArrayList;


@Path("album")
public class AlbumResource {

    HttpSession session;
    Injector injector = Guice.createInjector(new Main());
    Dao<String> dao = injector.getInstance( Dao.class );

    public AlbumResource(@Context HttpServletRequest req) {
        session= req.getSession(true);
    }

    @GET @Produces("application/json")
    @Path("/search")
    public Album getById() {
        return (Album)dao.getById(1, session);
    }
}
