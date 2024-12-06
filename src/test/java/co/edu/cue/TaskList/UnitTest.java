package co.edu.cue.TaskList;

import co.edu.cue.TaskList.entities.Task;
import co.edu.cue.TaskList.repositories.TaskRepository;
import co.edu.cue.TaskList.services.impls.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnitTest {
    @Mock
    private TaskRepository repository;
    @InjectMocks
    private TaskServiceImpl service;
    private final Task task = new Task.Builder()
            .id(1)
            .title("Titulo de tarea")
            .build();

    /** Unit Tests*/

    @Test
    public void testSaveTask(){
        Task task = new Task(1,"New Task");
        when(repository.save(any())).thenReturn(task);
        Task result = service.saveTask(task);
        assertNotNull(result);
        verify(repository,times(1)).save(any());
    }
    @Test
    public void updateTask() {
        Task existingTask = new Task(1, "Existing Task");
        when(repository.findById(1)).thenReturn(Optional.of(existingTask));
        when(repository.save(any())).thenReturn(existingTask);
        Task updatedTask = service.updateTask(1, "Updated Task Title");
        assertNotNull(updatedTask);
        assertEquals("Updated Task Title", updatedTask.getTitle());
        verify(repository, times(1)).findById(1);
        verify(repository, times(1)).save(any());
    }
    @Test
    public void testFindTask(){
        Task task = new Task(1,"New Task");
        when(repository.findById(1)).thenReturn(Optional.of(task));
        Task result = service.getTask(task.getId()).orElseThrow();
        assertNotNull(result);
        verify(repository,times(1)).findById(task.getId());
    }
    @Test
    public void testDeleteTask() {
        int taskId = 1;
        when(repository.existsById(taskId)).thenReturn(true);

        doNothing().when(repository).deleteById(taskId);

        String result = service.deleteTask(taskId);

        assertEquals("Task Deleted", result);
        verify(repository, times(1)).existsById(taskId);
        verify(repository, times(1)).deleteById(taskId);
    }
}
