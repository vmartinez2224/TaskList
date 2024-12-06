package co.edu.cue.TaskList.services;

import co.edu.cue.TaskList.entities.Task;

import java.util.Optional;

public interface TaskService {
    Task saveTask(Task task);
    Task updateTask(int id, String taskTitle);
    String deleteTask(int id);
    Optional<Task> getTask(int id);
}
