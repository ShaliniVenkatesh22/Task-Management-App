package org.task.app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.task.app.entity.Task;
import org.task.app.service.TaskService;
import org.task.app.utility.ResponseStructure;
import org.task.app.exception.*;

import java.util.List;

@RestController
@RequestMapping("/task/api")
public class TaskController {

	@Autowired
    private TaskService taskService;

    @PostMapping("/create")
    ResponseEntity<ResponseStructure<Task>> createTask(@Valid @RequestBody Task task){
        Task task1 = taskService.createTask(task);

        ResponseStructure<Task> response = new ResponseStructure<>();
        response.setData(task1);
        response.setMessage("Task Created");
        response.setStatuscode(HttpStatus.CREATED.value());

        return  new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    ResponseEntity<ResponseStructure<Task>> getTask(@PathVariable Long id){
        Task task = taskService.get(id);
        ResponseStructure<Task> response = new ResponseStructure<>();
        response.setData(task);
        response.setMessage("Task Found");
        response.setStatuscode(HttpStatus.OK.value());
        return  new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getall")
    ResponseEntity<ResponseStructure<List<Task>>> getAllTasks(){
        List<Task> tasks = taskService.getAll();

        ResponseStructure<List<Task>> response = new ResponseStructure<>();
        response.setData(tasks); // no casting needed
        response.setMessage("Tasks Found");
        response.setStatuscode(HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>  deleteTask(@PathVariable Long id){
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<ResponseStructure<Task>> updateTask(@PathVariable Long id,@Valid @RequestBody Task task){
        taskService.update(id, task);
        ResponseStructure<Task> response = new ResponseStructure<>();
        response.setData(task);
        response.setMessage("Task Updated");
        response.setStatuscode(HttpStatus.OK.value());
        return  new  ResponseEntity<>(response, HttpStatus.OK);
    }

}
