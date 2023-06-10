package com.project.trello.trelloProject.Services;

import com.project.trello.trelloProject.Entity.TaskCategories;
import com.project.trello.trelloProject.Repository.FoldersRepository;
import com.project.trello.trelloProject.Repository.TaskCategoriesRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskCategoriesService{

    private final TaskCategoriesRepository taskCategoriesRepository;


    public List<TaskCategories> getCategories (){
        List <TaskCategories> taskCategoriesList=taskCategoriesRepository.findAll();
        return taskCategoriesList;
    }

    public TaskCategories getCategoryById (Long id){
        return taskCategoriesRepository.findById(id).orElse(null);
    }

}
