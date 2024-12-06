package co.edu.cue.TaskList;

import co.edu.cue.TaskList.entities.Task;
import co.edu.cue.TaskList.repositories.TaskRepository;
import co.edu.cue.TaskList.services.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RedisTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testCreateTaskInDatabase() throws Exception {
        // Preparar el título de la tarea
        String taskTitle = "Tarea de prueba";

        // Realizar la solicitud POST para crear la tarea
        MvcResult result = mockMvc.perform(post("/tasks/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskTitle))
                .andExpect(status().isOk())
                .andReturn();

        // Convertir la respuesta a un objeto Task
        String jsonResponse = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        Task createdTask = objectMapper.readValue(jsonResponse, Task.class);

        // Verificaciones
        assertNotNull(createdTask.getId(), "El ID de la tarea no debería ser nulo");
        assertEquals(taskTitle, createdTask.getTitle(), "El título de la tarea debe coincidir");

        // Verificar que la tarea existe en la base de datos
        Optional<Task> retrievedTask = taskRepository.findById(createdTask.getId());
        assertTrue(retrievedTask.isPresent(), "La tarea debe existir en la base de datos");
        assertEquals(taskTitle, retrievedTask.get().getTitle(), "El título en la base de datos debe coincidir");
    }
}
