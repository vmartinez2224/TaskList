package co.edu.cue.TaskList;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import co.edu.cue.TaskList.entities.Task;
import co.edu.cue.TaskList.services.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SystemTest {

	@Autowired
	private DataSource dataSource;
	@LocalServerPort
	private int port;
	@Mock
	private TaskService taskService;
	@Autowired
	private MockMvc mockMvc;

	private final Task task = new Task.Builder()
			.id(1)
			.title("Titulo de tarea")
			.build();


	/** Integration Tests*/
	@Test
	void setup() {
		RestAssured.port = port;
	}
	@Test
	void testDatabaseConnection() throws Exception {
		assertNotNull(dataSource, "El DataSource debe estar configurado");
		try(Connection connection = dataSource.getConnection()) {
			assertNotNull(connection, "La conexión a la BD es exitosa");
		}
	}

	@Test
	void getTask() throws Exception {
		Task task = new Task();
		task.setId(1);
		when(taskService.getTask(1)).thenReturn(Optional.of(task));

		mockMvc.perform(get("/tasks/byId/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void deleteTask() {
		when(taskService.deleteTask(anyInt())).thenReturn("Task Deleted");
		RestAssured.given()
				.when()
				.delete("/tasks/delete/" + task.getId())
				.then()
				.statusCode(200)
				.contentType(ContentType.TEXT);
	}
	@Test
	void createTask() {
		when(taskService.saveTask(any(Task.class))).thenReturn(task);
		RestAssured.given()
				.contentType(ContentType.JSON)
				.body(task)
				.when()
				.post("/tasks/create")
				.then()
				.statusCode(200);
	}
	@Test
	void updateTask() throws Exception {
		when(taskService.updateTask(anyInt(),anyString())).thenReturn(task);

		mockMvc.perform(put("/tasks/update/" + task.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(task)))
				.andExpect(status().isOk());
	}
}
