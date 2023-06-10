package com.project.trello.trelloProject.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table (name = "t_folders")
@Getter
@Setter
public class Folders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "folder_name")
    private String name;

    @ManyToMany
    private List<TaskCategories> categoriesList;
}
