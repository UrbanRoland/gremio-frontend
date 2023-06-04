package com.gremio.persistence.entity;

import com.gremio.enums.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Audited
public class Task extends BaseEntity {

    @OneToMany
    private List<User> assignee;
    private String title;
    private Date due;
    @ManyToOne
    private Project project;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}