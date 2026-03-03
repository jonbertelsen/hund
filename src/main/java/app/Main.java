package app;

import app.controllers.DogController;
import app.daos.DogDAO;
import app.entities.Dog;
import app.enums.Breed;
import app.routes.Routes;
import io.javalin.Javalin;

public class Main {

    void main() {

        // Wiring up application
        DogDAO dogDAO = new DogDAO();
        DogController dogController = new DogController(dogDAO);
        Routes routes = new Routes(dogController);

        // Populate data
        dogDAO.create(new Dog("King", Breed.LABRADOR ));
        dogDAO.create(new Dog("Mini", Breed.DACHSHUND ));
        dogDAO.create(new Dog("Boss", Breed.FRENCHIE ));
        dogDAO.create(new Dog("Osvald", Breed.BRETON ));
        dogDAO.getAll().forEach(System.out::println);

        // Configuring and starting Javalin
        Javalin app = Javalin.create(config -> {
            config.routes.apiBuilder(routes.getRoutes());
            config.bundledPlugins.enableRouteOverview("/routes");
            config.routes.exception(RuntimeException.class, (e, ctx) -> {
                ctx.status(400).json(e.getMessage());
            });
        }).start(7070);
    }

}
