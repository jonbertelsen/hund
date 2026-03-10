package app;

import app.config.ApplicationConfig;
import app.daos.DogDAO;
import io.javalin.Javalin;

public class Main {

    void main() {
        DogDAO dogDAO = new DogDAO();
        Javalin app = ApplicationConfig.startServer(7070, dogDAO);
    }

}
