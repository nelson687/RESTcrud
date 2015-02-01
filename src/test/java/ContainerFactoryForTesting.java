import javax.ws.rs.ProcessingException;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.spi.TestContainer;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;

public class ContainerFactoryForTesting implements TestContainerFactory {
    @Override
    public TestContainer create(URI uri, DeploymentContext deploymentContext) {
        return new GrizzlyTestContainer(uri, deploymentContext);
    }
    public static class GrizzlyTestContainer implements TestContainer {
        private final URI uri;
        private final DeploymentContext deploymentContext;
        private HttpServer server;
        private static final Logger LOGGER = Logger.getLogger(GrizzlyTestContainer.class.getName());

        private GrizzlyTestContainer(URI uri, DeploymentContext deploymentContext) {
            this.deploymentContext = deploymentContext;
            this.uri = uri;
        }
        @Override
        public ClientConfig getClientConfig() {
            return null;
        }
        @Override
        public URI getBaseUri() {
            return uri;
        }
        @Override
        public void start() {
            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.log(Level.INFO, "Starting Custom GrizzlyTestContainer...");
            }
            try {
                this.server = GrizzlyHttpServerFactory.createHttpServer(uri, deploymentContext.getResourceConfig());
                // Initialize and register Jersey Servlet
                WebappContext context = new WebappContext("WebappContext", "");
                ServletRegistration registration = context.addServlet("ServletContainer", ServletContainer.class);
                registration.setInitParameter("javax.ws.rs.Application", deploymentContext.getResourceConfig().getApplication().getClass().getName());
                registration.setInitParameter("jersey.config.beanValidation.enableOutputValidationErrorEntity.server", "true");
                registration.addMapping("/*");
                context.deploy(server);
                this.server.start();
            } catch (ProcessingException e) {
                throw new TestContainerException(e);
            } catch (IOException e) {
                throw new TestContainerException(e);
            }
        }
        @Override
        public void stop() {
            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.log(Level.INFO, "Stopping Custom GrizzlyTestContainer...");
            }
            this.server.shutdown();
        }
    }
}