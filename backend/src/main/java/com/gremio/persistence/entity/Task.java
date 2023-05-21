package com.gremio.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Task extends BaseEntity {

    @OneToMany
    private List<User> assignee;
    private String name;
    private Date due;
    @ManyToOne
    private Project project;
    private String description;
}