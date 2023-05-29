package com.gremio.model.dto.filter;

import com.gremio.enums.TaskStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskFilter {
    private String title;
    private String description;
    private TaskStatus status;
}