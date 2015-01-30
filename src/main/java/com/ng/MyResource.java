package com.ng;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ng.dao.Dao;
import com.ng.domain.Album;
import com.ng.domain.Artist;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Path("myresource")
public class MyResource {

    HttpSession session;
    Injector injector = Guice.createInjector(new Main());
    Dao<String> dao = injector.getInstance( Dao.class );

    public MyResource(@Context HttpServletRequest req) {
        session= req.getSession(true);
    }

    @GET @Produces("application/json")
    @Path("/init")
    public String init() {

        ArrayList<Artist> artists = new ArrayList<Artist>();
        Artist artist = new Artist();
        artist.setName("Gallardo");
        artists.add(artist);


        ArrayList<Album> albums = new ArrayList<Album>();
        Album album = new Album("NelsonAlbum", artist);
        album.setId(0);
        Album album1 = new Album("Jorge Album",artist);
        album1.setId(1);
        Album album2 = new Album("Lorena Album", artist);
        album2.setId(2);
        albums.add(album);
        albums.add(album1);
        albums.add(album2);
        session.setAttribute("albums", albums);
        return "{status: Data successfully loaded}";
    }
}
