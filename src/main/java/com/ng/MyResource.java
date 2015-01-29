package com.ng;

import com.ng.dao.Dao;
import com.ng.domain.Album;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;


@Path("myresource")
public class MyResource {

    private Dao<String> dao;
//
//    @Inject
//    public MyResource( Dao<String> dao ) {
//        this.dao = dao;
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt() {
        //Album album =  (Album)dao.getById("1");
        //return album.getName();
        return "{status: ok}";
    }
}
