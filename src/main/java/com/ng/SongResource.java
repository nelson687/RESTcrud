package com.ng;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ng.dao.Dao;
import com.ng.domain.Album;
import com.ng.domain.Song;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;


@Path("song")
public class SongResource {

    Injector injector = Guice.createInjector(new Main());
    Dao<String> dao = injector.getInstance( Dao.class );

    @GET @Produces("application/json")
    @Path("/get/")
    public Song getById(@QueryParam( "id" ) int id, @Context ServletContext context) {
        //session= req.getSession(true);
        return (Song)dao.getById(id, context, "songs");
    }

    @GET @Produces("application/json")
    @Path("/search/")
    public ArrayList<Song> getByName(@Context ServletContext context, @QueryParam( "name" ) String name, @QueryParam( "genre" ) String genre, @QueryParam( "artist" ) String artist) {
        if(name != null){
           return dao.getSongByName(name, context);
        }else if(genre != null){
           return dao.getSongsByGenre(genre, context);
        }else if(artist != null){
            //return dao.getSongsByArtist(genre, session);
        }

        return new ArrayList<Song>();
    }


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/upload")
    public String saveSong(Song song, @Context ServletContext context) {
       // session= req.getSession(true);
        dao.saveSong(song, context);
        return "{save: success}";
    }

}
