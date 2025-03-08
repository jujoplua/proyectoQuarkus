package pe.edu.galaxy.training.ms.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class MascotaResourceTest {
    @Test
    void testMascotasEndpoint() {
        given()
          .when().get("/v1/mascotas")
          .then()
             .statusCode(200);
    }

}