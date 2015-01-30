package com.ng;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ng.dao.Dao;
import com.ng.domain.Album;
import com.ng.domain.Song;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import java.util.ArrayList;


@Path("song")
public class SongResource {

    HttpSession session;
    Injector injector = Guice.createInjector(new Main());
    Dao<String> dao = injector.getInstance( Dao.class );

    public SongResource(@Context HttpServletRequest req) {
        session= req.getSession(true);
    }

    @GET @Produces("application/json")
    @Path("/get/")
    public Song getById(@QueryParam( "id" ) int id) {
        return (Song)dao.getById(id, session, "songs");
    }

    @GET @Produces("application/json")
    @Path("/search/")
    public ArrayList<Album> getByName(@QueryParam( "name" ) String name, @QueryParam( "artist" ) String genre) {
        if(name != null){
            return dao.getAlbumByName(name, session);
        }else if(genre != null){
            return dao.getAlbumByGenre(genre, session);
        }else{
            return new ArrayList<Album>();
        }
    }

}
