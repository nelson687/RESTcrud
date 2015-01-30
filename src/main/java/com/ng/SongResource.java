package com.ng;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ng.dao.Dao;
import com.ng.domain.Album;
import com.ng.domain.Song;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
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
    public ArrayList<Song> getByName(@QueryParam( "name" ) String name, @QueryParam( "genre" ) String genre, @QueryParam( "artist" ) String artist) {
        if(name != null){
            return dao.getSongByName(name, session);
        }else if(genre != null){
            return dao.getSongsByGenre(genre, session);
        }else if(artist != null){
            //return dao.getSongsByArtist(genre, session);
        }

        return new ArrayList<Song>();
    }


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/upload")
    public void postStrMsg(Song song) {
        dao.saveSong(song, session);
    }

}
