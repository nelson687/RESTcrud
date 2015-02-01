import com.ng.MyResource;
import com.ng.domain.Song;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class SongResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new MyResourceConfig();
    }

    @Override
    protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
        return new ContainerFactoryForTesting();
    }

    @Before
    public void setUpChild(){
        target("init").request().get(String.class);
    }

    @Test
    public void canCreateDataTest() {
        final String result = target("init").request().get(String.class);
        assertEquals("{status: Data successfully loaded}", result);
    }

    @Test
    public void canGetSongByIdTest() {
        final Song song = target("song/get").queryParam("id", "0").request().get(Song.class);
        assertNotNull(song.getName());
    }

    @Test
    public void getEmptySongWhenIdNotFound() {
        final Song song = target("song/get").queryParam("id", "1").request().get(Song.class);
        assertNull(song.getName());
    }

    @Test
    public void canSearchBySongName() {
        target("song/search").queryParam("name", "nelson").request().get(ArrayList.class);
        String sdf = "";
    }
}