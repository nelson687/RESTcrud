import com.ng.domain.Song;
import net.sf.json.JSONArray;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class SongResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ApiResourceConfig();
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
        String songsList = target("song/search").queryParam("name", "song").request().get(String.class);
        JSONArray jsonArray = JSONArray.fromObject(songsList);
        assertEquals(jsonArray.getJSONObject(0).get("name"), "Song Name");
    }

    @Test
    public void canSearchByGenre() {
        String songsList = target("song/search").queryParam("genre", "rock").request().get(String.class);
        JSONArray jsonArray = JSONArray.fromObject(songsList);
        assertEquals(jsonArray.getJSONObject(0).get("name"), "Song Name");
    }

    @Test
    public void canSearchByArtist() {
        String songsList = target("song/search").queryParam("artist", "name").request().get(String.class);
        JSONArray jsonArray = JSONArray.fromObject(songsList);
        assertEquals(jsonArray.getJSONObject(0).get("name"), "Song Name");
    }

    @Test
    public void canUploadSong() {
        Entity<String> request = Entity.json("{\"artist\":{\"id\":0,\"name\":\"New Artist Name\"},\"genre\":\"Pop\",\"name\":\"New Song to upload\"}");
        String response = target("song/upload").request().post(request, String.class);


        String songsList = target("song/search").queryParam("name", "new").request().get(String.class);
        JSONArray jsonArray = JSONArray.fromObject(songsList);

        assertEquals(response, "{save: success}");
        assertNotNull(jsonArray.getJSONObject(0).get("name"));
    }

    @Test
    public void canDeleteSong() {
        Entity<String> uploadRequest = Entity.json("{\"artist\":{\"id\":0,\"name\":\"New Artist Name\"},\"genre\":\"Pop\",\"name\":\"New Song to upload\"}");
        target("song/upload").request().post(uploadRequest, String.class);

        Entity<String> request = Entity.json("{\"name\":\"New Song to upload\"}");
        String response = target("song/delete").request().post(request, String.class);

        assertEquals(response, "{remove: success}");
    }

    @Test
    public void canUpdateSong() {
        Entity<String> uploadRequest = Entity.json("{\"artist\":{\"id\":0,\"name\":\"New Artist Name\"},\"genre\":\"Pop\",\"name\":\"New Song to upload\"}");
        target("song/upload").request().post(uploadRequest, String.class);

        Entity<String> request = Entity.json("{\"id\": \"0\", \"name\":\"Updated song name\"}");
        String response = target("song/update").request().post(request, String.class);

        Song song = target("song/get").queryParam("id", "0").request().get(Song.class);

        assertEquals(response, "{update: success}");
        assertEquals(song.getName(), "Updated song name");

    }
}