//package co.edu.cue.TaskList;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//public class ControllerTest {
//    @BeforeEach
//    public void setUp() {
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = 8080; // Cambiar el puerto si aplicación usa otro
//    }
//
//    @Test
//    public void testCreateTask() {
//        String title = "New Task";
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(title)
//                .when()
//                .post("/tasks/create")
//                .then()
//                .statusCode(200)
//                .body("title", equalTo(title));
//    }
//
//    @Test
//    public void testGetTaskById() {
//        int taskId = 1; // Asegúrate de que este ID exista en la base de datos
//
//        given()
//                .when()
//                .get("/tasks/byId/" + taskId)
//                .then()
//                .statusCode(200)
//                .body("id", equalTo(taskId));
//    }
//
//    @Test
//    public void testUpdateTask() {
//        int taskId = 1; // Asegúrate de que este ID exista en la base de datos
//        String updatedTitle = "Updated Task Title";
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(updatedTitle)
//                .when()
//                .put("/tasks/update/" + taskId)
//                .then()
//                .statusCode(200)
//                .body("title", equalTo(updatedTitle));
//    }
//
//    @Test
//    public void testDeleteTask() {
//        int taskId = 1; // Asegúrate de que este ID exista en la base de datos
//
//        given()
//                .when()
//                .delete("/tasks/delete/" + taskId)
//                .then()
//                .statusCode(200)
//                .body(equalTo("Task Deleted"));
//    }
//
//    @Test
//    public void testCheckTaskExists() {
//        String taskKey = "task:1"; // Cambia a un valor que exista en Redis
//
//        given()
//                .when()
//                .get("/tasks/1/exists")
//                .then()
//                .statusCode(200)
//                .body(equalTo("true")); // o "false" dependiendo de si existe o no
//    }
//}
//

// Aja