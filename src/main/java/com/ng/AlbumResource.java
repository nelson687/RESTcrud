package com.ng;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ng.dao.Dao;
import com.ng.domain.Album;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;


@Path("album")
public class AlbumResource {

    Injector injector = Guice.createInjector(new Main());
    Dao<String> dao = injector.getInstance( Dao.class );

    @GET @Produces("application/json")
    @Path("/get/")
    public Album getById(@QueryParam( "id" ) int id, @Context ServletContext contex) {
        return (Album)dao.getById(id, contex, "albums");
    }

    @GET @Produces("application/json")
    @Path("/search/")
    public ArrayList<Album> getByName(@QueryParam( "name" ) String name, @Context ServletContext contex) {
        return dao.getAlbumByName(name, contex);
    }

}
