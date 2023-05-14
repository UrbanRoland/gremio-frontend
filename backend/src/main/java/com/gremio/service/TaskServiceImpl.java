package com.gremio.service;

import com.gremio.model.Task;
import com.gremio.repository.TaskRepository;
import com.gremio.service.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public Task addTask(final Task task) {
        return taskRepository.save(task);
    }
}