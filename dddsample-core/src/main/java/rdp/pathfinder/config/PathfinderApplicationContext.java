package rdp.pathfinder.config;

import rdp.pathfinder.api.GraphTraversalService;
import rdp.pathfinder.internal.GraphDAO;
import rdp.pathfinder.internal.GraphDAOStub;
import rdp.pathfinder.internal.GraphTraversalServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PathfinderApplicationContext {

    private GraphDAO graphDAO() {
        return new GraphDAOStub();
    }

    @Bean
    public GraphTraversalService graphTraversalService() {
        return new GraphTraversalServiceImpl(graphDAO());
    }
}