package com.ng;

import com.ng.domain.Album;
import com.ng.domain.Artist;
import com.ng.domain.Song;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.ArrayList;

@Path("init")
public class MyResource {

    @GET @Produces("application/json")
    public String init(@Context ServletContext context) {

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


        ArrayList<Song> songs = new ArrayList<Song>();
        Song song = new Song();
        song.setId(0);
        song.setName("Nelson Song");
        songs.add(song);


        context.setAttribute("albums", albums);
        context.setAttribute("artists", artists);
        context.setAttribute("songs", songs);
        context.setAttribute("initialized", true);
        return "{status: Data successfully loaded}";
    }
}
