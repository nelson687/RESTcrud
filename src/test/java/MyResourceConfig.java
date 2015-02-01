import com.ng.MyResource;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class MyResourceConfig extends ResourceConfig {

    public MyResourceConfig() {
        packages("com.ng");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(MoxyJsonFeature.class);
    }

} 