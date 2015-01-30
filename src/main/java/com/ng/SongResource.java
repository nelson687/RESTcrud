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
    public Song getById(@QueryParam( "id" ) String id, @Context ServletContext context) {

        if(context.getAttribute("initialized") != null) {

            int idNumber;
            try {
                idNumber = Integer.parseInt(id);
                Song song = (Song) dao.getById(idNumber, context, "songs");

                if (song != null) {
                    return song;
                } else {
                    return new Song();
                }
            } catch (NumberFormatException nfe) {
                return new Song();
            }
        }else{
            return new Song();
        }
    }

    @GET @Produces("application/json")
    @Path("/search/")
    public ArrayList<Song> getByName(@Context ServletContext context, @QueryParam( "name" ) String name, @QueryParam( "genre" ) String genre, @QueryParam( "artist" ) String artist) {

        if(context.getAttribute("initialized") != null) {

            if(name != null){
               return dao.getSongByName(name, context);
            }else if(genre != null){
               return dao.getSongsByGenre(genre, context);
            }else if(artist != null){
                return dao.getSongsByArtist(artist, context);
            }
        }
            return new ArrayList<Song>();
    }


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/upload")
    public String saveSong(Song song, @Context ServletContext context) {
        if(context.getAttribute("initialized") != null) {
            dao.saveSong(song, context);
            return "{save: success}";
        }else{
            return "{save: not initialized}";
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/delete")
    public String deleteSong(Song song, @Context ServletContext context) {
        if(context.getAttribute("initialized") != null) {
            return dao.deleteSong(song, context);
        }else{
            return "{delete: not initialized}";
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update")
    public String updateSong(Song song, @Context ServletContext context) {
        if(context.getAttribute("initialized") != null) {
            return dao.updateSong(song, context);
        }else{
            return "{update: not initialized}";
        }
    }

}
