package com.gremio.service.interfaces;

import com.gremio.model.dto.TaskDto;
import com.gremio.model.dto.filter.TaskFilter;
import com.gremio.persistence.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    Task addTask(Task task);

    Page<Task> getAllByFilter(TaskFilter taskFilter, Pageable pageable);
}