package com.gremio.controller;

import com.gremio.model.Task;
import com.gremio.service.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskServiceImpl taskService;

    @PostMapping(value = "/create")
    public ResponseEntity<Task> addTask(@RequestBody final Task task) {
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);
    }
}