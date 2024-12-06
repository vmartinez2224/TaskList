package co.edu.cue.TaskList.repositories;

import co.edu.cue.TaskList.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
}
