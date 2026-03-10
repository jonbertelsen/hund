package app.config;

import app.controllers.DogController;
import app.daos.DogDAO;
import app.entities.Dog;
import app.enums.Breed;
import app.routes.Routes;
import io.javalin.Javalin;

public class ApplicationConfig {

    public static Javalin startServer(int port, DogDAO dogDAO){
        // Wiring up application
        DogController dogController = new DogController(dogDAO);
        Routes routes = new Routes(dogController);

        // Configuring and starting Javalin
        Javalin app = Javalin.create(config -> {
            config.routes.apiBuilder(routes.getRoutes());
            config.bundledPlugins.enableRouteOverview("/routes");
            config.routes.exception(RuntimeException.class, (e, ctx) -> {
                ctx.status(400).json(e.getMessage());
            });
        }).start(port);
        return app;
    }

    public static void stopServer(Javalin app) {
        app.stop();
    }

}
