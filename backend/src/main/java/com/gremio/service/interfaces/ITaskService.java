package com.gremio.service.interfaces;

import com.gremio.model.Task;
import com.gremio.model.TaskPage;
import com.gremio.model.TaskSearchCriteria;
import org.springframework.data.domain.Page;

public interface ITaskService {
    Page<Task> getTasks(TaskPage taskPage, TaskSearchCriteria taskSearchCriteria);
    Task addTask(Task task);
}
