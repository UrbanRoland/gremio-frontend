package com.gremio.service;

import com.gremio.model.Task;
import com.gremio.model.TaskPage;
import com.gremio.model.TaskSearchCriteria;
import com.gremio.repository.TaskCriteriaRepository;
import com.gremio.repository.TaskRepository;
import com.gremio.service.interfaces.ITaskService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;
    private final TaskCriteriaRepository taskCriteriaRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskCriteriaRepository taskCriteriaRepository) {
        this.taskRepository = taskRepository;
        this.taskCriteriaRepository = taskCriteriaRepository;
    }

    @Override
    public Page<Task> getTasks(TaskPage taskPage, TaskSearchCriteria taskSearchCriteria) {
        return taskCriteriaRepository.findAllWithFilters(taskPage, taskSearchCriteria);
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }
}
