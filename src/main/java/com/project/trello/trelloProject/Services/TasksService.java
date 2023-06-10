package com.project.trello.trelloProject.Services;

import com.project.trello.trelloProject.Entity.Folders;
import com.project.trello.trelloProject.Entity.Tasks;
import com.project.trello.trelloProject.Repository.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {

    private final TasksRepository tasksRepository;

    public List<Tasks> getTasksByFolderId(Long folderId) {
        return tasksRepository.findByFoldersId(folderId);
    }
    public Tasks getTasksById(Long taskId) {
        return tasksRepository.findById(taskId).orElse(null);
    }
    public void createTask(Tasks tasks){
       tasksRepository.save(tasks);
    }
    public void updateTask(Tasks tasks){
        tasksRepository.save(tasks);
    }
    public void deleteTask(Long taskId){
        tasksRepository.deleteById(taskId);
    }
}
