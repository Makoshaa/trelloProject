package com.project.trello.trelloProject.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "t_tasks")
@Getter
@Setter
public class Tasks {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column (name = "description")
    private String description;

    @Column (name = "status")
    private int status;

    @ManyToOne
    private Folders folders;

}
