package org.task.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.task.app.entity.Task;
import org.task.app.exception.NotFoundException;
import org.task.app.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task){
        log.debug("Creating new task: {}", task.getTitle());
        return taskRepository.save(task);
    }

    public Task get(Long id) {
        log.debug("Fetching task with id {}", id);
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));
    }

    public List<Task> getAll(){
        log.debug("Fetching all tasks");
        return taskRepository.findAll();
    }

    public void delete(Long id) {
        log.debug("Deleting task with id {}", id);
        taskRepository.deleteById(id);
    }

    public Task update(Long id, Task updated) {
        log.debug("Updating task with id {}", id);

        Task existing = get(id);

        if (updated.getTitle() != null) existing.setTitle(updated.getTitle());
        if (updated.getDescription() != null) existing.setDescription(updated.getDescription());
        if (updated.getPriority() != null) existing.setPriority(updated.getPriority());
        if (updated.getDueDate() != null) existing.setDueDate(updated.getDueDate());

        return taskRepository.save(existing);
    }
}
