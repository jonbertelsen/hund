package app.routes;

import app.config.ApplicationConfig;
import app.daos.DogDAO;
import app.entities.Dog;
import app.populators.DogPopulator;
import io.javalin.Javalin;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

class RoutesTest {

    private static Javalin app;
    private static DogDAO dogDAO = new DogDAO();
    private static Map<Long, Dog> dogMap;

    @BeforeAll
    static void init(){
        app = ApplicationConfig.startServer(7070, dogDAO);
        RestAssured.baseURI =  "http://localhost:7070/dog";
    }

    @BeforeEach
    void setUp() {
        dogMap = DogPopulator.populate(dogDAO);
    }

    @AfterEach
    void tearDown() {
       dogDAO.clear();

    }

    @AfterAll
    static void shutDown(){
        ApplicationConfig.stopServer(app);
    }

    @Test
    void getAll() {
        given()
                .when()
                .get("/all")
                .then()
                .statusCode(200)
                .body("", containsInAnyOrder(
                        Map.of("id", 1, "name", "King", "breed", "LABRADOR"),
                        Map.of("id", 2, "name", "Mini", "breed", "DACHSHUND"),
                        Map.of("id", 3, "name", "Boss", "breed", "FRENCHIE"),
                        Map.of("id", 4, "name", "Osvald", "breed", "BRETON")
                ));
    }
}