package com.gremio.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<User> assignee;
    private String name;
    private Date due;
    @ManyToOne
    private Project project;
    private String description;
}
