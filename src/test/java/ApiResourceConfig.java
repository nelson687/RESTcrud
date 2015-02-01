import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class ApiResourceConfig extends org.glassfish.jersey.server.ResourceConfig {

    public ApiResourceConfig() {
        packages("com.ng");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(MoxyJsonFeature.class);
    }

} 