package com.gremio.controller;

import com.gremio.model.Task;
import com.gremio.model.TaskPage;
import com.gremio.model.TaskSearchCriteria;
import com.gremio.service.TaskServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/getTasks")
    public ResponseEntity<Page<Task>> getTasks(TaskPage taskPage, TaskSearchCriteria taskSearchCriteria){
        return new ResponseEntity<>(taskService.getTasks(taskPage, taskSearchCriteria),
                HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);
    }
}
