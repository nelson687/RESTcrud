package com.ng;

import com.google.inject.Guice;
import com.google.inject.Injector;
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

    Injector injector = Guice.createInjector(new Main());
    Dao<String> dao = injector.getInstance( Dao.class );
//
//    @Inject
//    public MyResource( Dao<String> dao ) {
//        this.dao = dao;
//    }

    @GET @Produces("application/json")
    public Album getIt() {
        Album album =  (Album)dao.getById("1");
        return album;
        //return "{status: ok}";
    }
}
