package co.edu.cue.TaskList.controllers;

import co.edu.cue.TaskList.entities.Task;
import co.edu.cue.TaskList.services.TaskService;
import co.edu.cue.TaskList.services.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    private final RedisService redisService;

    public TaskController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/{id}/exists")
    public boolean exists(@PathVariable String id) {
        return redisService.exists("task:" + id);
    }

    @PostMapping("/create")
    public ResponseEntity<Task> create(@RequestBody String title){
        Task task = new Task.Builder()
                .title(title)
                .build();
        return ResponseEntity.ok(taskService.saveTask(task));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Task> update(@PathVariable int id,
                                       @RequestBody String title){
        return ResponseEntity.ok(taskService.updateTask(id, title));
    }
    @GetMapping("/byId/{id}")
    public ResponseEntity<Optional<Task>> getById(@PathVariable int id){
        return ResponseEntity.ok(taskService.getTask(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        return ResponseEntity.ok(taskService.deleteTask(id));
    }
}
