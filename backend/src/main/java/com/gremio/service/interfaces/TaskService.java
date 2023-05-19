package com.gremio.service.interfaces;

import com.gremio.persistence.entity.Task;

public interface TaskService {
    Task addTask(Task task);
}