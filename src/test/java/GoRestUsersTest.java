import POJO.User;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class GoRestUsersTest {

    public String createRandomName(){
       return RandomStringUtils.randomAlphabetic(8);
    }

    public String createRandomEmail(){
        return RandomStringUtils.randomAlphabetic(8).toLowerCase()+"@techno.com";
    }

    @Test
    public void createAUser(){

        given()   // preparation (headers, parameters...)
                .header("Authorization","Bearer af5816f3305012a060718702a1fa3d1be109b27cc6fcb7aea466b369d761f834")
                .contentType(ContentType.JSON)
                .body("{\"name\":\""+createRandomName()+"\",\"email\":\""+createRandomEmail()+"\",\"gender\": \"male\",\"status\": \"active\"}")
                .log().uri()
                .log().body()

                .when()
                .post("https://gorest.co.in/public/v2/users")

                .then()
                .log().body()
                .statusCode(201)
                .contentType(ContentType.JSON);
    }

    @Test
    public void createAUserWithMaps(){

        Map<String,String> user = new HashMap<>();
        user.put("name",createRandomName());
        user.put("email",createRandomEmail());
        user.put("gender","female");
        user.put("status","active");

        given()   // preparation (headers, parameters...)
                .header("Authorization","Bearer af5816f3305012a060718702a1fa3d1be109b27cc6fcb7aea466b369d761f834")
                .contentType(ContentType.JSON)
                .body(user)
                .log().uri()
                .log().body()

                .when()
                .post("https://gorest.co.in/public/v2/users")

                .then()
                .log().body()
                .statusCode(201)
                .contentType(ContentType.JSON);
    }

    @Test
    public void createAUserWithObjects(){

        User user = new User();
        user.setName(createRandomName());
        user.setEmail(createRandomEmail());
        user.setGender("");

        given()   // preparation (headers, parameters...)
                .header("Authorization","Bearer af5816f3305012a060718702a1fa3d1be109b27cc6fcb7aea466b369d761f834")
                .contentType(ContentType.JSON)
                .body(user)
                .log().uri()
                .log().body()

                .when()
                .post("https://gorest.co.in/public/v2/users")

                .then()
                .log().body()
                .statusCode(201)
                .contentType(ContentType.JSON);
    }
}
